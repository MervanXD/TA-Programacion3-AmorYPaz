package pe.edu.pucp.softprog.gestcalendario.model;
import java.util.ArrayList;
import pe.edu.pucp.softprog.infraestructura.model.Grado;
import pe.edu.pucp.softprog.gestcursos.model.Curso;

public class PlanDeEstudio {
    private int idPlan;
    private String descripcion;
    private AnioAcademico anioAcademico;
    private Grado grado;
    private ArrayList<Curso> cursos;
    private int activo;

    public PlanDeEstudio(){};
    public PlanDeEstudio(int idPlan, String descripcion, AnioAcademico anioAcademico, Grado grado) {
        this.idPlan = idPlan;
        this.descripcion = descripcion;
        this.anioAcademico = anioAcademico;
        this.grado = grado;
        this.cursos = new ArrayList<>();
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public AnioAcademico getAnioAcademico() {
        return anioAcademico;
    }

    public void setAnioAcademico(AnioAcademico anioAcademico) {
        this.anioAcademico = anioAcademico;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }
}
