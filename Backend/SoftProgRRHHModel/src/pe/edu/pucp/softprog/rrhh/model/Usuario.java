package pe.edu.pucp.softprog.rrhh.model;

public class Usuario {   
    private int idUsuario;
    private String username;
    private String contrasena;       

    public Usuario(){};

    public Usuario(String username, String contrasena) {
        this.username = username;
        this.contrasena = contrasena;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
}

