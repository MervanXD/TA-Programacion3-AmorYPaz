package pe.edu.pucp.softprog.infraest.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.softprog.infraestructura.dao.InstitucionEducativaDAO;
import pe.edu.pucp.softprog.infraestructura.model.InstitucionEducativa;
import pe.edu.pucp.softprog.infraestructura.mysql.InstitucionEducativaMySQL;

@WebService(serviceName = "InstitucionEducativaWS")
public class InstitucionEducativaWS {

    private InstitucionEducativaDAO daoIEducativa;
    
    @WebMethod(operationName = "listarPorIdNombre")
    public ArrayList<InstitucionEducativa> listarPorIdNombre(@WebParam(name = "idNombre") String idNombre) {
        ArrayList<InstitucionEducativa> instituciones = null;
        if(idNombre == null) idNombre = "";
        try{
            daoIEducativa = new InstitucionEducativaMySQL();
            instituciones = daoIEducativa.listarInstitucionesPorIdNombre(idNombre);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return instituciones;
    }
    
    @WebMethod(operationName = "listarPorNombreYUgel")
    public ArrayList<InstitucionEducativa> listarPorNombreYUgel(
            @WebParam(name = "idNombre") String idNombre,
            @WebParam(name = "idUgel") int idUgel) {
        ArrayList<InstitucionEducativa> instituciones = null;
        if(idNombre == null) idNombre = "";
        try{
            daoIEducativa = new InstitucionEducativaMySQL();
            instituciones = daoIEducativa.listarInstitucionesPorNombreYUGEL(idNombre, idUgel);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return instituciones;
    }
    
}
