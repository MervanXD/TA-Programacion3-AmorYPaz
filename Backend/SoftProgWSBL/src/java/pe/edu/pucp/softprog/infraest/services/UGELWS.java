package pe.edu.pucp.softprog.infraest.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import pe.edu.pucp.softprog.infraestructura.dao.UGELDAO;
import pe.edu.pucp.softprog.infraestructura.model.UGEL;
import pe.edu.pucp.softprog.infraestructura.mysql.UGELMySQL;

@WebService(serviceName = "UGELWS", targetNamespace = "services.softprog.pucp.edu.pe")
public class UGELWS {
    private UGELDAO daoUGEL;
    @WebMethod(operationName = "obtenerUGELPorIdDirector")
    public UGEL obtenerUGELPorIdDirector(@WebParam(name = "idDirector") int idDirector) {
        UGEL ugel;
        try {
            daoUGEL = new UGELMySQL();
            ugel = daoUGEL.obtenerPorDirector(idDirector);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return ugel;
    }
}
