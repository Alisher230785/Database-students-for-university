package org.example;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface HandlerMethods {
    public ArrayList<String> Select(Connection con) throws SQLException;
    public String Select(Connection con, int id) throws SQLException;
    public ArrayList<String> Select(Connection con, String name) throws SQLException;
    public void Create(Connection con, String name, String last_name,int age, int course, double gpa) throws SQLException;
    public void Update(Connection con, String column, String value, String id) throws SQLException;
    public void Update(Connection con, String column, String value) throws SQLException;
    public void Delete(Connection con, String id) throws SQLException;
}