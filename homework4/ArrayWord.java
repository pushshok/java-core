/**
 * Java Core. Homework #4. class ArrayWord
 * @author Zdibnyak Maxim
 * @version 26.01.2022
 */

package ru.geekbrains.java_core;

import java.util.*;

public class ArrayWord {

    public static void main(String[] args) {
        List<String> wordList = Arrays.asList("one", "two", "one", "two", "two", "three", "one", "four", "one", "four",
                "one", "two", "five", "three", "three", "one", "three", "three", "five", "five");
        System.out.println("Origin array: " + wordList);

        Set<String> wordSet = new HashSet<String>(wordList);
        System.out.println("Unique vallues: " + wordSet);

        System.out.println();
        for (String w : wordSet) {
            HashMap<String, Integer> words = new HashMap();
            int j = 1;
            words.put(w,j);

            for (int i=0; i < wordList.size(); i++) {
                if (w.equals(wordList.get(i))) {
                    words.put(w,j++);
                }
            }
            System.out.println(w + " = " + words.put(w,j) + " times.");
        }

    }
}
