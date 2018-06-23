package controllers;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import modelo.rollo.Rollo;

import java.net.URL;
import java.util.ResourceBundle;

public class MaderaEnRolloController implements Initializable {

    @FXML
    private JFXTreeTableView<Rollo> tabla1;

    private ObservableList<Rollo> list;

    @FXML
    void agregaRollo(ActionEvent event) {
        System.out.println("Hola");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = FXCollections.observableArrayList();
        columnas();
    }

    // Se establecen las columnas de la tabla.
    private void columnas() {
        final TreeItem<Rollo> root = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
        list.forEach(x -> System.out.println(x));
        tabla1.setRoot(root);

        // Columna de numero de rollo.
        JFXTreeTableColumn<Rollo, Integer> numero = new JFXTreeTableColumn<>("#Numero");
        numero.setEditable(false);

        numero.setCellValueFactory((TreeTableColumn.CellDataFeatures<Rollo, Integer> param) -> {
            if (numero.validateValue(param))
                return param.getValue().getValue().numProperty().asObject();
            else
                return numero.getComputedValue(param);
        });

        // Columna diametro1.
        JFXTreeTableColumn<Rollo, Double> diametro1 = new JFXTreeTableColumn<>("Diametro 1");
        diametro1.setEditable(false);

        diametro1.setCellValueFactory((TreeTableColumn.CellDataFeatures<Rollo, Double> param) -> {
            if (diametro1.validateValue(param))
                return param.getValue().getValue().d1Property().asObject();
            else
                return diametro1.getComputedValue(param);
        });

        // Columna diametro2.
        JFXTreeTableColumn<Rollo, Double> diametro2 = new JFXTreeTableColumn<>("Diametro 2");
        diametro2.setEditable(false);

        diametro2.setCellValueFactory((TreeTableColumn.CellDataFeatures<Rollo, Double> param) -> {
            if (diametro2.validateValue(param))
                return param.getValue().getValue().d2Property().asObject();
            else
                return diametro2.getComputedValue(param);
        });

        // Columna de diametro promedio.
        JFXTreeTableColumn<Rollo, Double> diametropromedio = new JFXTreeTableColumn<>("Diametro promedio");
        diametropromedio.setEditable(false);

        diametropromedio.setCellValueFactory((TreeTableColumn.CellDataFeatures<Rollo, Double> param) -> {
            if (diametropromedio.validateValue(param))
                return param.getValue().getValue().dtProperty().asObject();
            else
                return diametropromedio.getComputedValue(param);
        });

        // Columna de volumen.
        JFXTreeTableColumn<Rollo, Double> volumen = new JFXTreeTableColumn<>("Volumen");
        volumen.setEditable(false);

        volumen.setCellValueFactory((TreeTableColumn.CellDataFeatures<Rollo, Double> param) -> {
            if (volumen.validateValue(param))
                return param.getValue().getValue().volProperty().asObject();
            else
                return volumen.getComputedValue(param);
        });

        //Operaciones con la tabla
        tabla1.setEditable(false);
        tabla1.setShowRoot(false);
        tabla1.getColumns().setAll(numero, diametro1, diametro2, diametropromedio, volumen);
    }

}
