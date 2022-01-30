/**
 * Java Core. Homework #3. Class Generalization
 * @author Zdibnyak Maxim
 * @version 25.01.2022
 */
package ru.geekbrains.java_core;
import java.util.Arrays;
import java.util.Scanner;

public class Generalization {
    private int[] nums;
    private int buffer;
    private int operandOne;
    private int operandTwo;

    public Generalization(int... nums) {
        this.nums = nums;
    }

    public void setOperandOne(int operandOne) {
        this.operandOne = operandOne;
    }

    public void setOperandTwo(int operandTwo) {
        this.operandTwo = operandTwo;
    }

    public int[] getNums() {
        return nums;
    }

    public int getBuffer() {
        return buffer;
    }

    public void setBuffer(int buffer) {
        this.buffer = buffer;
    }

    @Override
    public String toString() {
        String mass = "";

        for (int num: nums) {
            mass += num + " ";
            System.out.println(mass);
        }

        return mass;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Generalization gen = new Generalization(1, 3, 9, 2, 3, 4, 5, 0);
        System.out.println(Arrays.toString(gen.getNums()));

        System.out.println("Operand one = ");
        gen.setOperandOne(scanner.nextInt());
        System.out.println("Operand two = ");
        gen.setOperandTwo(scanner.nextInt());

        gen.setBuffer(gen.nums[gen.operandOne]);
        gen.nums[gen.operandOne] = gen.nums[gen.operandTwo];
        gen.nums[gen.operandTwo] = gen.getBuffer();
        System.out.println(Arrays.toString(gen.getNums()));
        
    }
}
