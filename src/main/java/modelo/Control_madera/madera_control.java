package modelo.Control_madera;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;

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
}
