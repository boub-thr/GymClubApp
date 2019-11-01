package services;

import java.sql.*;

public class SqliteConnection {

    public static Connection connector() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conx = DriverManager.getConnection("jdbc:sqlite:GymClub.sqlite");
            return conx ;

        } catch (Exception e) {
            System.out.println("error occured !!");
            System.out.println(e);
            return null ;
        }
    }
}
























