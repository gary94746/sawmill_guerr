package modelo.grueso;

import modelo.Conexion;

import java.sql.SQLException;
import java.util.ArrayList;

public class GruesoArray {
    private static GruesoArray instance;
    private Conexion conexion;
    private ArrayList<GruesoId> arrayList;

    private GruesoArray() {
        arrayList = new ArrayList<>();

        conexion = Conexion.getInstance();
        conexion.establecerConexion();
        try {
            var stm = conexion.getConection().createStatement();
            var rs = stm.executeQuery("SELECT * FROM grueso");

            while (rs.next())
                arrayList.add(new GruesoId(rs.getInt(1), rs.getString(2)));
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            conexion.cerrarConexion();
        }
    }

    public static GruesoArray getInstance() {
        if (instance == null)
            instance = new GruesoArray();
        return instance;
    }

    public ArrayList<GruesoId> getArrayList() {
        return arrayList;
    }
}
