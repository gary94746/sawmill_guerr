package modelo.rollo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Rollo extends RecursiveTreeObject<Rollo> {
    private IntegerProperty id;
    private IntegerProperty num;
    private DoubleProperty d1;
    private DoubleProperty d2;
    private DoubleProperty dt;
    private DoubleProperty vol;

    /*public Rollo(int id, int num, double d1, double d2, double dt, double vol) {
        this.id = new SimpleIntegerProperty(id);
        this.num = new SimpleIntegerProperty(num);
        this.d1 = new SimpleDoubleProperty(d1);
        this.d2 = new SimpleDoubleProperty(d2);
        this.dt = new SimpleDoubleProperty(dt);
        this.vol = new SimpleDoubleProperty(vol);
    }*/

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
}
