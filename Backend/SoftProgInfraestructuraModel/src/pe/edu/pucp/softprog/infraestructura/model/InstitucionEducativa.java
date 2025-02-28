package pe.edu.pucp.softprog.infraestructura.model;

import java.util.ArrayList;
import pe.edu.pucp.softprog.gestcalendario.model.AnioAcademico;
import pe.edu.pucp.softprog.rrhh.model.Director;

public class InstitucionEducativa {
    private int idInstitucion;
    private String nombre;
    private String direccion;
    private int cantidadGrados;
    private String correoElectronico;
    private String telefono;
     private ArrayList<Grado> grados;
    private ArrayList<AnioAcademico> aniosAcademicos;
    private UGEL ugel;
    private Director director;
    private boolean activo;
    private byte[] fotoInstitucion;

    public byte[] getFotoInstitucion() {
        return fotoInstitucion;
    }

    public void setFotoInstitucion(byte[] fotoInstitucion) {
        this.fotoInstitucion = fotoInstitucion;
    }

    
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correo_electronico) {
        this.correoElectronico = correo_electronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public InstitucionEducativa() {
        grados = new ArrayList<>();
        cantidadGrados = 0;
    }
    
    public InstitucionEducativa(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        grados = new ArrayList<>();
        cantidadGrados = 0;
    }

    public int getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(int idInstitucion) {
        this.idInstitucion = idInstitucion;
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

    public ArrayList<Grado> getGrados() {
        return grados;
    }

    public void setGrados(ArrayList<Grado> grados) {
        this.grados = grados;
    }

    public int getCantidadGrados() {
        return cantidadGrados;
    }

    public void setCantidadGrados(int cantidadGrados) {
        this.cantidadGrados = cantidadGrados;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public UGEL getUgel() {
        return ugel;
    }

    public void setUgel(UGEL ugel) {
        this.ugel = ugel;
    }

    public ArrayList<AnioAcademico> getAniosAcademicos() {
        return aniosAcademicos;
    }

    public void setAniosAcademicos(ArrayList<AnioAcademico> aniosAcademicos) {
        this.aniosAcademicos = aniosAcademicos;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
}
