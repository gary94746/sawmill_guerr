package controllers;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controllers.utils.Messages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
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
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Conexion;
import modelo.Control_madera.madera_control;
import tray.notification.NotificationType;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ResourceBundle;

import static controllers.utils.Validators.onlyNumbers;

public class ControlController implements Initializable {

    @FXML private JFXComboBox<String> comboGr;
    @FXML private JFXComboBox<String> comboAnc;
    @FXML private JFXComboBox<String> comboClase;
    @FXML private JFXTreeTableView <madera_control> tablaControl;
    @FXML private JFXTextField txtPiezas;
    @FXML private JFXTextField txtCubicacion;
    @FXML private JFXTextField txtPT;
    @FXML private JFXComboBox<String> comboLargo;
    @FXML private JFXComboBox<String> FiltrarGrueso;
    @FXML private JFXComboBox<String> FiltrarClase;
    @FXML private JFXTextField txtTotalPieza;
    @FXML private JFXTextField txtTotalPt;
    @FXML private JFXDatePicker fecha;
    @FXML private Label tituloRegistro;
    @FXML private JFXComboBox<String> FiltrarLargo;
    @FXML private JFXButton desplegar;
    @FXML private JFXButton agregar;
    @FXML private JFXButton btnDelete;
    @FXML private JFXButton historial;
    @FXML private JFXButton restablecer;

    double valcub;
    private Conexion conexion = Conexion.getInstance();


    private ObservableList<madera_control> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Customizacion de componentes
        FiltrarGrueso.getItems().addAll("3/4\"", "1 1/2\"", "2\"");
        FiltrarGrueso.setValue("3/4\"");

        FiltrarClase.getItems().addAll("TODOS","PRIMERA","SEGUNDA","TERCERA BUENA","TERCERA MALA","MADERA CRUZADA");
        FiltrarClase.setValue("TODOS");

        comboGr.getItems().addAll("3/4\"", "1 1/2\"", "2\"");
        comboGr.setValue("3/4\"");

        comboAnc.getItems().addAll("4","6","8","10","12");
        comboAnc.setValue("4");

        comboClase.getItems().addAll("PRIMERA","SEGUNDA","TERCERA BUENA","TERCERA MALA","MADERA CRUZADA");
        comboClase.setValue("PRIMERA");

        comboLargo.getItems().addAll("8 1/4\"","16 1/2\"");
        comboLargo.setValue("8 1/4\"");

        FiltrarLargo.getItems().addAll("8 1/4\"","16 1/2\"");
        FiltrarLargo.setValue("8 1/4\"");

        valcub=(.75*4*8.25)/12;
        txtCubicacion.setEditable(false);
        var tresCubicacion = format3Decimals(valcub);
        txtCubicacion.setText(String.valueOf(tresCubicacion));

        txtPT.setText("0");
        txtPT.setEditable(false);
        list = FXCollections.observableArrayList();

        txtTotalPieza.setEditable(false);
        txtTotalPt.setEditable(false);
        desplegar.setVisible(false);

        agregar.setTooltip(new Tooltip("Agregar"));
        btnDelete.setTooltip(new Tooltip("Eliminar"));
        historial.setTooltip(new Tooltip("Buscar"));
        restablecer.setTooltip(new Tooltip("Regrese al dia actual"));

