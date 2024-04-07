package com.spark.models;

import java.util.Random;

import com.spark.generator.ResponseGenerator;

import lombok.Data;

@Data
public class ResponseModel {

    private final ResponseGenerator responseParameters;

    public ResponseModel(ResponseGenerator responseParameters) {
        this.responseParameters = responseParameters;
    }

    /**
     * takes the parameters and returns a gpt3.5 query to generate the required
     * project schema
     * 
     * @return
     */
    public String getQuery() {
        StringBuilder responseBuilder = new StringBuilder();
        Random random = new Random();

        // Intro
        responseBuilder.append("Generate a project with the following specifications:").append("\n");

        // Time Unit
        responseBuilder.append("Time Unit: ").append(responseParameters.getTimeUnit()).append("\n");

        // Difficulty Level
        String[] difficultyDescriptions = { "a breeze", "fairly easy", "moderate", "quite challenging",
                "extremely difficult" };
        int difficultyIndex = (int) (responseParameters.getDifficulty().getDifficultyWeight()
                * (difficultyDescriptions.length - 1));
        responseBuilder.append("Difficulty Level: ").append(difficultyDescriptions[difficultyIndex]).append("\n");

        // Expected Duration
        responseBuilder.append("Expected Duration: ").append(responseParameters.getExpectedDuration()).append(" ");
        responseBuilder.append(responseParameters.getTimeUnit().toString().toLowerCase()).append("\n");

        // Programming Language
        responseBuilder.append("Programming Language: ").append(responseParameters.getLanguage()).append("\n");

        // Categories
        responseBuilder.append("Categories: ").append("[");
        for (int i = 0; i < responseParameters.getCategories().length; i++) {
            responseBuilder.append(responseParameters.getCategories()[i]);
            if (i != responseParameters.getCategories().length - 1) {
                responseBuilder.append(", ");
            }
        }
        responseBuilder.append("]").append("\n");

        // Creativity Level
        String[] creativityDescriptions = { "highly derivative", "somewhat original", "quite innovative",
                "groundbreaking" };
        int creativityIndex = (int) (responseParameters.getCreativity() * (creativityDescriptions.length - 1));
        responseBuilder.append("Creativity Level: ").append(creativityDescriptions[creativityIndex]).append("\n");

        // Project Fields
        String[] fieldIntros = { "The project should have the following components:",
                "The project should consist of these elements:", "The project should include these sections:" };
        responseBuilder.append("\n").append(fieldIntros[random.nextInt(fieldIntros.length)]).append("\n");
        responseBuilder.append("projectName (String)").append("\nSEPARATOR\n");
        responseBuilder.append("projectDescription (String)").append("\nSEPARATOR\n");
        responseBuilder.append("projectDeliverables (Array of Strings)").append("\nSEPARATOR\n");
        responseBuilder.append("   - Each element should be in the format: 'Deliverable Name (Due Date)'")
                .append("\nSEPARATOR\n");
        responseBuilder.append("completionDate (String)").append("\nSEPARATOR\n");
        responseBuilder.append("categories (Array of Strings)").append("\nSEPARATOR\n");
        responseBuilder.append("structureOverview (String)").append("\n");

        return responseBuilder.toString();
    }

}
