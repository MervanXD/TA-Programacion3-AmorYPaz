package pe.edu.pucp.softprog.gestacademica.model;

import java.util.Date;
import pe.edu.pucp.softprog.gestcalendario.model.AnioAcademico;
import pe.edu.pucp.softprog.rrhh.model.Estudiante;
import pe.edu.pucp.softprog.infraestructura.model.Grado;
import pe.edu.pucp.softprog.infraestructura.model.InstitucionEducativa;

public class Matricula {

    private int idMatricula;
    private Date fecha;
    private String estado;
    private TipoMatricula tipoMatricula;
    private Grado grado; //
    private AnioAcademico anioAcademico; //
    private Estudiante estudiante;
    private InstitucionEducativa institucion;

    public Matricula() {
    }
    public Date getFecha() {
        return fecha;
    }

    public InstitucionEducativa getInstitucion() {
        return institucion;
    }

    public void setInstitucion(InstitucionEducativa institucion) {
        this.institucion = institucion;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Matricula(TipoMatricula tipoMatricula, String estado, AnioAcademico anioAcademico, Estudiante estudiante, Grado grado) {
        this.tipoMatricula = tipoMatricula;
        this.estado = estado;
        this.anioAcademico = anioAcademico;
        this.estudiante = estudiante;
        this.grado = grado;
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    public TipoMatricula getTipoMatricula() {
        return tipoMatricula;
    }

    public void setTipoMatricula(TipoMatricula tipoMatricula) {
        this.tipoMatricula = tipoMatricula;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public AnioAcademico getAnioAcademico() {
        return anioAcademico;
    }

    public void setAnioAcademico(AnioAcademico anioAcademico) {
        this.anioAcademico = anioAcademico;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }
    
}
