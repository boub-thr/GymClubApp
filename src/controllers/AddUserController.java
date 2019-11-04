package controllers;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import services.UserService;

import java.sql.SQLException;

public class AddUserController {
    UserService userService = new UserService() ;
    @FXML
    TextField newUserName ;
    @FXML
    PasswordField newPassword ;
    @FXML
    PasswordField confirmNewPassword ;

     public void onSignUp() {
         System.out.println(newUserName.getText());
         System.out.println(newPassword.getText());
         System.out.println(confirmNewPassword.getText());
         try {
             userService.addNewUser(newUserName.getText(), newPassword.getText());
         } catch (SQLException e) {
             e.printStackTrace();
         }
     }
}
