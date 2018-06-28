package modelo.Control_madera;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class madera_control extends RecursiveTreeObject<madera_control> {

    private IntegerProperty id;
    private StringProperty largo;
    private StringProperty grueso;
    private StringProperty ancho;
    private StringProperty clase;
    private IntegerProperty pieza;
    private DoubleProperty cubicacion;
    private DoubleProperty pt;

    private StringProperty largo_segunda;
    private StringProperty grueso_segunda;
    private StringProperty ancho_segunda;
    private StringProperty clase_segunda;
    private IntegerProperty pieza_segunda;
    private DoubleProperty cubicacion_segunda;
    private DoubleProperty pt_segunda;

    private StringProperty largo_terceraB;
    private StringProperty grueso_terceraB;
    private StringProperty ancho_terceraB;
    private StringProperty clase_terceraB;
    private IntegerProperty pieza_terceraB;
    private DoubleProperty cubicacion_terceraB;
    private DoubleProperty pt_terceraB;

    private StringProperty largo_terceraM;
    private StringProperty grueso_terceraM;
    private StringProperty ancho_terceraM;
    private StringProperty clase_terceraM;
    private IntegerProperty pieza_terceraM;
    private DoubleProperty cubicacion_terceraM;
    private DoubleProperty pt_terceraM;

    private StringProperty largo_cruzada;
    private StringProperty grueso_cruzada;
    private StringProperty ancho_cruzada;
    private StringProperty clase_cruzada;
    private IntegerProperty pieza_cruzada;
    private DoubleProperty cubicacion_cruzada;
    private DoubleProperty pt_cruzada;



    public madera_control(String grueso, String ancho, String clase, int pieza, double cubicacion, double pt,String largo) {
        this.grueso = new SimpleStringProperty(grueso);
        this.ancho = new SimpleStringProperty(ancho);
        this.clase = new SimpleStringProperty(clase);
        this.pieza = new SimpleIntegerProperty(pieza);
        this.cubicacion = new SimpleDoubleProperty(cubicacion);
        this.pt = new SimpleDoubleProperty(pt);
        this.largo = new SimpleStringProperty(largo);
    }

    public madera_control(int id,String grueso, String ancho, String clase, int pieza, double cubicacion, double pt,String largo) {
        this.id = new SimpleIntegerProperty(id);
        this.grueso = new SimpleStringProperty(grueso);
        this.ancho = new SimpleStringProperty(ancho);
        this.clase = new SimpleStringProperty(clase);
        this.pieza = new SimpleIntegerProperty(pieza);
        this.cubicacion = new SimpleDoubleProperty(cubicacion);
        this.pt = new SimpleDoubleProperty(pt);
        this.largo = new SimpleStringProperty(largo);
    }

    public madera_control(String         largo,  String grueso,
                          String         ancho,  String clase,
                          int            pieza,  double cubicacion,double pt,
                          String largo_segunda,  String grueso_segunda,
                          String ancho_segunda,  String clase_segunda,
                          int    pieza_segunda,  double cubicacion_segunda,double pt_segunda,
                          String largo_terceraB, String grueso_terceraB,
                          String ancho_terceraB, String clase_terceraB,
                          int    pieza_terceraB, double cubicacion_terceraB,double pt_terceraB,
                          String largo_terceraM, String grueso_terceraM,
                          String ancho_terceraM, String clase_terceraM,
                          int    pieza_terceraM, double cubicacion_terceraM,double pt_terceraM,
                          String  largo_cruzada, String grueso_cruzada,
                          String  ancho_cruzada, String clase_cruzada,
                          int     pieza_cruzada, double cubicacion_cruzada,double pt_cruzada

     ){

        this.grueso= new SimpleStringProperty(grueso);
        this.ancho = new SimpleStringProperty(ancho);
        this.clase = new SimpleStringProperty(clase);
        this.pieza = new SimpleIntegerProperty(pieza);
        this.cubicacion = new SimpleDoubleProperty(cubicacion);
        this.pt    = new SimpleDoubleProperty(pt);
        this.largo = new SimpleStringProperty(largo);

        this.grueso_segunda= new SimpleStringProperty(grueso_segunda);
        this.ancho_segunda = new SimpleStringProperty(ancho_segunda);
        this.clase_segunda = new SimpleStringProperty(clase_segunda);
        this.pieza_segunda = new SimpleIntegerProperty(pieza_segunda);
        this.cubicacion_segunda = new SimpleDoubleProperty(cubicacion_segunda);
        this.pt_segunda    = new SimpleDoubleProperty(pt_segunda);
        this.largo_segunda = new SimpleStringProperty(largo_segunda);

        this.grueso_terceraB= new SimpleStringProperty(grueso_terceraB);
        this.ancho_terceraB = new SimpleStringProperty(ancho_terceraB);
        this.clase_terceraB = new SimpleStringProperty(clase_terceraB);
        this.pieza_terceraB = new SimpleIntegerProperty(pieza_terceraB);
        this.cubicacion_terceraB = new SimpleDoubleProperty(cubicacion_terceraB);
        this.pt_terceraB    = new SimpleDoubleProperty(pt_terceraB);
        this.largo_terceraB = new SimpleStringProperty(largo_terceraB);

        this.grueso_terceraM= new SimpleStringProperty(grueso_terceraM);
        this.ancho_terceraM = new SimpleStringProperty(ancho_terceraM);
        this.clase_terceraM = new SimpleStringProperty(clase_terceraM);
        this.pieza_terceraM = new SimpleIntegerProperty(pieza_terceraM);
        this.cubicacion_terceraM = new SimpleDoubleProperty(cubicacion_terceraM);
        this.pt_terceraM    = new SimpleDoubleProperty(pt_terceraM);
        this.largo_terceraM = new SimpleStringProperty(largo_terceraM);

        this.grueso_cruzada= new SimpleStringProperty(grueso_cruzada);
        this.ancho_cruzada = new SimpleStringProperty(ancho_cruzada);
        this.clase_cruzada = new SimpleStringProperty(clase_cruzada);
        this.pieza_cruzada = new SimpleIntegerProperty(pieza_cruzada);
        this.cubicacion_cruzada = new SimpleDoubleProperty(cubicacion_cruzada);
        this.pt_cruzada    = new SimpleDoubleProperty(pt_cruzada);
        this.largo_cruzada = new SimpleStringProperty(largo_cruzada);

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

    public String getLargo_segunda() {
        return largo_segunda.get();
    }

    public StringProperty largo_segundaProperty() {
        return largo_segunda;
    }

    public void setLargo_segunda(String largo_segunda) {
        this.largo_segunda.set(largo_segunda);
    }

    public String getGrueso_segunda() {
        return grueso_segunda.get();
    }

    public StringProperty grueso_segundaProperty() {
        return grueso_segunda;
    }

    public void setGrueso_segunda(String grueso_segunda) {
        this.grueso_segunda.set(grueso_segunda);
    }

    public String getAncho_segunda() {
        return ancho_segunda.get();
    }

    public StringProperty ancho_segundaProperty() {
        return ancho_segunda;
    }

    public void setAncho_segunda(String ancho_segunda) {
        this.ancho_segunda.set(ancho_segunda);
    }

    public String getClase_segunda() {
        return clase_segunda.get();
    }

    public StringProperty clase_segundaProperty() {
        return clase_segunda;
    }

    public void setClase_segunda(String clase_segunda) {
        this.clase_segunda.set(clase_segunda);
    }

    public int getPieza_segunda() {
        return pieza_segunda.get();
    }

    public IntegerProperty pieza_segundaProperty() {
        return pieza_segunda;
    }

    public void setPieza_segunda(int pieza_segunda) {
        this.pieza_segunda.set(pieza_segunda);
    }

    public double getCubicacion_segunda() {
        return cubicacion_segunda.get();
    }

    public DoubleProperty cubicacion_segundaProperty() {
        return cubicacion_segunda;
    }

    public void setCubicacion_segunda(double cubicacion_segunda) {
        this.cubicacion_segunda.set(cubicacion_segunda);
    }

    public double getPt_segunda() {
        return pt_segunda.get();
    }

    public DoubleProperty pt_segundaProperty() {
        return pt_segunda;
    }

    public void setPt_segunda(double pt_segunda) {
        this.pt_segunda.set(pt_segunda);
    }

    public String getLargo_terceraB() {
        return largo_terceraB.get();
    }

    public StringProperty largo_terceraBProperty() {
        return largo_terceraB;
    }

    public void setLargo_terceraB(String largo_terceraB) {
        this.largo_terceraB.set(largo_terceraB);
    }

    public String getGrueso_terceraB() {
        return grueso_terceraB.get();
    }

    public StringProperty grueso_terceraBProperty() {
        return grueso_terceraB;
    }

    public void setGrueso_terceraB(String grueso_terceraB) {
        this.grueso_terceraB.set(grueso_terceraB);
    }

    public String getAncho_terceraB() {
        return ancho_terceraB.get();
    }

    public StringProperty ancho_terceraBProperty() {
        return ancho_terceraB;
    }

    public void setAncho_terceraB(String ancho_terceraB) {
        this.ancho_terceraB.set(ancho_terceraB);
    }

    public String getClase_terceraB() {
        return clase_terceraB.get();
    }

    public StringProperty clase_terceraBProperty() {
        return clase_terceraB;
    }

    public void setClase_terceraB(String clase_terceraB) {
        this.clase_terceraB.set(clase_terceraB);
    }

    public int getPieza_terceraB() {
        return pieza_terceraB.get();
    }

    public IntegerProperty pieza_terceraBProperty() {
        return pieza_terceraB;
    }

    public void setPieza_terceraB(int pieza_terceraB) {
        this.pieza_terceraB.set(pieza_terceraB);
    }

    public double getCubicacion_terceraB() {
        return cubicacion_terceraB.get();
    }

    public DoubleProperty cubicacion_terceraBProperty() {
        return cubicacion_terceraB;
    }

    public void setCubicacion_terceraB(double cubicacion_terceraB) {
        this.cubicacion_terceraB.set(cubicacion_terceraB);
    }

    public double getPt_terceraB() {
        return pt_terceraB.get();
    }

    public DoubleProperty pt_terceraBProperty() {
        return pt_terceraB;
    }

    public void setPt_terceraB(double pt_terceraB) {
        this.pt_terceraB.set(pt_terceraB);
    }

    public String getLargo_terceraM() {
        return largo_terceraM.get();
    }

    public StringProperty largo_terceraMProperty() {
        return largo_terceraM;
    }

    public void setLargo_terceraM(String largo_terceraM) {
        this.largo_terceraM.set(largo_terceraM);
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

    public String getLargo_cruzada() {
        return largo_cruzada.get();
    }

    public StringProperty largo_cruzadaProperty() {
        return largo_cruzada;
    }

    public void setLargo_cruzada(String largo_cruzada) {
        this.largo_cruzada.set(largo_cruzada);
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

    public String getLargo() { return largo.get(); }

    public StringProperty largoProperty() {return largo;}

    public void setLargo(String largo) {this.largo.set(largo);}

    public static madera_control addControl(Connection connection, madera_control control) {
        try {
            var control_added = "SELECT * FROM add_control('" +
                    control.getGrueso()+"'"+
                    ",'"+control.getAncho() +"'"+
                    ",'"+control.getClase() +""+
                    "'," + control.getPieza() + "" +
                    "," + control.getCubicacion() + "" +
                    "," +control.getPt() +","+
                    "'"+control.getLargo()+"'"+");";
            System.out.println(control_added);

            var statementP = connection.createStatement();
            var resultSet1 = statementP.executeQuery(control_added);
            if (resultSet1.next())
                return new madera_control(
                        resultSet1.getInt("id"),
                        resultSet1.getString("grueso"),
                        resultSet1.getString("ancho"),
                        resultSet1.getString("clase"),
                        resultSet1.getInt("piezas"),
                        resultSet1.getDouble("cubicacion"),
                        resultSet1.getDouble("pies_tabla"),
                        resultSet1.getString("largo"));

        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }

    public static void obtenerDatos(Connection connection, ObservableList<madera_control> list,String grueso,String clase,String largo) {

        try {
            var datos = "select * from control_produccion where fecha= current_date and grueso like '" +grueso+ "' "+"and clase like '" + clase + "'" +"and largo like '" +largo+ "' ";

            System.out.println(datos);
            var statementP = connection.createStatement();
            var resultSet1 = statementP.executeQuery(datos);

            while (resultSet1.next()) {
                list.add(new madera_control(
                        resultSet1.getInt("id"),
                        resultSet1.getString("grueso"),
                        resultSet1.getString("ancho"),
                        resultSet1.getString("clase"),
                        resultSet1.getInt("piezas"),
                        resultSet1.getDouble("cubicacion"),
                        resultSet1.getDouble("pies_tabla"),
                        resultSet1.getString("largo")));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void obtenerDatosTodos(Connection connection, ObservableList<madera_control> list, String grueso,String largo) {
        try {
            var datos = "select * from control_produccion where fecha= current_date and grueso like '" +grueso+ "' "+"and largo like '" +largo+ "' ";

            System.out.println(datos);
            var statementP = connection.createStatement();
            var resultSet1 = statementP.executeQuery(datos);

            while (resultSet1.next()) {
                list.add(new madera_control(
                        resultSet1.getInt("id"),
                        resultSet1.getString("grueso"),
                        resultSet1.getString("ancho"),
                        resultSet1.getString("clase"),
                        resultSet1.getInt("piezas"),
                        resultSet1.getDouble("cubicacion"),
                        resultSet1.getDouble("pies_tabla"),
                        resultSet1.getString("largo")));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void obtenerDatosHistorial(Connection connection, ObservableList<madera_control> list) {
        try {
            var datos = "select * from control_produccion";

            System.out.println(datos);
            var statementP = connection.createStatement();
            var resultSet1 = statementP.executeQuery(datos);

            while (resultSet1.next()) {
                list.add(new madera_control(
                        resultSet1.getInt("id"),
                        resultSet1.getString("grueso"),
                        resultSet1.getString("ancho"),
                        resultSet1.getString("clase"),
                        resultSet1.getInt("piezas"),
                        resultSet1.getDouble("cubicacion"),
                        resultSet1.getDouble("pies_tabla"),
                        resultSet1.getString("largo")));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static int eliminarOtros(Connection connection, int id) {
        try {
            final String query = "DELETE FROM control_produccion WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            return statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
    }

    public static void historial(Connection connection, ObservableList<madera_control> list, String date, String grueso) {
        try {

            System.out.println(date);
            var query = "SELECT * FROM control_produccion where fecha = '" + date + "'::date"+" and grueso like '" +grueso+ "'";
            System.out.println(query);
            var statementP = connection.createStatement();
            var resultSet1 = statementP.executeQuery(query);

            while (resultSet1.next()) {
                list.add((new madera_control(
                        resultSet1.getInt("id"),
                        resultSet1.getString("grueso"),
                        resultSet1.getString("ancho"),
                        resultSet1.getString("clase"),
                        resultSet1.getInt("piezas"),
                        resultSet1.getDouble("cubicacion"),
                        resultSet1.getDouble("pies_tabla"),
                        resultSet1.getString("largo"))));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
