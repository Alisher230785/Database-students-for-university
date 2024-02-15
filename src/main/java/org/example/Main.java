package org.example;

import org.example.data.PostgresDB;
import org.example.handlers.HandleIOStream;
import java.sql.*;


public class Main extends HandleIOStream{
    public static void main(String[] args)  {
        Connection con = PostgresDB.GetConnect();
        Auth auth = new Auth(con);
        auth.polling();
    }
}