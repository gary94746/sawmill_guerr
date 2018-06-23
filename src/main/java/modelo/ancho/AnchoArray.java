package modelo.ancho;

import modelo.Conexion;

import java.sql.SQLException;
import java.util.ArrayList;


    public class AnchoArray {
        private static modelo.ancho.AnchoArray instance;
        private Conexion conexion;
        private ArrayList<AnchoId> arrayList;

        private AnchoArray() {
            arrayList = new ArrayList<>();

            conexion = Conexion.getInstance();
            conexion.establecerConexion();
            try {
                var stm = conexion.getConection().createStatement();
                var rs = stm.executeQuery("SELECT * FROM ancho");

                while (rs.next())
                    arrayList.add(new AnchoId(rs.getInt(1), rs.getString(2)));
            }catch (SQLException e) {
                e.printStackTrace();
            }finally {
                conexion.cerrarConexion();
            }
        }

        public static modelo.ancho.AnchoArray getInstance() {
            if (instance == null)
                instance = new modelo.ancho.AnchoArray();
            return instance;
        }

        public ArrayList<AnchoId> getArrayList() {
            return arrayList;
        }
    }



