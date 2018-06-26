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

    /**public static Rollo addRollo(Connection connection, int numero, double diametro1, double diametro2) {
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
    }*/
}
