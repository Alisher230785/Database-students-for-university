package org.example.handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class HandleAdmin extends HandleDB {
    private String message = "\nChoose an action\ns - Select(int id: DEFAULT: '*')\nSelect a student field by ID (Default value - '*')\nc - Create(int id, String name, String last_name, int age, int course, double gpa)\nCreate a new student field\nu - Update(String column, String value, int id : DEFAULT = '*')\nUpdate the information about the student field based by id\nd - Delete(int id)\nDelete a student field by id\na - SelectStipendHolders()\nreturn all students that have a schoolar (GPA > 2.67)\nr - Create new students via file\n" ;
    public void polling(Connection con){
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
                String input = sc.nextLine();
                input = input.replace(',', '.');
                double gpa = Double.parseDouble(input);
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
            } else if(Objects.equals(action, "r")) {
                System.out.println("please, ensure that you have written all properties of new students in the file CREATENEW.txt");
                System.out.println("confirm to upload?(y/n)");
                String qw = sc.nextLine();
                if(Objects.equals(qw, "y")) {
                    try {
                        File file = new File("C:\\Users\\Ali\\IdeaProjects\\Database-students-for-university\\src\\main\\java\\org\\example\\CREATENEW.txt");
                        Scanner temp = new Scanner(file);
                        while(temp.hasNext()) {
                            String line = temp.nextLine();
                            String[] parts = line.split("\\s+"); // Split the line based on whitespace

                            String name = parts[0];
                            String last_name = parts[1];
                            int age = Integer.parseInt(parts[2]);
                            int course = Integer.parseInt(parts[3]);
                            double gpa = Double.parseDouble(parts[4]);

                            Create(con,name,last_name,age,course,gpa);
                            System.out.println("new students were created");
                        }
                    } catch(FileNotFoundException e) {
                        System.out.println("File not found: " + e.getMessage());
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing numeric value in the file: " + e.getMessage());
                    } catch(SQLException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("the operation is closed");
                }
            }
        }
    }
    private boolean isInteger(String data) { // Method to check if the String is int
        try {
            Integer.parseInt(data);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}