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
import windowsAlert.AlertWindow;
import windowsAlert.NewWindowOpen;

public class ControllerAdminWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Document> documentsTable;

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
    private Button addDocumentButton;

    @FXML
    private Button deleteDocumentButton;

    @FXML
    private Button addInFileButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchText;


    @FXML
    private Button redactionFieldButton;

    @FXML
    private Button chooseButton;

    @FXML
    private Button updateButton;
    @FXML
    private Button outButton;
    @FXML
    private TextField nameField;
    @FXML
    private Button usersButton;



    @FXML
    private TextField dateField;

    @FXML
    private TextField aboutField;

    @FXML
    private TextField checkField;



    private final ObservableList<Document> listDocuments = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        initTextField();

        outButton.setOnAction(event -> {
            NewWindowOpen.newWindowOpen.openWindow(outButton,"../views/login.fxml");
        });
        usersButton.setOnAction(event -> {
            NewWindowOpen.newWindowOpen.openWindow(outButton,"../views/users.fxml");
        });

        updateButton.setOnAction(event -> {
            try {
                initUsers(Client.interactionsWithServer.allDocumet());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        try {
            initUsers(Client.interactionsWithServer.allDocumet());
        } catch (IOException e) {
            e.printStackTrace();
        }
        searchButton.setOnAction(event -> {
            initUsers(searchUser());

        });
        deleteDocumentButton.setOnAction(event -> {
            int count = documentsTable.getSelectionModel().getSelectedCells().get(0).getRow();

            Client.interactionsWithServer.deleteDocument(listDocuments.get(count).getId());
            try {
                initUsers(Client.interactionsWithServer.allDocumet());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        addInFileButton.setOnAction(event -> {
            int count = documentsTable.getSelectionModel().getSelectedCells().get(0).getRow();
            try {
                FileAddData.fileAddData.addInFile(listDocuments.get(count));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        redactionFieldButton.setOnAction(event -> {
            int count = documentsTable.getSelectionModel().getSelectedCells().get(0).getRow();

            Client.interactionsWithServer.redactionDoc(listDocuments.get(count).getId(),nameField.getText(), dateField.getText(), aboutField.getText(), checkField.getText());
            try {
                initUsers(Client.interactionsWithServer.allDocumet());
            } catch (IOException e) {
                e.printStackTrace();
            }
            initTextField();
        });

        chooseButton.setOnAction(event -> {
            int count = documentsTable.getSelectionModel().getSelectedCells().get(0).getRow();
            nameField.setText(listDocuments.get(count).getName());
            dateField.setText(listDocuments.get(count).getDate());
            aboutField.setText(listDocuments.get(count).getAbout());
            checkField.setText(listDocuments.get(count).getCheck());


        });
        addDocumentButton.setOnAction(event -> {
            NewWindowOpen.newWindowOpen.openWindow(addDocumentButton,"../views/addNewDocument.fxml");
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

        documentsTable.setItems(listDocuments);
    }
    private void initTextField(){
        nameField.setText("");
        dateField.setText("");
        aboutField.setText("");
        checkField.setText("");



    }

    private LinkedList<Document> searchUser(){
        String search = searchText.getText();
        LinkedList<Document> documentSearches = new LinkedList<>();
        for(Document document : listDocuments){
            if(search.equals(document.getName())){
                documentSearches.add(document);
            }

        }
         return documentSearches;
    }



}
