package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.LoginModel;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public LoginModel loginModel = new LoginModel() ;


    @FXML
    private Label isConnected ;
    @FXML
    private TextField  loginName ;
    @FXML
    private TextField loginPassword ;

    public void onLogin() {
        try {
            if (loginModel.isLogin(loginName.getText(), loginPassword.getText())) {
                isConnected.setText("User name and password is Correct");
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Pane root = loader.load(getClass().getResource("/views/dashbord.fxml").openStream());
                DashboardController dashboardController = (DashboardController) loader.getController();
                dashboardController.getUser(loginName.getText());
                primaryStage.setTitle("dashboard");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            }else {
                isConnected.setText("User name and password is InCorrect !!");
            }
        } catch (SQLException | IOException e) {
            isConnected.setText("User name and password is InCorrect !!");
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (loginModel.isDbConnected()){
            isConnected.setText("You successfully connected !");
        } else {
            isConnected.setText("you are not Connected");
        }

    }
}
