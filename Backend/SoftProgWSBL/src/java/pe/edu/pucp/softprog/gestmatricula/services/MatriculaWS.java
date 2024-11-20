package pe.edu.pucp.softprog.gestmatricula.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.softprog.gestacademica.model.Matricula;
import pe.edu.pucp.softprog.gestmatricula.dao.MatriculaDAO;
import pe.edu.pucp.softprog.gestmatricula.mysql.MatriculaMySQL;

@WebService(serviceName = "MatriculaWS", 
        targetNamespace = "services.softprog.pucp.edu.pe")
public class MatriculaWS {

    
    private MatriculaDAO daoMatricula;
    @WebMethod(operationName = "listarMatriculas")
    public ArrayList<Matricula> listarMatriculas() {
        ArrayList<Matricula> matriculas = null;
        try{
            daoMatricula = new MatriculaMySQL();
            matriculas = daoMatricula.listarTodos();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return matriculas;
    }
    @WebMethod(operationName = "insertarMatricula")
    public int insertarMatricula(@WebParam(name = "matricula")
            Matricula matricula){
        int resultado = 0;
        try{
            daoMatricula = new MatriculaMySQL();
            resultado = daoMatricula.insertar(matricula);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    @WebMethod(operationName = "eliminarMatricula")
    public int eliminarMatricula(@WebParam(name = "matricula") int idMatric){
        int resultado = 0;
        try{
            daoMatricula = new MatriculaMySQL();
            resultado = daoMatricula.eliminar(idMatric);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    @WebMethod(operationName = "modificarMatricula")
    public int modificarMatricula(@WebParam(name = "matricula") Matricula matricula){
        int resultado = 0;
        try{
            daoMatricula = new MatriculaMySQL();
            resultado = daoMatricula.modificar(matricula);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "listarMatriculasPorIdIE")
    public ArrayList<Matricula> listarMatriculasPorIdIE(int idInstitucion){
        ArrayList<Matricula> matriculas = new ArrayList<>();
        try{
            daoMatricula = new MatriculaMySQL();
            matriculas = daoMatricula.listarPorIdIE(idInstitucion);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return matriculas;
    }
    
    @WebMethod(operationName = "obtenerPorCriterios")
    public Matricula obtenerPorCriterios(int idAnio, int idGrado, int idPersona){
        Matricula matricula = null;
        try{
            daoMatricula = new MatriculaMySQL();
            matricula = daoMatricula.obtenerPorAnioGradoEstudiante(idAnio, idGrado, idPersona);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return matricula;
    }
}
