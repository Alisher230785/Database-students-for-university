package org.example;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface HandleDBMethods {
    public ArrayList<String> Select(Connection con) throws SQLException; // SELECT ALL
    public String Select(Connection con, int id) throws SQLException; // SELECT by id
    public ArrayList<String> Select(Connection con, String name) throws SQLException; // SELECT by name
    public void Create(Connection con, String name, String last_name,int age, int course, double gpa) throws SQLException; // CREATE new row
    public void Update(Connection con, String column, String value, int id) throws SQLException; // UPDATE a row
    public void Update(Connection con, String column, String value) throws SQLException; // UPDATE all rows
    public void Update(Connection con, String column, String value, String name) throws SQLException;
    public void Delete(Connection con, int id) throws SQLException; // DELETE a row by id
    public void Delete(Connection con, String id) throws SQLException; // DELETE a row by name
    public ArrayList<String> SelectStipendHolders(Connection con) throws SQLException; // SELECT students with scholarship
    public String Analyze(Connection con, String name) throws SQLException; // Analyze a student by specific criteria
}