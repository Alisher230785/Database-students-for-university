package org.example.data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDB {
    public static Connection GetConnect() {
        String connectionString = "jdbc:postgresql://localhost:5432/simpledb";
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(connectionString, "postgres", "ali228sql"); // creating the connection
            return con;
        } catch(SQLException e) { // Logging the exceptions
            System.out.println("connection error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
