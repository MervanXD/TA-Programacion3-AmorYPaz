package pe.edu.pucp.softprog.rrhh.model;
import java.util.Date;
import pe.edu.pucp.softprog.infraestructura.model.InstitucionEducativa;

public class PersonaEducativa extends Persona {
    private InstitucionEducativa institucionEdu;
	private Date fechaIngreso;

    // Constructor de PersonaEducativa que llama al constructor de la clase Persona
    public PersonaEducativa(String dni, String nombres, String apellidos, Date fechaNacimiento, String lengua,
                            String direccion, char sexo, String religion, InstitucionEducativa institucionEdu, Date fechaIngresos) {
        super(dni, nombres, apellidos, fechaNacimiento, lengua, direccion, sexo, religion);
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
