package models;


import services.SqliteConnection;

import java.sql.*;

public class LoginModel {
    Connection connection ;

    public LoginModel(){
        connection = SqliteConnection.connector();
        if(connection == null){
            System.out.println("Error Connecting !!");
            System.exit(1);
        }
    }

    public boolean isDbConnected() {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
          e.printStackTrace();
          return false ;
        }
    }

}







