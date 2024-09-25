package pe.edu.pucp.softprog.infraestructura.model;
import java.util.ArrayList;

public class Grado {
    private int idGrado;
    private String nombre;
    private Nivel nivel;

    public Grado(int idGrado, String nombre, Nivel nivel) {
        this.idGrado = idGrado;
        this.nombre = nombre;
        this.nivel = nivel;
    }

    public int getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(int idGrado) {
        this.idGrado = idGrado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }
}
