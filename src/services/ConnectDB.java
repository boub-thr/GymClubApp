package services;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectDB {
    Connection connection ;

    public ConnectDB() {
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
