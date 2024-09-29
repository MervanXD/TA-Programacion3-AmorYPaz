package pe.edu.pucp.softprog.infraestructura.model;

import java.util.ArrayList;
import pe.edu.pucp.softprog.rrhh.model.Estudiante;

public class Grado {
    private int idGrado;
    private String numero;
    private TipoNivel nivel;
    private int alumnosMatriculados;
    private ArrayList<Estudiante> estudiantes;
    private InstitucionEducativa institucion;

    public Grado(){}

    public Grado(String numero, TipoNivel nivel, int alumnosMatriculados, InstitucionEducativa institucion) {
        this.numero = numero;
        this.nivel = nivel;
        this.alumnosMatriculados = alumnosMatriculados;
        this.institucion = institucion;
    }

    public int getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(int idGrado) {
        this.idGrado = idGrado;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoNivel getNivel() {
        return nivel;
    }

    public void setNivel(TipoNivel nivel) {
        this.nivel = nivel;
    }

    public int getAlumnosMatriculados() {
        return alumnosMatriculados;
    }

    public void setAlumnosMatriculados(int alumnosMatriculados) {
        this.alumnosMatriculados = alumnosMatriculados;
    }

    public InstitucionEducativa getInstitucion() {
        return institucion;
    }

    public void setInstitucion(InstitucionEducativa institucion) {
        this.institucion = institucion;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }
    
}
