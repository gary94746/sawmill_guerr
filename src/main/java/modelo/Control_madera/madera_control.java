package modelo.Control_madera;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;

public class madera_control extends RecursiveTreeObject<madera_control> {

    private IntegerProperty id;
    private DoubleProperty grueso;
    private DoubleProperty ancho;
    private StringProperty clase;
    private IntegerProperty pieza;
    private DoubleProperty cubicacion;
    private DoubleProperty pt;

    public madera_control(int id,double grueso, double ancho, String clase, int pieza, double cubicacion, double pt) {
        this.id = new SimpleIntegerProperty(id);
        this.grueso = new SimpleDoubleProperty(grueso);
        this.ancho = new SimpleDoubleProperty(ancho);
        this.clase = new SimpleStringProperty(clase);
        this.pieza = new SimpleIntegerProperty(pieza);
        this.cubicacion = new SimpleDoubleProperty(cubicacion);
        this.pt = new SimpleDoubleProperty(pt);
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

    public double getGrueso() {
        return grueso.get();
    }

    public DoubleProperty gruesoProperty() {
        return grueso;
    }

    public void setGrueso(double grueso) {
        this.grueso.set(grueso);
    }

    public double getAncho() {
        return ancho.get();
    }

    public DoubleProperty anchoProperty() {
        return ancho;
    }

    public void setAncho(double ancho) {
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
}
