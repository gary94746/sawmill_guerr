package controllers;

import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class otrosController implements Initializable {

    @FXML
    private VBox vBoxOtros;


    @FXML
    private VBox vBoxotros;

    @FXML
    private JFXComboBox<String> ComboPz;
    private JFXComboBox<String> comboGr;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ComboPz.getItems().addAll("POL 4X4","POL 3.5X3.5","POL 3X3","BAR 2X4","BAR 1.5X3.5","VIGA 4x4","VIGA 4x6","VIGA 4x8");
        ComboPz.setValue("POL 4X4");





    }
}