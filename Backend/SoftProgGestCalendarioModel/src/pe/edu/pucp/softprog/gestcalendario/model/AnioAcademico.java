package pe.edu.pucp.softprog.gestcalendario.model;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softprog.gestmatricula.model.Matricula;
public class AnioAcademico {
    private int idAño;
    private Date fechaInicio;
    private Date fechaFin;
	private String estado;
    private ArrayList<PlanDeEstudio> planes;
    private ArrayList<Matricula> matriculas;

    public AnioAcademico(int idAño, Date fechaInicio, Date fechaFin, String estado) {
        this.idAño = idAño;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.planes = new ArrayList<>();
        this.matriculas = new ArrayList<>();
    }

    public int getIdAño() {
        return idAño;
    }

    public void setIdAño(int idAño) {
        this.idAño = idAño;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado(){
		return estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}

    public ArrayList<PlanDeEstudio> getPlanes() {
        return planes;
    }

    public void setPlanes(ArrayList<PlanDeEstudio> planes) {
        this.planes = planes;
    }

    public ArrayList<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(ArrayList<Matricula> matriculas) {
        this.matriculas = matriculas;
    }
}
