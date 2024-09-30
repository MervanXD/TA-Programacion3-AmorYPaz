package pe.edu.pucp.softprog.rrhh.model;

import java.util.Date;

public class Director extends Persona {
    private String tipoContrato;
    private Date fechaNombramiento;
    private String email;
    private boolean activo;

    public Director() {
    }

    public Director(String tipoContrato, Date fechaNombramiento, String email, String dni, String nombres, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, String lengua, String religion, char sexo, String direccion) {
        super(dni, nombres, apellidoPaterno, apellidoMaterno, fechaNacimiento, lengua, religion, sexo, direccion);
        this.tipoContrato = tipoContrato;
        this.fechaNombramiento = fechaNombramiento;
        this.email = email;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public Date getFechaNombramiento() {
        return fechaNombramiento;
    }

    public void setFechaNombramiento(Date fechaNombramiento) {
        this.fechaNombramiento = fechaNombramiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
}
