package pe.edu.pucp.softprog.infraestructura.model;

import java.util.ArrayList;
import pe.edu.pucp.softprog.gestcalendario.model.PlanDeEstudio;
import pe.edu.pucp.softprog.rrhh.model.Estudiante;

public class Grado {
    private int idGrado;
    private String numero;
    private TipoNivel nivel;
    private int alumnosMatriculados;
    private ArrayList<Estudiante> estudiantes;
    private InstitucionEducativa institucion;
    private PlanDeEstudio planDeEstudio;
    private boolean activo;

    public Grado(){
        estudiantes = new ArrayList<>();
        alumnosMatriculados = 0;
    }

    public Grado(String numero, TipoNivel nivel) {
        this.numero = numero;
        this.nivel = nivel;
        alumnosMatriculados = 0;
        estudiantes = new ArrayList<>();
    }

    public Grado(String numero, TipoNivel nivel, int alumnosMatriculados, ArrayList<Estudiante> estudiantes, InstitucionEducativa institucion, PlanDeEstudio planDeEstudio) {
        this.numero = numero;
        this.nivel = nivel;
        this.alumnosMatriculados = alumnosMatriculados;
        this.estudiantes = estudiantes;
        this.institucion = institucion;
        this.planDeEstudio = planDeEstudio;
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

    public boolean isActivo() {
        return activo;
    }

    public PlanDeEstudio getPlanDeEstudio() {
        return planDeEstudio;
    }

    public void setPlanDeEstudio(PlanDeEstudio planDeEstudio) {
        this.planDeEstudio = planDeEstudio;
    }
    
}
