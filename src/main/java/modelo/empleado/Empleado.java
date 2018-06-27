package modelo.empleado;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;

public class Empleado extends RecursiveTreeObject<Empleado> {
    private IntegerProperty id;
    private StringProperty nombre;
    private StringProperty apellido;
    private StringProperty cargo;
    private StringProperty usuario;
    private StringProperty pass;

    public Empleado(int id, String nombre, String apellido, String cargo, String usuario, String pass) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.cargo = new SimpleStringProperty(cargo);
        this.usuario = new SimpleStringProperty(usuario);
        this.pass =  new SimpleStringProperty(pass);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getApellido() {
        return apellido.get();
    }

    public StringProperty apellidoProperty() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido.set(apellido);
    }

    public String getCargo() {
        return cargo.get();
    }

    public StringProperty cargoProperty() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo.set(cargo);
    }

    public String getUsuario() {
        return usuario.get();
    }

    public StringProperty usuarioProperty() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario.set(usuario);
    }

    public String getPass() {
        return pass.get();
    }

    public StringProperty passProperty() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass.set(pass);
    }

    public static void loadEmployees(Connection connection, ObservableList<Empleado> list) {
        try{
            var query = "select id, UPPER(nombre), UPPER(apellidos), UPPER(puesto), usuario, pass from empleado;";
            var stm = connection.createStatement();
            var rs = stm.executeQuery(query);

            while(rs.next()) {
                list.add(new Empleado(
                        rs.getInt("id"),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString("usuario"),
                        rs.getString("pass")
                ));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
