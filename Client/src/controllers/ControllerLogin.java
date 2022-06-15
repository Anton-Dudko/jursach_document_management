package controllers;

import javafx.fxml.FXML;
import clientActions.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import windowsAlert.AlertWindow;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import windowsAlert.NewWindowOpen;


public class ControllerLogin {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginTextField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button newUserButton;



    private String login;
    private String password;

    @FXML
    void initialize() {
        textFieldInitialize();
        loginButton.setOnAction(event -> {
            confirmLoginAndPassword();
        });


        newUserButton.setOnAction(event -> {
            NewWindowOpen.newWindowOpen.openWindow(newUserButton,"../views/signUp.fxml");

        });
    }

    private void confirmLoginAndPassword(){

        login =  loginTextField.getText();
        password = passwordField.getText();
        if(!login.equals("")&&!password.equals("")) {
            Client.interactionsWithServer.send("авторизация");
            Client.interactionsWithServer.send(login+" "+password);
            Client.interactionsWithServer.chekUserInDatabase(loginButton);

        }else{
            AlertWindow.alertWindow.alertWindow("Заполните пустые поля !");
        }
    }


    private void textFieldInitialize(){
        loginTextField.setText("");
        passwordField.setText("");
    }
}
