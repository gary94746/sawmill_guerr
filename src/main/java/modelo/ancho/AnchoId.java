package modelo.ancho;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AnchoId {
    private IntegerProperty id;
    private StringProperty ancho;

    public AnchoId(int id, String ancho) {
        this.id = new SimpleIntegerProperty(id);
        this.ancho = new SimpleStringProperty(ancho);
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

    public String getAncho() {
        return ancho.get();
    }

    public StringProperty anchoProperty() {
        return ancho;
    }

    public void setAncho(String ancho) {
        this.ancho.set(ancho);
    }


    @Override
    public String toString() {
        return ancho.get();
    }

}
