package ru.geekbrains.java_core;

public class Training {
    String trainingName;
    int difficult;

    Training (String trainingName, int difficult) {
        this.trainingName = trainingName;
        this.difficult = difficult;
    }

    public int getDifficult() {
        return difficult;
    }

    @Override
    public String toString() {
        return ("Training name: " + trainingName + "; Difficult: " + difficult);
    }

}