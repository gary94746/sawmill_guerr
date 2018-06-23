package controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import modelo.ancho.AnchoArray;
import modelo.ancho.AnchoId;
import modelo.clases_madera.ClaseId;
import modelo.clases_madera.ClasesArray;
import modelo.grueso.GruesoArray;
import modelo.grueso.GruesoId;

import java.net.URL;
import java.util.ResourceBundle;

public class ControlController implements Initializable {

    @FXML
    private JFXComboBox<GruesoId> comboGr;
    @FXML
    private JFXComboBox<AnchoId> comboAnc;
    @FXML
    private JFXComboBox<ClaseId> comboClase;
    @FXML
    private JFXComboBox<String> ComboRegistro;
    @FXML
    private JFXTreeTableView tablaControl;

    private ObservableList lista;






    @Override
    public void initialize(URL location, ResourceBundle resources) {



        ComboRegistro.getItems().addAll("3/4\"", "1 1/2\"", "2\"");
        ComboRegistro.setValue("3/44\"");

        ClasesArray clasesArray = ClasesArray.getInstance();
        clasesArray.getArrayClases().forEach(x -> comboClase.getItems().add(x));

        GruesoArray gruesoArray = GruesoArray.getInstance();
        gruesoArray.getArrayList().forEach(y-> comboGr.getItems().add(y));
        comboGr.setValue(gruesoArray.getArrayList().get(0));


        AnchoArray anchoArray = AnchoArray.getInstance();
        anchoArray.getArrayList().forEach(j-> comboAnc.getItems().add(j));
        comboAnc.setValue(anchoArray.getArrayList().get(0));


    }
}
