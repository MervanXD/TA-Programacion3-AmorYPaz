package pe.edu.pucp.softprog.rrhh.model;
public class Usuario {
    private String dni;          
    private String contrasena;    
    private Persona cargo;        
    private String privilegio;    
    private int estado;           

    public Usuario(String dni, String contrasena, Persona cargo, String privilegio, int estado) {
        this.dni = dni;
        this.contrasena = contrasena;
        this.cargo = cargo;
        this.privilegio = privilegio;
        this.estado = estado;
    }

    // Getters
    public String getDni() {
        return dni;
    }

    public String getContrasena() {
        return contrasena;
    }

    public Persona getCargo() {
        return cargo;
    }

    public String getPrivilegio() {
        return privilegio;
    }

    public int getEstado() {
        return estado;
    }

    // Setters
    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setCargo(Persona cargo) {
        this.cargo = cargo;
    }

    public void setPrivilegio(String privilegio) {
        this.privilegio = privilegio;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}

