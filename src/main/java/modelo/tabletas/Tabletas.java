package modelo.tabletas;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Tabletas extends RecursiveTreeObject<Tabletas> {
    private IntegerProperty id;
    private StringProperty gruesoxancho;
    private IntegerProperty piezas;
    private DoubleProperty cubicacion;
    private DoubleProperty piestabla;
    private DoubleProperty longitud;

    public Tabletas(int id, String gruesoxancho, int piezas, double cubicacion, double piestabla, double longitud) {
        this.id = new SimpleIntegerProperty(id);
        this.gruesoxancho = new SimpleStringProperty(gruesoxancho);
        this.piezas = new SimpleIntegerProperty(piezas);
        this.cubicacion = new SimpleDoubleProperty(cubicacion);
        this.piestabla = new SimpleDoubleProperty(piestabla);
        this.longitud = new SimpleDoubleProperty(longitud);
    }

    public Tabletas(String gruesoxancho, int piezas, double cubicacion, double piestabla, double longitud) {
        this.gruesoxancho = new SimpleStringProperty(gruesoxancho);
        this.piezas = new SimpleIntegerProperty(piezas);
        this.cubicacion = new SimpleDoubleProperty(cubicacion);
        this.piestabla = new SimpleDoubleProperty(piestabla);
        this.longitud = new SimpleDoubleProperty(longitud);
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

    public String getGruesoxancho() {
        return gruesoxancho.get();
    }

    public StringProperty gruesoxanchoProperty() {
        return gruesoxancho;
    }

    public void setGruesoxancho(String gruesoxancho) {
        this.gruesoxancho.set(gruesoxancho);
    }

    public int getPiezas() {
        return piezas.get();
    }

    public IntegerProperty piezasProperty() {
        return piezas;
    }

    public void setPiezas(int piezas) {
        this.piezas.set(piezas);
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

    public double getPiestabla() {
        return piestabla.get();
    }

    public DoubleProperty piestablaProperty() {
        return piestabla;
    }

    public void setPiestabla(double piestabla) {
        this.piestabla.set(piestabla);
    }

    public double getLongitud() {
        return longitud.get();
    }

    public DoubleProperty longitudProperty() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud.set(longitud);
    }

    public static Tabletas addTableta(Connection connection, String gruesoporancho, int piezas, double cubicacion, double pies_tabla, int longitud) {
        try {
            var tab_added = "SELECT * FROM add_tabletas('" + gruesoporancho + "'," + piezas + "," + cubicacion + "," + pies_tabla + "," + longitud + ");";

            var statementP = connection.createStatement();
            var resultSet1 = statementP.executeQuery(tab_added);

            if (resultSet1.next())
                return new Tabletas(
                        resultSet1.getInt("id"),
                        resultSet1.getString("gruesoporancho"),
                        resultSet1.getInt("piezas"),
                        resultSet1.getDouble("cubicacion"),
                        resultSet1.getDouble("pies_tabla"),
                        resultSet1.getDouble("longitud")
                );

        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }

    public static void obtenerDatos(Connection connection, ObservableList<Tabletas> list) {
        try {
            var datos = "SELECT * FROM tabletas where fecha = current_date order by longitud asc";

            var statementP = connection.createStatement();
            var resultSet1 = statementP.executeQuery(datos);

            while (resultSet1.next()) {
                list.add(new Tabletas(resultSet1.getInt("id"),resultSet1.getString("gruesoporancho"), resultSet1.getInt("piezas"), resultSet1.getDouble("cubicacion"), resultSet1.getDouble("pies_tabla"), resultSet1.getDouble("longitud")));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void obtenerDatosFiltrados(Connection connection, ObservableList<Tabletas> list, String combo) {
        try {
            var datos = "SELECT * FROM tabletas where fecha = current_date and longitud = " + Integer.parseInt(combo);

            var statementP = connection.createStatement();
            var resultSet1 = statementP.executeQuery(datos);

            while (resultSet1.next()) {
                list.add(new Tabletas(resultSet1.getInt("id"),resultSet1.getString("gruesoporancho"), resultSet1.getInt("piezas"), resultSet1.getDouble("cubicacion"), resultSet1.getDouble("pies_tabla"), resultSet1.getDouble("longitud")));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void historial(Connection connection, ObservableList<Tabletas> list, String date) {
        try {

            System.out.println(date);
            var query = "SELECT * FROM tabletas where fecha = '" + date + "'::date order by longitud";

            var statementP = connection.createStatement();
            var resultSet1 = statementP.executeQuery(query);

            while (resultSet1.next()) {
                list.add(new Tabletas(resultSet1.getInt("id"),resultSet1.getString("gruesoporancho"), resultSet1.getInt("piezas"), resultSet1.getDouble("cubicacion"), resultSet1.getDouble("pies_tabla"), resultSet1.getDouble("longitud")));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int eliminarTableta(Connection connection, int id) {
        try {
            final String query = "DELETE FROM tabletas WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            return statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
    }
}
