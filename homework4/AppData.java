/**
 * Java Core. Homework #4. class Phonebook
 * @author Zdibnyak Maxim
 * @version 26.01.2022
 */
package ru.geekbrains.java_core;

import java.io.*;
import java.util.HashMap;

public class AppData {
    private String file;
    private String[] header;
    private int[][] data;

    public AppData (String file, String[] header, int[][] data) {
        this.file = file;
        this.header = header;
        this.data = data;
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    public void setData (int[][] data) {
        this.data = data;
    }

    public void save() {
        try (OutputStream out = new BufferedOutputStream (new FileOutputStream(this.file))) {
            out.write(this.toString().getBytes());
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public void load() throws NumberFormatException {
        try (BufferedReader in = new BufferedReader(new FileReader(this.file))) {
            String result = "";
            setHeader(in.readLine().split(";"));
            HashMap<Integer, String> newMap = new HashMap<>();

            int i = 0;
            while ((result = in.readLine()) != null) {
                newMap.put(i, result);
                i++;
            }
            int[][] stroker = new int[newMap.size()][newMap.get(0).split(";").length];
            for (i = 0; i < newMap.size(); i++) {
                String[] newString = newMap.get(i).split(";");
                for (int j = 0; j < newMap.size(); j++) {
                    try {
                        stroker[i][j] = Integer.parseInt(String.valueOf(newString[j]));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
            setData(stroker);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String outData = "";

        for (int i = 0; i < this.header.length; i++) {
            outData += header[i] + ";";
        }
        outData += "\n";
        for (int i = 0; i < this.data.length; i++) {
            for (int j = 0; j < this.data[0].length; j++) {
                outData += data[i][j] + ";";
            }
            outData += "\n";
        }
        System.out.println();
        System.out.println();
        return outData;
    }


    public static void main(String[] args) {
        String[] header = {"Value1", "Value1", "Value1"};
        int[][] dataBody = {{100, 200, 123}, {300, 400, 500}};
        AppData data = new AppData("file.csv", header, dataBody);

        data.save();
        data.load();
        System.out.println(data);

        int[][] dataBody2 = {{100, 200, 123}, {300, 400, 500}, {300, 400, 500}};
        data.setData(dataBody2);
        data.save();
        System.out.println(data);
    }
}



