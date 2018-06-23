package modelo.Control_madera;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;

public class madera_control extends RecursiveTreeObject<madera_control> {

    private IntegerProperty id;
    private StringProperty grueso;
    private StringProperty ancho;
    private StringProperty clase;
    private IntegerProperty pieza;
    private DoubleProperty cubicacion;
    private DoubleProperty pt;

    private IntegerProperty id_segunda;
    private StringProperty grueso_segunda;
    private StringProperty ancho_segunda;
    private StringProperty clase_segunda;
    private IntegerProperty pieza_segunda;
    private DoubleProperty cubicacion_segunda;
    private DoubleProperty pt_segunda;

    private IntegerProperty id_tercerab;
    private StringProperty grueso_tercerab;
    private StringProperty ancho_tercerab;
    private StringProperty clase_tercerab;
    private IntegerProperty pieza_tercerab;
    private DoubleProperty cubicacion_tercerab;
    private DoubleProperty pt_tercerab;

    private IntegerProperty id_terceraM;
    private StringProperty grueso_terceraM;
    private StringProperty ancho_terceraM;
    private StringProperty clase_terceraM;
    private IntegerProperty pieza_terceraM;
    private DoubleProperty cubicacion_terceraM;
    private DoubleProperty pt_terceraM;

    private StringProperty grueso_cruzada;
    private StringProperty ancho_cruzada;
    private StringProperty clase_cruzada;
    private IntegerProperty pieza_cruzada;
    private DoubleProperty cubicacion_cruzada;
    private DoubleProperty pt_cruzada;






    public madera_control(String grueso, String ancho, String clase, int pieza, double cubicacion, double pt) {
        this.grueso = new SimpleStringProperty(grueso);
        this.ancho = new SimpleStringProperty(ancho);
        this.clase = new SimpleStringProperty(clase);
        this.pieza = new SimpleIntegerProperty(pieza);
        this.cubicacion = new SimpleDoubleProperty(cubicacion);
        this.pt = new SimpleDoubleProperty(pt);
    }

    public madera_control(String grueso_segunda, String ancho_segunda, int pieza_segunda, String clase_segunda, double cubicacion_segunda, double pt_segunda) {
        this.grueso_segunda = new SimpleStringProperty(grueso_segunda);
        this.ancho_segunda = new SimpleStringProperty(ancho_segunda);
        this.clase_segunda = new SimpleStringProperty(clase_segunda);
        this.pieza_segunda = new SimpleIntegerProperty(pieza_segunda);
        this.cubicacion_segunda = new SimpleDoubleProperty(cubicacion_segunda);
        this.pt_segunda = new SimpleDoubleProperty(pt_segunda);
    }

    public madera_control(int pieza_tercerab,String grueso_tercerab, String ancho_tercerab, String clase_tercerab, double cubicacion_tercerab, double pt_tercerab) {
        this.grueso_tercerab= new SimpleStringProperty(grueso_tercerab);
        this.ancho_tercerab = new  SimpleStringProperty(ancho_tercerab);
        this.clase_tercerab = new SimpleStringProperty(clase_tercerab);
        this.pieza_tercerab = new SimpleIntegerProperty(pieza_tercerab);
        this.cubicacion_tercerab = new SimpleDoubleProperty(cubicacion_tercerab);
        this.pt_tercerab = new SimpleDoubleProperty(pt_tercerab);
    }


    public madera_control(String grueso_terceraM, String ancho_terceraM, String clase_terceraM,double cubicacion_terceraM ,int pieza_terceraM, double pt_terceraM) {
        this.grueso_terceraM = new SimpleStringProperty(grueso_terceraM);
        this.ancho_terceraM =  new  SimpleStringProperty(ancho_terceraM);
        this.clase_terceraM =  new SimpleStringProperty(clase_terceraM);
        this.pieza_terceraM = new SimpleIntegerProperty(pieza_terceraM);
        this.cubicacion_terceraM = new SimpleDoubleProperty(cubicacion_terceraM);
        this.pt_terceraM =new SimpleDoubleProperty(pt_terceraM);
    }


