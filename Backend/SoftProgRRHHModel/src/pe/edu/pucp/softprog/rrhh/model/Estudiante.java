package pe.edu.pucp.softprog.rrhh.model;
import java.util.Date;
public class Estudiante extends Persona {
    private int idEstudiante;
    private Persona apoderado;
    private boolean discapacidad;
    private String tipoDiscapacidad;
    private String alergias;
    private String enfermedadesCronicas;
	private String estado;
	private String origen;

    public Estudiante(String dni, String nombres, String apellidos, Date fechaNacimiento, String lengua,
                      String direccion, char sexo, String religion,
					  int idEstudiante, Persona apoderado, boolean discapacidad, 
                      String tipoDiscapacidad, String alergias, String enfermedadesCronicas, String estado, String origen) {
        super(dni, nombres, apellidos, fechaNacimiento, lengua, direccion, sexo, religion);
        this.idEstudiante = idEstudiante;
        this.apoderado = apoderado;
        this.discapacidad = discapacidad;
        this.tipoDiscapacidad = tipoDiscapacidad;
        this.alergias = alergias;
        this.enfermedadesCronicas = enfermedadesCronicas;
		this.estado = estado;
		this.origen = origen;
    }

    
    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Persona getApoderado() {
        return apoderado;
    }

    public void setApoderado(Persona apoderado) {
        this.apoderado = apoderado;
    }

    public boolean isDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(boolean discapacidad) {
        this.discapacidad = discapacidad;
    }

    public String getTipoDiscapacidad() {
        return tipoDiscapacidad;
    }

    public void setTipoDiscapacidad(String tipoDiscapacidad) {
        this.tipoDiscapacidad = tipoDiscapacidad;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getEnfermedadesCronicas() {
        return enfermedadesCronicas;
    }

    public void setEnfermedadesCronicas(String enfermedadesCronicas) {
        this.enfermedadesCronicas = enfermedadesCronicas;
    }
	
	public String getEstado(){
		return estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
	
	public String getOrigen(){
		return origen;
	}
	
	public void setOrigen(String origen){
		this.origen = origen;
	}
}

