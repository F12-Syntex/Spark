package com.spark.startup;

import com.spark.generator.ResponseGenerator;
import com.spark.parameters.Difficulty;

public class Spark {
    public static void main(String[] args) {

        ResponseGenerator.create()
                .withCreativity(0.3)
                .withDifficulty(Difficulty.EASY)
                .withExpectedDuring(7)
                .withLanguage("python");

    }
}
