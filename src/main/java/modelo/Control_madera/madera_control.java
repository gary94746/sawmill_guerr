package modelo.Control_madera;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class madera_control extends RecursiveTreeObject<madera_control> {

    private IntegerProperty id;
    private StringProperty largo;
    private StringProperty grueso;
    private StringProperty ancho;
    private StringProperty clase;
    private IntegerProperty pieza;
    private DoubleProperty cubicacion;
    private DoubleProperty pt;



    public madera_control(String grueso, String ancho, String clase, int pieza, double cubicacion, double pt,String largo) {
        this.grueso = new SimpleStringProperty(grueso);
        this.ancho = new SimpleStringProperty(ancho);
        this.clase = new SimpleStringProperty(clase);
        this.pieza = new SimpleIntegerProperty(pieza);
        this.cubicacion = new SimpleDoubleProperty(cubicacion);
        this.pt = new SimpleDoubleProperty(pt);
        this.largo = new SimpleStringProperty(largo);
    }

    public madera_control(int id,String grueso, String ancho, String clase, int pieza, double cubicacion, double pt,String largo) {


        this.id = new SimpleIntegerProperty(id);
        this.grueso = new SimpleStringProperty(grueso);
        this.ancho = new SimpleStringProperty(ancho);
        this.clase = new SimpleStringProperty(clase);
        this.pieza = new SimpleIntegerProperty(pieza);
        this.cubicacion = new SimpleDoubleProperty(cubicacion);
        this.pt = new SimpleDoubleProperty(pt);
        this.largo = new SimpleStringProperty(largo);
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

    public String getGrueso() {
        return grueso.get();
    }

    public StringProperty gruesoProperty() {
        return grueso;
    }

    public void setGrueso(String grueso) {
        this.grueso.set(grueso);
    }

    public String getAncho() {
        return ancho.get();
    }

    public StringProperty anchoProperty() {
        return ancho;
    }

    public void setAncho(String ancho) {
        this.ancho.set(ancho);
    }

    public String getClase() {
        return clase.get();
    }

    public StringProperty claseProperty() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase.set(clase);
    }

    public int getPieza() {
        return pieza.get();
    }

    public IntegerProperty piezaProperty() {
        return pieza;
    }

    public void setPieza(int pieza) {
        this.pieza.set(pieza);
    }

    public double getCubicacion() {
        return cubicacion.get();
    }

    public DoubleProperty cubicacionProperty() {
        return cubicacion;
    }

    public void setCubicacion(double cubicacion) {
        this.cubicacion.set(cubicacion);
    }

    public double getPt() {
        return pt.get();
    }

    public DoubleProperty ptProperty() {
        return pt;
    }

    public void setPt(double pt) {
        this.pt.set(pt);
    }

    public String getLargo() { return largo.get(); }

    public StringProperty largoProperty() {return largo;}

    public void setLargo(String largo) {this.largo.set(largo);}

    public static madera_control addControl(Connection connection, madera_control control) {
        try {
            var control_added = "SELECT * FROM add_control('" +
                    control.getGrueso()+"'"+
                    ",'"+control.getAncho() +"'"+
                    ",'"+control.getClase() +""+
                    "'," + control.getPieza() + "" +
                    "," + control.getCubicacion() + "" +
                    "," +control.getPt() +","+
                    "'"+control.getLargo()+"'"+");";
            //System.out.println(control_added);

            var statementP = connection.createStatement();
            var resultSet1 = statementP.executeQuery(control_added);
            if (resultSet1.next())
                return new madera_control(
                        resultSet1.getInt("id"),
                        resultSet1.getString("grueso"),
                        resultSet1.getString("ancho"),
                        resultSet1.getString("clase"),
                        resultSet1.getInt("piezas"),
                        resultSet1.getDouble("cubicacion"),
                        resultSet1.getDouble("pies_tabla"),
                        resultSet1.getString("largo"));

        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }

    public static void obtenerDatos(Connection connection, ObservableList<madera_control> list,String grueso,String clase,String largo) {

        try {
            var datos = "select * from control_produccion where fecha= current_date and grueso like '" +grueso+ "' "+"and clase like '" + clase + "'" +"and largo like '" +largo+ "' ";

            System.out.println(datos);
            var statementP = connection.createStatement();
            var resultSet1 = statementP.executeQuery(datos);

            while (resultSet1.next()) {
                list.add(new madera_control(
                        resultSet1.getInt("id"),
                        resultSet1.getString("grueso"),
                        resultSet1.getString("ancho"),
                        resultSet1.getString("clase"),
                        resultSet1.getInt("piezas"),
                        resultSet1.getDouble("cubicacion"),
                        resultSet1.getDouble("pies_tabla"),
                        resultSet1.getString("largo")));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void obtenerDatosTodos(Connection connection, ObservableList<madera_control> list, String grueso,String largo) {
        try {
            var datos = "select * from control_produccion where fecha= current_date and grueso like '" +grueso+ "' "+"and largo like '" +largo+ "' ";

            System.out.println(datos);
            var statementP = connection.createStatement();
            var resultSet1 = statementP.executeQuery(datos);

            while (resultSet1.next()) {
                list.add(new madera_control(
                        resultSet1.getInt("id"),
                        resultSet1.getString("grueso"),
                        resultSet1.getString("ancho"),
                        resultSet1.getString("clase"),
                        resultSet1.getInt("piezas"),
                        resultSet1.getDouble("cubicacion"),
                        resultSet1.getDouble("pies_tabla"),
                        resultSet1.getString("largo")));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public static int eliminarOtros(Connection connection, int id) {
        try {
            final String query = "DELETE FROM control_produccion WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            return statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
    }

    public static void historial(Connection connection, ObservableList<madera_control> list, String date, String grueso) {
        try {

            System.out.println(date);
            var query = "SELECT * FROM control_produccion where fecha = '" + date + "'::date"+" and grueso like '" +grueso+ "'";
            System.out.println(query);
            var statementP = connection.createStatement();
            var resultSet1 = statementP.executeQuery(query);

            while (resultSet1.next()) {
                list.add((new madera_control(
                        resultSet1.getInt("id"),
                        resultSet1.getString("grueso"),
                        resultSet1.getString("ancho"),
                        resultSet1.getString("clase"),
                        resultSet1.getInt("piezas"),
                        resultSet1.getDouble("cubicacion"),
                        resultSet1.getDouble("pies_tabla"),
                        resultSet1.getString("largo"))));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void obtenerDatosFecha(Connection connection, ObservableList<madera_control> list,String date,String grueso,String clase,String largo) {

        try {
            var datos = "select * from control_produccion where fecha = '" + date + "'::date"+" and grueso like '" +grueso+ "' "+"and clase like '" + clase + "'" +"and largo like '" +largo+ "' ";

            System.out.println(datos);
            var statementP = connection.createStatement();
            var resultSet1 = statementP.executeQuery(datos);

            while (resultSet1.next()) {
                list.add(new madera_control(
                        resultSet1.getInt("id"),
                        resultSet1.getString("grueso"),
                        resultSet1.getString("ancho"),
                        resultSet1.getString("clase"),
                        resultSet1.getInt("piezas"),
                        resultSet1.getDouble("cubicacion"),
                        resultSet1.getDouble("pies_tabla"),
                        resultSet1.getString("largo")));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void obtenerDatosFechaTodos(Connection connection, ObservableList<madera_control> list,String date, String grueso,String largo) {
        try {
            var datos = "select * from control_produccion where fecha = '" + date + "'::date"+" and grueso like '" +grueso+ "' "+"and largo like '" +largo+ "' ";

            System.out.println(datos);
            var statementP = connection.createStatement();
            var resultSet1 = statementP.executeQuery(datos);

            while (resultSet1.next()) {
                list.add(new madera_control(
                        resultSet1.getInt("id"),
                        resultSet1.getString("grueso"),
                        resultSet1.getString("ancho"),
                        resultSet1.getString("clase"),
                        resultSet1.getInt("piezas"),
                        resultSet1.getDouble("cubicacion"),
                        resultSet1.getDouble("pies_tabla"),
                        resultSet1.getString("largo")));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }




}
