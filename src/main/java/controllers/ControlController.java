package controllers;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controllers.utils.Messages;
import controllers.utils.Validators;
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
import javafx.scene.layout.VBox;
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
    @FXML private VBox VboxControl;

    private Validators handler = new Validators();


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

        txtPiezas.addEventFilter(KeyEvent.ANY, handler.onlyNumbers());


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
        fecha.setEditable(false);
        fecha.setValue(null);

        VboxControl.addEventHandler(KeyEvent.ANY,x->{
            if(x.getCode().getCode()==10){
                AgregarContro();
            }

                }


        );

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

            if (comboAnc.getSelectionModel().getSelectedItem() == "4") {

                if (comboGr.getSelectionModel().getSelectedItem() == "3/4\"") {
                    valcub = 2.062;
                }else if(comboGr.getSelectionModel().getSelectedItem() == "1 1/2\""){
                    valcub =4.125;
                }else if (comboGr.getSelectionModel().getSelectedItem() == "2\""){
                    valcub =5.500;
                }
                txtCubicacion.setText(String.valueOf(valcub));
            } else if (comboAnc.getSelectionModel().getSelectedItem() == "6") {
                if (comboGr.getSelectionModel().getSelectedItem() == "3/4\"") {
                    valcub = 3.093;
                }else if(comboGr.getSelectionModel().getSelectedItem() == "1 1/2\""){
                    valcub =6.187;
                }else if (comboGr.getSelectionModel().getSelectedItem() == "2\""){
                    valcub =8.250;
                }
                txtCubicacion.setText(String.valueOf(valcub));
            } else if (comboAnc.getSelectionModel().getSelectedItem() == "8") {
                if (comboGr.getSelectionModel().getSelectedItem() == "3/4\"") {
                    valcub = 4.125;
                }else if(comboGr.getSelectionModel().getSelectedItem() == "1 1/2\""){
                    valcub =8.250;
                }else if (comboGr.getSelectionModel().getSelectedItem() == "2\""){
                    valcub =11.000;
                }
                txtCubicacion.setText(String.valueOf(valcub));
            } else if (comboAnc.getSelectionModel().getSelectedItem() == "10") {
                if (comboGr.getSelectionModel().getSelectedItem() == "3/4\"") {
                    valcub = 5.156;
                }else if(comboGr.getSelectionModel().getSelectedItem() == "1 1/2\""){
                    valcub =10.312;
                }else if (comboGr.getSelectionModel().getSelectedItem() == "2\""){
                    valcub =13.750;
                }
                txtCubicacion.setText(String.valueOf(valcub));
            } else if (comboAnc.getSelectionModel().getSelectedItem() == "12") {
                if (comboGr.getSelectionModel().getSelectedItem() == "3/4\"") {
                    valcub = 6.187;
                }else if(comboGr.getSelectionModel().getSelectedItem() == "1 1/2\""){
                    valcub =12.375;
                }else if (comboGr.getSelectionModel().getSelectedItem() == "2\""){
                    valcub =16.500;
                }
                txtCubicacion.setText(String.valueOf(valcub));
            }

     }else{
            System.out.println("Calcular cubicaciones 2");
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
            var valcub3d = format3Decimals(valcub);
            txtCubicacion.setText(String.valueOf(valcub3d));
        } else if (comboAnc.getSelectionModel().getSelectedItem() == "6") {
            valcub = (var * 6 * 16.5) / 12;
            var valcub3d = format3Decimals(valcub);
            txtCubicacion.setText(String.valueOf(valcub3d));
        } else if (comboAnc.getSelectionModel().getSelectedItem() == "8") {
            valcub = (var * 8 * 16.5) / 12;
            var valcub3d = format3Decimals(valcub);
            txtCubicacion.setText(String.valueOf(valcub3d));
        } else if (comboAnc.getSelectionModel().getSelectedItem() == "10") {
            valcub = (var * 10 * 16.5) / 12;
            var valcub3d = format3Decimals(valcub);
            txtCubicacion.setText(String.valueOf(valcub3d));
        } else if (comboAnc.getSelectionModel().getSelectedItem() == "12") {
            valcub = (var * 12 * 16.5) / 12;
            var valcub3d = format3Decimals(valcub);
            txtCubicacion.setText(String.valueOf(valcub3d));
        }

    }

    public void agregarRegistro(madera_control x){
        try{
        conexion.establecerConexion();
        var newOtros = madera_control.addControl(conexion.getConection(),x);
        conexion.cerrarConexion();
        //System.out.println(newOtros==null);
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

        txtPiezas.setEditable(false);
        agregar.setDisable(true);
        btnDelete.setDisable(true);
        buscarporFecha ();
        Totales();

        Messages.setMessage("Filtar", "Historial del: " +
                fecha.getValue().getDayOfMonth() + "/" +
                fecha.getValue().getMonth() + "/" + fecha.getValue().getYear(), NotificationType.SUCCESS);

    }

    public void buscarporFecha (){
        try {
            var datePicker1 = fecha.getValue().getYear() + "-" + fecha.getValue().getMonthValue() + "-" + fecha.getValue().getDayOfMonth();
            tituloRegistro.setText("Historial del: " +
                    fecha.getValue().getDayOfMonth() + "/" +
                    fecha.getValue().getMonth() + "/" + fecha.getValue().getYear()
            );

            cargarDatos(datePicker1);
        }catch (java.lang.NullPointerException e){
            Messages.setMessage("Error.", "Seleccione una fecha", NotificationType.ERROR);
        }
    }

    @FXML
    void addControl(ActionEvent event) {
        AgregarContro();

    }

    public void AgregarContro(){
        try {
            agregarRegistro(new madera_control(comboGr.getSelectionModel().getSelectedItem(),
                    comboAnc.getSelectionModel().getSelectedItem(), comboClase.getSelectionModel().getSelectedItem(),
                    Integer.parseInt(txtPiezas.getText()), Double.parseDouble(txtCubicacion.getText()),
                    Double.parseDouble(txtPT.getText()), comboLargo.getSelectionModel().getSelectedItem()));
            llenarTabla();
            Totales();
            txtPiezas.setText("");
            txtPT.setText("0");
            Messages.setMessage("Agregado", "El registro se a agregado exitosamente", NotificationType.SUCCESS);


        }catch (Exception e){
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
        if(txtPiezas.getText()==""){
            txtCubicacion.setText("");
        }
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
            //System.out.println(fecha.getValue());
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
        try {
            int row = tablaControl.getSelectionModel().getSelectedItem().getValue().getId();
            conexion.establecerConexion();
            madera_control.eliminarOtros(conexion.getConection(), row);
            conexion.cerrarConexion();
            list.removeIf(x -> x.getId() == row);
            Totales();
            Messages.setMessage("Eliminado", "El registro se elimino exitosamente", NotificationType.SUCCESS);
        }catch (java.lang.NullPointerException e){
            Messages.setMessage("Error.", "Seleccione un registro para eliminar", NotificationType.ERROR);
        }
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
        fecha.setValue(null);
        tituloRegistro.setText("TABLA DE REGISTROS DEL DIA ACTUAL");
        Messages.setMessage("Restablecido", "Registros de la fecha actual", NotificationType.SUCCESS);
        txtPiezas.setEditable(true);
        agregar.setDisable(false);
        btnDelete.setDisable(false);
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