package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MaderaTabletasController {


    // Abre un Stage con la vista extra de tabletas.
    @FXML
    void despegar(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/views/extraview.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/chainsaw.png")));
        stage.setTitle("Menu principal");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }


}
