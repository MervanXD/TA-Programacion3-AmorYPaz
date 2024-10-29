package pe.edu.pucp.softprog.infraest.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import pe.edu.pucp.softprog.infraestructura.dao.UGELDAO;
import pe.edu.pucp.softprog.infraestructura.model.UGEL;
import pe.edu.pucp.softprog.infraestructura.mysql.UGELMySQL;

@WebService(serviceName = "UGELWS")
public class UGELWS {
    private UGELDAO daoUGEL;
    @WebMethod(operationName = "obtenerPorIdDirector")
    public UGEL obtenerPorIdDirector(@WebParam(name = "idDirector") int idDirector) {
        UGEL ugel = null;
        try {
            daoUGEL = new UGELMySQL();
            ugel = daoUGEL.obtenerPorDirector(idDirector);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return ugel;
    }
}
