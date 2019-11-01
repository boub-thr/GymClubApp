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
    public boolean isLogin(String userName, String psw) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null ;
        String query= "select * from USERS where user_name = ? and password = ?" ;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,psw);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true ;
            } else {
                return false ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false ;
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
    }
}







