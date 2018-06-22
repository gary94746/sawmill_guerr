package controllers;

import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ControlController implements Initializable {

    @FXML
    private JFXComboBox<String> comboGr;
    @FXML
    private JFXComboBox<String> comboAnc;
    @FXML
    private JFXComboBox<String> comboClase;
    @FXML
    private JFXComboBox<String> ComboRegistro;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboGr.getItems().addAll("3/4\"","1 1/2\"","2\"");
        comboGr.setValue("3/4\"");

        comboAnc.getItems().addAll("4","6","8","10","12");
        comboAnc.setValue("4");

        comboClase.getItems().addAll("Primera","Segunda","Tercera buena","Tercera mala","Madera Cruzada");
        comboClase.setValue("Primera");

        ComboRegistro.getItems().addAll("3/4\"","1 1/2\"","2\"");
        ComboRegistro.setValue("3/4\"");


    }
}
