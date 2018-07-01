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
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Conexion;
import modelo.empleado.Empleado;
import tray.notification.NotificationType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmpleadoController implements Initializable {

    @FXML private JFXTreeTableView<Empleado> tblEmpleado;
    @FXML private JFXTextField txtNombre;
    @FXML private JFXTextField txtApellido;
    @FXML private JFXTextField txtUsuario;
    @FXML private JFXPasswordField txtPass;
    @FXML private JFXTextField txtCargo;
    @FXML private JFXButton bntAdd;
    @FXML private JFXButton btnEdit;
    @FXML private JFXButton btnDelete;


    protected static ObservableList<Empleado> lista;
    private Conexion conexion = Conexion.getInstance();

    public static Empleado empleado;
    public static Action value;
    private Empleado val;

    private JFXPopup popupEstaSeguro;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lista = FXCollections.observableArrayList();

        //conexion
        conexion.establecerConexion();
        Empleado.loadEmployees(conexion.getConection(), lista);
        conexion.cerrarConexion();

        columns();

        //Tooltips
        btnDelete.setTooltip(Messages.setTooltipMessage("Eliminar empleado"));
        bntAdd.setTooltip(Messages.setTooltipMessage("Agregar empleado"));
        btnEdit.setTooltip(Messages.setTooltipMessage("Editar empleado"));

        //
        setFields();
    }

    private void columns() {
        final TreeItem<Empleado> root = new RecursiveTreeItem<>(lista, RecursiveTreeObject::getChildren);
        tblEmpleado.setRoot(root);

        JFXTreeTableColumn<Empleado, String> clmNombre = new JFXTreeTableColumn<>("Nombre".toUpperCase());
        JFXTreeTableColumn<Empleado, String> clmApellido = new JFXTreeTableColumn<>("Apellidos".toUpperCase());
        JFXTreeTableColumn<Empleado, String> clmCargo = new JFXTreeTableColumn<>("Cargo".toUpperCase());

        clmNombre.setCellValueFactory((TreeTableColumn.CellDataFeatures<Empleado, String> param) -> {
            if (clmNombre.validateValue(param))
                return param.getValue().getValue().nombreProperty();
            else
                return clmNombre.getComputedValue(param);
        });

        clmApellido.setCellValueFactory((TreeTableColumn.CellDataFeatures<Empleado, String> param) -> {
            if (clmApellido.validateValue(param))
                return param.getValue().getValue().apellidoProperty();
            else
                return clmApellido.getComputedValue(param);
        });

        clmCargo.setCellValueFactory((TreeTableColumn.CellDataFeatures<Empleado, String> param) -> {
            if (clmCargo.validateValue(param))
                return param.getValue().getValue().cargoProperty();
            else
                return clmCargo.getComputedValue(param);
        });

        //Operaciones con la tabla
        tblEmpleado.setEditable(false);
        tblEmpleado.setShowRoot(false);
        tblEmpleado.getColumns().setAll(clmNombre, clmApellido, clmCargo);
    }

    private void setFields() {
        tblEmpleado.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtNombre.setText(newValue.getValue().getNombre());
                txtApellido.setText(newValue.getValue().getApellido());
                txtUsuario.setText(newValue.getValue().getUsuario());
                txtPass.setText(newValue.getValue().getPass());
                txtCargo.setText(newValue.getValue().getCargo());
                empleado = tblEmpleado.getSelectionModel().getSelectedItem().getValue();
                val = tblEmpleado.getSelectionModel().getSelectedItem().getValue();
            }
        });
    }

    @FXML
    void add(ActionEvent event) throws IOException {
        value = Action.AGREGAR;
        Parent parent = FXMLLoader.load(getClass().getResource("/views/ventana_empleado.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("Agregar empleado");
        stage.show();
    }

    @FXML
    void editar(ActionEvent event) throws IOException {
        if (val != null) {
            value = Action.EDITAR;
            Parent parent = FXMLLoader.load(getClass().getResource("/views/ventana_empleado.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Editar empleado");
            stage.show();
        }
        else
            Messages.setMessage("Elija","Seleccione el empleado", NotificationType.INFORMATION);
    }

    @FXML
    void eliminar(ActionEvent event) {
        if (val != null) {
            initPopUpEliminar(lista, tblEmpleado);
            popupEstaSeguro.show(btnDelete, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
        }else
            Messages.setMessage("Elija","Seleccione el empleado a eliminar", NotificationType.INFORMATION);
    }

    private void initPopUpEliminar(ObservableList<Empleado> list, JFXTreeTableView tableView) {
        var vBox = new VBox();
        var gridPane = new GridPane();

        var column1 = new ColumnConstraints();
        var column2 = new ColumnConstraints();

        var lblSeguro = new Label("¿Esta seguro de eliminar este empleado?");
        var btnAceptar = new JFXButton("Eliminar");
        var btnCancelar = new JFXButton("Cancelar");

        btnAceptar.setOnAction(x -> eliminar());
        btnCancelar.setOnAction(x -> popupEstaSeguro.hide());

        gridPane.add(btnAceptar, 0,0);
        gridPane.add(btnCancelar,1,0);

        vBox.getChildren().addAll(lblSeguro, gridPane);

        gridPane.setHgap(20);
        gridPane.getColumnConstraints().addAll(column1, column2);
        gridPane.getColumnConstraints().forEach(x -> {
            x.setHgrow(Priority.SOMETIMES);
            x.setHalignment(HPos.CENTER);
        });

        vBox.getStylesheets().setAll("/estilos/dashboard.css");
        vBox.setPadding(new Insets(10));
        vBox.setAlignment(Pos.CENTER);
        VBox.setMargin(lblSeguro, new Insets(0,0,10,0));

        popupEstaSeguro = new JFXPopup(vBox);
    }

    private void eliminar() {
        if (val.getId() != 17) {
            value = Action.ELIMINAR;
            conexion.establecerConexion();
            var success = Empleado.eliminarEmpleado(conexion.getConection(), val.getId());
            conexion.cerrarConexion();

            if (success == 1) {
                lista.removeIf(x -> x.getId() == val.getId());
                Messages.setMessage("Eliminado", "El empleado se elimino", NotificationType.SUCCESS);
            } else
                Messages.setMessage("No se elimino", "Algo salio mal, intentelo despues", NotificationType.INFORMATION);
        }else
            Messages.setMessage("No se realizo","No se puede eliminar este empleado", NotificationType.INFORMATION);
    }
}

enum Action {
    EDITAR, ELIMINAR, AGREGAR
}

