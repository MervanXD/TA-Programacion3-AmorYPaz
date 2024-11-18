package pe.edu.pucp.softprog.gestcalendario.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.softprog.gestcursos.dao.CursoDAO;
import pe.edu.pucp.softprog.gestcursos.model.Curso;
import pe.edu.pucp.softprog.gestcursos.mysql.CursoMySQL;

@WebService(serviceName = "CursoWS", targetNamespace = "services.softprog.pucp.edu.pe")
public class CursoWS {
    
    private CursoDAO daoCurso;
    
    @WebMethod(operationName = "listarPorIdNombreCursos")
    public ArrayList<Curso> listarPorIdNombreCursos(@WebParam(name = "idNombre") String idNombre,
            @WebParam(name = "idIE") int idIE) {
        ArrayList<Curso> cursos = null;
        if(idNombre == null) idNombre = "";
        try{
            daoCurso = new CursoMySQL();
            cursos = daoCurso.listarPorIdNombre(idNombre, idIE);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return cursos;
    }
    
    @WebMethod(operationName = "listarPorIdPlan")
    public ArrayList<Curso> listarPorIdPlan(@WebParam(name = "idPlan") int idPlan) {
        ArrayList<Curso> cursos = null;
        try{
            daoCurso = new CursoMySQL();
            cursos = daoCurso.listarPorIdPlan(idPlan);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return cursos;
    }
    
    @WebMethod(operationName = "insertarCurso")
    public int insertarCurso(@WebParam(name = "curso")
            Curso curso, @WebParam(name = "idIE") int idIE){
        int resultado = 0;
        try{
            daoCurso = new CursoMySQL();
            resultado = daoCurso.insertar(curso, idIE);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "listarCursosPorIdIE")
    public ArrayList<Curso> listarCursosPorIdIE(@WebParam(name = "idIE")
            int idIE){
        ArrayList<Curso> cursos = null;
        try{
            daoCurso = new CursoMySQL();
            cursos = daoCurso.listarPorIdIE(idIE);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return cursos;
    }
    
    @WebMethod(operationName = "insertarCursoEnPlan")
    public int insertarCursoEnPlan(@WebParam(name = "idCurso")
            int idCurso, @WebParam(name = "idPlan") int idPlan){
        int resultado = 0;
        try{
            daoCurso = new CursoMySQL();
            resultado = daoCurso.insertarCursoPlanEstudios(idCurso, idPlan);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "eliminarCursoDePlan")
    public int eliminarCursoDePlan(@WebParam(name = "idCurso")
            int idCurso, @WebParam(name = "idPlan") int idPlan){
        int resultado = 0;
        try{
            daoCurso = new CursoMySQL();
            resultado = daoCurso.eliminarCursoDePlanEstudios(idCurso, idPlan);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
   
    
}
