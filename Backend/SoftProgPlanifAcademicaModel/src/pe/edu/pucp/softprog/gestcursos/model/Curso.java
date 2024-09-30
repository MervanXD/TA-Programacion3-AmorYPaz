package pe.edu.pucp.softprog.gestcursos.model;

public class Curso {

    private String idCurso;
    private String nombre;
    private boolean activo;
    
    public Curso() {
    }

    public Curso(String idCurso, String nombre) {
        this.idCurso = idCurso;
        this.nombre = nombre;
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public boolean isActivo() {
        return activo;
    }

}
