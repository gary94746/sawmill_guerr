package modelo.resumen;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collections;

public class Resumen extends RecursiveTreeObject<Resumen> {
    private StringProperty medida;
    private StringProperty clase;
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

    public Resumen(String medida, Double primera, Double segunda, Double tercera_buena, Double tercera_mala, Double madera_cruzada, Double cuadrado, Double viga) {
        this.medida = new SimpleStringProperty(medida);
        this.primera = new SimpleDoubleProperty(primera);
        this.segunda = new SimpleDoubleProperty(segunda);
        this.tercera_buena = new SimpleDoubleProperty(tercera_buena);
        this.tercera_mala = new SimpleDoubleProperty(tercera_mala);
        this.madera_cruzada = new SimpleDoubleProperty(madera_cruzada);
        this.cuadrado = new SimpleDoubleProperty(cuadrado);
        this.viga = new SimpleDoubleProperty(viga);
        this.total = new SimpleDoubleProperty(getTotalSum());
    }

    public Resumen() {
        this.medida = new SimpleStringProperty("");
        this.primera = new SimpleDoubleProperty(0.000);
        this.segunda = new SimpleDoubleProperty(0.000);
        this.tercera_buena = new SimpleDoubleProperty(0.000);
        this.tercera_mala = new SimpleDoubleProperty(0.000);
        this.madera_cruzada = new SimpleDoubleProperty(0.000);
        this.cuadrado = new SimpleDoubleProperty(0.000);
        this.viga = new SimpleDoubleProperty(0.000);
        this.total = new SimpleDoubleProperty(0.000);
    }

    public Resumen(String grueso, String clase, Double total) {
        this.medida = new SimpleStringProperty(grueso);
        this.clase = new SimpleStringProperty(clase);
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

    public String getClase() {
        return clase.get();
    }

    public StringProperty claseProperty() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase.set(clase);
    }

    public double getTotalSum() {
        NumberFormat format = new DecimalFormat("#0.000");
        var returned = primera.get() + segunda.get() +
                tercera_buena.get() + tercera_mala.get() + madera_cruzada.get() + cuadrado.get() + viga.get();
        var x = format.format(returned);
        return Double.parseDouble(x);
    }

    public static void get_data(Connection connection, ObservableList<Resumen> lista, String date1, String date2) {
         try{
             ObservableList<Resumen> fxlis = FXCollections.observableArrayList();

             //TEMPORAL VALUES
             var r1 = new Resumen();
                r1.setMedida("3/4\"");
             var r2 = new Resumen();
                r2.setMedida("1 1/2\"");
             var r3 = new Resumen();
                r3.setMedida("2\"");

             var query = "SELECT * FROM get_resumen('"+date1+"'::date,'"+date2+"'::date)";
             var statement = connection.createStatement();
             var rs = statement.executeQuery(query);

             while (rs.next())
                 fxlis.add(new Resumen(rs.getString(1), rs.getString(2), rs.getDouble(3)));

             fxlis.forEach(x -> {
                 switch (x.getMedida()) {
                     case "3/4":
                         if (r1.getPrimera() == 0.000)
                             r1.setPrimera((x.getClase().equals("PRIMERA")) ? x.getTotal() : 0.000);

                         if (r1.getSegunda() == 0.000)
                             r1.setSegunda((x.getClase().equals("SEGUNDA")) ? x.getTotal() : 0.000);

                         if (r1.getTercera_buena() == 0.000)
                             r1.setTercera_buena((x.getClase().equals("TERCERA BUENA")) ? x.getTotal() : 0.000);

                         if (r1.getTercera_mala() == 0.000)
                             r1.setTercera_mala((x.getClase().equals("TERCERA MALA")) ? x.getTotal() : 0.000);

                         if (r1.getMadera_cruzada() == 0.000)
                             r1.setMadera_cruzada((x.getClase().equals("MADERA CRUZADA")) ? x.getTotal() : 0.000);

                         if (r1.getCuadrado() == 0.000)
                             r1.setCuadrado((x.getClase().equals("CUADRADO")) ? x.getTotal() : 0.000);

                         if (r1.getViga() == 0.000)
                             r1.setViga((x.getClase().equals("VIGA")) ? x.getTotal() : 0.000);

                         r1.setTotal(r1.getTotalSum());

                         break;
                     case "1 1/2":
                         if (r2.getPrimera() == 0.000)
                             r2.setPrimera((x.getClase().equals("PRIMERA")) ? x.getTotal() : 0.000);

                         if (r2.getSegunda() == 0.000)
                             r2.setSegunda((x.getClase().equals("SEGUNDA")) ? x.getTotal() : 0.000);

                         if (r2.getTercera_buena() == 0.000)
                             r2.setTercera_buena((x.getClase().equals("TERCERA BUENA")) ? x.getTotal() : 0.000);

                         if (r2.getTercera_mala() == 0.000)
                             r2.setTercera_mala((x.getClase().equals("TERCERA MALA")) ? x.getTotal() : 0.000);

                         if (r2.getMadera_cruzada() == 0.000)
                             r2.setMadera_cruzada((x.getClase().equals("MADERA CRUZADA")) ? x.getTotal() : 0.000);

                         if (r2.getCuadrado() == 0.000)
                             r2.setCuadrado((x.getClase().equals("CUADRADO")) ? x.getTotal() : 0.000);

                         if (r2.getViga() == 0.000)
                             r2.setViga((x.getClase().equals("VIGA")) ? x.getTotal() : 0.000);

                         r2.setTotal(r2.getTotalSum());
                         break;
                     case "2":
                         if (r3.getPrimera() == 0.000)
                             r3.setPrimera((x.getClase().equals("PRIMERA")) ? x.getTotal() : 0.000);

                         if (r3.getSegunda() == 0.000)
                             r3.setSegunda((x.getClase().equals("SEGUNDA")) ? x.getTotal() : 0.000);

                         if (r3.getTercera_buena() == 0.000)
                             r3.setTercera_buena((x.getClase().equals("TERCERA BUENA")) ? x.getTotal() : 0.000);

                         if (r3.getTercera_mala() == 0.000)
                             r3.setTercera_mala((x.getClase().equals("TERCERA MALA")) ? x.getTotal() : 0.000);

                         if (r3.getMadera_cruzada() == 0.000)
                             r3.setMadera_cruzada((x.getClase().equals("MADERA CRUZADA")) ? x.getTotal() : 0.000);

                         if (r3.getCuadrado() == 0.000)
                             r3.setCuadrado((x.getClase().equals("CUADRADO")) ? x.getTotal() : 0.000);

                         if (r3.getViga() == 0.000)
                             r3.setViga((x.getClase().equals("VIGA")) ? x.getTotal() : 0.000);

                         r3.setTotal(r3.getTotalSum());
                         break;
                 }
             });

             lista.addAll(r1,r2,r3);

         }catch (SQLException e) {
             e.printStackTrace();
         }
    }
}
