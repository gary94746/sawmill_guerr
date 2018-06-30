package controllers;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controllers.utils.Messages;
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
import tray.notification.NotificationType;

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
    @FXML private JFXButton btnFechaActual;
    @FXML private JFXTreeTableView<Resumen> treTable;
    @FXML private JFXDatePicker date1;
    @FXML private JFXDatePicker date2;
    @FXML private TextField txtRollo;
    @FXML private TextField txtVolA;
    @FXML private TextField txtCofA;
    @FXML private Label lblResumen;


    private ObservableList<Resumen> list;
    private Conexion conexion = Conexion.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Tooltips
        btnImprimir.setTooltip(Messages.setTooltipMessage("Imprimir en pdf"));
        btnBuscar.setTooltip(Messages.setTooltipMessage("Buscar"));
        btnFechaActual.setTooltip(Messages.setTooltipMessage("Resumen actual"));

        //Fecha actual
        var dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        var dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        lblResumen.setText("Resumen del: " + dateFormat.format(new Date()));

        list = FXCollections.observableArrayList();
        columns();

        //actual data
        loadData(dateFormat1.format(new Date()),dateFormat1.format(new Date()));

    }

    @FXML
    void currentDate(ActionEvent event) {
        //actual data
        date1.getEditor().clear();
        date2.getEditor().clear();

        var dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        var dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        lblResumen.setText("Resumen del: " + dateFormat.format(new Date()));
        loadData(dateFormat1.format(new Date()),dateFormat1.format(new Date()));
        Messages.setMessage("Resumen actual", "Se muestra el resumen actual", NotificationType.SUCCESS);
    }


    @FXML
    void buscar(ActionEvent event) {
        var datePicker1 = date1.getValue().getYear() + "-" + date1.getValue().getMonthValue()+ "-" + date1.getValue().getDayOfMonth();
        var datePicker2 = date2.getValue().getYear() + "-" + date2.getValue().getMonthValue()+ "-" + date2.getValue().getDayOfMonth();

        lblResumen.setText("Resumen del: " +
                date1.getValue().getDayOfMonth() + "/" + date1.getValue().getMonth()+ "/" + date1.getValue().getYear() + " al: " +
                date2.getValue().getDayOfMonth() + "/" + date2.getValue().getMonth()+ "/" + date2.getValue().getYear()
        );

        loadData(datePicker1, datePicker2);

        Messages.setMessage("Resumen", "De:" + datePicker1 + " al " + datePicker2, NotificationType.SUCCESS);

    }

    @FXML
    void imprimir(ActionEvent event) {
        System.out.println("Imprimio en pdf");
    }

    private double format3Decimals(double numero) {
        NumberFormat d = new DecimalFormat("#0.000");
        var f = d.format(numero);
        return Double.parseDouble(f);
    }

    private void loadData(String datePicker1, String datePicker2) {
        list.removeIf(x -> true);
        conexion.establecerConexion();
        Resumen.get_data(conexion.getConection(), list, datePicker1, datePicker2);
        Resumen.get_data_otros(conexion.getConection(), list, datePicker1, datePicker2);
        var volRollos = Rollo.getVolumenRollosFecha(conexion.getConection(), datePicker1, datePicker2);
        conexion.cerrarConexion();

        // TOTALES /////////////
        var tPrimera = format3Decimals(list.parallelStream().mapToDouble(Resumen::getPrimera).sum());
        var tSegunda = format3Decimals(list.parallelStream().mapToDouble(Resumen::getSegunda).sum());
        var tTBuena = format3Decimals(list.parallelStream().mapToDouble(Resumen::getTercera_buena).sum());
        var tTMala = format3Decimals(list.parallelStream().mapToDouble(Resumen::getTercera_mala).sum());
        var tMCruz = format3Decimals(list.parallelStream().mapToDouble(Resumen::getMadera_cruzada).sum());
        var tCuadrado = format3Decimals(list.parallelStream().mapToDouble(Resumen::getCuadrado).sum());
        var tViga = format3Decimals(list.parallelStream().mapToDouble(Resumen::getViga).sum());
        var tPolin = format3Decimals(list.parallelStream().mapToDouble(Resumen::getPolin).sum());
        var tTotal = format3Decimals(list.parallelStream().mapToDouble(Resumen::getTotal).sum());


        //Text Fields Inferiores
        var suma = tPrimera * 0.00236;
        txtVolA.setText(""+format3Decimals(suma));
        txtRollo.setText(volRollos +"");
        txtCofA.setText(""+format3Decimals((suma/volRollos)*100));

        //  total
        list.add(new Resumen("TOTAL", tPrimera, tSegunda, tTBuena, tTMala, tMCruz, tCuadrado, tViga, tPolin, tTotal));

        //  % de clases
        list.add(new Resumen(
                "% CLASES",
                format3Decimals((tPrimera/tTotal)*100),
                format3Decimals((tSegunda/tTotal)*100),
                format3Decimals((tTBuena/tTotal)*100),
                format3Decimals((tTMala/tTotal)*100),
                format3Decimals((tMCruz/tTotal)*100),
                format3Decimals((tCuadrado/tTotal)*100),
                format3Decimals((tViga/tTotal)*100),
                format3Decimals((tPolin/tTotal)*100)
        ));
    }

    private void columns() {
        final TreeItem<Resumen> root = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
        treTable.setRoot(root);

        JFXTreeTableColumn<Resumen, String> clmMedida = new JFXTreeTableColumn<>("Medida".toUpperCase());
        JFXTreeTableColumn<Resumen, Double> clmPrimera = new JFXTreeTableColumn<>("Primera".toUpperCase());
        JFXTreeTableColumn<Resumen, Double> clmSegunda = new JFXTreeTableColumn<>("Segunda".toUpperCase());
        JFXTreeTableColumn<Resumen, Double> clmTerceraBuena = new JFXTreeTableColumn<>("Tercera Buena".toUpperCase());
        JFXTreeTableColumn<Resumen, Double> clmTerceraMala = new JFXTreeTableColumn<>("Tercera Mala".toUpperCase());
        JFXTreeTableColumn<Resumen, Double> clmMaderaCruzada = new JFXTreeTableColumn<>("Madera Cruzada".toUpperCase());
        JFXTreeTableColumn<Resumen, Double> clmCuadrado = new JFXTreeTableColumn<>("Cuadrado".toUpperCase());
        JFXTreeTableColumn<Resumen, Double> clmViga = new JFXTreeTableColumn<>("Viga".toUpperCase());
        JFXTreeTableColumn<Resumen, Double> clmPolin = new JFXTreeTableColumn<>("Polin".toUpperCase());
        JFXTreeTableColumn<Resumen, Double> clmTotal = new JFXTreeTableColumn<>("Total".toUpperCase());

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

        clmPolin.setCellValueFactory((TreeTableColumn.CellDataFeatures<Resumen, Double> param) -> {
            if (clmPolin.validateValue(param))
                return param.getValue().getValue().polinProperty().asObject();
            else
                return clmPolin.getComputedValue(param);
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
        treTable.getColumns().setAll(clmMedida, clmPrimera, clmSegunda, clmTerceraBuena, clmTerceraMala, clmMaderaCruzada, clmCuadrado, clmViga, clmPolin, clmTotal);
        treTable.getColumns().forEach(c -> c.setSortable(false));
    }

}