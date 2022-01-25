package ru.geekbrains.java_core;

public class ClassObject {

    public static void main(String[] args) {

        Person[] pers = new Person[4];
        pers[0] = new Person("Winer", 16);
        pers[1] = new Person("Wer", 10);
        pers[2] = new Person("Miner", 3);
        pers[3] = new Person("Iner", 6);

        Team team = new Team ("DreamTeam", pers);
        team.showResults("DreamTeam");

        Training[] train = new Training[3];
        train[0] = new Training("run", 5);
        train[1] = new Training("swim", 7);
        train[2] = new Training("fly", 15);

        System.out.println();
        Course course = new Course(train);
        course.showResults();
        System.out.println();

        course.doIt(team);
        team.showResults("DreamTeam");

    }
}
