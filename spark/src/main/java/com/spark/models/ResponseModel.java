package com.spark.generator;

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
    
        responseBuilder.append("Generate a project with the following specifications:").append("\n");
        responseBuilder.append("Time Unit: ").append(responseParameters.getTimeUnit()).append("\n");
        responseBuilder.append("Difficulty Level: ").append(responseParameters.getDifficulty().getDifficultyWeight()
                + " where 0 is the easiest, and 1 is the hardest").append("\n");
        responseBuilder.append("Expected Duration: ").append(responseParameters.getExpectedDuration()).append(" ");
        responseBuilder.append(responseParameters.getTimeUnit().toString().toLowerCase()).append("\n");
    
        responseBuilder.append("Programming Language: ").append(responseParameters.getLanguage()).append("\n");
        responseBuilder.append("Categories: ").append("[");
        for (int i = 0; i < responseParameters.getCategories().length; i++) {
            responseBuilder.append(responseParameters.getCategories()[i]);
            if (i != responseParameters.getCategories().length - 1) {
                responseBuilder.append(", ");
            }
        }
        responseBuilder.append("]").append("\n");
    
        responseBuilder.append("Creativity Level: ").append(responseParameters.getCreativity())
                .append(" (on a scale of 0 to 1)").append("\n");
    
        responseBuilder.append("\nThe project should have the following fields:").append("\n");
        responseBuilder.append("projectName (String)").append("\nSEPARATOR\n");
        responseBuilder.append("projectDescription (String)").append("\nSEPARATOR\n");
        responseBuilder.append("projectDeliverables (Array of Strings)").append("\nSEPARATOR\n");
        responseBuilder.append("   - Each element should be in the format: 'Deliverable Name (Due Date)'").append("\nSEPARATOR\n");
        responseBuilder.append("completionDate (String)").append("\nSEPARATOR\n");
        responseBuilder.append("categories (Array of Strings)").append("\nSEPARATOR\n");
        responseBuilder.append("structureOverview (String)").append("\n");
    
        return responseBuilder.toString();
    }

}
