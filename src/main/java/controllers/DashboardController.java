package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private BorderPane borderDashboard;

    //Parent
    private Parent parentResumen;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            parentResumen = FXMLLoader.load(getClass().getResource("/views/resumen.fxml"));
            borderDashboard.setCenter(parentResumen);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void setResumen(MouseEvent event) throws IOException {
        borderDashboard.setCenter(parentResumen);
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

    @FXML
    void setTableta(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/views/controlproduccion2.fxml"));
        borderDashboard.setCenter(parent);
    }

    @FXML
    void setTransformacion(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/views/controltransformacion.fxml"));
        borderDashboard.setCenter(parent);
    }

}