        llenarTabla();
        columns();
        Totales();
    }

    //Declaracion de la tabla y columnas
    //Columna1
    JFXTreeTableColumn<madera_control, String> Columna1 = new JFXTreeTableColumn<>("CLASE");
    //Columna2
    JFXTreeTableColumn<madera_control, String> Columna2 = new JFXTreeTableColumn<>("LARGO");
    //Columna3
    JFXTreeTableColumn<madera_control, String> Columna3 = new JFXTreeTableColumn<>("GRUESO");
    //Columna4
    JFXTreeTableColumn<madera_control, String> Columna4 = new JFXTreeTableColumn<>("ANCHO");
    //Columna5
    JFXTreeTableColumn<madera_control, Integer> Columna5 = new JFXTreeTableColumn<>("PIEZAS");
    //Columna6
    JFXTreeTableColumn<madera_control, Double> Columna6 = new JFXTreeTableColumn<>("CUBICACION");
    //Columna7
    JFXTreeTableColumn<madera_control, Double> Columna7 = new JFXTreeTableColumn<>("PT");



    private void columns() {
        final TreeItem<madera_control> root = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
        tablaControl.setRoot(root);


        //INICIO COLUMNA 1
            Columna1.setEditable(false);
            Columna1.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, String> param) -> {
                if (Columna1.validateValue(param)) {
                    return param.getValue().getValue().claseProperty();
                } else
                    return Columna1.getComputedValue(param);

            });


        //FIN DE LA COLUMNA 1

        //INICIO COLUMNA 2
            Columna2.setEditable(false);
            Columna2.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, String> param) -> {
                if (Columna2.validateValue(param)) {
                    return param.getValue().getValue().largoProperty();
                } else
                    return Columna2.getComputedValue(param);
            });


            //FIN DE LA COLUMNA 2

        //INICIO COLUMNA 3
            Columna3.setEditable(false);
            Columna3.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, String> param) -> {
                if (Columna3.validateValue(param)) {
                    return param.getValue().getValue().gruesoProperty();
                } else
                    return Columna3.getComputedValue(param);
            });


        //INICIO COLUMNA 4
            Columna4.setEditable(false);
            Columna4.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, String> param) -> {
                if (Columna4.validateValue(param)) {
                    return param.getValue().getValue().anchoProperty();
                } else
                    return Columna4.getComputedValue(param);
            });


        //FIN DE LA COLUMNA 4

        //INICIO COLUMNA 5
            Columna5.setEditable(false);
            Columna5.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Integer> param) -> {
                if (Columna5.validateValue(param)) {
                    return param.getValue().getValue().piezaProperty().asObject();
                } else
                    return Columna5.getComputedValue(param);
            });

        //FIN DE LA COLUMNA 5
        //INICIO COLUMNA 6
        Columna6.setEditable(false);
        Columna6.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
            if (Columna6.validateValue(param)) {
                return param.getValue().getValue().cubicacionProperty().asObject();
            } else
                return Columna6.getComputedValue(param);
        });

        //FIN DE LA COLUMNA 6

        //INICIO COLUMNA 6
        Columna7.setEditable(false);
        Columna7.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
            if (Columna7.validateValue(param)) {
                return param.getValue().getValue().ptProperty().asObject();
            } else
                return Columna7.getComputedValue(param);
        });

        //FIN DE LA COLUMNA 6
        //Operaciones con la tabla
        tablaControl.setEditable(false);
        tablaControl.setShowRoot(false);
        tablaControl.getColumns().setAll(Columna1, Columna2,Columna3,Columna4,Columna5,Columna6,Columna7);



    }

    public void CalPt(){
        try {
            double val1 = Double.parseDouble(txtCubicacion.getText());
            double val2 = Integer.parseInt(txtPiezas.getText());
            double resultado = val1 * val2;
            var pttres = format3Decimals(resultado);
            txtPT.setText(String.valueOf(pttres));
        }catch (Exception e){

        }

    }

    double var;
    public void calCubicacion() {
        //comboLargo.getItems().addAll("3/4\"","16 1/2\"");
        if(comboLargo.getSelectionModel().getSelectedItem()=="8 1/4\""){


            /**if (comboGr.getSelectionModel().getSelectedItem() == "3/4\"") {
                var = .75;
            }*/

            var=(comboGr.getSelectionModel().getSelectedItem() == "3/4\"")?.75 : 1.5;


              if (comboGr.getSelectionModel().getSelectedItem() == "2\"") {var = 2;}

            if (comboAnc.getSelectionModel().getSelectedItem() == "4") {
                valcub = (var * 4 * 8.25) / 12;
                var tresCubicacion = format3Decimals(valcub);
                txtCubicacion.setText(String.valueOf(tresCubicacion));
            } else if (comboAnc.getSelectionModel().getSelectedItem() == "6") {
                valcub = (var * 6 * 8.25) / 12;
                var tresCubicacion = format3Decimals(valcub);
                txtCubicacion.setText(String.valueOf(tresCubicacion));
            } else if (comboAnc.getSelectionModel().getSelectedItem() == "8") {
                valcub = (var * 8 * 8.25) / 12;
                var tresCubicacion = format3Decimals(valcub);
                txtCubicacion.setText(String.valueOf(tresCubicacion));
            } else if (comboAnc.getSelectionModel().getSelectedItem() == "10") {
                valcub = (var * 10 * 8.25) / 12;
                var tresCubicacion = format3Decimals(valcub);
                txtCubicacion.setText(String.valueOf(tresCubicacion));
            } else if (comboAnc.getSelectionModel().getSelectedItem() == "12") {
                valcub = (var * 12 * 8.25) / 12;
                var tresCubicacion = format3Decimals(valcub);
                txtCubicacion.setText(String.valueOf(tresCubicacion));
            }

     }else{
            calCubicacion2();
        }

    }

    public void calCubicacion2(){
        if (comboGr.getSelectionModel().getSelectedItem() == "3/4\"") {
            var=.75;
        }else if(comboGr.getSelectionModel().getSelectedItem() == "1 1/2\""){
            var=1.5;
        }else if (comboGr.getSelectionModel().getSelectedItem() == "2\""){
            var=2;
        }

        if (comboAnc.getSelectionModel().getSelectedItem() == "4") {
            valcub = (var * 4 * 16.5) / 12;
            txtCubicacion.setText(String.valueOf(valcub));
        } else if (comboAnc.getSelectionModel().getSelectedItem() == "6") {
            valcub = (var * 6 * 16.5) / 12;
            txtCubicacion.setText(String.valueOf(valcub));
        } else if (comboAnc.getSelectionModel().getSelectedItem() == "8") {
            valcub = (var * 8 * 16.5) / 12;
            txtCubicacion.setText(String.valueOf(valcub));
        } else if (comboAnc.getSelectionModel().getSelectedItem() == "10") {
            valcub = (var * 10 * 16.5) / 12;
            txtCubicacion.setText(String.valueOf(valcub));
        } else if (comboAnc.getSelectionModel().getSelectedItem() == "12") {
            valcub = (var * 12 * 16.5) / 12;
            txtCubicacion.setText(String.valueOf(valcub));
        }

    }

    public void agregarRegistro(madera_control x){
        try{
        conexion.establecerConexion();
        var newOtros = madera_control.addControl(conexion.getConection(),x);
        conexion.cerrarConexion();
        System.out.println(newOtros==null);
        if (newOtros != null) {
            list.add(newOtros);
        }

        }catch (NumberFormatException e){
            Messages.setMessage("Error.", "No se agrego numero de piezas.", NotificationType.ERROR);
        }
    }

    public void llenarTabla(){
        if(FiltrarClase.getSelectionModel().getSelectedItem()=="TODOS"){
            list.removeIf(x->true);
            conexion.establecerConexion();
            madera_control.obtenerDatosTodos(conexion.getConection(),list,FiltrarGrueso.getSelectionModel().getSelectedItem(),FiltrarLargo.getSelectionModel().getSelectedItem());
            conexion.cerrarConexion();

        }else{

        list.removeIf(x->true);
        conexion.establecerConexion();
        madera_control.obtenerDatos(conexion.getConection(), list, FiltrarGrueso.getSelectionModel().getSelectedItem(),
                FiltrarClase.getSelectionModel().getSelectedItem(),FiltrarLargo.getSelectionModel().getSelectedItem());
        conexion.cerrarConexion();
        }
    }


    private void cargarDatos(String datePicker) {
        if(FiltrarClase.getSelectionModel().getSelectedItem()=="TODOS") {
            list.removeIf(x -> true);
            conexion.establecerConexion();
            madera_control.obtenerDatosFechaTodos(conexion.getConection(), list, datePicker, FiltrarGrueso.getSelectionModel().getSelectedItem(), FiltrarLargo.getSelectionModel().getSelectedItem());
            conexion.cerrarConexion();
        }else{
            list.removeIf(x -> true);
            conexion.establecerConexion();
            madera_control.obtenerDatosFecha(conexion.getConection(), list, datePicker, FiltrarGrueso.getSelectionModel().getSelectedItem(),FiltrarClase.getSelectionModel().getSelectedItem() ,FiltrarLargo.getSelectionModel().getSelectedItem());
            conexion.cerrarConexion();


        }
    }

    private double format3Decimals(double numero) {
        NumberFormat d = new DecimalFormat("#0.000");
        var f = d.format(numero);
        return Double.parseDouble(f);
    }

    void Totales(){
        var tTotal  = format3Decimals(list.parallelStream().mapToDouble(madera_control::getPieza).sum());
        var tTotalPt= format3Decimals(list.parallelStream().mapToDouble(madera_control::getPt).sum());
        txtTotalPieza.setText((tTotal+""));
        txtTotalPt.setText((tTotalPt+""));
    }

    @FXML
    void ActionHistorial(ActionEvent event) {
        buscarporFecha ();

    }

    public void buscarporFecha (){
        txtPiezas.setEditable(false);
        var datePicker1 = fecha.getValue().getYear() + "-" + fecha.getValue().getMonthValue()+ "-" + fecha.getValue().getDayOfMonth();
        tituloRegistro.setText("Historial del: " +
                fecha.getValue().getDayOfMonth() + "/" +
                fecha.getValue().getMonth()+ "/" + fecha.getValue().getYear()
        );

        cargarDatos(datePicker1);

    }

    @FXML
    void addControl(ActionEvent event) {
        try {
            agregarRegistro(new madera_control(comboGr.getSelectionModel().getSelectedItem(),
                    comboAnc.getSelectionModel().getSelectedItem(), comboClase.getSelectionModel().getSelectedItem(),
                    Integer.parseInt(txtPiezas.getText()), Double.parseDouble(txtCubicacion.getText()),
                    Double.parseDouble(txtPT.getText()), comboLargo.getSelectionModel().getSelectedItem()));
            llenarTabla();
            Totales();
            txtPiezas.setText("");

        }catch (NumberFormatException e){
        Messages.setMessage("Error.", "No se agrego numero de piezas.", NotificationType.ERROR);
     }

    }


    @FXML
    void actionAncho(ActionEvent event) {
        calCubicacion();
        CalPt();
    }

    @FXML
    void ActionPieza(KeyEvent event) {

        if(txtPiezas.getText().equals("")){
            txtPT.setText("0");
        }else{
            calCubicacion();
            CalPt();
        }


    }

    @FXML
    void validarSoloNumeros(KeyEvent event) {

    }

    @FXML
    void ActionClase(ActionEvent event) {
        columns();

    }

    @FXML
    void actionFiltroClase(ActionEvent event) {
        if(fecha.getValue() == null) {
            llenarTabla();
            Totales();
        }else{
            buscarporFecha();
            Totales();
        }
    }

    @FXML
    void actionFiltroGrueso(ActionEvent event) {
        if(fecha.getValue() == null) {
            llenarTabla();
            Totales();
        }else{
            buscarporFecha();
            Totales();
        }
    }

    @FXML
    void deleteControlP(ActionEvent event) {
        int row = tablaControl.getSelectionModel().getSelectedItem().getValue().getId();
        conexion.establecerConexion();
        madera_control.eliminarOtros(conexion.getConection(), row);
        conexion.cerrarConexion();
        list.removeIf(x -> x.getId() == row);

    }

    @FXML
    void ActionComboGr(ActionEvent event) {
        calCubicacion();
        CalPt();
    }

    @FXML
    void ActionComboLargo(ActionEvent event) {
        calCubicacion();
        CalPt();
    }

    @FXML
    void ActionRestablecer(ActionEvent event) {
        tituloRegistro.setText("TABLA DE REGISTROS DEL DIA ACTUAL");
        txtPiezas.setEditable(true);
        fecha.getEditor().clear();
        llenarTabla();
        Totales();
    }

    @FXML
    void actionFiltroLargo(ActionEvent event) {
        if(fecha.getValue() == null) {
            llenarTabla();
            Totales();
        }else{
            buscarporFecha();
            Totales();
        }

    }

    @FXML
    void ventanaHistorial(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/views/HistorialControl.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            //stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/chainsaw.png")));
            stage.setTitle("Control de produccion");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        }catch (Exception e){}

    }

}