package pe.edu.pucp.softprog.rrhh.model;
import java.util.Date;
import pe.edu.pucp.softprog.infraestructura.model.InstitucionEducativa;
public class Director extends PersonaEducativa {
	private String tipoContrato;
	private Date fechaNombramiento;
	
    public Director(String dni, String nombres, String apellidos, Date fechaNacimiento, String lengua,
                         String direccion, char sexo, String religion, InstitucionEducativa institucionEdu, 
						 Date fechaIngreso, String tipoContrato, Date fechaNombramiento) {
        super(dni, nombres, apellidos, fechaNacimiento, lengua, direccion, sexo, religion, institucionEdu, fechaIngreso);
		this.tipoContrato = tipoContrato;
		this.fechaNombramiento = fechaNombramiento;
    }
	
	public String getTipoContrato(){
		return tipoContrato;
	}
	
	public void setTipoContrato(String tipoContrato){
		this.tipoContrato = tipoContrato;
	}
	
	public Date getFechaNombramiento(){
		return fechaNombramiento;
	}
	
	public void setFechaNombramiento(Date fechaNombramiento){
		this.fechaNombramiento = fechaNombramiento;
	}
}

