package pe.edu.pucp.softprog.gestmatricula.model;
import java.util.ArrayList;
import pe.edu.pucp.softprog.gestcursos.model.Curso;
import pe.edu.pucp.softprog.rrhh.model.Estudiante;
import pe.edu.pucp.softprog.infraestructura.model.Grado;
public class Ascenso extends Matricula {
    private int cursosAprobados;
    private int cursosJalados;
    private ArrayList<Curso> cursos; 

    public Ascenso(int idMatricula, int anioMatricula, Estudiante estudiante, Grado gradoMatriculado, String estado, int cursosAprobados, int cursosJalados) {
        super(idMatricula, anioMatricula, estudiante, gradoMatriculado, estado);
        this.cursosAprobados = cursosAprobados;
        this.cursosJalados = cursosJalados;
        this.cursos = new ArrayList<>();
    }

    public int getCursosAprobados() {
        return cursosAprobados;
    }

    public void setCursosAprobados(int cursosAprobados) {
        this.cursosAprobados = cursosAprobados;
    }

    public int getCursosJalados() {
        return cursosJalados;
    }

    public void setCursosJalados(int cursosJalados) {
        this.cursosJalados = cursosJalados;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }
}
