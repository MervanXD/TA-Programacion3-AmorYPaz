package pe.edu.pucp.softprog.gestmatricula.model;
import pe.edu.pucp.softprog.infraestructura.model.InstitucionEducativa;
import pe.edu.pucp.softprog.rrhh.model.Estudiante;
import pe.edu.pucp.softprog.infraestructura.model.Grado;

public class Traslado extends Matricula {
    private InstitucionEducativa IEOrigen; 

    public Traslado(int idMatricula, int anioMatricula, Estudiante estudiante, Grado gradoMatriculado, String estado, InstitucionEducativa IEOrigen) {
        super(idMatricula, anioMatricula, estudiante, gradoMatriculado, estado);
        this.IEOrigen = IEOrigen;
    }

    public InstitucionEducativa getIEOrigen() {
        return IEOrigen;
    }

    public void setIEOrigen(InstitucionEducativa IEOrigen) {
        this.IEOrigen = IEOrigen;
    }
}
