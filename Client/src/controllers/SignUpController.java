package controllers;


import java.net.URL;
import java.util.ResourceBundle;

import clientActions.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import windowsAlert.AlertWindow;
import windowsAlert.NewWindowOpen;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField familInput;

    @FXML
    private TextField emailInput;

    @FXML
    private TextField passwordInput;

    @FXML
    private Button signUpButton;

    @FXML
    private Button exitButton;

    @FXML
    void initialize() {

        signUpButton.setOnAction(event -> {
            if(valid()){
                Client.interactionsWithServer.addNewUserInDataBase(nameInput.getText(),familInput.getText(),emailInput.getText(),passwordInput.getText(),"user");
                AlertWindow.alertWindow.alertWindow("Операция прошла успешно !");
                NewWindowOpen.newWindowOpen.openWindow(exitButton,"../views/login.fxml");
            }

        });

        exitButton.setOnAction(event -> {
            NewWindowOpen.newWindowOpen.openWindow(exitButton,"../views/login.fxml");
        });

    }

    private boolean valid(){
        if(nameInput.getText().equals("")||familInput.getText().equals("")||emailInput.getText().equals("")||passwordInput.getText().equals("")){
            AlertWindow.alertWindow.alertWindow("Заполните пустые поля !");
            return false;
        }
        return true;

    }
}