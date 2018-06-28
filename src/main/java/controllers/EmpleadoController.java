package controllers;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controllers.utils.Messages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import modelo.Conexion;
import modelo.empleado.Empleado;

import java.net.URL;
import java.util.ResourceBundle;

public class EmpleadoController implements Initializable {

    @FXML private JFXTreeTableView<Empleado> tblEmpleado;
    @FXML private JFXTextField txtNombre;
    @FXML private JFXTextField txtApellido;
    @FXML private JFXTextField txtUsuario;
    @FXML private JFXPasswordField txtPass;
    @FXML private JFXTextField txtCargo;

    @FXML
    private JFXButton bntAdd;

    @FXML
    private JFXButton btnEdit;

    @FXML
    private JFXButton btnDelete;



    private ObservableList<Empleado> lista;
    private Conexion conexion = Conexion.getInstance();

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

        JFXTreeTableColumn<Empleado, String> clmNombre = new JFXTreeTableColumn<>("Nombre");
        JFXTreeTableColumn<Empleado, String> clmApellido = new JFXTreeTableColumn<>("Apellidos");
        JFXTreeTableColumn<Empleado, String> clmCargo = new JFXTreeTableColumn<>("Cargo");

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
            }
        });
    }

    @FXML
    void add(ActionEvent event) {

    }

    @FXML
    void editar(ActionEvent event) {

    }

    @FXML
    void eliminar(ActionEvent event) {

    }


}
