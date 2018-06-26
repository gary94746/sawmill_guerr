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

    @FXML
    private JFXComboBox<String> comboLongitud;

    @FXML
    private JFXComboBox<String> comboGruesoporancho;

    private Conexion conexion = Conexion.getInstance();


    @FXML
    void agregaTableta(ActionEvent event) {
        System.out.println("Hola2");
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
        comboGruesoporancho.getItems().addAll("3/4 x 4", "3/4 x 6", "3/4 x 8", "3/4 x 10", "3/4 x 12", "1 1/2 x 4", "1 1/2 x 6", "1 1/2 x 8", "1 1/2 x 10", "1 1/2 x 12");
        comboGruesoporancho.setValue("3/4 x 4");

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

    /**public void agregarTableta(Tabletas x){
        conexion.establecerConexion();
        var newOtros = Tabletas.addOtros(conexion.getConection(),x);
        conexion.cerrarConexion();
        System.out.println(newOtros==null);
        if (newOtros != null) {
            list.add(newOtros);
        }
    }*/
}
