package pe.edu.pucp.softprog.infraest.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.softprog.infraestructura.dao.GradoDAO;
import pe.edu.pucp.softprog.infraestructura.model.Grado;
import pe.edu.pucp.softprog.infraestructura.mysql.GradoMySQL;

@WebService(serviceName = "GradoWS",
        targetNamespace = "services.softprog.pucp.edu.pe")
public class GradoWS {
    
    private GradoDAO daoGrado;
    @WebMethod(operationName = "obtenerPorIdPlanEstudios")
    public Grado obtenerPorIdPlanEstudios(@WebParam(name = "idPlan") int idPlan) {
        Grado grado;
        try{
            daoGrado = new GradoMySQL();
            grado = daoGrado.obtenerPorIdPlanEstudios(idPlan);
        }catch(Exception ex){
            grado = null;
            System.out.println(ex.getMessage());
        }
        return grado;
    }
    
    @WebMethod(operationName = "listarPorIdIE")
    public ArrayList<Grado> listarPorIdIE(@WebParam(name = "idInstitucion") int idInstitucion) {
        ArrayList<Grado> planes = null;
        try{
            daoGrado = new GradoMySQL();
            planes = daoGrado.listarTodosPorIdIE(idInstitucion);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return planes;
    }
}
