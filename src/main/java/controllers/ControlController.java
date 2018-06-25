package controllers;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import modelo.ancho.AnchoArray;
import modelo.ancho.AnchoId;
import modelo.clases_madera.ClaseId;
import modelo.clases_madera.ClasesArray;
import modelo.grueso.GruesoArray;
import modelo.grueso.GruesoId;
import modelo.Control_madera.madera_control;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ControlController implements Initializable {

    @FXML
    private JFXComboBox<String> comboGr;
    @FXML
    private JFXComboBox<String> comboAnc;
    @FXML
    private JFXComboBox<String> comboClase;
    @FXML
    private JFXComboBox<String> ComboRegistro;
    @FXML
    private JFXTreeTableView <madera_control> tablaControl;
    @FXML
    private JFXTextField txtPiezas;
    @FXML
    private JFXTextField txtCubicacion;
    @FXML
    private JFXTextField txtPT;
    @FXML
    private JFXComboBox<String> comboLargo;

    double valcub;


    private ObservableList<madera_control> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        //Customizacion de componentes
        ComboRegistro.getItems().addAll("3/4\"", "1 1/2\"", "2\"");
        ComboRegistro.setValue("3/4\"");

        comboGr.getItems().addAll("3/4\"", "1 1/2\"", "2\"");
        comboGr.setValue("3/4\"");

        comboAnc.getItems().addAll("4","6","8","10","12");
        comboAnc.setValue("4");

        comboClase.getItems().addAll("PRIMERA","SEGUNDA","TERCERA BUENA","TERCERA MALA","MADERA CRUZADA");
        comboClase.setValue("PRIMERA");

        comboLargo.getItems().addAll("3/4\"","16 1/2\"");
        comboLargo.setValue("3/4\"");

        valcub=(.75*4*8.25)/12;
        txtCubicacion.setText(String.valueOf(valcub));

        txtPT.setText("0");


        /**ClasesArray clasesArray = ClasesArray.getInstance();
        clasesArray.getArrayClases().forEach(x -> comboClase.getItems().add(x));

        GruesoArray gruesoArray = GruesoArray.getInstance();
        gruesoArray.getArrayList().forEach(y-> comboGr.getItems().add(y));
        comboGr.setValue(gruesoArray.getArrayList().get(0));


        AnchoArray anchoArray = AnchoArray.getInstance();
        anchoArray.getArrayList().forEach(j-> comboAnc.getItems().add(j));
        comboAnc.setValue(anchoArray.getArrayList().get(0));*/
        list = FXCollections.observableArrayList();
        columns();

    }
    //Declaracion de la tabla y columnas
    //Columna1
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


    private void columns() {
        final TreeItem<madera_control> root = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
        list.forEach(x -> System.out.println(x));
        tablaControl.setRoot(root);


        //INICIO COLUMNA 1
        if(comboClase.getSelectionModel().getSelectedItem()=="PRIMERA") {

            System.out.println("Entro if de la primera");
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

        }
        //FIN DE LA COLUMNA 1

        //INICIO COLUMNA 2
        else if(comboClase.getSelectionModel().getSelectedItem()=="SEGUNDA") {
            System.out.println("Entro al if de la segunda");
            Columna2.setEditable(false);
            Columna2.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, String> param) -> {
                if (Columna2.validateValue(param)) {
                    return param.getValue().getValue().clase_segundaProperty();
                } else
                    return Columna2.getComputedValue(param);
            });

            //subcolumna 4
            subcolumna4.setEditable(false);

            subcolumna4.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Integer> param) -> {
                if (subcolumna4.validateValue(param)) {
                    return param.getValue().getValue().pieza_segundaProperty().asObject();
                } else
                    return subcolumna4.getComputedValue(param);
            });

            //subcolumna 5
            subcolumna5.setEditable(false);

            subcolumna5.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
                if (subcolumna5.validateValue(param)) {
                    return param.getValue().getValue().cubicacion_segundaProperty().asObject();
                } else
                    return subcolumna5.getComputedValue(param);
            });


            //subcolumna 3
            subcolumna6.setEditable(false);

            subcolumna6.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
                if (subcolumna6.validateValue(param)) {
                    return param.getValue().getValue().pt_segundaProperty().asObject();
                } else
                    return subcolumna6.getComputedValue(param);
            });
            //FIN DE LA COLUMNA 2
        }
        //INICIO COLUMNA 3
        else if(comboClase.getSelectionModel().getSelectedItem()=="TERCERA BUENA") {
            Columna3.setEditable(false);
            Columna3.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, String> param) -> {
                if (Columna3.validateValue(param)) {
                    return param.getValue().getValue().clase_tercerabProperty();
                } else
                    return Columna3.getComputedValue(param);
            });

            //subcolumna 4
            subcolumna7.setEditable(false);

            subcolumna7.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Integer> param) -> {
                if (subcolumna7.validateValue(param)) {
                    return param.getValue().getValue().pieza_tercerabProperty().asObject();
                } else
                    return subcolumna7.getComputedValue(param);
            });

            //subcolumna 5
            subcolumna8.setEditable(false);

            subcolumna8.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
                if (subcolumna8.validateValue(param)) {
                    return param.getValue().getValue().cubicacion_tercerabProperty().asObject();
                } else
                    return subcolumna8.getComputedValue(param);
            });


            //subcolumna 3
            subcolumna9.setEditable(false);

            subcolumna9.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
                if (subcolumna9.validateValue(param)) {
                    return param.getValue().getValue().pt_tercerabProperty().asObject();
                } else
                    return subcolumna9.getComputedValue(param);
            });
            //FIN DE LA COLUMNA 3
        }
        //INICIO COLUMNA 4
        else if(comboClase.getSelectionModel().getSelectedItem()=="TERCERA MALA") {
            Columna4.setEditable(false);
            Columna4.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, String> param) -> {
                if (Columna4.validateValue(param)) {
                    return param.getValue().getValue().clase_terceraMProperty();
                } else
                    return Columna4.getComputedValue(param);
            });

            //subcolumna 4
            subcolumna10.setEditable(false);

            subcolumna10.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Integer> param) -> {
                if (subcolumna10.validateValue(param)) {
                    return param.getValue().getValue().pieza_terceraMProperty().asObject();
                } else
                    return subcolumna10.getComputedValue(param);
            });

            //subcolumna 5
            subcolumna11.setEditable(false);
            subcolumna11.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
                if (subcolumna11.validateValue(param)) {
                    return param.getValue().getValue().cubicacion_terceraMProperty().asObject();
                } else
                    return subcolumna11.getComputedValue(param);
            });


            //subcolumna 3
            subcolumna12.setEditable(false);
            subcolumna12.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
                if (subcolumna12.validateValue(param)) {
                    return param.getValue().getValue().pt_terceraMProperty().asObject();
                } else
                    return subcolumna12.getComputedValue(param);
            });
        }
        //FIN DE LA COLUMNA 4

        //INICIO COLUMNA 5
        else if(comboClase.getSelectionModel().getSelectedItem()=="MADERA CRUZADA") {
            Columna5.setEditable(false);
            Columna5.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, String> param) -> {
                if (Columna5.validateValue(param)) {
                    return param.getValue().getValue().clase_cruzadaProperty();
                } else
                    return Columna5.getComputedValue(param);
            });

            //subcolumna 4
            subcolumna13.setEditable(false);
            subcolumna13.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Integer> param) -> {
                if (subcolumna13.validateValue(param)) {
                    return param.getValue().getValue().pieza_cruzadaProperty().asObject();
                } else
                    return subcolumna13.getComputedValue(param);
            });

            //subcolumna 5
            subcolumna14.setEditable(false);

            subcolumna14.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
                if (subcolumna14.validateValue(param)) {
                    return param.getValue().getValue().cubicacion_cruzadaProperty().asObject();
                } else
                    return subcolumna14.getComputedValue(param);
            });


            //subcolumna 3
            subcolumna15.setEditable(false);

            subcolumna15.setCellValueFactory((TreeTableColumn.CellDataFeatures<madera_control, Double> param) -> {
                if (subcolumna15.validateValue(param)) {
                    return param.getValue().getValue().pt_cruzadaProperty().asObject();
                } else
                    return subcolumna15.getComputedValue(param);
            });

        }
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
        tablaControl.setEditable(false);
        tablaControl.setShowRoot(false);
        tablaControl.getColumns().setAll(Columna0,Columna1, Columna2,Columna3,Columna4,Columna5);
        Columna0.getColumns().setAll(subcolumna01,subcolumna02);
        Columna1.getColumns().setAll(subcolumna1,subcolumna2,subcolumna3);
        Columna2.getColumns().setAll(subcolumna4,subcolumna5,subcolumna6);
        Columna3.getColumns().setAll(subcolumna7,subcolumna8,subcolumna9);
        Columna4.getColumns().setAll(subcolumna10,subcolumna11,subcolumna12);
        Columna5.getColumns().setAll(subcolumna13,subcolumna14,subcolumna15);


    }

    public void agregarRegistro(madera_control x){
        list.add(x);

    }

    public void CalPt(){
        try {
            double val1 = Double.parseDouble(txtCubicacion.getText());
            double val2 = Integer.parseInt(txtPiezas.getText());
            double resultado = val1 * val2;
            txtPT.setText(String.valueOf(resultado));
        }catch (Exception e){

        }

    }

    @FXML
    void addControl(ActionEvent event) {

        if(comboClase.getSelectionModel().getSelectedItem()=="PRIMERA") {

            agregarRegistro(new madera_control(comboGr.getSelectionModel().getSelectedItem(),
                    comboAnc.getSelectionModel().getSelectedItem(),comboClase.getSelectionModel().getSelectedItem(),
                    Integer.parseInt(txtPiezas.getText()),Double.parseDouble(txtCubicacion.getText()),
                    Double.parseDouble(txtPT.getText())));
        }

        else if(comboClase.getSelectionModel().getSelectedItem()=="SEGUNDA") {
            agregarRegistro(new madera_control(comboGr.getSelectionModel().getSelectedItem(),
                    comboAnc.getSelectionModel().getSelectedItem(), Integer.parseInt(txtPiezas.getText()),
                    comboClase.getSelectionModel().getSelectedItem(), Double.parseDouble(txtCubicacion.getText()),
                    Double.parseDouble(txtPT.getText())));
        }
    }


    @FXML
    void actionAncho(ActionEvent event) {
        if(comboAnc.getSelectionModel().getSelectedItem()=="4"){
            valcub=(.75*4*8.25)/12;
            txtCubicacion.setText(String.valueOf(valcub));
        }else if(comboAnc.getSelectionModel().getSelectedItem()=="6"){
            valcub=(.75*6*8.25)/12;
            txtCubicacion.setText(String.valueOf(valcub));
        }else if(comboAnc.getSelectionModel().getSelectedItem()=="8"){
            valcub=(.75*8*8.25)/12;
            txtCubicacion.setText(String.valueOf(valcub));
        }else if(comboAnc.getSelectionModel().getSelectedItem()=="10"){
            valcub=(.75*10*8.25)/12;
            txtCubicacion.setText(String.valueOf(valcub));
        }else if(comboAnc.getSelectionModel().getSelectedItem()=="12"){
            valcub=(.75*12*8.25)/12;
            txtCubicacion.setText(String.valueOf(valcub));
        }
    }

    @FXML
    void ActionPieza(KeyEvent event) {
        if(txtPiezas.getText().equals("")){
            txtPT.setText("0");
        }else{
            CalPt();
        }


    }

    @FXML
    void ActionClase(ActionEvent event) {
        columns();

    }





}