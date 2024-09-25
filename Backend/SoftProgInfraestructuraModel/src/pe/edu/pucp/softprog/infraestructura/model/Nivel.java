package pe.edu.pucp.softprog.infraestructura.model;
import java.util.ArrayList;

public class Nivel {
    private int idNivel;
    private String nombre;
    private TipoNivel tipo;
    private int cantidadGrados;
    private ArrayList<Grado> grados;
    private int alumnosMatriculados;

    public Nivel(int idNivel, String nombre, TipoNivel tipo, int cantidadGrados, ArrayList<Grado> grados, int alumnosMatriculados) {
        this.idNivel = idNivel;
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantidadGrados = cantidadGrados;
        this.grados = new ArrayList<>();
        this.alumnosMatriculados = alumnosMatriculados;
    }

    public int getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(int idNivel) {
        this.idNivel = idNivel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoNivel getTipo() {
        return tipo;
    }

    public void setTipo(TipoNivel tipo) {
        this.tipo = tipo;
    }

    public int getCantidadGrados() {
        return cantidadGrados;
    }

    public void setCantidadGrados(int cantidadGrados) {
        this.cantidadGrados = cantidadGrados;
    }

    public ArrayList<Grado> getGrados() {
        return grados;
    }

    public void setGrados(ArrayList<Grado> grados) {
        this.grados = grados;
    }

    public int getAlumnosMatriculados() {
        return alumnosMatriculados;
    }

    public void setAlumnosMatriculados(int alumnosMatriculados) {
        this.alumnosMatriculados = alumnosMatriculados;
    }
}
