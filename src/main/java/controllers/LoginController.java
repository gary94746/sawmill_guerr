package controllers;

import com.jfoenix.controls.JFXTreeTableColumn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    void login(ActionEvent event) throws IOException {
        //Close login stage
        var loginStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        loginStage.close();

        //Open the dashboard window
        Parent parent = FXMLLoader.load(getClass().getResource("/views/dashboard.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/60c83449-dd46-44df-9384-0713810ea1c3.jpeg")));
        stage.setTitle("Menu principal");
        stage.show();
    }
}
