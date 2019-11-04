package services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    Connection connection ;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null ;

    public  UserService() {
        connection = SqliteConnection.connector();
        if(connection == null){
            System.out.println("Error Connecting !!");
            System.exit(1);
        }
    }

    //signUp new User
    public void addNewUser(String userName, String psw) throws SQLException {
        String query= "insert into USERS(user_name, password) values(?, ? )" ;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,crypteFct(psw));
            preparedStatement.executeUpdate();
            System.out.println("insersion executé");
            System.out.println(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            preparedStatement.close();
        }

    }

    //signIn
    public boolean isLogin(String userName, String psw) throws SQLException {

        String query= "select * from USERS where user_name = ? and password = ?" ;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,crypteFct(psw));
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

    private String crypteFct(String msg ){
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(msg.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
            return generatedPassword;
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return null ;
        }

    }
}
