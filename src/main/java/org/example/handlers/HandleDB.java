package org.example.handlers;

import org.example.HandleDBMethods;
import org.example.models.Student;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

public class HandleDB implements HandleDBMethods {

    @Override
    public ArrayList<String> Select(Connection con) throws SQLException { // SELECT ALL
        ArrayList<String> students = new ArrayList<>();
        String sql = "SELECT id, name, last_name, age, course, gpa FROM students ORDER BY id;";
        Statement stmt = con.createStatement(); // creating a SQL statement
        ResultSet rs = stmt.executeQuery(sql); // executing it

        while (rs.next()) { // handling the response
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String last_name = rs.getString("last_name");
            int age = rs.getInt("age");
            int course = rs.getInt("course");
            double gpa = rs.getDouble("gpa");

            Student student = new Student(id, name, last_name, age, course, gpa); // creating new user
            students.add(student.toString());
        }
        if(!students.isEmpty()) { // check if the students is not empty
            return students; // return all students
        } else {
            // No rows were returned
            System.out.println("No rows found.");
            return null;
        }
    }

    @Override
    public String Select(Connection con, int ID) throws SQLException { // SELECT by id
        String sql = "SELECT id, name, last_name, age, course, gpa FROM students WHERE id = " + ID + ";";
        Statement stmt = con.createStatement(); // creating a SQL statement
        ResultSet rs = stmt.executeQuery(sql); // executing it
        if (rs.next()) { // handling the response
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String last_name = rs.getString("last_name");
            int age = rs.getInt("age");
            int course = rs.getInt("course");
            double gpa = rs.getDouble("gpa");

            return new Student(id, name, last_name, age, course, gpa).toString();
        } else {
            // No rows were returned
            System.out.println("No rows found.");
            return null;
        }
    }

    @Override
    public ArrayList<String> Select(Connection con, String Name) throws SQLException { // SELECT by name
        ArrayList<String> students = new ArrayList<>();
        String sql = "SELECT id, name, last_name, age, course, gpa FROM students WHERE name = '" + Name + "' ;";
        Statement stmt = con.createStatement(); // creating a SQL statement
        ResultSet rs = stmt.executeQuery(sql); // executing it
        while (rs.next()) { // handling the response
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String last_name = rs.getString("last_name");
            int age = rs.getInt("age");
            int course = rs.getInt("course");
            double gpa = rs.getDouble("gpa");

            Student student = new Student(id, name, last_name, age, course, gpa); // creating new user
            students.add(student.toString());
        }
        if(!students.isEmpty()) { // check if the students is not empty
            return students; // return all students
        } else {
            // No rows were returned
            System.out.println("No rows found.");
            return null;
        }
    }
    @Override
    public void Create(Connection con,String name, String last_name,int age, int course, double gpa) throws SQLException { // CREATE new Student
        String sql = "INSERT INTO students (name, last_name, age, course, gpa) VALUES ('" + name +"', '" + last_name + "', " + String.valueOf(age) + ", " +  String.valueOf(course) + ", " + String.valueOf(gpa) +" );";
        Statement stmt = con.createStatement(); // creating a SQL statement
        int rs = stmt.executeUpdate(sql); // executing it, without waiting a response
    }

    @Override
    public void Update(Connection con, String column, String value, int id) throws SQLException { // UPDATE by id
        String sql = null;
        if(Objects.equals(column, "name") || Objects.equals(column, "last_name")) { // determine which SQL statement to choose
            sql = "UPDATE students SET " + column + " = '" + value + "' WHERE id = " + String.valueOf(id) + ";";
        } else {
            sql = "UPDATE students SET " + column + " = " + value + " WHERE id = " + String.valueOf(id) + ";";
        }
        Statement stmt = con.createStatement();
        int rs = stmt.executeUpdate(sql); // executing
    }

    @Override
    public void Update(Connection con, String column, String value) throws SQLException { // UPDATE ALL
        String sql = null;
        if(Objects.equals(column, "name") || Objects.equals(column, "last_name")) { // determine which SQL statement to choose
            sql = "UPDATE students SET " + column + " = '" + value + "' ;";
        } else {
            sql = "UPDATE students SET " + column + " = " + value + " ;";
        }
        Statement stmt = con.createStatement();
        int rs = stmt.executeUpdate(sql); // executing
    }

    @Override
    public void Update(Connection con, String column, String value, String name) throws SQLException { // UPDATE by name
        String sql = null;
        if(Objects.equals(column, "name") || Objects.equals(column, "last_name")) { // determine which SQL statement to choose
            sql = "UPDATE students SET " + column + " = '" + value + "' WHERE name = '" + name + "' ;";
        } else {
            sql = "UPDATE students SET " + column + " = " + value + " WHERE name = '" + name + "' ;";
        }
        Statement stmt = con.createStatement();
        int rs = stmt.executeUpdate(sql); // executing
    }

    @Override
    public void Delete(Connection con, int id) throws SQLException { // DELETE by id
        String sql = "DELETE FROM students WHERE id = " + String.valueOf(id) + " ;";
        Statement stmt = con.createStatement(); // creating a SQL statement
        int rs = stmt.executeUpdate(sql); // executing it
    }

    @Override
    public void Delete(Connection con, String name) throws SQLException { // DELETE by name
        String sql = "DELETE FROM students WHERE name = '" + name + "' ;";
        Statement stmt = con.createStatement(); // creating a SQL statement
        int rs = stmt.executeUpdate(sql); // executing it
    }

    @Override
    public ArrayList<String> SelectStipendHolders(Connection con) throws SQLException { // SELECT students with schoolarship
        ArrayList<String> students = new ArrayList<>();
        String sql = "SELECT id, name, last_name, age, course, gpa FROM students WHERE gpa > 2.67;";
        Statement stmt = con.createStatement(); // creating a SQL statement
        ResultSet rs = stmt.executeQuery(sql); // executing it
        while (rs.next()) { // handling the response
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String last_name = rs.getString("last_name");
            int age = rs.getInt("age");
            int course = rs.getInt("course");
            double gpa = rs.getDouble("gpa");

            Student student = new Student(id, name, last_name, age, course, gpa); // creating new user
            students.add(student.toString());
        }
        if(!students.isEmpty()) { // check if the students is not empty
            return students; // return all students
        } else {
            // No rows were returned
            System.out.println("No rows found.");
            return null;
        }
    }

    @Override
    public String Analyze(Connection con, String name) throws SQLException {
        return null;
    }
}
