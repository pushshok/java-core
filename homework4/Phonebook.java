/**
 * Java Core. Homework #4. class Phonebook
 * @author Zdibnyak Maxim
 * @version 26.01.2022
 */

package ru.geekbrains.java_core;

import java.util.HashMap;
import java.util.LinkedList;

public class Phonebook {
    private final HashMap<String, LinkedList<String>> entity = new HashMap<>();

    public Phonebook (String name, String phoneNumbers) {
            LinkedList<String> list = new LinkedList<>();
            list.add(phoneNumbers);
            this.entity.put(name, list);
    }

    public void add(String name, String phoneNumbers) {
        if (this.entity.containsKey(name)) {
            LinkedList<String> list = this.entity.get(name);
            list.add(phoneNumbers);
            this.entity.replace(name, list);
        } else {
            LinkedList<String> list = new LinkedList<>();
            list.add(phoneNumbers);
            this.entity.put(name, list);
        }
    }

    public String get(String name) {
        if (this.entity.containsKey(name)) {
            return "Abonent: " + name + " numbers: " + this.entity.get(name);
        } return "Abonent not found!";
    }

    public static void main(String[] args) {
        Phonebook entry = new Phonebook("Vasiliev", "+7 (978) 000-99-77");
        entry.add("Vasiliev", "+7 (928) 550-99-77");
        entry.add("Vasiliev", "+7 (928) 550-99-88");
        entry.add("Petrov", "+7 (926) 006-99-77");
        entry.add("Tonyan", "+7 (916) 006-79-77");
        entry.add("Tonyan", "+7 (918) 006-99-77");

        System.out.println(entry.get("Vasiliev"));
        System.out.println(entry.get("Tonyan"));
    }


}
