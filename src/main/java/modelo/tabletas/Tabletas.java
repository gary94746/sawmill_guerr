package modelo.tabletas;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import modelo.rollo.Rollo;

import java.sql.Connection;

public class Tabletas extends RecursiveTreeObject<Tabletas> {
    private StringProperty gruesoxancho;
    private IntegerProperty piezas;
    private DoubleProperty cubicacion;
    private DoubleProperty piestabla;
    private DoubleProperty longitud;

    public Tabletas(String gruesoxancho, int piezas, double cubicacion, double piestabla, double longitud) {
        this.gruesoxancho = new SimpleStringProperty(gruesoxancho);
        this.piezas = new SimpleIntegerProperty(piezas);
        this.cubicacion = new SimpleDoubleProperty(cubicacion);
        this.piestabla = new SimpleDoubleProperty(piestabla);
        this.longitud = new SimpleDoubleProperty(longitud);
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
                list.add(new Tabletas(resultSet1.getString("gruesoporancho"), resultSet1.getInt("piezas"), resultSet1.getDouble("cubicacion"), resultSet1.getDouble("pies_tabla"), resultSet1.getDouble("longitud")));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
