package modelo.tabletas;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;

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
}
