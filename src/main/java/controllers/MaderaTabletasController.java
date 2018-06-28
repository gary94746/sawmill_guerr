package controllers;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Conexion;
import modelo.rollo.Rollo;
import modelo.tabletas.Tabletas;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MaderaTabletasController implements Initializable {

    @FXML
    private JFXTreeTableView<Tabletas> tabla2;

    private ObservableList<Tabletas> list;
    private double cubos;
    private double pt;
    private String concatena;

    @FXML
    private JFXComboBox<String> comboLongitud;

    @FXML
    private JFXButton btnAgregar;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnEliminar;

    @FXML
    private JFXButton btnSubtotales;

    @FXML
    private JFXButton btnBuscar;

    @FXML
    private JFXButton btnRegreso;

    @FXML
    private JFXDatePicker fechaTableta;

    @FXML
    private JFXComboBox<String> comboGrueso;

    @FXML
    private JFXComboBox<String> comboAncho;

    @FXML
    private JFXTextField txtPiezas;

    @FXML
    private Label lblTitulo;

    private Conexion conexion = Conexion.getInstance();


    @FXML
    void agregaTableta(ActionEvent event) {
        asignarCubicacion(comboGrueso.getSelectionModel().getSelectedItem(), Double.parseDouble(comboAncho.getSelectionModel().getSelectedItem()), Double.parseDouble(comboLongitud.getSelectionModel().getSelectedItem()));
        calcularPt(cubos, Integer.parseInt(txtPiezas.getText()));
        gruesoAncho(comboGrueso.getSelectionModel().getSelectedItem(), comboAncho.getSelectionModel().getSelectedItem());
        agregarRegistro(null);
    }

    @FXML
    void buscarFechaTableta(ActionEvent event) {
        var datePicker1 = fechaTableta.getValue().getYear() + "-" + fechaTableta.getValue().getMonthValue()+ "-" + fechaTableta.getValue().getDayOfMonth();
        lblTitulo.setText("Historia del: " +
                fechaTableta.getValue().getDayOfMonth() + "/" + fechaTableta.getValue().getMonth()+ "/" + fechaTableta.getValue().getYear()
        );

        cargarDatos(datePicker1);

        comboGrueso.setDisable(true);
        comboAncho.setDisable(true);
        comboLongitud.setDisable(true);
        txtPiezas.setDisable(true);
        btnAgregar.setDisable(true);
        btnEditar.setDisable(true);
        btnEliminar.setDisable(true);
        btnSubtotales.setDisable(true);
    }

    @FXML
    void returnFechaTableta(ActionEvent event) {
        list.removeIf(x -> true);

        conexion.establecerConexion();
        Tabletas.obtenerDatos(conexion.getConection(), list);
        conexion.cerrarConexion();

        lblTitulo.setText("Control de produccion de madera aserrada");

        comboGrueso.setDisable(false);
        comboAncho.setDisable(false);
        comboLongitud.setDisable(false);
        txtPiezas.setDisable(false);
        btnAgregar.setDisable(false);
        btnEditar.setDisable(false);
        btnEliminar.setDisable(false);
        btnSubtotales.setDisable(false);
    }

    private void cargarDatos(String datePicker) {
        list.removeIf(x -> true);
        conexion.establecerConexion();
        Tabletas.historial(conexion.getConection(), list, datePicker);
        conexion.cerrarConexion();
    }

    @FXML
    void eliminaTableta(ActionEvent event) {
        int row = tabla2.getSelectionModel().getSelectedItem().getValue().getId();
        conexion.establecerConexion();
        Tabletas.eliminarTableta(conexion.getConection(), row);
        conexion.cerrarConexion();
        list.removeIf(x -> x.getId() == row);
    }

    @FXML
    void despegar(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/views/extraview.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/servicio02.png")));
        stage.setTitle("Menu principal");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = FXCollections.observableArrayList();
        comboLongitud.getItems().addAll("2", "3", "4", "5", "6", "7");
        comboLongitud.setValue("2");
        comboGrueso.getItems().addAll("3/4", "1 1/2");
        comboGrueso.setValue("3/4");
        comboAncho.getItems().addAll("4", "6", "8", "10", "12");
        comboAncho.setValue("4");

        conexion.establecerConexion();
        Tabletas.obtenerDatos(conexion.getConection(), list);
        conexion.cerrarConexion();
        columnas();

        btnAgregar.setTooltip(new Tooltip("Agregar"));
        btnBuscar.setTooltip(new Tooltip("Buscar"));
        btnEditar.setTooltip(new Tooltip("Editar"));
        btnEliminar.setTooltip(new Tooltip("Eliminar"));
        btnRegreso.setTooltip(new Tooltip("Regrese al dia actual"));
        btnSubtotales.setTooltip(new Tooltip("Despliega los subtotales"));
    }

    private void columnas() {
        final TreeItem<Tabletas> root = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
        tabla2.setRoot(root);

        // Columna de grueso por ancho.
        JFXTreeTableColumn<Tabletas, String> gruesoporancho = new JFXTreeTableColumn<>("Grueso por ancho");
        gruesoporancho.setEditable(false);

        gruesoporancho.setCellValueFactory((TreeTableColumn.CellDataFeatures<Tabletas, String> param) -> {
            if (gruesoporancho.validateValue(param))
                return param.getValue().getValue().gruesoxanchoProperty();
            else
                return gruesoporancho.getComputedValue(param);
        });

        // Columna de piezas.
        JFXTreeTableColumn<Tabletas, Integer> piezas = new JFXTreeTableColumn<>("Piezas");
        piezas.setEditable(false);

        piezas.setCellValueFactory((TreeTableColumn.CellDataFeatures<Tabletas, Integer> param) -> {
            if (piezas.validateValue(param))
                return param.getValue().getValue().piezasProperty().asObject();
            else
                return piezas.getComputedValue(param);
        });

        // Columna de cubicacion.
        JFXTreeTableColumn<Tabletas, Double> cubicacion = new JFXTreeTableColumn<>("Cubicacion");
        cubicacion.setEditable(false);

        cubicacion.setCellValueFactory((TreeTableColumn.CellDataFeatures<Tabletas, Double> param) -> {
            if (cubicacion.validateValue(param))
                return param.getValue().getValue().cubicacionProperty().asObject();
            else
                return cubicacion.getComputedValue(param);
        });

        // Columna de pies-tabla.
        JFXTreeTableColumn<Tabletas, Double> piestabla = new JFXTreeTableColumn<>("Pies - Tabla");
        piestabla.setEditable(false);

        piestabla.setCellValueFactory((TreeTableColumn.CellDataFeatures<Tabletas, Double> param) -> {
            if (piestabla.validateValue(param))
                return param.getValue().getValue().piestablaProperty().asObject();
            else
                return piestabla.getComputedValue(param);
        });

        // Columna de grueso por ancho.
        JFXTreeTableColumn<Tabletas, Double> longitud = new JFXTreeTableColumn<>("Longitud");
        longitud.setEditable(false);

        longitud.setCellValueFactory((TreeTableColumn.CellDataFeatures<Tabletas, Double> param) -> {
            if (longitud.validateValue(param))
                return param.getValue().getValue().longitudProperty().asObject();
            else
                return longitud.getComputedValue(param);
        });

        //Operaciones con la tabla
        tabla2.setEditable(false);
        tabla2.setShowRoot(false);
        tabla2.getColumns().setAll(gruesoporancho, piezas, cubicacion, piestabla, longitud);
    }

    public void asignarCubicacion(String grueso, double ancho, double longitud){
        double valorGrueso = 0;

        if (grueso == "3/4") {
            valorGrueso = 0.75;
        } else if (grueso == "1 1/2") {
            valorGrueso = 1.5;
        }

        cubos = (valorGrueso * ancho * longitud)/12;
    }

    public double calcularPt(double cubicacion, int piezas) {
        System.out.println(piezas);
        return cubicacion * piezas;
    }

    public void gruesoAncho(String grueso, String ancho) {
        concatena = grueso + " x " + ancho;
    }

    public void agregarRegistro(Rollo x){
        conexion.establecerConexion();
        var newTableta = Tabletas.addTableta(conexion.getConection(), concatena, Integer.parseInt(txtPiezas.getText()), cubos, calcularPt(cubos, Integer.parseInt(txtPiezas.getText())), Integer.parseInt(comboLongitud.getSelectionModel().getSelectedItem()));
        conexion.cerrarConexion();
        if (newTableta != null) {
            list.add(newTableta);
        }
    }
}
