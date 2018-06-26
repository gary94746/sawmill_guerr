package controllers;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import modelo.Conexion;
import modelo.resumen.Resumen;
import modelo.rollo.Rollo;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class ResumenController implements Initializable {

    @FXML private JFXButton btnImprimir;
    @FXML private JFXButton btnBuscar;
    @FXML private JFXTreeTableView<Resumen> treTable;
    @FXML private JFXDatePicker date1;
    @FXML private JFXDatePicker date2;
    @FXML private TextField txtRollo;
    @FXML private TextField txtVolA;
    @FXML private TextField txtCofA;


    private ObservableList<Resumen> list;
    private Conexion conexion = Conexion.getInstance();

    @FXML private Label lblResumen;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Tooltips
        btnImprimir.setTooltip(new Tooltip("Imprimir en pdf"));
        btnBuscar.setTooltip(new Tooltip("Buscar"));

        //Resumen actual
        //Fecha actual
        var dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        var dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        lblResumen.setText("Resumen del: " + dateFormat.format(new Date()));

        list = FXCollections.observableArrayList();
        columns();

        var suma = list.parallelStream().mapToDouble(Resumen::getTotal).sum();
        System.out.println(suma);

        //lista
        conexion.establecerConexion();
        Resumen.get_data(conexion.getConection(), list, dateFormat1.format(new Date()),dateFormat1.format(new Date()));
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
    void buscar(ActionEvent event) {
        var datePicker1 = date1.getValue().getYear() + "-" + date1.getValue().getMonthValue()+ "-" + date1.getValue().getDayOfMonth();
        var datePicker2 = date2.getValue().getYear() + "-" + date2.getValue().getMonthValue()+ "-" + date2.getValue().getDayOfMonth();

        list.removeIf(x -> true);
        conexion.establecerConexion();
        Resumen.get_data(conexion.getConection(), list, datePicker1, datePicker2);
        var volRollos = Rollo.getVolumenRollosFecha(conexion.getConection(), datePicker1, datePicker2);
        conexion.cerrarConexion();

        lblResumen.setText("Resumen del: " +
                date1.getValue().getDayOfMonth() + "/" + date1.getValue().getMonth()+ "/" + date1.getValue().getYear() + " al: " +
                date2.getValue().getDayOfMonth() + "/" + date2.getValue().getMonth()+ "/" + date2.getValue().getYear());


        var suma = list.parallelStream().mapToDouble(Resumen::getTotal).sum() * 0.00236;
        NumberFormat d = new DecimalFormat("#0.000");
        txtVolA.setText(d.format(suma));
        txtRollo.setText(volRollos +"");
        txtCofA.setText(d.format((suma/volRollos)*100));
    }

    @FXML
    void imprimir(ActionEvent event) {
        System.out.println("Imprimio en pdf");
    }

}