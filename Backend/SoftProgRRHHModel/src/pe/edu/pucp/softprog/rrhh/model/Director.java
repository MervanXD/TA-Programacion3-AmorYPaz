package pe.edu.pucp.softprog.rrhh.model;
import java.util.Date;
import pe.edu.pucp.softprog.infraestructura.model.InstitucionEducativa;

public class Director extends PersonaEducativa {
	private String tipoContrato;
	private Date fechaNombramiento;
	private String email;
        
    public Director(){}
    
    public Director(String dni, String nombres, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, String lengua,
                         String direccion, char sexo, String religion, InstitucionEducativa institucionEdu, 
						 Date fechaIngreso, String tipoContrato, Date fechaNombramiento,String email) {
        super(dni, nombres, apellidoPaterno,apellidoMaterno, fechaNacimiento, lengua, direccion, sexo, religion, institucionEdu, fechaIngreso);
		this.tipoContrato = tipoContrato;
		this.fechaNombramiento = fechaNombramiento;
		this.email = email;
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

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}

