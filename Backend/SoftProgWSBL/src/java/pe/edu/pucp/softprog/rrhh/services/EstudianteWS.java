package pe.edu.pucp.softprog.rrhh.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.softprog.rrhh.dao.EstudianteDAO;
import pe.edu.pucp.softprog.rrhh.model.Estudiante;
import pe.edu.pucp.softprog.rrhh.mysql.EstudianteMySQL;

@WebService(serviceName = "EstudianteWS", targetNamespace = "services.softprog.pucp.edu.pe")
public class EstudianteWS {

    private EstudianteDAO daoEstudiante;
    @WebMethod(operationName = "listarEstudiantesPorGrado")
    public ArrayList<Estudiante> listarEstudiantesPorGrado(@WebParam(name = "idGrado")
            int idGrado) {
        ArrayList<Estudiante> estudiantes = null;
        try{
            daoEstudiante = new EstudianteMySQL();
            estudiantes = daoEstudiante.listarEstudiantesPorGrado(idGrado);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return estudiantes;
    }
    @WebMethod(operationName = "insertarEstudiante")
    public int insertarEstudiante(@WebParam(name = "estudiante")
            Estudiante estudiante){
        int resultado = 0;
        try{
            daoEstudiante = new EstudianteMySQL();
            resultado = daoEstudiante.insertar(estudiante);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    @WebMethod(operationName = "eliminarEstudiante")
    public int eliminarEstudiante(@WebParam(name = "idEstudiante") int idEstudiante){
        int resultado = 0;
        try{
            daoEstudiante = new EstudianteMySQL();
            resultado = daoEstudiante.eliminar(idEstudiante);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "modificarEstudiante")
    public int modificarEstudiante(@WebParam(name = "estudiante") Estudiante estudiante){
        int resultado = 0;
        try{
            daoEstudiante = new EstudianteMySQL();
            resultado = daoEstudiante.modificar(estudiante);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "listarEstudiantesPorIE")
    public ArrayList<Estudiante> listarEstudiantesPorIE(@WebParam(name = "idIE")
            int idIE) {
        ArrayList<Estudiante> estudiantes = null;
        try{
            daoEstudiante = new EstudianteMySQL();
            estudiantes = daoEstudiante.listarEstudiantesPorIE(idIE);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return estudiantes;
    }
    
    @WebMethod(operationName = "listarEstPorIEYNombreDNI")
    public ArrayList<Estudiante> listarEstPorIEYNombreDNI(@WebParam(name = "idIE")
            int idIE, @WebParam(name = "nombreDNI") String nombreDNI) {
        ArrayList<Estudiante> estudiantes = null;
        try{
            daoEstudiante = new EstudianteMySQL();
            estudiantes = daoEstudiante.listarEstPorIEYNombreDNI(idIE, nombreDNI);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return estudiantes;
    }
    
    @WebMethod(operationName = "listarEstudiantesPorInstitucionEducativa")
    public ArrayList<Estudiante> listarEstudiantesPorInstitucionEducativa(@WebParam(name = "idIE")
            int idIE) {
        ArrayList<Estudiante> estudiantes = null;
        try{
            daoEstudiante = new EstudianteMySQL();
            estudiantes = daoEstudiante.listarEstudiantesPorInstitucionEducativa(idIE);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return estudiantes;
    }
    
    @WebMethod(operationName = "listarEstudiantesParaMatricula")
    public ArrayList<Estudiante> listarEstudiantesParaMatricula(@WebParam(name = "idIE")
            int idIE) {
        ArrayList<Estudiante> estudiantes = null;
        try{
            daoEstudiante = new EstudianteMySQL();
            estudiantes = daoEstudiante.listarEstudiantesParaMatricula(idIE);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return estudiantes;
    }
    
    @WebMethod(operationName = "listarEstudiantesPorMatriculasIE")
    public ArrayList<Estudiante> listarEstudiantesPorMatriculasIE(@WebParam(name = "idIE")
            int idIE) {
        ArrayList<Estudiante> estudiantes = null;
        try{
            daoEstudiante = new EstudianteMySQL();
            estudiantes = daoEstudiante.listarEstudiantesPorMatriculaIE(idIE);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return estudiantes;
    }
}
