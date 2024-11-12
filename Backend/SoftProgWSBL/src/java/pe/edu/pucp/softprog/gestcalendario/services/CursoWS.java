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
    public ArrayList<Curso> listarPorIdNombreCursos(@WebParam(name = "idNombre") String idNombre) {
        ArrayList<Curso> cursos = null;
        if(idNombre == null) idNombre = "";
        try{
            daoCurso = new CursoMySQL();
            cursos = daoCurso.listarPorIdNombre(idNombre);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return cursos;
    }
    
    @WebMethod(operationName = "insertarCurso")
    public int insertarCurso(@WebParam(name = "curso")
            Curso curso){
        int resultado = 0;
        try{
            daoCurso = new CursoMySQL();
            resultado = daoCurso.insertar(curso);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
}
