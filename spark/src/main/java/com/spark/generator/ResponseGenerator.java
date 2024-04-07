package com.spark.generator;
//for scalability
import java.time.temporal.ChronoUnit;

import com.spark.parameters.Difficulty;

import lombok.Data;

/**
 * reponse genertor that takes in parameters through a factory paradigm and
 * returns a response model
 */
@Data
public final class ResponseGenerator {

    private ChronoUnit timeUnit = ChronoUnit.DAYS;
    private Difficulty difficulty;
    private int expectedDuration; // expected duration of the project in days
    private String language;
    private String[] categories;
    private double creativity;

    public static ResponseGenerator create() {
        ResponseGenerator response = new ResponseGenerator();
        response.difficulty = Difficulty.MEDIUM;
        response.expectedDuration = 30;
        response.language = "java";
        response.categories = new String[] { "" };
        response.creativity = 0.5;

        return response;
    }

    public ResponseGenerator withDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public ResponseGenerator withExpectedDuring(int expectedDuration){
        this.expectedDuration = expectedDuration;
        return this;
    }

    public ResponseGenerator withLanguage(String language){
        this.language = language;
        return this;
    }

    public ResponseGenerator withCategories(String... categories){
        this.categories = categories.clone();
        return this;
    }

    public ResponseGenerator withCreativity(double creativity) {
        this.creativity = creativity;
        return this;
    }

    public ResponseGenerator withTimeUnit(ChronoUnit timeUnit) {
        this.timeUnit = timeUnit;
        return this;
    }

    public ResponseModel build() {
        ResponseModel response = new ResponseModel(this);    
        return response;    
    }
}
