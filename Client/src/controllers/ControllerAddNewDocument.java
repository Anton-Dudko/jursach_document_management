package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import clientActions.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import windowsAlert.AlertWindow;
import windowsAlert.NewWindowOpen;

public class ControllerAddNewDocument {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameField;

    @FXML
    private TextField dateField;

    @FXML
    private TextField aboutField;

    @FXML
    private TextField checkField;


    @FXML
    private Button addButton;

 

    @FXML
    void initialize() {

        addButton.setOnAction(event -> {
            if(valid()) {
                Client.interactionsWithServer.addNewDocumentInDataBase(nameField.getText(), dateField.getText(), aboutField.getText(), checkField.getText());

            }

            NewWindowOpen.newWindowOpen.openWindow(addButton,"../views/adminWindow.fxml");
        });
    }
    private boolean valid(){
        if(aboutField.getText().equals("")||nameField.getText().equals("")||dateField.getText().equals("")||aboutField.getText().equals("")){
            AlertWindow.alertWindow.alertWindow("Заполните пустые поля !");
           return false;
        }
        return true;

    }
}
