package org.example.handlers;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class HandleIOStream extends HandleDB{
    private String message = "\nChoose an action\ns - Select(int id: DEFAULT: '*')\nSelect a student field by ID (Default value - '*')\nc - Create(int id, String name, String last_name, int age, int course, double gpa)\nCreate a new student field\nu - Update(String column, String value, int id : DEFAULT = '*')\nUpdate the information about the student field based by id\nd - Delete(int id)\nDelete a student field by id\na -SelectStipendHolders()\nreturn all students that have a schoolar (GPA > 2.67)\n" ;
    public void Polling(Connection con) throws SQLException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println(message); // printing available actions
            String action = sc.nextLine(); // getting an action
            if(Objects.equals(action, "s")) { // SELECT
                System.out.println("write student id or name\nRemember: '*' will return an information from all students\n");
                String data = sc.nextLine();
                if(Objects.equals(data, "*")) { // SELECT ALL
                    try {
                        System.out.println(Select(con));
                    } catch (java.sql.SQLException e) {
                        System.out.println("something went wrong: " + e);
                    }
                } else if(isInteger(data)) { // SELECT by id
                    try {
                        System.out.println(Select(con, Integer.parseInt(data)));
                    } catch (java.sql.SQLException e) {
                        System.out.println("something went wrong: " + e);
                    }
                } else { // SELECT by name
                    try {
                        System.out.println(Select(con, data));
                    } catch (java.sql.SQLException e) {
                        System.out.println("something went wrong: " + e);
                    }
                }
            } else if (Objects.equals(action, "c")) { // CREATE
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
                    Create(con,name,last_name,age,course,gpa); // CREATE new student
                } catch (java.sql.SQLException e) {
                    System.out.println("something went wrong: " + e);
                }
            } else if(Objects.equals(action, "u")) { // UPDATE ALL
                System.out.println("Enter a column");
                String column = sc.nextLine();
                System.out.println("Enter a value");
                String value = sc.nextLine();
                System.out.println("Enter an id or name\nRemember: '*' will update for all students' columns");
                String data = sc.nextLine();
                if(Objects.equals(data, "*")) { // UPDATE ALL
                    try {
                        Update(con,column,value);
                    } catch (java.sql.SQLException e) {
                        System.out.println("something went wrong: " + e);
                    }
                } else if(isInteger(data)) { // UPDATE by id
                    try {
                        Update(con,column,value,Integer.parseInt(data));
                    } catch (java.sql.SQLException e) {
                        System.out.println("something went wrong: " + e);
                    }
                } else {
                    try {
                        Update(con,column,value,data); // UPDATE by name
                    } catch (java.sql.SQLException e) {
                        System.out.println("something went wrong: " + e);
                    }
                }
            } else if(Objects.equals(action, "d")) { // DELETE
                System.out.println("Write an id or name");
                String data = sc.nextLine();
                if(isInteger(data)) { // DELETE by id
                    try {
                        Delete(con,Integer.parseInt(data));
                    } catch(java.sql.SQLException e) {
                        System.out.println("something went wrong: " + e);
                    }
                } else { // DELETE by name
                    try {
                        Delete(con,data);
                    } catch(java.sql.SQLException e) {
                        System.out.println("something went wrong: " + e);
                    }
                }
            } else if(Objects.equals(action, "a")) { // SELECT all students with schoolarship
                try {
                    System.out.println(SelectStipendHolders(con));
                } catch(java.sql.SQLException e) {
                    System.out.println("something went wrong: " + e);
                }
            }
        }
    }
    public boolean isInteger(String data) { // Method to check if the String is int
        try {
            Integer.parseInt(data);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
