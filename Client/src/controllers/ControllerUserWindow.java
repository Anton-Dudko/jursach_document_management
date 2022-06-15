package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import clientActions.Client;
import fileConnection.FileAddData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Document;
import windowsAlert.NewWindowOpen;

public class ControllerUserWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button searchButton;

    @FXML
    private TextField textFieldSearch;

    @FXML
    private TextField calculateField;



    @FXML
    private Button outButton;

    @FXML
    private Button updateData;

    @FXML
    private TableView<Document> booksTable;

    @FXML
    private TableColumn<Document, Integer> idTab;

    @FXML
    private TableColumn<Document, String> nameTab;

    @FXML
    private TableColumn<Document, String> dateTab;

    @FXML
    private TableColumn<Document, String > aboutTab;

    @FXML
    private TableColumn<Document, String> checkTab;



    @FXML
    private Button addDocumentInHistoryButton;


    private final ObservableList<Document> listDocuments = FXCollections.observableArrayList();


    @FXML
    void initialize() {

        try {
            initUsers(Client.interactionsWithServer.allDocumet());
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateData.setOnAction(event -> {
            try {
                initUsers(Client.interactionsWithServer.allDocumet());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        addDocumentInHistoryButton.setOnAction(event -> {
            int count = booksTable.getSelectionModel().getSelectedCells().get(0).getRow();
            try {
                FileAddData.fileAddData.addInFile(listDocuments.get(count));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        searchButton.setOnAction(event -> {
            initUsers(searchUser());

        });


        outButton.setOnAction(event -> {
            NewWindowOpen.newWindowOpen.openWindow(outButton,"../views/login.fxml");

        });

    }

    private void initUsers(LinkedList<Document> documents){

        listDocuments.clear();
        listDocuments.addAll(documents);

        idTab.setCellValueFactory(new PropertyValueFactory<Document, Integer>("id"));
        nameTab.setCellValueFactory(new PropertyValueFactory<Document, String>("name"));
        dateTab.setCellValueFactory(new PropertyValueFactory<Document, String>("date"));
        aboutTab.setCellValueFactory(new PropertyValueFactory<Document, String>("about"));
        checkTab.setCellValueFactory(new PropertyValueFactory<Document, String>("check"));

        booksTable.setItems(listDocuments);
    }

    private LinkedList<Document> searchUser(){
        String search = textFieldSearch.getText();
        LinkedList<Document> documentSearches = new LinkedList<>();
        for(Document document : listDocuments){
            if(search.equals(document.getName())){
                documentSearches.add(document);
            }

        }
        return documentSearches;
    }
}
