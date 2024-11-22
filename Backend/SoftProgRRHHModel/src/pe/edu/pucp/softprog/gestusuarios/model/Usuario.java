package pe.edu.pucp.softprog.gestusuarios.model;

import pe.edu.pucp.softprog.rrhh.model.Director;

public class Usuario {   
    private int idUsuario;
    private String username;
    private String contrasena;
    private TipoUsuario tipoUsuario;
    private Director director;
    private boolean activo;

    public Usuario(){};

    public Usuario(String username, String contrasena, TipoUsuario tipoUsuario) {
        this.username = username;
        this.contrasena = contrasena;
        this.tipoUsuario = tipoUsuario;
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

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }
    
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}

