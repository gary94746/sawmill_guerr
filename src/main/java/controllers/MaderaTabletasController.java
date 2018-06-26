package controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import java.util.ResourceBundle;

public class MaderaTabletasController implements Initializable {

    @FXML
    private JFXTreeTableView<Tabletas> tabla2;

    private ObservableList<Tabletas> list;
    private double cubos;

    @FXML
    private JFXComboBox<String> comboLongitud;

    @FXML
    private JFXComboBox<String> comboGrueso;

    @FXML
    private JFXComboBox<String> comboAncho;

    private Conexion conexion = Conexion.getInstance();


    @FXML
    void agregaTableta(ActionEvent event) {

    }

    // Abre un Stage con la vista extra de tabletas.
    @FXML
    void despegar(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/views/extraview.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/chainsaw.png")));
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
        comboAncho.getItems().addAll("2", "4", "6", "8", "10", "12");
        comboAncho.setValue("2");

        columnas();
    }

    private void columnas() {
        final TreeItem<Tabletas> root = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
        list.forEach(x -> System.out.println(x));
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
                return param.getValue().getValue().cubicacionProperty().asObject();
            else
                return piestabla.getComputedValue(param);
        });

        //Operaciones con la tabla
        tabla2.setEditable(false);
        tabla2.setShowRoot(false);
        tabla2.getColumns().setAll(gruesoporancho, piezas, cubicacion, piestabla);
    }

    public void asignarCubicacion(){

    }
}
