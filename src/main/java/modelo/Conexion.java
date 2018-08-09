package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static Conexion instance;
    private Connection conection;
    //private final String URL = "jdbc:postgresql://35.193.221.157/aserradero?currentSchema=aserradero_warrior";
    //private final String USER = "aserradero";
    //private final String PASSWORD = "samwillGuerrero";

    private final String URL = "jdbc:postgresql://localhost/postgres?currentSchema=aserradero_warrior";
    private final String USER = "postgres";
    private final String PASSWORD = "admin";

    private Conexion(){
    }

    public static Conexion getInstance(){
        if(instance == null)
            instance = new Conexion();
        return instance;
    }

    public void establecerConexion() {
        try {
            Class.forName("org.postgresql.Driver");
            conection = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(ClassNotFoundException ignored){}
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void cerrarConexion() {
        try {
            conection.close();
        }catch (Exception ignored){}
    }


    public Connection getConection() {
        return conection;
    }

    public void setConection (Connection conection) {
        this.conection = conection;
    }
}
