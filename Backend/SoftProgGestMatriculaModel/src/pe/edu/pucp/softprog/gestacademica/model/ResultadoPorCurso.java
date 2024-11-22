package pe.edu.pucp.softprog.gestacademica.model;

import pe.edu.pucp.softprog.gestcursos.model.Curso;

public class ResultadoPorCurso {
    private Curso curso;
    private int calificacion;
    private Matricula matricula;

    public ResultadoPorCurso() {
    }
    
    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }
    
    
    
}
