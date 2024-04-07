package com.spark.generator;

import java.time.temporal.ChronoUnit;

/**
 * reponse genertor that takes in parameters through a factory paradigm and
 * returns a response model
 */
public final class ResponseGenerator {

    private final ChronoUnit timeUnit = ChronoUnit.DAYS;
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

}
