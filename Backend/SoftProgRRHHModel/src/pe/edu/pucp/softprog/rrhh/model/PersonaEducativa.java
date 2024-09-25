package pe.edu.pucp.softprog.rrhh.model;
import java.util.Date;
import pe.edu.pucp.softprog.infraestructura.model.InstitucionEducativa;

public class PersonaEducativa extends Persona {
    private InstitucionEducativa institucionEdu;
	private Date fechaIngreso;
    
    public PersonaEducativa(){}
    // Constructor de PersonaEducativa que llama al constructor de la clase Persona
    public PersonaEducativa(String dni, String nombres, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, String lengua,
                            String direccion, char sexo, String religion, InstitucionEducativa institucionEdu, Date fechaIngresos) {
        super(dni, nombres, apellidoPaterno,apellidoMaterno, fechaNacimiento, lengua, direccion, sexo, religion);
        this.institucionEdu = institucionEdu;
		this.fechaIngreso = fechaIngreso;
    }

    // Getters
    public InstitucionEducativa getInstitucionEdu() {
        return institucionEdu;
    }
	
	public Date getFechaIngreso(){
		return fechaIngreso;
	}
	
	//Setters
    public void setInstitucionEdu(InstitucionEducativa institucionEdu) {
        this.institucionEdu = institucionEdu;
    }
	
	public void setFechaIngreso(Date fechaIngreso){
		this.fechaIngreso = fechaIngreso;
	}
}
