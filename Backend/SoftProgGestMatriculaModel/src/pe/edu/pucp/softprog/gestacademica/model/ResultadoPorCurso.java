package pe.edu.pucp.softprog.gestacademica.model;

import pe.edu.pucp.softprog.gestcursos.model.Curso;

public class ResultadoPorCurso {
    private Curso curso;
    private ResultadoAnual resultadoAnual;

    public ResultadoPorCurso(Curso curso, ResultadoAnual resultadoAnual) {
        this.curso = curso;
        this.resultadoAnual = resultadoAnual;
    }

    public ResultadoAnual getResultadoAnual() {
        return resultadoAnual;
    }

    public void setResultadoAnual(ResultadoAnual resultadoAnual) {
        this.resultadoAnual = resultadoAnual;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
}