    public madera_control(String grueso_cruzada, String ancho_cruzada, String clase_cruzada,double pt_cruzada, int pieza_cruzada, int cubicacion_cruzada) {
        this.grueso_cruzada= new SimpleStringProperty(grueso_cruzada);
        this.ancho_cruzada = new  SimpleStringProperty(ancho_cruzada);
        this.clase_cruzada = new SimpleStringProperty(clase_cruzada);
        this.pieza_cruzada = new SimpleIntegerProperty(pieza_cruzada);
        this.cubicacion_cruzada = new SimpleDoubleProperty(cubicacion_cruzada);
        this.pt_cruzada    = new SimpleDoubleProperty(pt_cruzada);
    }


    public String getGrueso_cruzada() {
        return grueso_cruzada.get();
    }

    public StringProperty grueso_cruzadaProperty() {
        return grueso_cruzada;
    }

    public void setGrueso_cruzada(String grueso_cruzada) {
        this.grueso_cruzada.set(grueso_cruzada);
    }

    public String getAncho_cruzada() {
        return ancho_cruzada.get();
    }

    public StringProperty ancho_cruzadaProperty() {
        return ancho_cruzada;
    }

    public void setAncho_cruzada(String ancho_cruzada) {
        this.ancho_cruzada.set(ancho_cruzada);
    }

    public String getClase_cruzada() {
        return clase_cruzada.get();
    }

    public StringProperty clase_cruzadaProperty() {
        return clase_cruzada;
    }

    public void setClase_cruzada(String clase_cruzada) {
        this.clase_cruzada.set(clase_cruzada);
    }

    public int getPieza_cruzada() {
        return pieza_cruzada.get();
    }

    public IntegerProperty pieza_cruzadaProperty() {
        return pieza_cruzada;
    }

    public void setPieza_cruzada(int pieza_cruzada) {
        this.pieza_cruzada.set(pieza_cruzada);
    }

    public double getCubicacion_cruzada() {
        return cubicacion_cruzada.get();
    }

    public DoubleProperty cubicacion_cruzadaProperty() {
        return cubicacion_cruzada;
    }

    public void setCubicacion_cruzada(double cubicacion_cruzada) {
        this.cubicacion_cruzada.set(cubicacion_cruzada);
    }

    public double getPt_cruzada() {
        return pt_cruzada.get();
    }

    public DoubleProperty pt_cruzadaProperty() {
        return pt_cruzada;
    }

    public void setPt_cruzada(double pt_cruzada) {
        this.pt_cruzada.set(pt_cruzada);
    }

    public int getId_terceraM() {
        return id_terceraM.get();
    }

    public IntegerProperty id_terceraMProperty() {
        return id_terceraM;
    }

    public void setId_terceraM(int id_terceraM) {
        this.id_terceraM.set(id_terceraM);
    }

    public String getGrueso_terceraM() {
        return grueso_terceraM.get();
    }

    public StringProperty grueso_terceraMProperty() {
        return grueso_terceraM;
    }

    public void setGrueso_terceraM(String grueso_terceraM) {
        this.grueso_terceraM.set(grueso_terceraM);
    }

    public String getAncho_terceraM() {
        return ancho_terceraM.get();
    }

    public StringProperty ancho_terceraMProperty() {
        return ancho_terceraM;
    }

    public void setAncho_terceraM(String ancho_terceraM) {
        this.ancho_terceraM.set(ancho_terceraM);
    }

    public String getClase_terceraM() {
        return clase_terceraM.get();
    }

    public StringProperty clase_terceraMProperty() {
        return clase_terceraM;
    }

    public void setClase_terceraM(String clase_terceraM) {
        this.clase_terceraM.set(clase_terceraM);
    }

    public int getPieza_terceraM() {
        return pieza_terceraM.get();
    }

    public IntegerProperty pieza_terceraMProperty() {
        return pieza_terceraM;
    }

    public void setPieza_terceraM(int pieza_terceraM) {
        this.pieza_terceraM.set(pieza_terceraM);
    }

    public double getCubicacion_terceraM() {
        return cubicacion_terceraM.get();
    }

