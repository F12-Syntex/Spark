package com.spark.startup;

import com.spark.generator.ProjectBuilder;
import com.spark.generator.ResponseGenerator;
import com.spark.models.ProjectModel;
import com.spark.models.ResponseModel;
import com.spark.parameters.Difficulty;

public class Spark {
    public static void main(String[] args) {

        ResponseModel responseModel = ResponseGenerator.create()
                .withCreativity(0.7)
                .withDifficulty(Difficulty.HARD)
                .withExpectedDuring(10)
                .withLanguage("java")
                .build();

        ProjectBuilder projectBuilder = new ProjectBuilder(responseModel);
        ProjectModel projectSchema = projectBuilder.getProjectModel();    

        System.out.println(projectSchema.getRecommendation());
    }
}
