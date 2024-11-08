package pe.edu.pucp.softprog.infraestructura.model;
import java.util.ArrayList;
import pe.edu.pucp.softprog.rrhh.model.Director;

public class UGEL {
    private int idUgel;
    private String codigo;
    private String distrito;
    private int cantidadIE;
    private ArrayList<InstitucionEducativa> institucionesEducativas;
    private Director directorUgel;
    private boolean activo;

    public UGEL() {
        institucionesEducativas = new ArrayList<>();
        cantidadIE = 0;
    }

    public UGEL(String codigo, String distrito) {
        this.codigo = codigo;
        this.distrito = distrito;
        institucionesEducativas = new ArrayList<>();
        cantidadIE = 0;
    }

    public UGEL(String codigo, String distrito, Director directorUgel, int cantidadIE, ArrayList<InstitucionEducativa> institucionesEducativas) {
        this.codigo = codigo;
        this.distrito = distrito;
        this.directorUgel = directorUgel;
        this.cantidadIE = cantidadIE;
        this.institucionesEducativas = institucionesEducativas;
    }

    public int getIdUgel() {
        return idUgel;
    }

    public void setIdUgel(int idUgel) {
        this.idUgel = idUgel;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public int getCantidadIE() {
        return cantidadIE;
    }

    public void setCantidadIE(int cantidadIE) {
        this.cantidadIE = cantidadIE;
    }

    public ArrayList<InstitucionEducativa> getInstitucionesEducativas() {
        return institucionesEducativas;
    }

    public void setInstitucionesEducativas(ArrayList<InstitucionEducativa> institucionesEducativas) {
        this.institucionesEducativas = institucionesEducativas;
    }

    public Director getDirectorUgel() {
        return directorUgel;
    }

    public void setDirectorUgel(Director directorUgel) {
        this.directorUgel = directorUgel;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
}
