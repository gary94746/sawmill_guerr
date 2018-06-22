package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

public class ClasesArray {
    private static ClasesArray instance;
    private ArrayList<ClaseId> arrayClases;
    private Conexion conexion;


    private ClasesArray() {
        arrayClases = new ArrayList<>();

        conexion = Conexion.getInstance();
        conexion.establecerConexion();
        try {
            var stm = conexion.getConection().createStatement();
            var rs = stm.executeQuery("SELECT * FROM clase");

            while (rs.next())
                arrayClases.add(new ClaseId(rs.getInt(1), rs.getString(2)));
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            conexion.cerrarConexion();
        }
    }

    public static ClasesArray getInstance() {
        if (instance == null)
            instance = new ClasesArray();
        return instance;
    }

    public ArrayList<ClaseId> getArrayClases() {
        return arrayClases;
    }

    public void setArrayClases(ArrayList<ClaseId> arrayClases) {
        this.arrayClases = arrayClases;
    }
}