    public DoubleProperty cubicacion_terceraMProperty() {
        return cubicacion_terceraM;
    }

    public void setCubicacion_terceraM(double cubicacion_terceraM) {
        this.cubicacion_terceraM.set(cubicacion_terceraM);
    }

    public double getPt_terceraM() {
        return pt_terceraM.get();
    }

    public DoubleProperty pt_terceraMProperty() {
        return pt_terceraM;
    }

    public void setPt_terceraM(double pt_terceraM) {
        this.pt_terceraM.set(pt_terceraM);
    }

    public String getGrueso_tercerab() {
        return grueso_tercerab.get();
    }

    public StringProperty grueso_tercerabProperty() {
        return grueso_tercerab;
    }

    public void setGrueso_tercerab(String grueso_tercerab) {
        this.grueso_tercerab.set(grueso_tercerab);
    }

    public String getAncho_tercerab() {
        return ancho_tercerab.get();
    }

    public StringProperty ancho_tercerabProperty() {
        return ancho_tercerab;
    }

    public void setAncho_tercerab(String ancho_tercerab) {
        this.ancho_tercerab.set(ancho_tercerab);
    }

    public String getClase_tercerab() {
        return clase_tercerab.get();
    }

    public StringProperty clase_tercerabProperty() {
        return clase_tercerab;
    }

    public void setClase_tercerab(String clase_tercerab) {
        this.clase_tercerab.set(clase_tercerab);
    }

    public int getPieza_tercerab() {
        return pieza_tercerab.get();
    }

    public IntegerProperty pieza_tercerabProperty() {
        return pieza_tercerab;
    }

    public void setPieza_tercerab(int pieza_tercerab) {
        this.pieza_tercerab.set(pieza_tercerab);
    }

    public double getCubicacion_tercerab() {
        return cubicacion_tercerab.get();
    }

    public DoubleProperty cubicacion_tercerabProperty() {
        return cubicacion_tercerab;
    }

    public void setCubicacion_tercerab(double cubicacion_tercerab) {
        this.cubicacion_tercerab.set(cubicacion_tercerab);
    }

    public double getPt_tercerab() {
        return pt_tercerab.get();
    }

    public DoubleProperty pt_tercerabProperty() {
        return pt_tercerab;
    }

    public void setPt_tercerab(double pt_tercerab) {
        this.pt_tercerab.set(pt_tercerab);
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

    public int getId_segunda() {return id_segunda.get(); }

    public IntegerProperty id_segundaProperty() {return id_segunda; }

    public void setId_segunda(int id_segunda) {this.id_segunda.set(id_segunda);    }

    public String getGrueso_segunda() {return grueso_segunda.get(); }

    public StringProperty grueso_segundaProperty() {return grueso_segunda; }

    public void setGrueso_segunda(String grueso_segunda) {this.grueso_segunda.set(grueso_segunda); }

    public String getAncho_segunda() {return ancho_segunda.get();}

    public StringProperty ancho_segundaProperty() {return ancho_segunda;}

    public void setAncho_segunda(String ancho_segunda) {this.ancho_segunda.set(ancho_segunda);}

    public String getClase_segunda() {return clase_segunda.get();}

    public StringProperty clase_segundaProperty() {return clase_segunda;}

    public void setClase_segunda(String clase_segunda) {this.clase_segunda.set(clase_segunda); }

    public int getPieza_segunda() {return pieza_segunda.get();}

    public IntegerProperty pieza_segundaProperty() {return pieza_segunda;}

    public void setPieza_segunda(int pieza_segunda) {this.pieza_segunda.set(pieza_segunda);}

    public double getCubicacion_segunda() { return cubicacion_segunda.get();}

    public DoubleProperty cubicacion_segundaProperty() {return cubicacion_segunda;}

    public void setCubicacion_segunda(double cubicacion_segunda) {this.cubicacion_segunda.set(cubicacion_segunda);
    }

    public double getPt_segunda() { return pt_segunda.get(); }

    public DoubleProperty pt_segundaProperty() {return pt_segunda;}

    public void setPt_segunda(double pt_segunda) {this.pt_segunda.set(pt_segunda);}
}
