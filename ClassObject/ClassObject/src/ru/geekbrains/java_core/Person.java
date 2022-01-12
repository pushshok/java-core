package ru.geekbrains.java_core;


public class Person {
    private String name;
    private int strenght;
    private boolean result;


    Person(String name, int strenght) {
        this.name = name;
        this.strenght = strenght;
        this.result = false;
    }

    public int getStrenght() {
        return strenght;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return ("Name: " + name + "; Strenght: " + strenght + "; Result: " + result);
    }
}