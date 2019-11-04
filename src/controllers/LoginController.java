package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.LoginModel;
import services.ConnectDB;
import services.UserService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ConnectDB cnx = new ConnectDB() ;
    private UserService userService = new UserService();


    @FXML
    private Label isConnected ;
    @FXML
    private TextField  loginName ;
    @FXML
    private TextField loginPassword ;

    // the login form need validation
    public void onLogin(ActionEvent event) {
        try {
            if (userService.isLogin(loginName.getText(), loginPassword.getText())) {
                isConnected.setText("User name and password is Correct");
                ((Node)event.getSource()).getScene().getWindow().hide();
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Pane root = loader.load(getClass().getResource("/views/dashbord.fxml").openStream());
                DashboardController dashboardController = (DashboardController) loader.getController();
                dashboardController.getUser(loginName.getText());
                primaryStage.setTitle("dashboard");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            }else {
                //Validation
                isConnected.setText("User name and password is InCorrect !!");
            }
        } catch (SQLException | IOException e) {
            isConnected.setText("User name and password is InCorrect !!");
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (cnx.isDbConnected()){
            isConnected.setText("You successfully connected !");
        } else {
            isConnected.setText("you are not Connected");
        }

    }
}
