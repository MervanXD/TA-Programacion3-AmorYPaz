package pe.edu.pucp.softprog.infraest.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.softprog.infraestructura.dao.GradoDAO;
import pe.edu.pucp.softprog.infraestructura.dao.InstitucionEducativaDAO;
import pe.edu.pucp.softprog.infraestructura.model.InstitucionEducativa;
import pe.edu.pucp.softprog.infraestructura.model.TipoNivel;
import pe.edu.pucp.softprog.infraestructura.mysql.GradoMySQL;
import pe.edu.pucp.softprog.infraestructura.mysql.InstitucionEducativaMySQL;

@WebService(serviceName = "InstitucionEducativaWS", targetNamespace = "services.softprog.pucp.edu.pe")
public class InstitucionEducativaWS {

    private InstitucionEducativaDAO daoIEducativa;
    private GradoDAO daoGrado;
    
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
    
    @WebMethod(operationName = "obtenerPorId")
    public InstitucionEducativa obtenerPorId (@WebParam(name = "idInstitucion") int idInstitucion) {
        InstitucionEducativa institucion = null;
        try{
            daoIEducativa = new InstitucionEducativaMySQL();
            institucion = daoIEducativa.obtenerPorId(idInstitucion);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return institucion;
    }
    
    @WebMethod(operationName = "insertarInstitucion")
    public int insertarEmpleado(@WebParam(name = "institucionEdu")
            InstitucionEducativa institucionEdu,
            @WebParam(name="cantInicial") int cantIni,
            @WebParam(name="cantPrimaria") int cantPrim,
            @WebParam(name="cantSecundaria") int cantSec){
        int resultado = 0;
        try{
            daoIEducativa = new InstitucionEducativaMySQL();
            int fid_IE = daoIEducativa.insertar(institucionEdu);
            daoGrado=new GradoMySQL();
            resultado = daoGrado.insertarTodosLosGrados(cantIni,fid_IE,TipoNivel.INICIAL);
            resultado = daoGrado.insertarTodosLosGrados(cantPrim,fid_IE,TipoNivel.PRIMARIA);
            resultado = daoGrado.insertarTodosLosGrados(cantSec,fid_IE,TipoNivel.SECUNDARIA);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "modificarInstitucion")
    public int modificarInstitucion(@WebParam(name = "institucionEdu")
            InstitucionEducativa institucionEdu){
        int resultado = 0;
        try{
            daoIEducativa = new InstitucionEducativaMySQL();
            resultado = daoIEducativa.modificar(institucionEdu);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "obtenerIEPorIdDirector")
    public InstitucionEducativa obtenerIEPorIdDirector(@WebParam(name = "idDirector") int idDirector) {
        InstitucionEducativa institucion;
        try {
            daoIEducativa = new InstitucionEducativaMySQL();
            institucion = daoIEducativa.obtenerPorDirector(idDirector);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return institucion;
    }
}
