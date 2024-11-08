package pe.edu.pucp.softprog.gestacademica.model;

import java.util.ArrayList;
import pe.edu.pucp.softprog.rrhh.model.Estudiante;

public class ResultadoAnual {
    private Estudiante estudiante;
    private Matricula matricula;
    private int merito;
    private int cantidadCursosAnual;
    private double promedioAnual;
    private ArrayList<ResultadoPorCurso> resultadosPorCurso;
    
    public ResultadoAnual(){
        this.resultadosPorCurso = new ArrayList<>();
    }
    
    public ResultadoAnual(Estudiante estudiante, Matricula matricula, int merito, int cantidadCursosAnual, double promedioAnual) {
        this.estudiante = estudiante;
        this.matricula = matricula;
        this.merito = merito;
        this.cantidadCursosAnual = cantidadCursosAnual;
        this.promedioAnual = promedioAnual;
        this.resultadosPorCurso = new ArrayList<>();
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public int getMerito() {
        return merito;
    }

    public void setMerito(int merito) {
        this.merito = merito;
    }

    public int getCantidadCursosAnual() {
        return cantidadCursosAnual;
    }

    public void setCantidadCursosAnual(int cantidadCursosAnual) {
        this.cantidadCursosAnual = cantidadCursosAnual;
    }

    public double getPromedioAnual() {
        return promedioAnual;
    }

    public void setPromedioAnual(double promedioAnual) {
        this.promedioAnual = promedioAnual;
    }

    public ArrayList<ResultadoPorCurso> getResultadosPorCurso() {
        return resultadosPorCurso;
    }

    public void setResultadosPorCurso(ArrayList<ResultadoPorCurso> resultadosPorCurso) {
        this.resultadosPorCurso = resultadosPorCurso;
    }
    
}
