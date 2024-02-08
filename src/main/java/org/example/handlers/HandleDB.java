package org.example.handlers;

import org.example.HandlerMethods;
import org.example.models.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

public class HandleDB implements HandlerMethods {

    @Override
    public ArrayList<String> Select(Connection con) throws SQLException {
        ArrayList<String> users = new ArrayList<>();
        String sql = "SELECT id, name, last_name, age, course, gpa FROM students ORDER BY id;";
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String last_name = rs.getString("last_name");
            int age = rs.getInt("age");
            int course = rs.getInt("course");
            double gpa = rs.getDouble("gpa");

            User user = new User(id, name, last_name, age, course, gpa);
            users.add(user.toString());
        }
        return users;
    }

    @Override
    public String Select(Connection con, int data) throws SQLException {
        String sql = "SELECT id, name, last_name, age, course, gpa FROM students WHERE id = " + data + ";";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        if (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String last_name = rs.getString("last_name");
            int age = rs.getInt("age");
            int course = rs.getInt("course");
            double gpa = rs.getDouble("gpa");

            return new User(id, name, last_name, age, course, gpa).toString();
        } else {
            // No rows were returned
            System.out.println("No rows found.");
            return null; // or handle the absence of data in another way
        }
    }

    @Override
    public ArrayList<String> Select(Connection con, String Name) throws SQLException {
        ArrayList<String> users = new ArrayList<>();
        String sql = "SELECT id, name, last_name, age, course, gpa FROM students WHERE name = '" + Name + "' ;";
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String last_name = rs.getString("last_name");
            int age = rs.getInt("age");
            int course = rs.getInt("course");
            double gpa = rs.getDouble("gpa");

            User user = new User(id, name, last_name, age, course, gpa);
            users.add(user.toString());
        }
        return users;
    }
    @Override
    public void Create(Connection con,String name, String last_name,int age, int course, double gpa) throws SQLException {
        String sql = "INSERT INTO students (name, last_name, age, course, gpa) VALUES ('" + name +"', '" + last_name + "', " + String.valueOf(age) + ", " +  String.valueOf(course) + ", " + String.valueOf(gpa) +" );";
        Statement stmt = con.createStatement();
        int rs = stmt.executeUpdate(sql);
    }

    @Override
    public void Update(Connection con, String column, String value, String id) throws SQLException {
        String sql = null;
        if(Objects.equals(column, "name") || Objects.equals(column, "last_name")) {
            sql = "UPDATE students SET " + column + " = '" + value + "' WHERE id = " + id + ";";
        } else {
            sql = "UPDATE students SET " + column + " = " + value + " WHERE id = " + id + ";";
        }
        Statement stmt = con.createStatement();
        int rs = stmt.executeUpdate(sql);
    }

    @Override
    public void Update(Connection con, String column, String value) throws SQLException {
        String sql = null;
        if(Objects.equals(column, "name") || Objects.equals(column, "last_name")) {
            sql = "UPDATE students SET " + column + " = '" + value + "' ;";
        } else {
            sql = "UPDATE students SET " + column + " = " + value + " ;";
        }
        Statement stmt = con.createStatement();
        int rs = stmt.executeUpdate(sql);
    }

    @Override
    public void Delete(Connection con, String id) throws SQLException {
        String sql = "DELETE FROM students WHERE id = " + id + " ;";
        Statement stmt = con.createStatement();
        int rs = stmt.executeUpdate(sql);
    }
}
