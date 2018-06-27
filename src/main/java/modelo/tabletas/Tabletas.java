package modelo.tabletas;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;
import modelo.rollo.Rollo;

import java.sql.Connection;

public class Tabletas extends RecursiveTreeObject<Tabletas> {
    private StringProperty gruesoxancho;
    private IntegerProperty piezas;
    private DoubleProperty cubicacion;
    private DoubleProperty piestabla;

    public Tabletas(String gruesoxancho, int piezas, double cubicacion, double piestabla) {
        this.gruesoxancho = new SimpleStringProperty(gruesoxancho);
        this.piezas = new SimpleIntegerProperty(piezas);
        this.cubicacion = new SimpleDoubleProperty(cubicacion);
        this.piestabla = new SimpleDoubleProperty(piestabla);
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
                        resultSet1.getDouble("pies_tabla")
                );

        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }
}
