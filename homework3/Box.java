/**
 * Java Core. Homework #3. Class Box
 * @author Zdibnyak Maxim
 * @version 25.01.2022
 */

package ru.geekbrains.java_core;
import static java.lang.Math.abs;

public class Box<V extends Fruit> {
    private V fruitType;
    private float boxWeight;
    private float weightFullBox;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Box (float boxWeight){
        this.boxWeight = boxWeight;
        this.fruitType = null;
        this.count = 0;
    }

    public Box (float boxWeight, V obj, int count){
        this.boxWeight = boxWeight;
        this.fruitType = obj;
        this.count = count;
    }

    public V getFruitType() {
        return fruitType;
    }

    public void setFruitType (V box) {
        this.fruitType = fruitType;
    }

    public void addFruit (V obj, int count) throws BoxTypeException {
        if (fruitType == null) {
            this.fruitType = obj;
            this.count = count;
        } else if ((fruitType != null) && (fruitType.getClass() == obj.getClass())) {
            this.count = this.count + count;
        } else throw new BoxTypeException("Fruits in the box have a clacc " + fruitType.getClass().getSimpleName() +
                    ". And they don't have a" + obj.getClass().getSimpleName() + "!");
    }

    public float getWeight() {
        this.weightFullBox = fruitType.getWeight() * (float) this.count + this.boxWeight;
        return weightFullBox;
    }
    
    public static void sortBox (Box box1, Box box2) throws BoxTypeException {
        if ((box1.getFruitType() != null) && (box2.getFruitType() != null)) {
            if (box1.getFruitType() == box2.getFruitType()) {
                box1.addFruit(box2.getFruitType(), box2.getCount());
                box2.setCount(0);
                box2.setFruitType(null);
            } else throw new BoxTypeException("Fruits in the box1 have a clacc " + box1.getFruitType().getClass().getSimpleName() +
                    ". But fruits in the box2 have a clacc " + box2.getFruitType().getClass().getSimpleName() + ". " +
                    "Error!");
        } else System.out.println("One or two box is empty!");
    }

    public boolean compare(Box box) {
        if (abs(this.getWeight() - box.getWeight()) <= 0.0001) {
            return true;
        } else return false;
    }

    @Override
    public String toString () {
        return ("Fruits in the box is " + this.fruitType.getName() + " in count " + this.count + ". The box has " +
                "weight " + this.boxWeight + " and brutto of the box is " + this.getWeight());
    }

    public static void main(String[] args) throws BoxTypeException {
        Apple apple = new Apple("Golden",1.0f);
        Orange orange = new Orange("Mandarin", 1.5f);

        System.out.println(apple + ". " + orange);

        Box boxOfApple = new Box(2.0f, apple, 10);
        Box boxOfApple2 = new Box(1.0f, apple, 15);
        Box boxOfOrange = new Box(1.5f, orange, 7);
        Box boxOfOrange2 = new Box(2.5f, orange, 9);

        System.out.println(boxOfApple + "\n" + boxOfApple2 + "\n" + boxOfOrange + "\n" + boxOfOrange2);

        System.out.println(boxOfApple.compare(boxOfOrange));
        System.out.println(boxOfApple.compare(boxOfOrange2));
        System.out.println(boxOfApple2.compare(boxOfOrange));
        System.out.println(boxOfApple2.compare(boxOfOrange2));

        sortBox(boxOfApple,boxOfApple2);
        sortBox(boxOfOrange,boxOfOrange2);
        System.out.println(boxOfApple + "\n" + boxOfApple2);
        System.out.println(boxOfOrange + "\n" + boxOfOrange2);
        try {
            sortBox(boxOfApple, boxOfOrange);
        } catch (BoxTypeException e) {
            e.getMessage();
        }

        System.out.println(boxOfApple + "\n" + boxOfOrange);
    }
}
