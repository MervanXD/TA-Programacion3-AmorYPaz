package pe.edu.pucp.softprog.infraestructura.model;
import java.util.ArrayList;

public class UGEL {
    private int numUgel;                        
    private int cantIE;                         
    private ArrayList<InstitucionEducativa> colegios;  

    public UGEL(int numUgel, int cantIE) {
        this.numUgel = numUgel;
        this.cantIE = cantIE;  
        this.colegios = new ArrayList<>();  
    }

    // Getters
    public int getNumUgel() {
        return numUgel;
    }

    public int getCantIE() {
        return cantIE;
    }

    public ArrayList<InstitucionEducativa> getColegios() {
        return colegios;
    }

    // Setters
    public void setNumUgel(int numUgel) {
        this.numUgel = numUgel;
    }

    public void setCantIE(int cantIE) {
        this.cantIE = cantIE;
    }

    public void setColegios(ArrayList<InstitucionEducativa> colegios) {
        this.colegios = colegios;
    }

}
