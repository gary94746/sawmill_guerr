package modelo.otros_madera;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class otros_mad extends RecursiveTreeObject<otros_mad> {

    private IntegerProperty id;
    private StringProperty selPieza;
    private IntegerProperty pieza;
    private DoubleProperty cubicacion;
    private DoubleProperty pt;




    public otros_mad(int id,String selPieza, int pieza, double cubicacion, double pt) {
        this.id    = new SimpleIntegerProperty(id);
        this.selPieza = new SimpleStringProperty(selPieza);
        this.pieza = new SimpleIntegerProperty(pieza);
        this.cubicacion = new SimpleDoubleProperty(cubicacion);
        this.pt = new SimpleDoubleProperty(pt);
    }

    public otros_mad(String selPieza, int pieza, double cubicacion, double pt) {
        this.selPieza = new SimpleStringProperty(selPieza);
        this.pieza = new SimpleIntegerProperty(pieza);
        this.cubicacion = new SimpleDoubleProperty(cubicacion);
        this.pt = new SimpleDoubleProperty(pt);
    }

    public static otros_mad addOtros(Connection connection, otros_mad otros) {
       try {
           var others_added = "SELECT * FROM add_otros('" +
                   otros.getSelPieza() +
                   "'," + otros.getPieza() + "" +
                   "," + otros.getCubicacion() + "," +
                   otros.getPt() + ");";
           System.out.println(others_added);

           var statementP = connection.createStatement();
           var resultSet1 = statementP.executeQuery(others_added);

           if (resultSet1.next())
               return new otros_mad(
                       resultSet1.getInt("id"),
                       resultSet1.getString("otros"),
                       resultSet1.getInt("piezas"),
                       resultSet1.getDouble("cubicacion"),
                       resultSet1.getDouble("pies_tabla"));

       }catch (Exception e){
           e.printStackTrace();

       }
        return null;
    }




    public static otros_mad updateOtros(Connection connection, otros_mad otros){
        try {
            var others_added = "SELECT * FROM update_otros(" +
                    ""  + otros.getId()+
                    ",'"+otros.getSelPieza()    +"'"+
                    "',"+ otros.getPieza()      + "" +
                    "," + otros.getCubicacion() + "," +
                    otros.getPt() + ");";
            System.out.println(others_added);

            var statementP = connection.createStatement();
            var resultSet1 = statementP.executeQuery(others_added);

            if (resultSet1.next())
                return new otros_mad(
                        resultSet1.getInt("id"),
                        resultSet1.getString("otros"),
                        resultSet1.getInt("piezas"),
                        resultSet1.getDouble("cubicacion"),
                        resultSet1.getDouble("pies_tabla"));

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void obtenerDatos(Connection connection, ObservableList<otros_mad> list) {
        try {
            var datos = "SELECT * FROM otras where fecha = current_date";
            System.out.println(datos);
            var statementP = connection.createStatement();
            var resultSet1 = statementP.executeQuery(datos);

            while (resultSet1.next()) {
                list.add(new otros_mad(
                        resultSet1.getInt("id"),
                        resultSet1.getString("otros"),
                        resultSet1.getInt("piezas"),
                        resultSet1.getDouble("cubicacion"),
                        resultSet1.getDouble("pies_tabla")));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static int eliminarOtros(Connection connection, int id) {
        try {
            final String query = "DELETE FROM otras WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            return statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
    }

    public static void historial(Connection connection, ObservableList<otros_mad> list, String date) {
        try {

            System.out.println(date);
            var query = "SELECT * FROM otras where fecha = '" + date + "'::date";
            System.out.println(query);
            var statementP = connection.createStatement();
            var resultSet1 = statementP.executeQuery(query);

            while (resultSet1.next()) {
                list.add((new otros_mad(
                        resultSet1.getInt("id"),
                        resultSet1.getString("otros"),
                        resultSet1.getInt("piezas"),
                        resultSet1.getDouble("cubicacion"),
                        resultSet1.getDouble("pies_tabla"))));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public String getSelPieza() {
        return selPieza.get();
    }

    public StringProperty selPiezaProperty() {
        return selPieza;
    }

    public void setSelPieza(String selPieza) {
        this.selPieza.set(selPieza);
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
}
