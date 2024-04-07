package com.spark.models;

import lombok.Data;

@Data
public class ProjectModel {
    private final String projectName;
    private final String projectDescription;
    private final String[] projectDeliverables;
    private final String completionDate;
    private final String[] categories;
    private final String structureOverview;


    public ProjectModel(String projectName, String projectDescription, String[] projectDeliverables, String completionDate, String[] categories, String structureOverview) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectDeliverables = projectDeliverables;
        this.completionDate = completionDate;
        this.categories = categories;
        this.structureOverview = structureOverview;
    }
}
