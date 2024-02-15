package org.example;

import org.example.data.PostgresDB;
import java.sql.*;


public class Main{
    public static void main(String[] args)  {
        Connection con = PostgresDB.GetConnect();
        Auth auth = new Auth(con);
        auth.polling();
    }
}