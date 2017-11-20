package santiago.appreuniones.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by roms_ on 22/10/2017.
 */

public class Reunion implements Serializable{
    private int id;
    private String nombre;
    private int owner;
    private String descripcion;
    private String fecha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Reunion{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", owner=" + owner +
                ", descripcion='" + descripcion + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
