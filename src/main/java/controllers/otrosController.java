package controllers;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controllers.utils.Messages;
import controllers.utils.Validators;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import modelo.Conexion;
import modelo.Control_madera.madera_control;
import modelo.otros_madera.otros_mad;
import tray.notification.NotificationType;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ResourceBundle;

public class otrosController implements Initializable {

    @FXML private JFXComboBox<String> ComboPz;
    @FXML private JFXTreeTableView<otros_mad> tablaOtros;
    @FXML private JFXTextField txtCubicacion;
    @FXML private TextField txtPieza;
    @FXML private JFXTextField txtPt;
    @FXML private JFXTextField txtTotalPieza;
    @FXML private JFXTextField txtTotalPt;
    @FXML private JFXDatePicker fechaOtros;
    @FXML private Label TituloRegistroOtros;
    @FXML private JFXButton btnAgregar;
    @FXML private JFXButton btnDelete;
    @FXML private JFXButton historial;
    @FXML private JFXButton restablecer;
    private Validators handler = new Validators();






    double valcub;
    private ObservableList<otros_mad> list;

    private Conexion conexion = Conexion.getInstance();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ComboPz.getItems().addAll("POL 4X4","POL 3.5X3.5","POL 3X3","BAR 2X4","BAR 1.5X3.5","VIGA 4x4","VIGA 4x6","VIGA 4x8","CUAD 1*1");
        ComboPz.setValue("POL 4X4");
        txtPt.setText("0");
        list = FXCollections.observableArrayList();
        txtCubicacion.setEditable(false);
        txtPt.setEditable(false);
        valcub=(4*4*8.25)/12;
        var valcub3 = format3Decimals(valcub);
        conexion.establecerConexion();
        otros_mad.obtenerDatos(conexion.getConection(), list);
        conexion.cerrarConexion();
        txtCubicacion.setText(String.valueOf(valcub3));
        txtTotalPieza.setEditable(false);
        txtTotalPt.setEditable(false);

        btnAgregar.setTooltip(new Tooltip("Agregar"));
        btnDelete.setTooltip(new Tooltip("Eliminar"));
        historial.setTooltip(new Tooltip("Buscar"));
        restablecer.setTooltip(new Tooltip("Regrese al dia actual"));
        txtPieza.addEventFilter(KeyEvent.ANY, handler.onlyNumbers());

        //fechaOtros.setEditable(false);
        columns();
        Totales();
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


    public void agregarRegistro(otros_mad x){
        conexion.establecerConexion();
        var newOtros = otros_mad.addOtros(conexion.getConection(),x);
        conexion.cerrarConexion();
        System.out.println(newOtros==null);
        if (newOtros != null) {
           list.add(newOtros);
       }
    }


    public void CalPt(){
        try {
            double val1 = Double.parseDouble(txtCubicacion.getText());
            double val2 = Integer.parseInt(txtPieza.getText());
            double resultado = val1 * val2;
            var resultado3 = format3Decimals(resultado);
            txtPt.setText(String.valueOf(resultado3));
        }catch (Exception e){

        }

    }

    void Totales(){
        var tTotal  = format3Decimals(list.parallelStream().mapToDouble(otros_mad::getPieza).sum());
        var tTotalPt= format3Decimals(list.parallelStream().mapToDouble(otros_mad::getPt).sum());
        txtTotalPieza.setText((tTotal+""));
        txtTotalPt.setText((tTotalPt+""));
    }

    void CalCubicacion(){
        if(ComboPz.getSelectionModel().getSelectedItem()=="POL 4X4"){
            valcub=(4*4*8.25)/12;
            var valcub3 = format3Decimals(valcub);
            txtCubicacion.setText(String.valueOf(valcub3));
        }else if(ComboPz.getSelectionModel().getSelectedItem()=="POL 3.5X3.5"){
            valcub=(3.5*3.5*8.25)/12;
            var valcub3 = format3Decimals(valcub);
            txtCubicacion.setText(String.valueOf(valcub3));
        }else if(ComboPz.getSelectionModel().getSelectedItem()=="POL 3X3") {
            valcub = (3 * 3 * 8.25) / 12;
            var valcub3 = format3Decimals(valcub);
            txtCubicacion.setText(String.valueOf(valcub3));
        }else if(ComboPz.getSelectionModel().getSelectedItem()=="BAR 2X4"){
            valcub=(2*4*8.25)/12;
            var valcub3 = format3Decimals(valcub);
            txtCubicacion.setText(String.valueOf(valcub3));
        }else if(ComboPz.getSelectionModel().getSelectedItem()=="BAR 1.5X3.5"){
            valcub=(1.5*3.5*8.25)/12;
            var valcub3 = format3Decimals(valcub);
            txtCubicacion.setText(String.valueOf(valcub3));
        }else if(ComboPz.getSelectionModel().getSelectedItem()=="VIGA 4x4"){
            valcub=(4*4*16.5)/12;
            var valcub3 = format3Decimals(valcub);
            txtCubicacion.setText(String.valueOf(valcub3));
        }else if(ComboPz.getSelectionModel().getSelectedItem()=="VIGA 4x6"){
            valcub=(4*6*16.5)/12;
            var valcub3 = format3Decimals(valcub);
            txtCubicacion.setText(String.valueOf(valcub3));
        }else if(ComboPz.getSelectionModel().getSelectedItem()=="VIGA 4x8"){
            valcub=(4*8*16.5)/12;
            var valcub3 = format3Decimals(valcub);
            txtCubicacion.setText(String.valueOf(valcub3));
        }else if(ComboPz.getSelectionModel().getSelectedItem()=="CUAD 1*1"){
            txtCubicacion.setText("0.333");
        }


    }

