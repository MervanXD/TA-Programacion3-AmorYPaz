package pe.edu.pucp.softprog.infraestructura.model;
import java.util.ArrayList;
import pe.edu.pucp.softprog.rrhh.model.Supervisor;

public class UGEL {
    private int idUgel;
    private int numero;
    private Supervisor supervisor;
    private int cantidadIE;
    private ArrayList<InstitucionEducativa> institucionesEducativas;  

    public UGEL() {
        institucionesEducativas = new ArrayList<>();
        cantidadIE = 0;
    }

    public UGEL(int numero) {
        this.numero = numero;
    }

    public UGEL(int numero, int cantidadIE, ArrayList<InstitucionEducativa> institucionesEducativas) {
        this.numero = numero;
        this.cantidadIE = cantidadIE;
        this.institucionesEducativas = institucionesEducativas;
    }

    public int getIdUgel() {
        return idUgel;
    }

    public void setIdUgel(int idUgel) {
        this.idUgel = idUgel;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }
}
