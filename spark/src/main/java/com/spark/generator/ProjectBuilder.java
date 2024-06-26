package com.spark.generator;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spark.models.ProjectModel;
import com.spark.models.ResponseModel;

import lombok.Data;

@Data
public class ProjectBuilder {

    private final ResponseModel responseModel;
    private final String OPENAI_KEY = System.getenv("OPENAI_API_KEY");

    public ProjectBuilder(ResponseModel responseModel) {
        this.responseModel = responseModel;
    }

    /**
     * takes the parameters and returns a gpt3.5 query to generate the required
     * project schema
     * 
     * @return the project schema in json format
     */
    private JsonObject getProjectSchemaAsJson() {

        String completionsEndPoint = "https://api.openai.com/v1/chat/completions";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(completionsEndPoint);
        httpPost.setHeader("Authorization", "Bearer " + OPENAI_KEY);
        httpPost.setHeader("Content-Type", "application/json");

        try {
            JsonObject messageContent = new JsonObject();
            messageContent.addProperty("role", "user");
            messageContent.addProperty("content", this.responseModel.getQuery());

            JsonArray messages = new JsonArray();
            messages.add(messageContent);

            JsonObject root = new JsonObject();
            root.addProperty("model", "gpt-3.5-turbo");
            root.add("messages", messages);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            StringEntity requestEntity = new StringEntity(gson.toJson(root));
            httpPost.setEntity(requestEntity);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                String responseString = EntityUtils.toString(response.getEntity());
                JsonObject responseJson = JsonParser.parseString(responseString).getAsJsonObject();
                JsonObject resJson = responseJson.getAsJsonArray("choices").get(0).getAsJsonObject().getAsJsonObject("message");
                return resJson;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JsonObject();
    }

    public ProjectModel getProjectModel() {
        JsonObject projectSchema = getProjectSchemaAsJson();
    
        String response = projectSchema.get("content").getAsString();
        String[] responseFields = response.split("SEPARATOR");
    
        String projectName = this.getStringAfterColon(responseFields[0].trim().trim());
        String projectDescription = this.getStringAfterColon(responseFields[1].trim());
        String projectDeliverablesString = this.getStringAfterColon(responseFields[2].trim());
        String[] projectDeliverables = projectDeliverablesString.split("\n- ");
        for (int i = 0; i < projectDeliverables.length; i++) {
            projectDeliverables[i] = projectDeliverables[i].trim();
        }
        String completionDate = this.getStringAfterColon(responseFields[3].trim());
        String categoriesString = this.getStringAfterColon(responseFields[4].trim());
        String[] categories;
        if (categoriesString.isEmpty()) {
            categories = new String[0];
        } else {
            categories = categoriesString.substring(1, categoriesString.length() - 1).split(", ");
        }
        String structureOverview = this.getStringAfterColon(responseFields[5]).trim();
    
        ProjectModel projectModel = new ProjectModel(projectName, projectDescription, projectDeliverables, completionDate, categories, structureOverview);
        return projectModel;
    }

    private String getStringAfterColon(String input) {
        String everythingBefore = input.split(":")[0].trim();
        return input.substring(everythingBefore.length() + 1).trim();
    }
}
