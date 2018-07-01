package controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controllers.utils.Messages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import modelo.Conexion;
import modelo.empleado.Empleado;
import tray.notification.NotificationType;

import java.net.URL;
import java.util.ResourceBundle;

public class VentanaEmpleadoController implements Initializable {
    @FXML private JFXTextField txtNombre;
    @FXML private JFXTextField txtApellidos;
    @FXML private JFXTextField txtUsuario;
    @FXML private JFXPasswordField txtPass;
    @FXML private JFXComboBox<String> cmbCargo;

    private Conexion conexion = Conexion.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbCargo.getItems().addAll("Representante legal".toUpperCase(), "Jefe de Produccion".toUpperCase(), "Auxiliar administrativo".toUpperCase());

        //
        if (EmpleadoController.value == Action.EDITAR) {
            var e = EmpleadoController.empleado;
            txtNombre.setText(e.getNombre());
            txtApellidos.setText(e.getApellido());
            txtUsuario.setText(e.getUsuario());
            txtPass.setText(e.getPass());
            cmbCargo.setValue(e.getCargo());
        }
    }

    @FXML
    void agregar(ActionEvent event) {
        if (EmpleadoController.value == Action.AGREGAR)
            agregarEmpleado(event);
        else if (EmpleadoController.value == Action.EDITAR)
            editarEmpleado(event);
    }

    private void editarEmpleado(ActionEvent event) {
        if (!verificarCampos()) {
            conexion.establecerConexion();
            var em = new Empleado(
                    EmpleadoController.empleado.getId(),
                    txtNombre.getText().toUpperCase(),
                    txtApellidos.getText().toUpperCase(),
                    cmbCargo.getValue(),
                    txtUsuario.getText(),
                    txtPass.getText()
            );
            var eA = Empleado.updateEmpleado(conexion.getConection(), em);
            conexion.cerrarConexion();

            if (eA == 1) {
                var loginStage = (Stage)((Node) event.getSource()).getScene().getWindow();
                loginStage.close();
                EmpleadoController.lista.removeIf(x -> EmpleadoController.empleado.getId() == x.getId());
                EmpleadoController.lista.add(em);
                Messages.setMessage("Editado", "Se edito el empleado", NotificationType.SUCCESS);
            }
        }else
            Messages.setMessage("Verifique","Rellene todos los campos", NotificationType.INFORMATION);
    }

    private void agregarEmpleado(ActionEvent event) {
        if (!verificarCampos()) {
            conexion.establecerConexion();
            var eA = Empleado.addEmpleado(conexion.getConection(), new Empleado(
                    txtNombre.getText(),
                    txtApellidos.getText(),
                    cmbCargo.getValue(),
                    txtUsuario.getText(),
                    txtPass.getText()
            ));
            conexion.cerrarConexion();

            if (eA != null) {
                var loginStage = (Stage)((Node) event.getSource()).getScene().getWindow();
                loginStage.close();
                EmpleadoController.lista.add(eA);
                Messages.setMessage("Agregado", "Se agrego el nuevo empleado", NotificationType.SUCCESS);
            }
        }else
            Messages.setMessage("Verifique","Rellene todos los campos", NotificationType.INFORMATION);
    }

    private boolean verificarCampos() {
        return txtUsuario.getText().equals("")
                || txtApellidos.getText().equals("")
                || txtUsuario.getText().equals("")
                || txtPass.getText().equals("")
                || cmbCargo.getValue() == null;
    }
}
