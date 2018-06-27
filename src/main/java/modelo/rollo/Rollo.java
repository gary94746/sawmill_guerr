package modelo.rollo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import modelo.otros_madera.otros_mad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Rollo extends RecursiveTreeObject<Rollo> {
    private IntegerProperty id;
    private IntegerProperty num;
    private DoubleProperty d1;
    private DoubleProperty d2;
    private DoubleProperty dt;
    private DoubleProperty vol;

    public Rollo(int id, int num, double d1, double d2, double dt, double vol) {
        this.id = new SimpleIntegerProperty(id);
        this.num = new SimpleIntegerProperty(num);
        this.d1 = new SimpleDoubleProperty(d1);
        this.d2 = new SimpleDoubleProperty(d2);
        this.dt = new SimpleDoubleProperty(dt);
        this.vol = new SimpleDoubleProperty(vol);
    }

    public Rollo(int num, double d1, double d2, double dt, double vol) {
        this.num = new SimpleIntegerProperty(num);
        this.d1 = new SimpleDoubleProperty(d1);
        this.d2 = new SimpleDoubleProperty(d2);
        this.dt = new SimpleDoubleProperty(dt);
        this.vol = new SimpleDoubleProperty(vol);
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

    public int getNum() {
        return num.get();
    }

    public IntegerProperty numProperty() {
        return num;
    }

    public void setNum(int num) {
        this.num.set(num);
    }

    public double getD1() {
        return d1.get();
    }

    public DoubleProperty d1Property() {
        return d1;
    }

    public void setD1(double d1) {
        this.d1.set(d1);
    }

    public double getD2() {
        return d2.get();
    }

    public DoubleProperty d2Property() {
        return d2;
    }

    public void setD2(double d2) {
        this.d2.set(d2);
    }

    public double getDt() {
        return dt.get();
    }

    public DoubleProperty dtProperty() {
        return dt;
    }

    public void setDt(double dt) {
        this.dt.set(dt);
    }

    public double getVol() {
        return vol.get();
    }

    public DoubleProperty volProperty() {
        return vol;
    }

    public void setVol(double vol) {
        this.vol.set(vol);
    }

    public static double getVolumenRollosFecha(Connection connection, String date1, String date2) {
        try {
            var stm = connection.createStatement();
            var rs  =stm.executeQuery("SELECT * FROM get_volumen_rollos('"+date1+"'::date,'"+date2+"'::date)");

            if (rs.next())
                return rs.getDouble(1);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return -1.0;
    }

    public static Rollo addRollo(Connection connection, int numero, double diametro1, double diametro2) {
        try {
            var roll_added = "SELECT * FROM add_rollos(" + numero + "," + diametro1 + "," + diametro2 + ");";
            System.out.println(roll_added);

            var statementP = connection.createStatement();
            var resultSet1 = statementP.executeQuery(roll_added);

            if (resultSet1.next())
                return new Rollo(
                        resultSet1.getInt("numero"),
                        resultSet1.getInt("diametro1"),
                        resultSet1.getDouble("diametro2"),
                        resultSet1.getDouble("diametro_promedio"),
                        resultSet1.getDouble("volumen")
                );

        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }

    public static void obtenerDatos(Connection connection, ObservableList<Rollo> list) {
        try {
            var datos = "SELECT * FROM rollo where fecha = current_date order by numero";

            var statementP = connection.createStatement();
            var resultSet1 = statementP.executeQuery(datos);

            while (resultSet1.next()) {
                list.add(new Rollo(resultSet1.getInt("id"), resultSet1.getInt("numero"),  resultSet1.getInt("diametro1"), resultSet1.getDouble("diametro2"),
                        resultSet1.getDouble("diametro_promedio"), resultSet1.getDouble("volumen")));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static int eliminarRollo(Connection connection, int id) {
        try {
            final String query = "DELETE FROM rollo WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            return statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
    }


}
