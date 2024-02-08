package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private int id;
    private String name;
    private String last_name;
    private int age;
    private int course;
    private double gpa;

    @Override
    public String toString() {
        return "\n" + id + ": \n" +
                "Name: " + name +
                "\nLastname: " + last_name +
                "\nAge: " + age +
                "\ncourse: " + course +
                "\nGPA: " + gpa;
    }
}