package pe.edu.pucp.softprog.gestcalendario.model;

import java.util.ArrayList;
import pe.edu.pucp.softprog.gestcursos.model.Curso;

public class PlanDeEstudio {

    private int idPlan;
    private String descripcion;
    private int numCursos;
    private AnioAcademico anioAcademico;
    private ArrayList<Curso> cursos;
    private boolean activo;

    public PlanDeEstudio() {
        this.cursos = new ArrayList<>();
        this.numCursos = 0;
    }

    public PlanDeEstudio(String descripcion) {
        this.descripcion = descripcion;
        this.cursos = new ArrayList<>();
        this.numCursos = 0;
    }

    public boolean isActivo() {
        return activo;
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

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }

    public int getNumCursos() {
        return numCursos;
    }

    public void setNumCursos(int numCursos) {
        this.numCursos = numCursos;
    }
    
}
