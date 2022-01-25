package ru.geekbrains.java_core;

import static java.lang.String.valueOf;

public class ClassExceptions {

    private String table[][];

    public void checkArray (String [][] table) throws MyArraySizeException, MyArrayDataException {
        int tableSum = 0;
        if ((table.length == 4) && (table[0].length == 4)) {
            for (int i = 0; i < table.length; i++) {
                for (int j = 0; j < table[0].length; j++) {
                    System.out.print(table[i][j] + " ");
                }
                System.out.println();
            }

            for (int i = 0; i < table.length; i++) {
                int innerSum = 0;
                for (int j = 0; j < table[0].length; j++) {
                    Object var;
                    var = transformCell(table[i][j], i, j);
                    if ((var != null) && (var instanceof Integer)) {
                        innerSum = innerSum + (Integer) var;
                    } else {
                        throw new MyArrayDataException("Data type is " + var.getClass().getSimpleName() + " in string: " + (i+1) + " and row: " + (j+1));
                    }
                }
                tableSum += innerSum;
            }
            System.out.println(tableSum);
        } else {
            throw new MyArraySizeException("Array size must have 4x4 resolution!");
        }
    }

    public Integer transformCell (Object cell, int i, int j) throws MyArrayDataException {
        try {
            return Integer.parseInt(valueOf(cell));
        } catch (NumberFormatException e) {
            System.out.println("Data type is " + cell.getClass().getSimpleName() + " in string: " + (i+1) + " and " +
                    "row: " + (j+1));
        }
        return 0;
    }

    public static void main(String[] args) {
        String table1 [][] = {{"5","7","3","17"}, {"7","0","1","12"}, {"8","1","2","3"}, {"8","1","2","3"}, {"8","1",
                "2","3"}};
        String table2 [][] = {{"4","A","3","17"}, {"7","0","1","B"}, {"8","1","2","3"}, {"8","1","2","3"}};
        ClassExceptions exceptOne = new ClassExceptions();
        ClassExceptions exceptTwo = new ClassExceptions();
        try {
            exceptOne.checkArray(table1);
        } catch (MyArraySizeException e) {
        } catch (MyArrayDataException e) {
        }

        try {
            exceptTwo.checkArray(table2);
        } catch (MyArraySizeException e) {
        } catch (MyArrayDataException e) {
        }

        System.out.println("Program throw exception and work!");
    }
}




