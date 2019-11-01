package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import models.LoginModel;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public LoginModel loginModel = new LoginModel() ;

    @FXML
    private Label isConnected ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (loginModel.isDbConnected()){
            isConnected.setText("You successfully connected !");
        } else {
            isConnected.setText("you are not Connected");
        }

    }
}
