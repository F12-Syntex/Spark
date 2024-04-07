package com.spark.parameters;

public enum Difficulty {

    EASY(0.3), MEDIUM(0.6), HARD(0.9);

    private double difficulty;

    private Difficulty(double difficulty) {
        this.difficulty = difficulty;
    }

    public double getDifficulty() {
        return difficulty;
    }
}
