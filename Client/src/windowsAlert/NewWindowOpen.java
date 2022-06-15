package windowsAlert;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class NewWindowOpen {
    public static NewWindowOpen newWindowOpen =  new NewWindowOpen();
    private NewWindowOpen(){}

    public void openWindow(Button button, String file){
        Stage stage = (Stage)button.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
