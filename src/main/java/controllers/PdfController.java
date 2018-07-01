package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controllers.utils.Messages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import modelo.resumen.Resumen;
import tray.notification.NotificationType;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class PdfController implements Initializable {
    @FXML private VBox vBox;
    @FXML private JFXTreeTableView<Resumen> treeTable;
    @FXML private Label lblFecha;
    @FXML private Label lblHora;
    @FXML private JFXButton btnImprimir;
    @FXML private HBox boxImprimir;
    @FXML private Label lblResumenFecha;
    @FXML private TextField txtRollo;
    @FXML private TextField txtVolA;
    @FXML private TextField txtCofA;
    @FXML private TextField txtTotalPiezas;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var date = new SimpleDateFormat("dd-MM-yyyy");
        var hour = new SimpleDateFormat("HH:mm");

        //Text
        txtRollo.setText(ResumenController.rollo+"");
        txtVolA.setText(ResumenController.volA+"");
        txtCofA.setText(ResumenController.coefA+"");
        txtTotalPiezas.setText(ResumenController.totPiezas+"");

        lblFecha.setText(date.format(new Date()));
        lblHora.setText(hour.format(new Date()));
        lblResumenFecha.setText(ResumenController.texResumen);

        columns();
    }

    private void columns() {
        final TreeItem<Resumen> root = new RecursiveTreeItem<>(ResumenController.list, RecursiveTreeObject::getChildren);
        treeTable.setRoot(root);

        JFXTreeTableColumn<Resumen, String> clmMedida = new JFXTreeTableColumn<>("Medida".toUpperCase());
        JFXTreeTableColumn<Resumen, Double> clmPrimera = new JFXTreeTableColumn<>("Primera".toUpperCase());
        JFXTreeTableColumn<Resumen, Double> clmSegunda = new JFXTreeTableColumn<>("Segunda".toUpperCase());
        JFXTreeTableColumn<Resumen, Double> clmTerceraBuena = new JFXTreeTableColumn<>("Tercera Buena".toUpperCase());
        JFXTreeTableColumn<Resumen, Double> clmTerceraMala = new JFXTreeTableColumn<>("Tercera Mala".toUpperCase());
        JFXTreeTableColumn<Resumen, Double> clmMaderaCruzada = new JFXTreeTableColumn<>("Madera Cruzada".toUpperCase());
        JFXTreeTableColumn<Resumen, Double> clmCuadrado = new JFXTreeTableColumn<>("Barrote".toUpperCase());
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
        clmMedida.getStyleClass().add("leftAlignment");
        treeTable.setEditable(false);
        treeTable.setShowRoot(false);
        treeTable.getColumns().setAll(clmMedida, clmPrimera, clmSegunda, clmTerceraBuena, clmTerceraMala, clmMaderaCruzada, clmCuadrado, clmViga, clmPolin, clmTotal);
        treeTable.getColumns().forEach(c -> c.setSortable(false));
    }

    @FXML
    void imprimir(ActionEvent event) {
        btnImprimir.setVisible(false);
        var v = (Stage)((Node) event.getSource()).getScene().getWindow();
        vBox.getChildren().removeIf(x -> x.equals(boxImprimir));

        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout
                = printer.createPageLayout(Paper.NA_LETTER, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
        PrinterAttributes attr = printer.getPrinterAttributes();
        PrinterJob job = PrinterJob.createPrinterJob();
        double scaleX
                = pageLayout.getPrintableWidth() / vBox.getBoundsInParent().getWidth();
        double scaleY
                = pageLayout.getPrintableHeight() / vBox.getBoundsInParent().getHeight();
        Scale scale = new Scale(scaleX, scaleY);
        vBox.getTransforms().add(scale);

        if (job != null && job.showPrintDialog(vBox.getScene().getWindow())) {
            boolean success = job.printPage(pageLayout, vBox);
            if (success) {
                job.endJob();
                v.close();
            }
        }
        vBox.getTransforms().remove(scale);
    }
}
