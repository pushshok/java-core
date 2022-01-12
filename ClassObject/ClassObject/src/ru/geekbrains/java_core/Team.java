package ru.geekbrains.java_core;

public class Team {
    String teamName;
    Person[] personTeam = new Person[4];

    Team (String teamName, Person[] setPersonTeam){
        this.teamName = teamName;
        this.personTeam = setPersonTeam;
    }

//    public Person[] setPersonTeam(Person[] personTeam) {
//        return personTeam;
//    }

    public void showAll (Person[] personTeam) {
        for (Person person : personTeam) {
            System.out.println(person);
        }
    }

    public void showResults(String teamName) {
        for (Person person : personTeam) {
            if (person.getResult() == true) {
                System.out.println(person);
            }
        }
    }
}