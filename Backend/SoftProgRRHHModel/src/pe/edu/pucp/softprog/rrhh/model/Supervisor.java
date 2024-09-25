package pe.edu.pucp.softprog.rrhh.model;
import java.util.Date;
import pe.edu.pucp.softprog.infraestructura.model.UGEL;
import pe.edu.pucp.softprog.infraestructura.model.InstitucionEducativa;
public class Supervisor extends PersonaEducativa {
	private UGEL ugel;
	private int aniosExperienciaSupervisor;
	private String email;
        private String distrito;
      
    public Supervisor(){}
    public Supervisor(String dni, String nombres, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, String lengua,
                       String direccion, char sexo, String religion, InstitucionEducativa institucionEdu, 
					   Date fechaIngreso, UGEL ugel, int aniosExperienciaSupervisor,
                                           String email, String distrito) {
        super(dni, nombres, apellidoPaterno,apellidoMaterno, fechaNacimiento, lengua, direccion, sexo, religion, institucionEdu, fechaIngreso);
		this.ugel = ugel;
		this.aniosExperienciaSupervisor = aniosExperienciaSupervisor;
                this.email=email;
                this.distrito=distrito;
	}
	
    public UGEL getUgel(){
            return ugel;
    }

    public void setUgel(UGEL ugel){
            this.ugel = ugel;
    }

    public int getAniosExperienciaSupervisor(){
            return aniosExperienciaSupervisor;
    }

    public void setAniosExperienciaSupervisor(int aniosExperienciaSupervisor){
            this.aniosExperienciaSupervisor = aniosExperienciaSupervisor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }
    
}