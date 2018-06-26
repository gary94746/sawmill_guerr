package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.VBox;
import modelo.Conexion;
import modelo.resumen.Resumen;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ResumenController implements Initializable {

    @FXML private JFXButton btnHistorial;
    @FXML private JFXButton btnImprimir;
    @FXML private JFXTreeTableView<Resumen> treTable;

    private ObservableList<Resumen> list;
    private Conexion conexion = Conexion.getInstance();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Tooltips
        btnImprimir.setTooltip(new Tooltip("Imprimir en pdf"));
        btnHistorial.setTooltip(new Tooltip("Ver el historial"));

        list = FXCollections.observableArrayList();
        columns();

        //lista
        conexion.establecerConexion();
        Resumen.get_data(conexion.getConection(), list, "2016-03-01","2018-03-08");
        conexion.cerrarConexion();

    }


    private void columns() {
        final TreeItem<Resumen> root = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
        treTable.setRoot(root);

        JFXTreeTableColumn<Resumen, String> clmMedida = new JFXTreeTableColumn<>("Medida");
        JFXTreeTableColumn<Resumen, Double> clmPrimera = new JFXTreeTableColumn<>("Primera");
        JFXTreeTableColumn<Resumen, Double> clmSegunda = new JFXTreeTableColumn<>("Segunda");
        JFXTreeTableColumn<Resumen, Double> clmTerceraBuena = new JFXTreeTableColumn<>("Tercera Buena");
        JFXTreeTableColumn<Resumen, Double> clmTerceraMala = new JFXTreeTableColumn<>("Tercera Mala");
        JFXTreeTableColumn<Resumen, Double> clmMaderaCruzada = new JFXTreeTableColumn<>("Madera Cruzada");
        JFXTreeTableColumn<Resumen, Double> clmCuadrado = new JFXTreeTableColumn<>("Cuadrado");
        JFXTreeTableColumn<Resumen, Double> clmViga = new JFXTreeTableColumn<>("Viga");
        JFXTreeTableColumn<Resumen, Double> clmTotal = new JFXTreeTableColumn<>("Total");

        clmMedida.setCellValueFactory((TreeTableColumn.CellDataFeatures<Resumen, String> param) -> {
            if (clmMedida.validateValue(param))
                return param.getValue().getValue().medidaProperty();
            else
                return clmMedida.getComputedValue(param);
        });

        clmPrimera.setCellValueFactory((TreeTableColumn.CellDataFeatures<Resumen, Double> param) -> {
            if (clmPrimera.validateValue(param))
                return param.getValue().getValue().primeraProperty().asObject();
            else
                return clmPrimera.getComputedValue(param);
        });

        clmSegunda.setCellValueFactory((TreeTableColumn.CellDataFeatures<Resumen, Double> param) -> {
            if (clmSegunda.validateValue(param))
                return param.getValue().getValue().segundaProperty().asObject();
            else
                return clmSegunda.getComputedValue(param);
        });

        clmTerceraBuena.setCellValueFactory((TreeTableColumn.CellDataFeatures<Resumen, Double> param) -> {
            if (clmTerceraBuena.validateValue(param))
                return param.getValue().getValue().tercera_buenaProperty().asObject();
            else
                return clmTerceraBuena.getComputedValue(param);
        });

        clmTerceraMala.setCellValueFactory((TreeTableColumn.CellDataFeatures<Resumen, Double> param) -> {
            if (clmTerceraMala.validateValue(param))
                return param.getValue().getValue().tercera_malaProperty().asObject();
            else
                return clmTerceraMala.getComputedValue(param);
        });

        clmMaderaCruzada.setCellValueFactory((TreeTableColumn.CellDataFeatures<Resumen, Double> param) -> {
            if (clmMaderaCruzada.validateValue(param))
                return param.getValue().getValue().madera_cruzadaProperty().asObject();
            else
                return clmMaderaCruzada.getComputedValue(param);
        });

        clmCuadrado.setCellValueFactory((TreeTableColumn.CellDataFeatures<Resumen, Double> param) -> {
            if (clmCuadrado.validateValue(param))
                return param.getValue().getValue().cuadradoProperty().asObject();
            else
                return clmCuadrado.getComputedValue(param);
        });

        clmViga.setCellValueFactory((TreeTableColumn.CellDataFeatures<Resumen, Double> param) -> {
            if (clmViga.validateValue(param))
                return param.getValue().getValue().vigaProperty().asObject();
            else
                return clmViga.getComputedValue(param);
        });

        clmTotal.setCellValueFactory((TreeTableColumn.CellDataFeatures<Resumen, Double> param) -> {
            if (clmTotal.validateValue(param))
                return param.getValue().getValue().totalProperty().asObject();
            else
                return clmTotal.getComputedValue(param);
        });

        //Operaciones con la tabla
        treTable.setEditable(false);
        treTable.setShowRoot(false);
        treTable.getColumns().setAll(clmMedida, clmPrimera, clmSegunda, clmTerceraBuena, clmTerceraMala, clmMaderaCruzada, clmCuadrado, clmViga, clmTotal);
    }

    @FXML
    void historial(ActionEvent event) {
        System.out.println("Esta en el historial");
    }

    @FXML
    void imprimir(ActionEvent event) {
        System.out.println("Imprimio en pdf");
    }

}