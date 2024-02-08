package org.example;
import org.example.handlers.HandleIOStream;

import java.sql.*;

import org.example.models.User;

import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String connectionString = "jdbc:postgresql://localhost:5432/simpledb";
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(connectionString, "postgres", "ali228sql");
            HandleIOStream handler = new HandleIOStream();
            handler.Polling(con);
        } catch(SQLException e) {
            System.out.println("connection error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}