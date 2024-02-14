package org.example;

import org.example.handlers.HandleAdmin;
import org.example.handlers.HandleStudent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Scanner;

public class Auth {
    Scanner sc = new Scanner(System.in);
    private Connection con;
    public Auth(Connection con) {
        this.con = con;
    }
    public void GetUser() {
        try {
            System.out.println("Enter your name");
            String name = sc.nextLine();
            System.out.println("Enter your password");
            String password = sc.nextLine();

            String result = VerifyUser(con, name, password);
            if (Objects.equals(result, "admin")) {
                System.out.println("Authentication successful");
                HandleAdmin handler = new HandleAdmin();
                handler.polling(con);
            } else if(Objects.equals(result, "student")) {
                System.out.println("Authentication successful");
                HandleStudent handler = new HandleStudent(name);
                handler.polling(con);
            } else {
                System.out.println("Authentication failed");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }

    }
    public void CreateUser() throws SQLException {
        try {
            System.out.println("Enter your name");
            String name = sc.nextLine();
            System.out.println("Create new password");
            String password = sc.nextLine();
            System.out.println("Enter a privilege");
            String privilege = sc.nextLine();

            if(CheckUser(con,name,password,privilege)) {
                System.out.println("New user was created");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    private static String VerifyUser(Connection con, String name, String password) throws SQLException {
        String query = "SELECT password, privilege FROM users WHERE name = '" + name + "' ;";
        Statement stmt = con.createStatement(); // creating a SQL statement
        ResultSet rs = stmt.executeQuery(query); // executing it
        if (rs.next()) {
            String rsPassword = rs.getString("password").trim(); // erasing unnecessary spaces
            if(password.equals(rsPassword)) {
                String rsPrivilege = rs.getString("privilege").trim(); // erasing unnecessary spaces
                if(rsPrivilege.equals("admin")) {
                    return "admin";
                } else if(rsPrivilege.equals("student")) {
                    return "student";
                }
            } else {
                System.out.println("the password is incorrect");
                return null;
            }
        } else {
            System.out.println("User not found");
            return null;
        }
        return null;
    }

    private static boolean CheckUser(Connection con, String name, String password, String privilege) throws SQLException {
        String query = "SELECT name FROM users WHERE name = '" + name + "' ;";
        Statement stmt = con.createStatement(); // creating a SQL statement
        ResultSet rs = stmt.executeQuery(query); // executing it
        if (rs.next()) {
            System.out.println("The user was already created");
            return false;
        } else {
            String Query = "INSERT INTO users (name, password, privilege) VALUES ('" + name + "', '" + password + "', '" + privilege + "') ;";
            Statement Stmt = con.createStatement(); // creating a SQL statement
            int Rs = stmt.executeUpdate(Query); // executing it
            return true;
        }
    }
}
