package pe.edu.pucp.softprog.rrhh.model;

import java.util.Date;

public class Estudiante extends Persona {
    private int idEstudiante;
    private int cantCursos;
    private double promedio;
    private String alergias;
    private String discapacidades;
    private String estado;
    private Persona apoderado;
    
    public Estudiante() {
        apoderado = new Persona();
        cantCursos = 0;
        promedio = 0;
    }
    
    public Estudiante(Persona apoderado, String alergias, String estado, String discapacidades, String dni, String nombres, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, String lengua, String religion, char sexo, String direccion) {
        super(dni, nombres, apellidoPaterno, apellidoMaterno, fechaNacimiento, lengua, religion, sexo, direccion);
        this.apoderado = apoderado;
        this.alergias = alergias;
        this.estado = estado;
        this.discapacidades = discapacidades;
        cantCursos = 0;
        promedio = 0;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
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

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
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
    
}
