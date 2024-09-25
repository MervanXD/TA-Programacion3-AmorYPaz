package pe.edu.pucp.softprog.rrhh.model;
import java.util.Date;

public abstract class Persona {
    private String dni;
    private String nombres;
    private String apellidos;
    private Date fechaNacimiento;
    private String lengua;
    private String direccion;
    private char sexo;
    private String religion;
    
    public Persona(String dni, String nombres, String apellidos, Date fechaNacimiento, String lengua, 
                   String direccion, char sexo, String religion) {
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.lengua = lengua;
        this.direccion = direccion;
        this.sexo = sexo;
        this.religion = religion;
    }
    
    // Getters
    public String getDni() {
        return dni;
    }
    
    public String getNombres() {
        return nombres;
    }
    
    public String getApellidos() {
        return apellidos;
    }
    
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    public String getLengua() {
        return lengua;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public char getSexo() {
        return sexo;
    }
    
    public String getReligion() {
        return religion;
    }

    // Setters
    public void setDni(String dni) {
        this.dni = dni;
    }
    
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public void setLengua(String lengua) {
        this.lengua = lengua;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
    
    public void setReligion(String religion) {
        this.religion = religion;
    }
}
