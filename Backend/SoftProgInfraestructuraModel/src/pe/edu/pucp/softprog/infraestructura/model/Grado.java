package pe.edu.pucp.softprog.infraestructura.model;

public class Grado {
    private int idGrado;
    private String nombre;
    private Nivel nivel;
    private InstitucionEducativa institucion;
    public Grado(){};
    private int activo;

    public Grado(int idGrado, String nombre, Nivel nivel) {
        this.idGrado = idGrado;
        this.nombre = nombre;
        this.nivel = nivel;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public int getIdGrado() {
        return idGrado;
    }

    public InstitucionEducativa getInstitucion() {
        return institucion;
    }

    public void setInstitucion(InstitucionEducativa institucion) {
        this.institucion = institucion;
    }

    public void setIdGrado(int idGrado) {
        this.idGrado = idGrado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }
}
