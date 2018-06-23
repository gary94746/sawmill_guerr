package controllers;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableRow;
import modelo.rollo.Rollo;

import java.net.URL;
import java.util.ResourceBundle;

public class MaderaEnRolloController implements Initializable {

    @FXML
    private JFXTextField txtD1;

    @FXML
    private JFXTextField txtD2;

    @FXML
    private JFXTreeTableView<Rollo> tabla1;
    private ObservableList<Rollo> list;
    private int count = 1;

    @FXML
    void agregaRollo(ActionEvent event) {
        double a = (Integer.valueOf(txtD1.getText()) + Integer.valueOf(txtD2.getText())) / 2;
        double b = Math.pow(a / 100, 2) * 0.7854 * 2.56;
        list.add(new Rollo(count,Integer.valueOf(txtD1.getText()),Integer.valueOf(txtD2.getText()),a,b));
        txtD1.setText("");
        txtD2.setText("");
        count++;
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
