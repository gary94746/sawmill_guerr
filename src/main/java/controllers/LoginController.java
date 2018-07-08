package controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controllers.utils.Messages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import modelo.Conexion;
import tray.notification.NotificationType;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginController {
    private Conexion conexion = Conexion.getInstance();
    @FXML private JFXTextField txtUser;
    @FXML private JFXPasswordField txtPass;

    @FXML
    void login(ActionEvent event) throws IOException {
        //Close login stage
        var loginStage = (Stage)((Node) event.getSource()).getScene().getWindow();

        //Open the dashboard window
        if (isCorrect()) {
            loginStage.close();

            Parent parent = FXMLLoader.load(getClass().getResource("/views/dashboard.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/servicio02.png")));
            stage.setTitle("Menu principal");
            stage.show();

            Messages.setMessage("Bienvenido","Usuario: "+ txtUser.getText(), NotificationType.SUCCESS);
        }else {
            Messages.setMessage("Verifique","Ingrese datos validos", NotificationType.INFORMATION);
        }
    }

    private boolean isCorrect() {
        conexion.establecerConexion();
        var c = getLogin(conexion.getConection(), txtUser.getText(), txtPass.getText());
        conexion.cerrarConexion();

        return c;
    }

    private boolean getLogin(Connection conn, String user, String pass) {
        try {
            var query = "SELECT * FROM existUser('" + user + "','" +pass+ "')";
            var stm = conn.createStatement();

            var rs = stm.executeQuery(query);

            if (rs.next())
                return rs.getBoolean(1);
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
