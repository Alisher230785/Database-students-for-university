package org.example.handlers;
import java.sql.Connection;
import java.util.Objects;
import java.util.Scanner;

public class HandleIOStream extends HandleDB{
    private String message = "\nChoose an action\ns - Select(int id: DEFAULT: '*')\nSelect a student field by ID (Default value - '*')\nc - Create(int id, String name, String last_name, boolean gender, int course, double gpa)\nCreate a new student field\nu - Update(String column, String value, int id : DEFAULT = '*')\nUpdate the information about the student field based by id\nd - Delete(int id)\nDelete a student field by id\n" ;
    public void Polling(Connection con) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println(message);
            String action = sc.nextLine();
            if(Objects.equals(action, "s")) {
                System.out.println("write student id or name which you need to get an information from\nRemember: '*' will return an information from all students\n");
                String data = sc.nextLine();
                if(Objects.equals(data, "*")) {
                    try {
                        System.out.println(Select(con));
                    } catch (java.sql.SQLException e) {
                        System.out.println("something went wrong: " + e);
                    }
                } else if(isInteger(data)) {
                    try {
                        System.out.println(Select(con, Integer.parseInt(data)));
                    } catch (java.sql.SQLException e) {
                        System.out.println("something went wrong: " + e);
                    }
                } else {
                    try {
                        System.out.println(Select(con, data));
                    } catch (java.sql.SQLException e) {
                        System.out.println("something went wrong: " + e);
                    }
                }
            } else if (Objects.equals(action, "c")) {
                System.out.println("Enter student's name");
                String name = sc.nextLine();
                System.out.println("Enter student's lastname");
                String last_name = sc.nextLine();
                System.out.println("Enter student's age");
                int age = Integer.parseInt(sc.nextLine());
                System.out.println("Enter student's course");
                int course = Integer.parseInt(sc.nextLine());
                System.out.println("Enter student's gpa");
                double gpa = Double.parseDouble(sc.nextLine());
                try {
                    Create(con,name,last_name,age,course,gpa);
                } catch (java.sql.SQLException e) {
                    System.out.println("something went wrong: " + e);
                }
            } else if(Objects.equals(action, "u")) {
                System.out.println("Enter a column");
                String column = sc.nextLine();
                System.out.println("Enter a value");
                String value = sc.nextLine();
                System.out.println("Enter an id\nRemember: '*' will update for all students' columns");
                String data = sc.nextLine();
                if(Objects.equals(data, "*")) {
                    try {
                        Update(con,column,value);
                    } catch (java.sql.SQLException e) {
                        System.out.println("something went wrong: " + e);
                    }
                } else if(isInteger(data)) {
                    try {
                        Update(con,column,value,data);
                    } catch (java.sql.SQLException e) {
                        System.out.println("something went wrong: " + e);
                    }
                } else {
                    System.out.println("please, write an id");
                }
            } else if(Objects.equals(action, "d")) {
                System.out.println("Write an id");
                String id = sc.nextLine();
                if(isInteger(id)) {
                    try {
                        Delete(con,id);
                    } catch(java.sql.SQLException e) {
                        System.out.println("something went wrong: " + e);
                    }
                } else {
                    System.out.println("please, write an id");
                }
            }
        }
    }
    public boolean isInteger(String data) {
        try {
            Integer.parseInt(data);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
