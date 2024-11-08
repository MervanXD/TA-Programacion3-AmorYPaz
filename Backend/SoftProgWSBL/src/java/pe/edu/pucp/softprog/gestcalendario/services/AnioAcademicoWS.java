package pe.edu.pucp.softprog.gestcalendario.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.softprog.gestcalendario.dao.AnioAcademicoDAO;
import pe.edu.pucp.softprog.gestcalendario.model.AnioAcademico;
import pe.edu.pucp.softprog.gestcalendario.mysql.AnioAcademicoMySQL;

@WebService(serviceName = "AnioAcademicoWS", targetNamespace = "services.softprog.pucp.edu.pe")
public class AnioAcademicoWS {

    private AnioAcademicoDAO daoAnio;

    @WebMethod(operationName = "insertarAnioAcademico")
    public int insertarAnioAcademico(@WebParam(name = "anioAcademico")
            AnioAcademico anioAcademico){
        int resultado = 0;
        try{
            daoAnio = new AnioAcademicoMySQL();
            resultado = daoAnio.insertar(anioAcademico);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "listarAnioAcademico")
    public ArrayList<AnioAcademico> listarAnioAcademico(){
        ArrayList<AnioAcademico> anios=new ArrayList<>();
        try{
            daoAnio = new AnioAcademicoMySQL();
            anios = daoAnio.listarTodos();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return anios;
    }
}
