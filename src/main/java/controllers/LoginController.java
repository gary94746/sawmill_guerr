package controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import modelo.Conexion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginController {
    private Conexion conexion = Conexion.getInstance();
    @FXML private JFXTextField txtUser;
    @FXML private JFXPasswordField txtPass;

    @FXML
    void login(ActionEvent event) throws IOException {
        txtUser.setText("martin09");
        txtPass.setText("48551qw");
        //Close login stage
        var loginStage = (Stage)((Node) event.getSource()).getScene().getWindow();

        //Open the dashboard window
        if (isCorrect()) {
            loginStage.close();
            Parent parent = FXMLLoader.load(getClass().getResource("/views/dashboard.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/60c83449-dd46-44df-9384-0713810ea1c3.jpeg")));
            stage.setTitle("Menu principal");
            stage.show();
        }else {
            System.out.println("none");
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
