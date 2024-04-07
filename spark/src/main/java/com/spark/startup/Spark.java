package com.spark.startup;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.spark.generator.ProjectBuilder;
import com.spark.generator.ResponseGenerator;
import com.spark.generator.ResponseModel;
import com.spark.parameters.Difficulty;

public class Spark {
    public static void main(String[] args) {

        ResponseModel responseModel = ResponseGenerator.create()
                .withCreativity(0.3)
                .withDifficulty(Difficulty.EASY)
                .withExpectedDuring(7)
                .withLanguage("python")
                .build();

        ProjectBuilder projectBuilder = new ProjectBuilder(responseModel);
        JsonObject projectSchema = projectBuilder.getProjectSchema();

        GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
        System.out.println(gsonBuilder.create().toJson(projectSchema));
    }
}
