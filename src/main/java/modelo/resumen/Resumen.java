package modelo.resumen;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.*;
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
    private StringProperty largo;
    private DoubleProperty primera;
    private DoubleProperty segunda;
    private DoubleProperty tercera_buena;
    private DoubleProperty tercera_mala;
    private DoubleProperty madera_cruzada;
    private DoubleProperty cuadrado;
    private DoubleProperty viga;
    private DoubleProperty polin;
    private DoubleProperty total;
    private IntegerProperty piezas;

    public Resumen(String medida, Double primera, Double segunda, Double tercera_buena, Double tercera_mala, Double madera_cruzada, Double cuadrado, Double viga, Double polin,Double total) {
        this.medida = new SimpleStringProperty(medida);
        this.primera = new SimpleDoubleProperty(primera);
        this.segunda = new SimpleDoubleProperty(segunda);
        this.tercera_buena = new SimpleDoubleProperty(tercera_buena);
        this.tercera_mala = new SimpleDoubleProperty(tercera_mala);
        this.madera_cruzada = new SimpleDoubleProperty(madera_cruzada);
        this.cuadrado = new SimpleDoubleProperty(cuadrado);
        this.viga = new SimpleDoubleProperty(viga);
        this.polin = new SimpleDoubleProperty(polin);
        this.total = new SimpleDoubleProperty(total);
    }

    public Resumen(String medida, Double primera, Double segunda, Double tercera_buena, Double tercera_mala, Double madera_cruzada, Double cuadrado, Double viga, Double polin) {
        this.medida = new SimpleStringProperty(medida);
        this.primera = new SimpleDoubleProperty(primera);
        this.segunda = new SimpleDoubleProperty(segunda);
        this.tercera_buena = new SimpleDoubleProperty(tercera_buena);
        this.tercera_mala = new SimpleDoubleProperty(tercera_mala);
        this.madera_cruzada = new SimpleDoubleProperty(madera_cruzada);
        this.cuadrado = new SimpleDoubleProperty(cuadrado);
        this.viga = new SimpleDoubleProperty(viga);
        this.polin = new SimpleDoubleProperty(polin);
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
        this.polin = new SimpleDoubleProperty(0.000);
        this.total = new SimpleDoubleProperty(0.000);
    }

    public Resumen(String grueso, String clase, Double total, String largo) {
        this.medida = new SimpleStringProperty(grueso);
        this.clase = new SimpleStringProperty(clase);
        this.total = new SimpleDoubleProperty(total);
        this.largo = new SimpleStringProperty(largo);
    }

    public Resumen(String grueso, String clase, Double total) {
        this.medida = new SimpleStringProperty(grueso);
        this.clase = new SimpleStringProperty(clase);
        this.total = new SimpleDoubleProperty(total);
    }

    public Resumen(String clase, int piezas) {
        this.clase = new SimpleStringProperty(clase);
        this.piezas = new SimpleIntegerProperty(piezas);
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

    public double getPolin() {
        return polin.get();
    }

    public DoubleProperty polinProperty() {
        return polin;
    }

    public void setPolin(double polin) {
        this.polin.set(polin);
    }

    public String getLargo() {
        return largo.get();
    }

    public StringProperty largoProperty() {
        return largo;
    }

    public void setLargo(String largo) {
        this.largo.set(largo);
    }

    public int getPiezas() {
        return piezas.get();
    }

    public IntegerProperty piezasProperty() {
        return piezas;
    }

    public void setPiezas(int piezas) {
        this.piezas.set(piezas);
    }

    public double getTotalSum() {
        NumberFormat format = new DecimalFormat("#0.000");
        var returned = primera.get() + segunda.get() +
                tercera_buena.get() + tercera_mala.get() + madera_cruzada.get() + cuadrado.get() + viga.get() + polin.get();
        var x = format.format(returned);
        return Double.parseDouble(x);
    }

    public static void get_data(Connection connection, ObservableList<Resumen> lista, String date1, String date2) {
         try{
             ObservableList<Resumen> fxlis = FXCollections.observableArrayList();

             //TEMPORAL VALUES 8 1/4
             var r1 = new Resumen();
             var r2 = new Resumen();
             var r3 = new Resumen();
             //16 1/2
             var r4 = new Resumen();
             var r5 = new Resumen();
             var r6 = new Resumen();

             //medidas
             r1.setMedida("3/4\"    8 1/4\"");
             r4.setMedida("3/4\"    16 1/2\"");
             r2.setMedida("1 1/2\" 8 1/4\"");
             r5.setMedida("1 1/2\" 16 1/2\"");
             r3.setMedida("2\"       8 1/4\"");
             r6.setMedida("2\"       16 1/2\"");

             var query = "SELECT * FROM get_resumen('"+date1+"'::date,'"+date2+"'::date)";
             var statement = connection.createStatement();
             var rs = statement.executeQuery(query);

             while (rs.next())
                 fxlis.add(new Resumen(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getString(4)));

             fxlis.forEach(x -> {
                 switch (x.getMedida() + " "+ x.getLargo()) {
                     case "3/4\" 8 1/4\"":
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

                         r1.setTotal(r1.getTotalSum());

                         break;
                     case "1 1/2\" 8 1/4\"":
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

                         r2.setTotal(r2.getTotalSum());
                         break;
                     case "2\" 8 1/4\"":
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

                         r3.setTotal(r3.getTotalSum());
                         break;
                     case "3/4\" 16 1/2\"":
                         if (r4.getPrimera() == 0.000)
                             r4.setPrimera((x.getClase().equals("PRIMERA")) ? x.getTotal() : 0.000);

                         if (r4.getSegunda() == 0.000)
                             r4.setSegunda((x.getClase().equals("SEGUNDA")) ? x.getTotal() : 0.000);

                         if (r4.getTercera_buena() == 0.000)
                             r4.setTercera_buena((x.getClase().equals("TERCERA BUENA")) ? x.getTotal() : 0.000);

                         if (r4.getTercera_mala() == 0.000)
                             r4.setTercera_mala((x.getClase().equals("TERCERA MALA")) ? x.getTotal() : 0.000);

                         if (r4.getMadera_cruzada() == 0.000)
                             r4.setMadera_cruzada((x.getClase().equals("MADERA CRUZADA")) ? x.getTotal() : 0.000);

                         r4.setTotal(r4.getTotalSum());

                         break;
                     case "1 1/2\" 16 1/2\"":
                         if (r5.getPrimera() == 0.000)
                             r5.setPrimera((x.getClase().equals("PRIMERA")) ? x.getTotal() : 0.000);

                         if (r5.getSegunda() == 0.000)
                             r5.setSegunda((x.getClase().equals("SEGUNDA")) ? x.getTotal() : 0.000);

                         if (r5.getTercera_buena() == 0.000)
                             r5.setTercera_buena((x.getClase().equals("TERCERA BUENA")) ? x.getTotal() : 0.000);

                         if (r5.getTercera_mala() == 0.000)
                             r5.setTercera_mala((x.getClase().equals("TERCERA MALA")) ? x.getTotal() : 0.000);

                         if (r5.getMadera_cruzada() == 0.000)
                             r5.setMadera_cruzada((x.getClase().equals("MADERA CRUZADA")) ? x.getTotal() : 0.000);

                         r5.setTotal(r5.getTotalSum());
                         break;
                     case "2\" 16 1/2\"":
                         if (r6.getPrimera() == 0.000)
                             r6.setPrimera((x.getClase().equals("PRIMERA")) ? x.getTotal() : 0.000);

                         if (r6.getSegunda() == 0.000)
                             r6.setSegunda((x.getClase().equals("SEGUNDA")) ? x.getTotal() : 0.000);

                         if (r6.getTercera_buena() == 0.000)
                             r6.setTercera_buena((x.getClase().equals("TERCERA BUENA")) ? x.getTotal() : 0.000);

                         if (r6.getTercera_mala() == 0.000)
                             r6.setTercera_mala((x.getClase().equals("TERCERA MALA")) ? x.getTotal() : 0.000);

                         if (r6.getMadera_cruzada() == 0.000)
                             r6.setMadera_cruzada((x.getClase().equals("MADERA CRUZADA")) ? x.getTotal() : 0.000);

                         r6.setTotal(r6.getTotalSum());
                         break;
                 }

             });

             lista.addAll(r1,r2,r3,r4,r5,r6);

         }catch (SQLException e) {
             e.printStackTrace();
         }
    }

    public static void get_data_otros(Connection connection, ObservableList<Resumen> lista, String date1, String date2) {
        try{
            ObservableList<Resumen> fxlis = FXCollections.observableArrayList();

            //TEMPORAL VALUES
            var r1 = new Resumen();
            r1.setMedida("POL 4X4");
            var r2 = new Resumen();
            r2.setMedida("POL 3.5X3.5");
            var r3 = new Resumen();
            r3.setMedida("BAR 2X4");
            var r4 = new Resumen();
            r4.setMedida("BAR 1.5X3.5");
            var r5 = new Resumen();
            r5.setMedida("VIGA 4x4");
            var r6 = new Resumen();
            r6.setMedida("VIGA 4x6");
            var r7 = new Resumen();
            r7.setMedida("VIGA 4x8");
            var r8 = new Resumen();
            r8.setMedida("POL 3X3");

            var query = "SELECT * FROM get_resumen_otros('"+date1+"'::date,'"+date2+"'::date)";
            var statement = connection.createStatement();
            var rs = statement.executeQuery(query);

            while (rs.next())
                fxlis.add(new Resumen("", rs.getString(1), rs.getDouble(2)));

            fxlis.forEach(x -> {
                switch (x.getClase()) {
                    case "POL 4X4":
                        if (r1.getPolin() == 0.000)
                            r1.setPolin((x.getClase().equals("POL 4X4")) ? x.getTotal() : 0.000);

                        r1.setTotal(r1.getTotalSum());
                        break;
                    case "POL 3X3":
                        if (r8.getPolin() == 0.000)
                            r8.setPolin((x.getClase().equals("POL 3X3")) ? x.getTotal() : 0.000);

                        r8.setTotal(r8.getTotalSum());
                        break;
                    case "POL 3.5X3.5":
                        if (r2.getPolin() == 0.000)
                            r2.setPolin((x.getClase().equals("POL 3.5X3.5")) ? x.getTotal() : 0.000);

                        r2.setTotal(r2.getTotalSum());
                        break;
                    case "BAR 2X4":
                        if (r3.getCuadrado() == 0.000)
                            r3.setCuadrado((x.getClase().equals("BAR 2X4")) ? x.getTotal() : 0.000);

                        r3.setTotal(r3.getTotalSum());
                        break;
                    case "BAR 1.5X3.5":
                        if (r4.getCuadrado() == 0.000)
                            r4.setCuadrado((x.getClase().equals("BAR 1.5X3.5")) ? x.getTotal() : 0.000);

                        r4.setTotal(r4.getTotalSum());
                        break;
                    case "VIGA 4x4":
                        if (r5.getViga() == 0.000)
                            r5.setViga((x.getClase().equals("VIGA 4x4")) ? x.getTotal() : 0.000);

                        r5.setTotal(r5.getTotalSum());
                        break;
                    case "VIGA 4x6":
                        if (r6.getViga() == 0.000)
                            r6.setViga((x.getClase().equals("VIGA 4x6")) ? x.getTotal() : 0.000);

                        r6.setTotal(r6.getTotalSum());
                        break;
                    case "VIGA 4x8":
                        if (r7.getViga() == 0.000)
                            r7.setViga((x.getClase().equals("VIGA 4x8")) ? x.getTotal() : 0.000);

                        r7.setTotal(r7.getTotalSum());
                        break;
                }
            });

            lista.addAll(r1,r2,r8,r3,r4,r5,r6,r7);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void get_piezas(Connection connection, ObservableList<Resumen> lista, String date1, String date2) {
        try{
            ObservableList<Resumen> fxlis = FXCollections.observableArrayList();

            //TEMPORAL VALUES
            var r1 = new Resumen();
            r1.setMedida("PIEZAS");

            var query = "SELECT clase, SUM(piezas) FROM control_produccion WHERE FECHA BETWEEN '"+date1+"'::date AND '"+date2+"'::date  GROUP BY clase;";
            var statement = connection.createStatement();
            var rs = statement.executeQuery(query);

            while (rs.next())
                fxlis.add(new Resumen(rs.getString(1), rs.getInt(2)));

            fxlis.forEach(x -> {
                switch (x.getClase()) {
                    case "PRIMERA":
                        if (r1.getPrimera() == 0)
                            r1.setPrimera(x.getPiezas());
                        break;
                    case "SEGUNDA":
                        if (r1.getSegunda() == 0)
                            r1.setSegunda(x.getPiezas());
                        break;
                    case "TERCERA BUENA":
                        if (r1.getTercera_buena() == 0)
                            r1.setTercera_buena(x.getPiezas());
                        break;
                    case "TERCERA MALA":
                        if (r1.getTercera_mala() == 0)
                            r1.setTercera_mala(x.getPiezas());
                        break;
                    case "MADERA CRUZADA":
                        if (r1.getMadera_cruzada() == 0)
                            r1.setMadera_cruzada(x.getPiezas());
                        break;
                }
            });

            fxlis.removeIf(x -> true);

            var query1 = "SELECT otros, SUM(piezas) FROM otras WHERE FECHA BETWEEN '"+date1+"'::date AND '"+date2+"'::date  GROUP BY otros;";
            var statement1 = connection.createStatement();
            var rs1 = statement1.executeQuery(query1);

            while (rs1.next())
                fxlis.add(new Resumen(rs1.getString(1), rs1.getInt(2)));

            fxlis.forEach(x -> {
                switch (x.getClase().substring(0,2)) {
                    case "PO":
                        r1.setPolin(r1.getPolin() + x.getPiezas());
                        break;
                    case "VI":
                        r1.setViga(r1.getViga() + x.getPiezas());
                        break;
                    case "BA":
                        r1.setCuadrado(r1.getCuadrado() + x.getPiezas());
                        break;
                }
            });

            r1.setTotal(r1.getTotalSum());

            lista.addAll(r1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
