package org.example.handlers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class HandleStudent extends HandleDB{
    private String name;
    public HandleStudent(String name) {
        this.name = name;
    }
    String message = "choose an action\ng - get information about yourself\na - analyze yourself by a GPA";
    public void polling(Connection con) {
        Scanner sc = new Scanner(System.in);
        try {
            while(true) {
                System.out.println(message);
                String action = sc.nextLine();
                if(Objects.equals(action, "g")) {
                    System.out.println(Select(con,name));
                } else if(Objects.equals(action, "a")) {
                    System.out.println(Analyze(con,name));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }

    }
}
