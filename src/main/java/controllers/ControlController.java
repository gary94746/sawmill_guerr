package controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.VBox;
import modelo.ancho.AnchoArray;
import modelo.ancho.AnchoId;
import modelo.clases_madera.ClaseId;
import modelo.clases_madera.ClasesArray;
import modelo.grueso.GruesoArray;
import modelo.grueso.GruesoId;
import modelo.Control_madera.madera_control;

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
    @FXML
    private JFXTreeTableView <madera_control> tablaControl;


    private ObservableList<madera_control> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        ComboRegistro.getItems().addAll("3/4\"", "1 1/2\"", "2\"");
        ComboRegistro.setValue("3/4\"");

        comboGr.getItems().addAll("3/4\"", "1 1/2\"", "2\"");
        comboGr.setValue("3/4\"");

        comboAnc.getItems().addAll("4","6","8","10","12");
        comboAnc.setValue("4");

        comboClase.getItems().addAll("PRIMERA","SEGUNDA","TERCERA BUENA","TERCERA MALA","MADERA CRUZADA");
        comboClase.setValue("PRIMERA");



        /**ClasesArray clasesArray = ClasesArray.getInstance();
        clasesArray.getArrayClases().forEach(x -> comboClase.getItems().add(x));

        GruesoArray gruesoArray = GruesoArray.getInstance();
        gruesoArray.getArrayList().forEach(y-> comboGr.getItems().add(y));
        comboGr.setValue(gruesoArray.getArrayList().get(0));


        AnchoArray anchoArray = AnchoArray.getInstance();
        anchoArray.getArrayList().forEach(j-> comboAnc.getItems().add(j));
        comboAnc.setValue(anchoArray.getArrayList().get(0));*/
        list = FXCollections.observableArrayList();
        columns();

    }


    private void columns() {


        final TreeItem<madera_control> root = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
        list.forEach(x -> System.out.println(x));
        tablaControl.setRoot(root);


        //INICIO COLUMNA 1
        JFXTreeTableColumn<madera_control, Double> Columna1 = new JFXTreeTableColumn<>("PRIMERA");
        Columna1.setEditable(false);

        Columna1.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
            if (Columna1.validateValue(param)) {
                return param.getValue().getValue().gruesoProperty().asObject();
            } else
                return Columna1.getComputedValue(param);
        });

        //subcolumna 1
        JFXTreeTableColumn<madera_control, Double> subcolumna1 = new JFXTreeTableColumn<>("PIEZAS");
        subcolumna1.setEditable(false);

        subcolumna1.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
            if (subcolumna1.validateValue(param)) {
                return param.getValue().getValue().gruesoProperty().asObject();
            } else
                return subcolumna1.getComputedValue(param);
        });

        //subcolumna 2
        JFXTreeTableColumn<madera_control, Double> subcolumna2 = new JFXTreeTableColumn<>("CUB");
        subcolumna2.setEditable(false);

        subcolumna2.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
            if (subcolumna2.validateValue(param)) {
                return param.getValue().getValue().gruesoProperty().asObject();
            } else
                return subcolumna2.getComputedValue(param);
        });


        //subcolumna 3
        JFXTreeTableColumn<madera_control, Double> subcolumna3 = new JFXTreeTableColumn<>("PT");
        subcolumna3.setEditable(false);

        subcolumna3.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
            if (subcolumna3.validateValue(param)) {
                return param.getValue().getValue().gruesoProperty().asObject();
            } else
                return subcolumna3.getComputedValue(param);
        });
          //FIN DE LA COLUMNA 1

        //INICIO COLUMNA 2
        JFXTreeTableColumn<madera_control, Double> Columna2 = new JFXTreeTableColumn<>("SEGUNDA");
        Columna2.setEditable(false);

        Columna2.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
            if (Columna2.validateValue(param)) {
                return param.getValue().getValue().gruesoProperty().asObject();
            } else
                return Columna2.getComputedValue(param);
        });

        //subcolumna 4
        JFXTreeTableColumn<madera_control, Double> subcolumna4 = new JFXTreeTableColumn<>("PIEZAS");
        subcolumna4.setEditable(false);

        subcolumna4.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
            if (subcolumna4.validateValue(param)) {
                return param.getValue().getValue().gruesoProperty().asObject();
            } else
                return subcolumna4.getComputedValue(param);
        });

        //subcolumna 5
        JFXTreeTableColumn<madera_control, Double> subcolumna5 = new JFXTreeTableColumn<>("CUB");
        subcolumna5.setEditable(false);

        subcolumna5.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
            if (subcolumna5.validateValue(param)) {
                return param.getValue().getValue().gruesoProperty().asObject();
            } else
                return subcolumna5.getComputedValue(param);
        });


        //subcolumna 3
        JFXTreeTableColumn<madera_control, Double> subcolumna6 = new JFXTreeTableColumn<>("PT");
        subcolumna6.setEditable(false);

        subcolumna6.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
            if (subcolumna6.validateValue(param)) {
                return param.getValue().getValue().gruesoProperty().asObject();
            } else
                return subcolumna6.getComputedValue(param);
        });
        //FIN DE LA COLUMNA 2

        //INICIO COLUMNA 3
        JFXTreeTableColumn<madera_control, Double> Columna3 = new JFXTreeTableColumn<>("TERCERA BUENA");
        Columna3.setEditable(false);

        Columna3.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
            if (Columna3.validateValue(param)) {
                return param.getValue().getValue().gruesoProperty().asObject();
            } else
                return Columna3.getComputedValue(param);
        });

        //subcolumna 4
        JFXTreeTableColumn<madera_control, Double> subcolumna7 = new JFXTreeTableColumn<>("PIEZAS");
        subcolumna7.setEditable(false);

        subcolumna7.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
            if (subcolumna7.validateValue(param)) {
                return param.getValue().getValue().gruesoProperty().asObject();
            } else
                return subcolumna7.getComputedValue(param);
        });

        //subcolumna 5
        JFXTreeTableColumn<madera_control, Double> subcolumna8 = new JFXTreeTableColumn<>("CUB");
        subcolumna8.setEditable(false);

        subcolumna8.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
            if (subcolumna8.validateValue(param)) {
                return param.getValue().getValue().gruesoProperty().asObject();
            } else
                return subcolumna8.getComputedValue(param);
        });


        //subcolumna 3
        JFXTreeTableColumn<madera_control, Double> subcolumna9 = new JFXTreeTableColumn<>("PT");
        subcolumna9.setEditable(false);

        subcolumna9.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
            if (subcolumna9.validateValue(param)) {
                return param.getValue().getValue().gruesoProperty().asObject();
            } else
                return subcolumna9.getComputedValue(param);
        });
        //FIN DE LA COLUMNA 3

        //INICIO COLUMNA 5
        JFXTreeTableColumn<madera_control, Double> Columna5 = new JFXTreeTableColumn<>("MADERA CRUZADA");
        Columna5.setEditable(false);

        Columna5.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
            if (Columna5.validateValue(param)) {
                return param.getValue().getValue().gruesoProperty().asObject();
            } else
                return Columna5.getComputedValue(param);
        });

        //subcolumna 4
        JFXTreeTableColumn<madera_control, Double> subcolumna13 = new JFXTreeTableColumn<>("PIEZAS");
        subcolumna13.setEditable(false);

        subcolumna13.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
            if (subcolumna13.validateValue(param)) {
                return param.getValue().getValue().gruesoProperty().asObject();
            } else
                return subcolumna13.getComputedValue(param);
        });

        //subcolumna 5
        JFXTreeTableColumn<madera_control, Double> subcolumna14 = new JFXTreeTableColumn<>("CUB");
        subcolumna14.setEditable(false);

        subcolumna14.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
            if (subcolumna14.validateValue(param)) {
                return param.getValue().getValue().gruesoProperty().asObject();
            } else
                return subcolumna14.getComputedValue(param);
        });


        //subcolumna 3
        JFXTreeTableColumn<madera_control, Double> subcolumna15 = new JFXTreeTableColumn<>("PT");
        subcolumna15.setEditable(false);

        subcolumna15.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
            if (subcolumna15.validateValue(param)) {
                return param.getValue().getValue().gruesoProperty().asObject();
            } else
                return subcolumna15.getComputedValue(param);
        });
        //FIN DE LA COLUMNA 4

        //INICIO COLUMNA 4
        JFXTreeTableColumn<madera_control, Double> Columna4 = new JFXTreeTableColumn<>("TERCERA MALA");
        Columna4.setEditable(false);

        Columna4.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
            if (Columna4.validateValue(param)) {
                return param.getValue().getValue().gruesoProperty().asObject();
            } else
                return Columna4.getComputedValue(param);
        });

        //subcolumna 4
        JFXTreeTableColumn<madera_control, Double> subcolumna10 = new JFXTreeTableColumn<>("PIEZAS");
        subcolumna10.setEditable(false);

        subcolumna10.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
            if (subcolumna10.validateValue(param)) {
                return param.getValue().getValue().gruesoProperty().asObject();
            } else
                return subcolumna10.getComputedValue(param);
        });

        //subcolumna 5
        JFXTreeTableColumn<madera_control, Double> subcolumna11 = new JFXTreeTableColumn<>("CUB");
        subcolumna11.setEditable(false);

        subcolumna11.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
            if (subcolumna11.validateValue(param)) {
                return param.getValue().getValue().gruesoProperty().asObject();
            } else
                return subcolumna11.getComputedValue(param);
        });


        //subcolumna 3
        JFXTreeTableColumn<madera_control, Double> subcolumna12 = new JFXTreeTableColumn<>("PT");
        subcolumna12.setEditable(false);

        subcolumna12.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
            if (subcolumna12.validateValue(param)) {
                return param.getValue().getValue().gruesoProperty().asObject();
            } else
                return subcolumna12.getComputedValue(param);
        });
        //FIN DE LA COLUMNA 4


        //Operaciones con la tabla
        tablaControl.setEditable(false);
        tablaControl.setShowRoot(false);
        tablaControl.getColumns().setAll(Columna1, Columna2,Columna3,Columna4,Columna5);
        Columna1.getColumns().setAll(subcolumna1,subcolumna2,subcolumna3);
        Columna2.getColumns().setAll(subcolumna4,subcolumna5,subcolumna6);
        Columna3.getColumns().setAll(subcolumna7,subcolumna8,subcolumna9);
        Columna4.getColumns().setAll(subcolumna10,subcolumna11,subcolumna12);
        Columna5.getColumns().setAll(subcolumna13,subcolumna14,subcolumna15);



    }
}
