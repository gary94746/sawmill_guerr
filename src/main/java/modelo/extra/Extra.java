package modelo.extra;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Extra extends RecursiveTreeObject<Extra> {
    private DoubleProperty longitudColumn2;
    private DoubleProperty longitudColumn3;
    private DoubleProperty longitudColumn4;
    private DoubleProperty longitudColumn5;
    private DoubleProperty longitudColumn6;
    private DoubleProperty longitudColumn7;

    public Extra(double longitudColumn2, double longitudColumn3, double longitudColumn4, double longitudColumn5, double longitudColumn6, double longitudColumn7) {
        this.longitudColumn2 = new SimpleDoubleProperty(longitudColumn2);
        this.longitudColumn3 = new SimpleDoubleProperty(longitudColumn3);
        this.longitudColumn4 = new SimpleDoubleProperty(longitudColumn4);
        this.longitudColumn5 = new SimpleDoubleProperty(longitudColumn5);
        this.longitudColumn6 = new SimpleDoubleProperty(longitudColumn6);
        this.longitudColumn7 = new SimpleDoubleProperty(longitudColumn7);
    }

    public double getLongitudColumn2() {
        return longitudColumn2.get();
    }

    public DoubleProperty longitudColumn2Property() {
        return longitudColumn2;
    }

    public void setLongitudColumn2(double longitudColumn2) {
        this.longitudColumn2.set(longitudColumn2);
    }

    public double getLongitudColumn3() {
        return longitudColumn3.get();
    }

    public DoubleProperty longitudColumn3Property() {
        return longitudColumn3;
    }

    public void setLongitudColumn3(double longitudColumn3) {
        this.longitudColumn3.set(longitudColumn3);
    }

    public double getLongitudColumn4() {
        return longitudColumn4.get();
    }

    public DoubleProperty longitudColumn4Property() {
        return longitudColumn4;
    }

    public void setLongitudColumn4(double longitudColumn4) {
        this.longitudColumn4.set(longitudColumn4);
    }

    public double getLongitudColumn5() {
        return longitudColumn5.get();
    }

    public DoubleProperty longitudColumn5Property() {
        return longitudColumn5;
    }

    public void setLongitudColumn5(double longitudColumn5) {
        this.longitudColumn5.set(longitudColumn5);
    }

    public double getLongitudColumn6() {
        return longitudColumn6.get();
    }

    public DoubleProperty longitudColumn6Property() {
        return longitudColumn6;
    }

    public void setLongitudColumn6(double longitudColumn6) {
        this.longitudColumn6.set(longitudColumn6);
    }

    public double getLongitudColumn7() {
        return longitudColumn7.get();
    }

    public DoubleProperty longitudColumn7Property() {
        return longitudColumn7;
    }

    public void setLongitudColumn7(double longitudColumn7) {
        this.longitudColumn7.set(longitudColumn7);
    }
}
