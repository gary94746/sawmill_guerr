package modelo.empleado;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import controllers.utils.Messages;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import tray.notification.NotificationType;

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

    public Empleado(String nombre, String apellido, String cargo, String usuario, String pass) {
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

    public static Empleado addEmpleado(Connection connection, Empleado e) {
        try {
            var query = "SELECT * FROM add_empleado('"+e.getNombre()+"', '"+e.getApellido()+"', '"+e.getCargo()+"', '"+e.getUsuario()+"', '"+e.getPass()+"')";

            var stm = connection.createStatement();
            var rs = stm.executeQuery(query);

            if (rs.next())
                return new Empleado(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellidos"),
                    rs.getString("puesto"),
                    rs.getString("usuario"),
                    rs.getString("pass")
                );
        }catch(SQLException exception) {
            if (exception.getMessage().contains("duplicate key value"))
                Messages.setMessage("Usuario incorrecto","Ese usuario ya esta registrado", NotificationType.INFORMATION);
        }

        return null;
    }

    public static int eliminarEmpleado(Connection connection, int id) {
        try {
            var query = "DELETE FROM empleado WHERE id = ?";
            var stm = connection.prepareStatement(query);
            stm.setInt(1, id);

            return stm.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public static int updateEmpleado(Connection connection, Empleado e) {
        try {
            var query = "UPDATE aserradero_warrior.empleado\n" +
                    "\tSET nombre=?, apellidos=?, puesto=?, usuario=?, pass=?\n" +
                    "\tWHERE id = ?;";
            var stm = connection.prepareStatement(query);

            stm.setString(1, e.getNombre());
            stm.setString(2, e.getApellido());
            stm.setString(3,e.getCargo());
            stm.setString(4, e.getUsuario());
            stm.setString(5, e.getPass());
            stm.setInt(6, e.getId());

            return stm.executeUpdate();

        }catch (SQLException exception) {
            System.out.println(exception.getMessage());
            if (exception.getMessage().contains("duplicate key value"))
                Messages.setMessage("Usuario incorrecto","Ese usuario ya esta registrado", NotificationType.INFORMATION);
        }

        return 0;
    }
}
