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
}
