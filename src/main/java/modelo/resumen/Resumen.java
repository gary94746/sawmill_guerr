package modelo.resumen;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Resumen extends RecursiveTreeObject<Resumen> {
    private StringProperty medida;
    private DoubleProperty primera;
    private DoubleProperty segunda;
    private DoubleProperty tercera_buena;
    private DoubleProperty tercera_mala;
    private DoubleProperty madera_cruzada;
    private DoubleProperty cuadrado;
    private DoubleProperty viga;
    private DoubleProperty total;

    public Resumen(String medida, Double primera, Double segunda, Double tercera_buena, Double tercera_mala, Double madera_cruzada, Double cuadrado, Double viga, Double total) {
        this.medida = new SimpleStringProperty(medida);
        this.primera = new SimpleDoubleProperty(primera);
        this.segunda = new SimpleDoubleProperty(segunda);
        this.tercera_buena = new SimpleDoubleProperty(tercera_buena);
        this.tercera_mala = new SimpleDoubleProperty(tercera_mala);
        this.madera_cruzada = new SimpleDoubleProperty(madera_cruzada);
        this.cuadrado = new SimpleDoubleProperty(cuadrado);
        this.viga = new SimpleDoubleProperty(viga);
        this.total = new SimpleDoubleProperty(total);
    }

    public String getMedida() {
        return medida.get();
    }

    public StringProperty medidaProperty() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida.set(medida);
    }

    public double getPrimera() {
        return primera.get();
    }

    public DoubleProperty primeraProperty() {
        return primera;
    }

    public void setPrimera(double primera) {
        this.primera.set(primera);
    }

    public double getSegunda() {
        return segunda.get();
    }

    public DoubleProperty segundaProperty() {
        return segunda;
    }

    public void setSegunda(double segunda) {
        this.segunda.set(segunda);
    }

    public double getTercera_buena() {
        return tercera_buena.get();
    }

    public DoubleProperty tercera_buenaProperty() {
        return tercera_buena;
    }

    public void setTercera_buena(double tercera_buena) {
        this.tercera_buena.set(tercera_buena);
    }

    public double getTercera_mala() {
        return tercera_mala.get();
    }

    public DoubleProperty tercera_malaProperty() {
        return tercera_mala;
    }

    public void setTercera_mala(double tercera_mala) {
        this.tercera_mala.set(tercera_mala);
    }

    public double getMadera_cruzada() {
        return madera_cruzada.get();
    }

    public DoubleProperty madera_cruzadaProperty() {
        return madera_cruzada;
    }

    public void setMadera_cruzada(double madera_cruzada) {
        this.madera_cruzada.set(madera_cruzada);
    }

    public double getCuadrado() {
        return cuadrado.get();
    }

    public DoubleProperty cuadradoProperty() {
        return cuadrado;
    }

    public void setCuadrado(double cuadrado) {
        this.cuadrado.set(cuadrado);
    }

    public double getViga() {
        return viga.get();
    }

    public DoubleProperty vigaProperty() {
        return viga;
    }

    public void setViga(double viga) {
        this.viga.set(viga);
    }

    public double getTotal() {
        return total.get();
    }

    public DoubleProperty totalProperty() {
        return total;
    }

    public void setTotal(double total) {
        this.total.set(total);
    }
}
