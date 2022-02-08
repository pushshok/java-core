/**
 * Java Core. Homework #3. Class Fruit
 * @author Zdibnyak Maxim
 * @version 25.01.2022
 */

package ru.geekbrains.java_core;

public class Fruit {
    private float weight;
    private String name;

    public Fruit (String name, float weight) {
        this.name = name;
        this.weight = weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }

    public String getName() {
        return  name;
    }

    @Override
    public String toString () {
        return ("Sort of fruits is " + name + " that weight is " + weight);
    }
}
