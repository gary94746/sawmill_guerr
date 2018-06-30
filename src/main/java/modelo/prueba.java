package modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class prueba {
    StringProperty nombre;
    IntegerProperty edad;

    public prueba(String nombre, int edad){
        this.nombre = new SimpleStringProperty(nombre);
        this.edad = new SimpleIntegerProperty(edad);
    }

    public prueba(){
        this.nombre = new SimpleStringProperty("");
        this.edad = new SimpleIntegerProperty(0);

    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public int getEdad() {
        return edad.get();
    }

    public IntegerProperty edadProperty() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad.set(edad);
    }
}

   class test23 {
    public static void main(String[] args) {
        prueba p = new prueba();
        //p.setNombre("nombre");
        p.setEdad(10);
        prueba p2 = new prueba();
        //p2.setEdad(20);
        p2.setNombre("nombre");
        prueba p3 = new prueba();

     while(p3.getNombre()==""){
            p3.setNombre(p.getNombre());
            p3.setNombre(p2.getNombre());
        }

        System.out.println(p3.getEdad());

     if(p3.getNombre()==""){


     }

     while(p3.getEdad()==0);{
            System.out.println(p3.getEdad());
           p3.setEdad(p.getEdad());
           p3.setEdad(p2.getEdad());

        }



        System.out.println(p3.getNombre()+":"+p3.getEdad());
     }



}