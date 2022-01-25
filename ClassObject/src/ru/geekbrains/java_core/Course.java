package ru.geekbrains.java_core;

public class Course {
    Training[] trainingCourse = new Training[3];

    Course(Training[] trainingCourse) {
        this.trainingCourse = trainingCourse;
    }

//    public Training[] setTrainingCourse(Training[] course[]) {
//
//    }
//    @Override
//    public String toString() {
//        return ("Name: " + name + "; Strenght: " + "; Result: " + result);
//    }

    public void doIt(Team team) {
        for (Training training : trainingCourse) {
            for (Person person : team.personTeam) {
                if (person.getStrenght() - training.getDifficult() <= 0) {
                    person.setResult(false);
                } else person.setResult(true);
            }
        }
    }

    public void showResults() {
        for (Training training : trainingCourse) {
            System.out.println(training);
        }
    }
}
