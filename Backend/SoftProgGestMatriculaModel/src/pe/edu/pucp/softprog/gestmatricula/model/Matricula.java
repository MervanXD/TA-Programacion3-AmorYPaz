package pe.edu.pucp.softprog.gestmatricula.model;
import pe.edu.pucp.softprog.rrhh.model.Estudiante;
import pe.edu.pucp.softprog.infraestructura.model.Grado;
public class Matricula {
    private int idMatricula;
    private int anioMatricula;
    private Estudiante estudiante;
    private Grado gradoMatriculado;
    private String estado;

    public Matricula(){}
    public Matricula(int idMatricula, int anioMatricula, Estudiante estudiante, Grado gradoMatriculado, String estado) {
        this.idMatricula = idMatricula;
        this.anioMatricula = anioMatricula;
        this.estudiante = estudiante;
        this.gradoMatriculado = gradoMatriculado;
        this.estado = estado;
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    public int getAnioMatricula() {
        return anioMatricula;
    }

    public void setAnioMatricula(int anioMatricula) {
        this.anioMatricula = anioMatricula;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Grado getGradoMatriculado() {
        return gradoMatriculado;
    }

    public void setGradoMatriculado(Grado gradoMatriculado) {
        this.gradoMatriculado = gradoMatriculado;
    }

    public String getEstado(){
            return estado;
    }

    public void setEstado(String estado){
            this.estado = estado;
    }
}
