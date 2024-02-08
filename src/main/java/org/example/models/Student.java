package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor // use of Lombok library
@NoArgsConstructor
@Getter
@Setter
public class Student {
    private int id; // Students properties
    private String name;
    private String last_name;
    private int age;
    private int course;
    private double gpa;

    @Override
    public String toString() { //custom toString method
        return "\nID: " + id +
                "\nName: " + name +
                "\nLastname: " + last_name +
                "\nAge: " + age +
                "\ncourse: " + course +
                "\nGPA: " + gpa;
    }
}