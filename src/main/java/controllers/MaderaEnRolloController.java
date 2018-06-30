package controllers;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controllers.utils.Validators;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import modelo.Conexion;
import modelo.rollo.Rollo;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ResourceBundle;

public class MaderaEnRolloController implements Initializable {

    @FXML
    private JFXTextField txtD1;

    @FXML
    private JFXTextField txtD2;

    @FXML
    private JFXTextField volumenTotal;

    @FXML
    private JFXDatePicker fecha;

    @FXML
    private JFXButton botonAgregar;

    @FXML
    private JFXButton botonEliminar;

    @FXML
    private JFXButton botonBuscar;

    @FXML
    private JFXButton botonFechaActual;

    @FXML
    private Label lblTitulo;

    @FXML
    private JFXTreeTableView<Rollo> tabla1;
    private ObservableList<Rollo> list;

    private int count = 1;

    private Conexion conexion = Conexion.getInstance();


    @FXML
    void agregaRollo(ActionEvent event) {
            agregarRegistro(null);
            txtD1.setText("");
            txtD2.setText("");
            count++;

            var total = list.parallelStream().mapToDouble(Rollo::getVol).sum();
            volumenTotal.setText(format3Decimals(total) + "");
    }

    @FXML
    void returnFecha(ActionEvent event) {
        list.removeIf(x -> true);

        conexion.establecerConexion();
        Rollo.obtenerDatos(conexion.getConection(), list);
        conexion.cerrarConexion();

        txtD1.setDisable(false);
        txtD2.setDisable(false);
        botonAgregar.setDisable(false);
        botonEliminar.setDisable(false);

        var total = list.parallelStream().mapToDouble(Rollo::getVol).sum();
        volumenTotal.setText(format3Decimals(total) + "");

        lblTitulo.setText("Control de transformacion de madera en rollo.");

        fecha.getEditor().clear();
    }

    @FXML
    void eliminaRollo(ActionEvent event) {
        int row = tabla1.getSelectionModel().getSelectedItem().getValue().getId();
        conexion.establecerConexion();
        Rollo.eliminarRollo(conexion.getConection(), row);
        conexion.cerrarConexion();
        list.removeIf(x -> x.getId() == row);

        var total = list.parallelStream().mapToDouble(Rollo::getVol).sum();
        volumenTotal.setText(format3Decimals(total) + "");
    }

    @FXML
    void buscarFecha(ActionEvent event) {
        var datePicker1 = fecha.getValue().getYear() + "-" + fecha.getValue().getMonthValue()+ "-" + fecha.getValue().getDayOfMonth();
        lblTitulo.setText("Historia del: " + fecha.getValue().getDayOfMonth() + "/" + fecha.getValue().getMonth() + "/" + fecha.getValue().getYear()
        );

        cargarDatos(datePicker1);

        txtD2.setDisable(true);
        txtD1.setDisable(true);
        botonAgregar.setDisable(true);
        botonEliminar.setDisable(true);

        var total = list.parallelStream().mapToDouble(Rollo::getVol).sum();
        volumenTotal.setText(format3Decimals(total) + "");
    }

    private void cargarDatos(String datePicker) {
        list.removeIf(x -> true);
        conexion.establecerConexion();
        Rollo.historial(conexion.getConection(), list, datePicker);
        conexion.cerrarConexion();
    }

    private double format3Decimals(double numero) {
        NumberFormat d = new DecimalFormat("#0.000");
        var f = d.format(numero);
        return Double.parseDouble(f);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = FXCollections.observableArrayList();
        columnas();
        conexion.establecerConexion();
        Rollo.obtenerDatos(conexion.getConection(), list);
        conexion.cerrarConexion();

        var total = list.parallelStream().mapToDouble(Rollo::getVol).sum();
        volumenTotal.setText(format3Decimals(total) + "");

        txtD1.addEventFilter(KeyEvent.ANY, Validators.onlyNumbers());

        botonAgregar.setTooltip(new Tooltip("Agregar"));
        botonBuscar.setTooltip(new Tooltip("Buscar"));
        botonEliminar.setTooltip(new Tooltip("Eliminar"));
        botonFechaActual.setTooltip(new Tooltip("Regrese al dia actual"));
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

    public void agregarRegistro(Rollo x){
            conexion.establecerConexion();
            var newRollo = Rollo.addRollo(conexion.getConection(), count, Double.parseDouble(txtD1.getText()), Double.parseDouble(txtD2.getText()));
            conexion.cerrarConexion();
            if (newRollo != null) {
                list.add(newRollo);
            }
    }
}
