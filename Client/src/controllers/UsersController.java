package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import clientActions.Client;
import clientActions.ClientActionsWithServer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.User;
import windowsAlert.NewWindowOpen;


public class UsersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<User> usersView;

    @FXML
    private TableColumn<User, String> idTab;

    @FXML
    private TableColumn<User, String> emailTab;

    @FXML
    private TableColumn<User, String> passwordTab;

    @FXML
    private TableColumn<User, String> rollTab;


    @FXML
    private Button deleteBtn;

    @FXML
    private Button backBtn;
    private final ObservableList<User> users = FXCollections.observableArrayList();

    @FXML
    void initialize() {


        try {
            initUsers(Client.interactionsWithServer.showAllUsers());
        } catch (IOException e) {
            e.printStackTrace();
        }

        deleteBtn.setOnAction(event -> {
            int  count = usersView.getSelectionModel().getSelectedCells().get(0).getRow();
            Client.interactionsWithServer.deleteUser(users.get(count).getId());

            try {
                initUsers(Client.interactionsWithServer.showAllUsers());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        backBtn.setOnAction(event -> {
            NewWindowOpen.newWindowOpen.openWindow(backBtn,"../views/adminWindow.fxml");
        });



    }
    private void initUsers(LinkedList<User> listDb){
        users.clear();
        users.addAll(listDb);
        idTab.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
        emailTab.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        passwordTab.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        rollTab.setCellValueFactory(new PropertyValueFactory<User, String>("roll"));

        usersView.setItems(users);
    }
}
