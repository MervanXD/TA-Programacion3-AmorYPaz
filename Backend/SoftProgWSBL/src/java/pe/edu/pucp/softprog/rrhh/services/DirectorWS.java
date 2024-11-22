package pe.edu.pucp.softprog.rrhh.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.softprog.rrhh.dao.DirectorDAO;
import pe.edu.pucp.softprog.rrhh.model.Director;
import pe.edu.pucp.softprog.rrhh.mysql.DirectorMySQL;

@WebService(serviceName = "DirectorWS", targetNamespace = "services.softprog.pucp.edu.pe")
public class DirectorWS {
    private DirectorDAO daoDirector;
    @WebMethod(operationName = "listarDirectoresTodas")
    public ArrayList<Director> listarDirectoresTodas() {
        ArrayList<Director> directores = null;
        try{
            daoDirector = new DirectorMySQL();
            directores = daoDirector.listarTodos();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return directores;
    }
    
    @WebMethod(operationName = "listarDirectoresDisponibles")
    public ArrayList<Director> listarDirectoresDisponibles(@WebParam(name = "idDirector")
            int idDirector) {
        ArrayList<Director> directores = null;
        try{
            daoDirector = new DirectorMySQL();
            directores = daoDirector.listarTodosDisponibles(idDirector);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return directores;
    }
    
    @WebMethod(operationName = "listarDirectoresPorNombre")
    public ArrayList<Director> listarDirectoresPorNombre(@WebParam(name = "nombreDir")
            String nombreDir) {
        ArrayList<Director> directores = null;
        try{
            daoDirector = new DirectorMySQL();
            directores = daoDirector.listarDirectoresPorNombre(nombreDir);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return directores;
    }
    
    @WebMethod(operationName = "insertarDirector")
    public int insertarEmpleado(@WebParam(name = "director")
            Director director){
        int resultado = 0;
        try{
            daoDirector = new DirectorMySQL();
            resultado = daoDirector.insertar(director);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "buscarDirector")
    public Director buscarDirector(@WebParam(name = "idDirector")
            int idDirector){
        Director director=null;
        try{
            daoDirector = new DirectorMySQL();
            director = daoDirector.obtenerPorId(idDirector);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return director;
    }
    @WebMethod(operationName = "eliminarDirector")
    public int eliminarDirector(@WebParam(name = "idDirector") int idDirector){
        int resultado = 0;
        try{
            daoDirector = new DirectorMySQL();
            resultado = daoDirector.eliminar(idDirector);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    @WebMethod(operationName = "modificarDirector")
    public int modificarDirector(@WebParam(name = "director") Director director){
        int resultado = 0;
        try{
            daoDirector = new DirectorMySQL();
            resultado = daoDirector.modificar(director);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
}
