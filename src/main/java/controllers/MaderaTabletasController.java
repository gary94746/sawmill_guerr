package controllers;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controllers.utils.Messages;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Conexion;
import modelo.extra.Extra;
import modelo.rollo.Rollo;
import modelo.tabletas.Tabletas;
import tray.notification.NotificationType;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MaderaTabletasController implements Initializable {

    private ObservableList<Tabletas> list;
    private double cubos;
    private double pt;
    private String concatena;
    public static Extra subtotales;
    public static Double totalFinal;

    @FXML private JFXTreeTableView<Tabletas> tabla2;
    @FXML private JFXComboBox<String> comboLongitud;
    @FXML private JFXComboBox<String> comboFiltro;
    @FXML private JFXButton btnAgregar;
    @FXML private JFXButton btnEliminar;
    @FXML private JFXButton btnSubtotales;
    @FXML private JFXButton btnBuscar;
    @FXML private JFXButton btnRegreso;
    @FXML private JFXDatePicker fechaTableta;
    @FXML private JFXComboBox<String> comboGrueso;
    @FXML private JFXComboBox<String> comboAncho;
    @FXML private JFXTextField txtPiezas;
    @FXML private Label lblTitulo;

    private Conexion conexion = Conexion.getInstance();

    @FXML
    void agregaTableta(ActionEvent event) {
        try {
            asignarCubicacion(comboGrueso.getSelectionModel().getSelectedItem(), Double.parseDouble(comboAncho.getSelectionModel().getSelectedItem()), Double.parseDouble(comboLongitud.getSelectionModel().getSelectedItem()));
            calcularPt(cubos, Integer.parseInt(txtPiezas.getText()));
            gruesoAncho(comboGrueso.getSelectionModel().getSelectedItem(), comboAncho.getSelectionModel().getSelectedItem());
            agregarRegistro(null);
            txtPiezas.setText("");
            Messages.setMessage("Agregado", "Se han agregado las piezas", NotificationType.SUCCESS);

        } catch (NumberFormatException e) {
            //e.printStackTrace();
            Messages.setMessage("Error.", "No se agrego un numero de piezas.", NotificationType.ERROR);
        }
    }

    @FXML
    void buscarFechaTableta(ActionEvent event) {
        try {
            var datePicker1 = fechaTableta.getValue().getYear() + "-" + fechaTableta.getValue().getMonthValue() + "-" + fechaTableta.getValue().getDayOfMonth();
            lblTitulo.setText("Registro del: " +
                    fechaTableta.getValue().getDayOfMonth() + "/" + fechaTableta.getValue().getMonth() + "/" + fechaTableta.getValue().getYear()
            );

            cargarDatos(datePicker1);

            comboGrueso.setDisable(true);
            comboAncho.setDisable(true);
            comboLongitud.setDisable(true);
            txtPiezas.setDisable(true);
            btnAgregar.setDisable(true);
            btnEliminar.setDisable(true);
            //btnSubtotales.setDisable(true);

            comboFiltro.setValue("Todos");
            subTotales();

            Messages.setMessage("Registros", "Ahora visualiza una fecha distinta a la actual", NotificationType.SUCCESS);

        } catch (Exception e) {
            Messages.setMessage("Error", "No selecciono una fecha", NotificationType.ERROR);

        }

    }

    @FXML
    void returnFechaTableta(ActionEvent event) {
        list.removeIf(x -> true);

        conexion.establecerConexion();
        Tabletas.obtenerDatos(conexion.getConection(), list);
        conexion.cerrarConexion();

        lblTitulo.setText("Control de produccion de madera aserrada. [Tabletas]");

        comboGrueso.setDisable(false);
        comboAncho.setDisable(false);
        comboLongitud.setDisable(false);
        txtPiezas.setDisable(false);
        btnAgregar.setDisable(false);
        //btnSubtotales.setDisable(false);
        btnEliminar.setDisable(false);

        fechaTableta.getEditor().clear();
        fechaTableta.setValue(null);
        comboFiltro.setValue("Todos");
        subTotales();

        Messages.setMessage("Dia actual", "Ahora visualiza la fecha actual", NotificationType.SUCCESS);
    }

    @FXML
    void eliminaTableta(ActionEvent event) {
        try {
            int row = tabla2.getSelectionModel().getSelectedItem().getValue().getId();
            conexion.establecerConexion();
            Tabletas.eliminarTableta(conexion.getConection(), row);
            conexion.cerrarConexion();
            list.removeIf(x -> x.getId() == row);
            Messages.setMessage("Se elimino", "La tableta se elimino satisfactoriamente", NotificationType.SUCCESS);

        } catch (Exception e) {
            Messages.setMessage("Error", "No se selecciono una fila", NotificationType.ERROR);
        }
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
        stage.setResizable(false);
    }

    @FXML
    void filtrados(ActionEvent event) {
        llenarTabla();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = FXCollections.observableArrayList();
        comboLongitud.getItems().addAll("2", "3", "4", "5", "6", "7");
        comboLongitud.setValue("2");
        comboGrueso.getItems().addAll("3/4\"", "1 1/2\"");
        comboGrueso.setValue("3/4\"");
        comboAncho.getItems().addAll("4", "6", "8", "10", "12");
        comboAncho.setValue("4");
        comboFiltro.getItems().addAll("Todos", "2", "3", "4", "5", "6", "7");
        comboFiltro.setValue("Todos");

        //conexion.establecerConexion();
        //Tabletas.obtenerDatos(conexion.getConection(), list);
        //conexion.cerrarConexion();
        llenarTabla();
        columnas();

        btnAgregar.setTooltip(new Tooltip("Agregar"));
        btnBuscar.setTooltip(new Tooltip("Buscar"));
        btnEliminar.setTooltip(new Tooltip("Eliminar"));
        btnRegreso.setTooltip(new Tooltip("Regrese al dia actual"));
        btnSubtotales.setTooltip(new Tooltip("Despliega los subtotales"));

        subTotales();
    }

    private void cargarDatos(String datePicker) {
        list.removeIf(x -> true);
        conexion.establecerConexion();
        Tabletas.historial(conexion.getConection(), list, datePicker);
        conexion.cerrarConexion();
    }

    private void llenarTabla() {
        if (fechaTableta.getValue() == null) {
            if (comboFiltro.getSelectionModel().getSelectedItem() == "Todos") {
                list.removeIf(x -> true);
                conexion.establecerConexion();
                Tabletas.obtenerDatos(conexion.getConection(), list);
                conexion.cerrarConexion();
            } else {
                list.removeIf(x -> true);
                conexion.establecerConexion();
                Tabletas.obtenerDatosFiltrados(conexion.getConection(), list, comboFiltro.getSelectionModel().getSelectedItem());
                conexion.cerrarConexion();
            }
        } else {
            if (comboFiltro.getSelectionModel().getSelectedItem() == "Todos") {
                var datePicker1 = fechaTableta.getValue().getYear() + "-" + fechaTableta.getValue().getMonthValue() + "-" + fechaTableta.getValue().getDayOfMonth();

                list.removeIf(x -> true);
                conexion.establecerConexion();
                Tabletas.obtenerDatosFecha(conexion.getConection(), list, datePicker1);
                conexion.cerrarConexion();
            } else {
                var datePicker1 = fechaTableta.getValue().getYear() + "-" + fechaTableta.getValue().getMonthValue() + "-" + fechaTableta.getValue().getDayOfMonth();

                list.removeIf(x -> true);
                conexion.establecerConexion();
                Tabletas.obtenerDatosFiltradosFecha(conexion.getConection(), list, comboFiltro.getSelectionModel().getSelectedItem(), datePicker1);
                conexion.cerrarConexion();
            }
        }
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

    public void asignarCubicacion(String grueso, double ancho, double longitud) {
        double valorGrueso = 0;

        if (grueso == "3/4\"") {
            valorGrueso = 0.75;
        } else if (grueso == "1 1/2\"") {
            valorGrueso = 1.5;
        }

        cubos = (valorGrueso * ancho * longitud) / 12;
    }

    public double calcularPt(double cubicacion, int piezas) {
        return cubicacion * piezas;
    }

    public void gruesoAncho(String grueso, String ancho) {
        concatena = grueso + " x " + ancho;
    }

    public void agregarRegistro(Rollo x) {
        conexion.establecerConexion();
        var newTableta = Tabletas.addTableta(conexion.getConection(), concatena, Integer.parseInt(txtPiezas.getText()), cubos, calcularPt(cubos, Integer.parseInt(txtPiezas.getText())), Integer.parseInt(comboLongitud.getSelectionModel().getSelectedItem()));
        conexion.cerrarConexion();
        if (newTableta != null) {
            list.add(newTableta);
        }
    }

    public void subTotales() {
        var sub2 = list.stream().filter(q -> q.getLongitud() == 2.0).mapToDouble(Tabletas::getPiestabla).sum();

        var sub3 = list.stream().filter(q -> q.getLongitud() == 3.0).mapToDouble(Tabletas::getPiestabla).sum();

        var sub4 = list.stream().filter(q -> q.getLongitud() == 4.0).mapToDouble(Tabletas::getPiestabla).sum();

        var sub5 = list.stream().filter(q -> q.getLongitud() == 5.0).mapToDouble(Tabletas::getPiestabla).sum();

        var sub6 = list.stream().filter(q -> q.getLongitud() == 6.0).mapToDouble(Tabletas::getPiestabla).sum();

        var sub7 = list.stream().filter(q -> q.getLongitud() == 7.0).mapToDouble(Tabletas::getPiestabla).sum();

        var x = list.parallelStream().mapToDouble(Tabletas::getPiestabla).sum();

        subtotales = new Extra(sub2, sub3, sub4, sub5, sub6, sub7);
        totalFinal = x;
    }
}