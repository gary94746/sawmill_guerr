package modelo.otros_madera;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;
import javafx.collections.ObservableList;

public class otros_mad extends RecursiveTreeObject<otros_mad> {

    private IntegerProperty id;
    private StringProperty selPieza;
    private IntegerProperty pieza;
    private DoubleProperty cubicacion;
    private DoubleProperty pt;




    public otros_mad(String selPieza, int pieza, double cubicacion, double pt) {
        this.selPieza = new SimpleStringProperty(selPieza);
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
