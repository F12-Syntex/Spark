package com.spark.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class ProjectModel {
    private final String projectName;
    private final String projectDescription;
    private final String[] projectDeliverables;
    private final String completionDate;
    private final String[] categories;
    private final String structureOverview;

    /**
     * Get the project recommendation
     * 
     * @return String containing the recommendation
     */
    public String getRecommendation() {
        StringBuilder recommendation = new StringBuilder();
        recommendation.append("Project Name: ").append(projectName).append("\n");
        recommendation.append("Description: ").append(projectDescription).append("\n");
        recommendation.append("Deliverables: ");
        for (String deliverable : projectDeliverables) {
            recommendation.append(deliverable).append(", ");
        }
        recommendation.delete(recommendation.length() - 2, recommendation.length()); // Remove the last ", "
        recommendation.append("\nCompletion Date: ").append(completionDate).append("\n");
        recommendation.append("Categories: ");
        for (String category : categories) {
            recommendation.append(category).append(", ");
        }
        recommendation.delete(recommendation.length() - 2, recommendation.length()); // Remove the last ", "
        recommendation.append("\nStructure Overview: ").append(structureOverview).append("\n");
        recommendation.append(
                "\nBased on the information provided, this project seems to be a good fit for our organization. The deliverables align with our goals, and the completion date is reasonable. I recommend proceeding with this project.");
        return recommendation.toString();
    }
}
