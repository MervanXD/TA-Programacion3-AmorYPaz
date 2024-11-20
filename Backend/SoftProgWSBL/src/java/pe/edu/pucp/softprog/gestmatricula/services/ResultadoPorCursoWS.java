package pe.edu.pucp.softprog.gestmatricula.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.softprog.gestacademica.model.ResultadoPorCurso;
import pe.edu.pucp.softprog.gestmatricula.dao.ResultadoPorCursoDAO;
import pe.edu.pucp.softprog.gestmatricula.mysql.ResultadoPorCursoMySQL;

@WebService(serviceName = "ResultadoPorCursoWS", targetNamespace = "services.softprog.pucp.edu.pe")
public class ResultadoPorCursoWS {
    private ResultadoPorCursoDAO daoResultadoCurso;
    
    @WebMethod(operationName = "listarPorIdMatricula")
    public ArrayList<ResultadoPorCurso> listarPorIdMatricula(
            @WebParam(name = "idMatricula") int idMatricula) {
        ArrayList<ResultadoPorCurso> resultados = null;
        try{
            daoResultadoCurso = new ResultadoPorCursoMySQL();
            resultados = daoResultadoCurso.listarPorMatricula(idMatricula);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultados;
    }
    
    @WebMethod(operationName = "insertarResultado")
    public int insertarResultado(@WebParam(name = "resultado")
            ResultadoPorCurso resultado){
        int result = 0;
        try{
            daoResultadoCurso = new ResultadoPorCursoMySQL();
            result = daoResultadoCurso.insertar(resultado);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return result;
    }
}
