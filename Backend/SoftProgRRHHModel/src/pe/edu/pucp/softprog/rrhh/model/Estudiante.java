package pe.edu.pucp.softprog.rrhh.model;

import java.util.Date;

public class Estudiante extends Persona {
    private int cantCursos;
    private double promedio;
    private String condicionesMedicas;
    private String discapacidades;
    private String estado;
    private boolean activo;
    private Persona apoderado;
    
    public Estudiante() {
        this.apoderado = new Persona();
        this.cantCursos = 0;
        this.promedio = 0;
    }
    
    public Estudiante(Persona apoderado, String condicionesMedicas, String estado, String discapacidades, String dni, String nombres, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, String lengua, String religion, char sexo, String direccion) {
        super(dni, nombres, apellidoPaterno, apellidoMaterno, fechaNacimiento, lengua, religion, sexo, direccion);
        this.apoderado = apoderado;
        this.condicionesMedicas = condicionesMedicas;
        this.estado = estado;
        this.discapacidades = discapacidades;
        this.cantCursos = 0;
        this.promedio = 0;
    }

    public int getCantCursos() {
        return cantCursos;
    }

    public void setCantCursos(int cantCursos) {
        this.cantCursos = cantCursos;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public Persona getApoderado() {
        return apoderado;
    }

    public void setApoderado(Persona apoderado) {
        this.apoderado = apoderado;
    }

    public String getCondicionesMedicas() {
        return condicionesMedicas;
    }

    public void setCondicionesMedicas(String condicionesMedicas) {
        this.condicionesMedicas = condicionesMedicas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDiscapacidades() {
        return discapacidades;
    }

    public void setDiscapacidades(String discapacidades) {
        this.discapacidades = discapacidades;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
}
