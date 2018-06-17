package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class DashboardController {


    @FXML
    private BorderPane borderDashboard;


    @FXML
    void setResumen(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/views/resumen.fxml"));
        borderDashboard.setCenter(parent);
    }

    @FXML
    void setControl(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/views/Control.fxml"));
        borderDashboard.setCenter(parent);
    }

    @FXML
    void setOtros(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/views/otros.fxml"));
        borderDashboard.setCenter(parent);
    }





}
