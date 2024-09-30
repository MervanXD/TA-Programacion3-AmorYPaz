package pe.edu.pucp.softprog.gestcalendario.model;

import java.util.ArrayList;
import java.util.Date;

public class AnioAcademico {

    private int idAnio;
    private int numero;
    private Date fechaInicio;
    private Date fechaFin;
    private String estado;
    private ArrayList<PlanDeEstudio> planes;
    private boolean activo;

    public AnioAcademico() {
        this.planes = new ArrayList<>();
    }

    public AnioAcademico(int numero, Date fechaInicio, Date fechaFin, String estado) {
        this.numero = numero;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.planes = new ArrayList<>();
    }

    public int getIdAnio() {
        return idAnio;
    }

    public void setIdAnio(int idAnio) {
        this.idAnio = idAnio;
    }
    
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList<PlanDeEstudio> getPlanes() {
        return planes;
    }

    public void setPlanes(ArrayList<PlanDeEstudio> planes) {
        this.planes = planes;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

}
