package pe.edu.pucp.softprog.rrhh.model;
import java.util.Date;
import pe.edu.pucp.softprog.infraestructura.model.UGEL;
import pe.edu.pucp.softprog.infraestructura.model.InstitucionEducativa;
public class Supervisor extends PersonaEducativa {
	private UGEL ugel;
	private int aniosExperienciaSupervisor;
	
    public Supervisor(String dni, String nombres, String apellidos, Date fechaNacimiento, String lengua,
                       String direccion, char sexo, String religion, InstitucionEducativa institucionEdu, 
					   Date fechaIngreso, UGEL ugel, int aniosExperienciaSupervisor) {
        super(dni, nombres, apellidos, fechaNacimiento, lengua, direccion, sexo, religion, institucionEdu, fechaIngreso);
		this.ugel = ugel;
		this.aniosExperienciaSupervisor = aniosExperienciaSupervisor;
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
}