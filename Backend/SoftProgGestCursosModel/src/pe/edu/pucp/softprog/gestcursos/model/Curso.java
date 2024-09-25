package pe.edu.pucp.softprog.gestcursos.model;
public class Curso {
    private String idCurso;
    private String nombre;
    private double notaFinal;
    
    public Curso(){}
    public Curso(String idCurso, String nombre, double notaFinal) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.notaFinal = notaFinal;
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

    public double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(double notaFinal) {
        this.notaFinal = notaFinal;
    }

}
