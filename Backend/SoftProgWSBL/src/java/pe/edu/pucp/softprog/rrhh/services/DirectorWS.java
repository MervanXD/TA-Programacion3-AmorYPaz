package pe.edu.pucp.softprog.rrhh.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import java.util.ArrayList;
import pe.edu.pucp.softprog.rrhh.dao.DirectorDAO;
import pe.edu.pucp.softprog.rrhh.model.Director;
import pe.edu.pucp.softprog.rrhh.mysql.DirectorMySQL;

@WebService(serviceName = "DirectorWS")
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
}
