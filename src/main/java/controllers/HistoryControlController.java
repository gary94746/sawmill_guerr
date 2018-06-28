package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import modelo.Conexion;
import modelo.Control_madera.madera_control;

import java.net.URL;
import java.util.ResourceBundle;

public class HistoryControlController implements Initializable{

    @FXML private JFXButton buscar;

    @FXML
    private JFXTreeTableView<madera_control> tablaHistorial;

    @FXML
    private JFXTreeTableView<?> tablaHistorial2;

    private ObservableList<madera_control> list;
    private Conexion conexion = Conexion.getInstance();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = FXCollections.observableArrayList();
        columns();
        llenarTabla();

    }

    public void llenarTabla(){
            list.removeIf(x->true);
            conexion.establecerConexion();
            madera_control.obtenerDatosHistorial(conexion.getConection(),list);
            conexion.cerrarConexion();

        }



    private void columns() {


        final TreeItem<madera_control> root = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);

        list.forEach(x -> System.out.println(x));
        tablaHistorial.setRoot(root);

        JFXTreeTableColumn<madera_control, String> Columna1 = new JFXTreeTableColumn<>("PRIMERA");
        //subcolumnas
        JFXTreeTableColumn<madera_control, Integer> subcolumna1 = new JFXTreeTableColumn<>("PIEZAS");
        JFXTreeTableColumn<madera_control, Double> subcolumna2 = new JFXTreeTableColumn<>("CUB");
        JFXTreeTableColumn<madera_control, Double> subcolumna3 = new JFXTreeTableColumn<>("PT");
        //Columna2
        JFXTreeTableColumn<madera_control, String> Columna2 = new JFXTreeTableColumn<>("SEGUNDA");
        //subcolumnas
        JFXTreeTableColumn<madera_control, Integer> subcolumna4 = new JFXTreeTableColumn<>("PIEZAS");
        JFXTreeTableColumn<madera_control, Double> subcolumna5 = new JFXTreeTableColumn<>("CUB");
        JFXTreeTableColumn<madera_control, Double> subcolumna6 = new JFXTreeTableColumn<>("PT");
        //Columna3
        JFXTreeTableColumn<madera_control, String> Columna3 = new JFXTreeTableColumn<>("TERCERA BUENA");
        //subcolumnas
        JFXTreeTableColumn<madera_control, Integer> subcolumna7 = new JFXTreeTableColumn<>("PIEZAS");
        JFXTreeTableColumn<madera_control, Double> subcolumna8 = new JFXTreeTableColumn<>("CUB");
        JFXTreeTableColumn<madera_control, Double> subcolumna9 = new JFXTreeTableColumn<>("PT");
        //Columna4
        JFXTreeTableColumn<madera_control, String> Columna4 = new JFXTreeTableColumn<>("TERCERA MALA");
        //subColumnas
        JFXTreeTableColumn<madera_control, Integer> subcolumna10 = new JFXTreeTableColumn<>("PIEZAS");
        JFXTreeTableColumn<madera_control, Double> subcolumna11 = new JFXTreeTableColumn<>("CUB");
        JFXTreeTableColumn<madera_control, Double> subcolumna12 = new JFXTreeTableColumn<>("PT");
        //Columna5
        JFXTreeTableColumn<madera_control, String> Columna5 = new JFXTreeTableColumn<>("MADERA CRUZADA");
        //subColumnas
        JFXTreeTableColumn<madera_control, Integer> subcolumna13 = new JFXTreeTableColumn<>("PIEZAS");
        JFXTreeTableColumn<madera_control, Double> subcolumna14 = new JFXTreeTableColumn<>("CUB");
        JFXTreeTableColumn<madera_control, Double> subcolumna15 = new JFXTreeTableColumn<>("PT");



        //INICIO COLUMNA 1
            Columna1.setEditable(false);
            Columna1.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, String> param) -> {
                if (Columna1.validateValue(param)) {
                    return param.getValue().getValue().claseProperty();
                } else
                    return Columna1.getComputedValue(param);

            });

            //subcolumna 1
            subcolumna1.setEditable(false);
            subcolumna1.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Integer> param) -> {
                if (subcolumna1.validateValue(param)) {
                    return param.getValue().getValue().piezaProperty().asObject();
                } else
                    return subcolumna1.getComputedValue(param);
            });

            //subcolumna 2
            subcolumna2.setEditable(false);
            subcolumna2.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
                if (subcolumna2.validateValue(param)) {
                    return param.getValue().getValue().cubicacionProperty().asObject();
                } else
                    return subcolumna2.getComputedValue(param);
            });


            //subcolumna 3
            subcolumna3.setEditable(false);
            subcolumna3.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
                if (subcolumna3.validateValue(param)) {
                    return param.getValue().getValue().ptProperty().asObject();
                } else
                    return subcolumna3.getComputedValue(param);
            });


        //FIN DE LA COLUMNA 1

        //INICIO COLUMNA 2
            Columna2.setEditable(false);
            Columna2.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, String> param) -> {
                if (Columna2.validateValue(param)) {
                    return param.getValue().getValue().claseProperty();
                } else
                    return Columna2.getComputedValue(param);
            });

            //subcolumna 4
            subcolumna4.setEditable(false);

            subcolumna4.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Integer> param) -> {
                if (subcolumna4.validateValue(param)) {
                    return param.getValue().getValue().piezaProperty().asObject();
                } else
                    return subcolumna4.getComputedValue(param);
            });

            //subcolumna 5
            subcolumna5.setEditable(false);

            subcolumna5.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
                if (subcolumna5.validateValue(param)) {
                    return param.getValue().getValue().cubicacionProperty().asObject();
                } else
                    return subcolumna5.getComputedValue(param);
            });


            //subcolumna 3
            subcolumna6.setEditable(false);

            subcolumna6.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
                if (subcolumna6.validateValue(param)) {
                    return param.getValue().getValue().ptProperty().asObject();
                } else
                    return subcolumna6.getComputedValue(param);
            });
            //FIN DE LA COLUMNA 2

        //INICIO COLUMNA 3
            Columna3.setEditable(false);
            Columna3.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, String> param) -> {
                if (Columna3.validateValue(param)) {
                    return param.getValue().getValue().claseProperty();
                } else
                    return Columna3.getComputedValue(param);
            });

            //subcolumna 4
            subcolumna7.setEditable(false);

            subcolumna7.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Integer> param) -> {
                if (subcolumna7.validateValue(param)) {
                    return param.getValue().getValue().piezaProperty().asObject();
                } else
                    return subcolumna7.getComputedValue(param);
            });

            //subcolumna 5
            subcolumna8.setEditable(false);

            subcolumna8.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
                if (subcolumna8.validateValue(param)) {
                    return param.getValue().getValue().cubicacionProperty().asObject();
                } else
                    return subcolumna8.getComputedValue(param);
            });


            //subcolumna 3
            subcolumna9.setEditable(false);

            subcolumna9.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
                if (subcolumna9.validateValue(param)) {
                    return param.getValue().getValue().ptProperty().asObject();
                } else
                    return subcolumna9.getComputedValue(param);
            });
            //FIN DE LA COLUMNA 3

        //INICIO COLUMNA 4
            Columna4.setEditable(false);
            Columna4.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, String> param) -> {
                if (Columna4.validateValue(param)) {
                    return param.getValue().getValue().claseProperty();
                } else
                    return Columna4.getComputedValue(param);
            });

            //subcolumna 4
            subcolumna10.setEditable(false);

            subcolumna10.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Integer> param) -> {
                if (subcolumna10.validateValue(param)) {
                    return param.getValue().getValue().piezaProperty().asObject();
                } else
                    return subcolumna10.getComputedValue(param);
            });

            //subcolumna 5
            subcolumna11.setEditable(false);
            subcolumna11.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
                if (subcolumna11.validateValue(param)) {
                    return param.getValue().getValue().cubicacionProperty().asObject();
                } else
                    return subcolumna11.getComputedValue(param);
            });


            //subcolumna 3
            subcolumna12.setEditable(false);
            subcolumna12.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
                if (subcolumna12.validateValue(param)) {
                    return param.getValue().getValue().ptProperty().asObject();
                } else
                    return subcolumna12.getComputedValue(param);
            });

        //FIN DE LA COLUMNA 4

        //INICIO COLUMNA 5
            Columna5.setEditable(false);
            Columna5.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, String> param) -> {
                if (Columna5.validateValue(param)) {
                    return param.getValue().getValue().claseProperty();
                } else
                    return Columna5.getComputedValue(param);
            });

            //subcolumna 4
            subcolumna13.setEditable(false);
            subcolumna13.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Integer> param) -> {
                if (subcolumna13.validateValue(param)) {
                    return param.getValue().getValue().piezaProperty().asObject();
                } else
                    return subcolumna13.getComputedValue(param);
            });

            //subcolumna 5
            subcolumna14.setEditable(false);

            subcolumna14.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
                if (subcolumna14.validateValue(param)) {
                    return param.getValue().getValue().cubicacionProperty().asObject();
                } else
                    return subcolumna14.getComputedValue(param);
            });


            //subcolumna 3
            subcolumna15.setEditable(false);

            subcolumna15.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
                if (subcolumna15.validateValue(param)) {
                    return param.getValue().getValue().ptProperty().asObject();
                } else
                    return subcolumna15.getComputedValue(param);
            });


        //FIN DE LA COLUMNA 5

        //INICIO COLUMNA 0
        JFXTreeTableColumn<madera_control, String> Columna0 = new JFXTreeTableColumn<>("MEDIDA");
        Columna0.setEditable(false);

        Columna0.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, String> param) -> {
            if (Columna0.validateValue(param)) {
                return param.getValue().getValue().claseProperty();
            } else
                return Columna0.getComputedValue(param);
        });

        //subcolumna 4
        JFXTreeTableColumn<madera_control, String> subcolumna01 = new JFXTreeTableColumn<>("GRUESO");
        subcolumna01.setEditable(false);

        subcolumna01.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, String> param) -> {
            if (subcolumna01.validateValue(param)) {
                return param.getValue().getValue().gruesoProperty();
            } else
                return subcolumna01.getComputedValue(param);
        });

        //subcolumna 5
        JFXTreeTableColumn<madera_control, String> subcolumna02 = new JFXTreeTableColumn<>("ANCHO");
        subcolumna02.setEditable(false);

        subcolumna02.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, String> param) -> {
            if (subcolumna02.validateValue(param)) {
                return param.getValue().getValue().anchoProperty();
            } else
                return subcolumna02.getComputedValue(param);
        });
        //FIN DE LA COLUMNA 0



        //Operaciones con la tabla
        tablaHistorial.setEditable(false);
        tablaHistorial.setShowRoot(false);
        tablaHistorial.getColumns().setAll(Columna0,Columna1, Columna2,Columna3,Columna4,Columna5);
        Columna0.getColumns().setAll(subcolumna01,subcolumna02);
        Columna1.getColumns().setAll(subcolumna1,subcolumna2,subcolumna3);
        Columna2.getColumns().setAll(subcolumna4,subcolumna5,subcolumna6);
        Columna3.getColumns().setAll(subcolumna7,subcolumna8,subcolumna9);
        Columna4.getColumns().setAll(subcolumna10,subcolumna11,subcolumna12);
        Columna5.getColumns().setAll(subcolumna13,subcolumna14,subcolumna15);

  }



}
