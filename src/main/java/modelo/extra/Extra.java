package modelo.extra;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Extra extends RecursiveTreeObject<Extra> {
    private IntegerProperty longitudColumn2;
    private IntegerProperty longitudColumn3;
    private IntegerProperty longitudColumn4;
    private IntegerProperty longitudColumn5;
    private IntegerProperty longitudColumn6;
    private IntegerProperty longitudColumn7;

    public Extra(int longitudColumn2, int longitudColumn3, int longitudColumn4, int longitudColumn5, int longitudColumn6, int longitudColumn7) {
        this.longitudColumn2 = new SimpleIntegerProperty(longitudColumn2);
        this.longitudColumn3 = new SimpleIntegerProperty(longitudColumn3);
        this.longitudColumn4 = new SimpleIntegerProperty(longitudColumn4);
        this.longitudColumn5 = new SimpleIntegerProperty(longitudColumn5);
        this.longitudColumn6 = new SimpleIntegerProperty(longitudColumn6);
        this.longitudColumn7 = new SimpleIntegerProperty(longitudColumn7);
    }

    public int getLongitudColumn2() {
        return longitudColumn2.get();
    }

    public IntegerProperty longitudColumn2Property() {
        return longitudColumn2;
    }

    public void setLongitudColumn2(int longitudColumn2) {
        this.longitudColumn2.set(longitudColumn2);
    }

    public int getLongitudColumn3() {
        return longitudColumn3.get();
    }

    public IntegerProperty longitudColumn3Property() {
        return longitudColumn3;
    }

    public void setLongitudColumn3(int longitudColumn3) {
        this.longitudColumn3.set(longitudColumn3);
    }

    public int getLongitudColumn4() {
        return longitudColumn4.get();
    }

    public IntegerProperty longitudColumn4Property() {
        return longitudColumn4;
    }

    public void setLongitudColumn4(int longitudColumn4) {
        this.longitudColumn4.set(longitudColumn4);
    }

    public int getLongitudColumn5() {
        return longitudColumn5.get();
    }

    public IntegerProperty longitudColumn5Property() {
        return longitudColumn5;
    }

    public void setLongitudColumn5(int longitudColumn5) {
        this.longitudColumn5.set(longitudColumn5);
    }

    public int getLongitudColumn6() {
        return longitudColumn6.get();
    }

    public IntegerProperty longitudColumn6Property() {
        return longitudColumn6;
    }

    public void setLongitudColumn6(int longitudColumn6) {
        this.longitudColumn6.set(longitudColumn6);
    }

    public int getLongitudColumn7() {
        return longitudColumn7.get();
    }

    public IntegerProperty longitudColumn7Property() {
        return longitudColumn7;
    }

    public void setLongitudColumn7(int longitudColumn7) {
        this.longitudColumn7.set(longitudColumn7);
    }
}
