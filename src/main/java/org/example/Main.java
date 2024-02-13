package org.example;

import org.example.data.PostgresDB;
import org.example.handlers.HandleIOStream;
import java.sql.*;
import java.util.Objects;
import java.util.Scanner;


public class Main extends HandleIOStream{
    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        try {
            Connection con = PostgresDB.GetConnect();
            Auth auth = new Auth(con);
            while(true) {
                System.out.println("choose an action\na - Authentificate to the system\nr - registrate to the system\n");
                String action = sc.nextLine();
                if(Objects.equals(action, "a")) {
                    auth.GetUser();
                } else if (Objects.equals(action, "r")) {
                    auth.CreateUser();
                } else {
                    System.out.println("Please, choose an action");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
    }
}