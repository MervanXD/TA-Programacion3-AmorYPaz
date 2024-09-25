package pe.edu.pucp.softprog.rrhh.model;
import java.util.Date;

public abstract class Persona {
    private int idPersona;

    private String dni;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Date fechaNacimiento;
    private String lengua;
    private String direccion;
    private char sexo;
    private String religion;
    public Persona(){}
    public Persona(String dni, String nombres, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, String lengua, 
                   String direccion, char sexo, String religion) {
        this.dni = dni;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.lengua = lengua;
        this.direccion = direccion;
        this.sexo = sexo;
        this.religion = religion;
    }
    
    // Getters
    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }
    public String getDni() {
        return dni;
    }
    
    public String getNombres() {
        return nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
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
