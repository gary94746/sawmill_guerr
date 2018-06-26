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
import modelo.otros_madera.otros_mad;

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

    @FXML
    private JFXTreeTableView<otros_mad> tablaOtros;

    private ObservableList<otros_mad> list;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ComboPz.getItems().addAll("POL 4X4","POL 3.5X3.5","POL 3X3","BAR 2X4","BAR 1.5X3.5","VIGA 4x4","VIGA 4x6","VIGA 4x8");
        ComboPz.setValue("POL 4X4");
        list = FXCollections.observableArrayList();
        columns();

    }


    //Declaracion de la tabla y columnas
    //Columna1
    JFXTreeTableColumn<otros_mad, String> Columna1 = new JFXTreeTableColumn<>("OTRAS");
    //Columna2
    JFXTreeTableColumn<otros_mad, Integer> Columna2 = new JFXTreeTableColumn<otros_mad, Integer>("N PIEZA");
    //Columna3
    JFXTreeTableColumn<otros_mad, Double> Columna3 = new JFXTreeTableColumn<>("CUBICACION");
    //Columna4
    JFXTreeTableColumn<otros_mad, Double> Columna4 = new JFXTreeTableColumn<>("PT");

    private void columns() {
        final TreeItem<otros_mad> root = new RecursiveTreeItem<otros_mad>(list, RecursiveTreeObject::getChildren);
        list.forEach(x -> System.out.println(x));
        tablaOtros.setRoot(root);

            //Columna1
            Columna1.setEditable(false);
            Columna1.setCellValueFactory((TreeTableColumn.CellDataFeatures<otros_mad, String> param) -> {
                if (Columna1.validateValue(param)) {
                    return param.getValue().getValue().selPiezaProperty();
                } else
                    return Columna1.getComputedValue(param);

            });
        //Columna2
        Columna2.setEditable(false);
        Columna2.setCellValueFactory((TreeTableColumn.CellDataFeatures<otros_mad, Integer> param) -> {
            if (Columna2.validateValue(param)) {
                return param.getValue().getValue().piezaProperty().asObject();
            } else
                return Columna2.getComputedValue(param);

        });

        //Columna3
        Columna3.setEditable(false);
        Columna3.setCellValueFactory((TreeTableColumn.CellDataFeatures<otros_mad, Double> param) -> {
            if (Columna3.validateValue(param)) {
                return param.getValue().getValue().cubicacionProperty().asObject();
            } else
                return Columna3.getComputedValue(param);

        });

        //Columna4
        Columna4.setEditable(false);
        Columna4.setCellValueFactory((TreeTableColumn.CellDataFeatures<otros_mad, Double> param) -> {
            if (Columna4.validateValue(param)) {
                return param.getValue().getValue().ptProperty().asObject();
            } else
                return Columna4.getComputedValue(param);

        });


        tablaOtros.setEditable(false);
        tablaOtros.setShowRoot(false);
        tablaOtros.getColumns().setAll(Columna1, Columna2,Columna3,Columna4);

    }


}