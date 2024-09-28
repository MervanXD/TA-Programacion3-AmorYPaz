package pe.edu.pucp.softprog.infraestructura.model;
import java.util.ArrayList;

public class InstitucionEducativa {
    private int idSede;
    private String nombre;
    private String direccion;
    private ArrayList<Nivel> niveles;
    private int activo;
    
    public InstitucionEducativa(){};

    public InstitucionEducativa(String nombre, String direccion, ArrayList<Nivel> niveles) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.niveles = new ArrayList<>();
    }
    
    public InstitucionEducativa(int idSede, String nombre, String direccion, ArrayList<Nivel> niveles) {
        this.idSede = idSede;
        this.nombre = nombre;
        this.direccion = direccion;
        this.niveles = new ArrayList<>();
    }
    

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<Nivel> getNiveles() {
        return niveles;
    }

    public void setNiveles(ArrayList<Nivel> niveles) {
        this.niveles = niveles;
    }
}
