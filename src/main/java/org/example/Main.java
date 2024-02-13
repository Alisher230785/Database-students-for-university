package org.example;

import org.example.handlers.HandleIOStream;
import java.sql.*;


public class Main extends HandleIOStream{
    public static void main(String[] args)  {
        String connectionString = "jdbc:postgresql://localhost:5432/simpledb";
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(connectionString, "postgres", "qwerty"); // creating the connection
            HandleIOStream handler = new HandleIOStream();
            handler.Polling(con); // start handling the user Inputs
        } catch(SQLException e) { // Logging the exceptions
            System.out.println("connection error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}