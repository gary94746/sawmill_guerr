package modelo.grueso;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GruesoId {
    private IntegerProperty id;
    private StringProperty grueso;

    public GruesoId(int id, String grueso) {
        this.id = new SimpleIntegerProperty(id);
        this.grueso = new SimpleStringProperty(grueso);
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

    @Override
    public String toString() {
        return grueso.get() + "\"";
    }
}
