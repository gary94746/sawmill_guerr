package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import modelo.ClasesArray;
import modelo.Conexion;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/chainsaw.png")));
        stage.setTitle("Menu principal");
        stage.show();
    }
}