    private double format3Decimals(double numero) {
        NumberFormat d = new DecimalFormat("#0.000");
        var f = d.format(numero);
        return Double.parseDouble(f);
    }

    private void cargarDatos(String datePicker) {
        list.removeIf(x -> true);
        conexion.establecerConexion();
        otros_mad.historial(conexion.getConection(), list, datePicker);
        conexion.cerrarConexion();
    }

    private void ObtenerDatos(){
        list.removeIf(x -> true);
        conexion.establecerConexion();
        otros_mad.obtenerDatos(conexion.getConection(), list);
        conexion.cerrarConexion();
    }


    @FXML
    void ActionHistorialOtros(ActionEvent event) {
        try {
            txtPieza.setEditable(false);
            btnAgregar.setDisable(true);
            btnDelete.setDisable(true);
            var datePicker1 = fechaOtros.getValue().getYear() + "-" + fechaOtros.getValue().getMonthValue() + "-" + fechaOtros.getValue().getDayOfMonth();
            TituloRegistroOtros.setText("Historial del: " +
                    fechaOtros.getValue().getDayOfMonth() + "/" +
                    fechaOtros.getValue().getMonth() + "/" + fechaOtros.getValue().getYear()
            );

            Messages.setMessage("Filtar", "Historial del: " +
                    fechaOtros.getValue().getDayOfMonth() + "/" +
                    fechaOtros.getValue().getMonth() + "/" + fechaOtros.getValue().getYear(), NotificationType.SUCCESS);

            cargarDatos(datePicker1);
            Totales();
        }catch (java.lang.NullPointerException e){
            Messages.setMessage("Error.", "Ingrese una fecha", NotificationType.ERROR);
            txtPieza.setEditable(true);
            btnAgregar.setDisable(false);
            btnDelete.setDisable(false);

        }
    }



    @FXML
    void ActionComboPz(ActionEvent event) {
        CalCubicacion();
        CalPt();
    }


    @FXML
    void ActionPieza(KeyEvent event) {
        CalCubicacion();
        if(txtPieza.getText().equals("")){
            txtPt.setText("0");
        }
        else{
            CalPt();
        }
    }


    @FXML
    void addOtros(ActionEvent event) {
        try {
            agregarRegistro(new otros_mad(ComboPz.getSelectionModel().getSelectedItem(),
                    Integer.parseInt(txtPieza.getText()), Double.parseDouble(txtCubicacion.getText()),
                    Double.parseDouble(txtPt.getText())));
            txtPt.setText("0");
            txtPieza.setText("");
            ObtenerDatos();
            Totales();
            Messages.setMessage("Agregado", "El registro se agrego exitosamente", NotificationType.SUCCESS);

        }catch (NumberFormatException e){
            Messages.setMessage("Error.", "No se agrego numero de piezas.", NotificationType.ERROR);
        }
    }

    @FXML
    void deleteOtros(ActionEvent event) {
        try {
            int row = tablaOtros.getSelectionModel().getSelectedItem().getValue().getId();
            conexion.establecerConexion();
            otros_mad.eliminarOtros(conexion.getConection(), row);
            conexion.cerrarConexion();
            list.removeIf(x -> x.getId() == row);
            ObtenerDatos();
            Totales();
            Messages.setMessage("Eliminado", "El registro se elimino exitosamente", NotificationType.SUCCESS);
        }catch (java.lang.NullPointerException e){
            Messages.setMessage("Error.", "Seleccione un registro para eliminar", NotificationType.ERROR);

        }
    }


    @FXML
    public void ActionRestablecerOtros(ActionEvent actionEvent) {
        TituloRegistroOtros.setText("TABLA DE REGISTROS DEL DIA ACTUAL");
        list.removeIf(x -> true);
        fechaOtros.getEditor().clear();
        txtPieza.setEditable(true);
        btnAgregar.setDisable(false);
        btnDelete.setDisable(false);
        conexion.establecerConexion();
        otros_mad.obtenerDatos(conexion.getConection(), list);
        conexion.cerrarConexion();
        Totales();
    }
}