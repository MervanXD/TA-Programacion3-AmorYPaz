/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.softprog.gestcalendario.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.softprog.gestcalendario.dao.PlanDeEstudioDAO;
import pe.edu.pucp.softprog.gestcalendario.model.PlanDeEstudio;
import pe.edu.pucp.softprog.gestcalendario.mysql.PlanDeEstudioMySQL;


@WebService(serviceName = "PlanDeEstudioWS", 
        targetNamespace = "services.softprog.pucp.edu.pe")
public class PlanDeEstudioWS {

    private PlanDeEstudioDAO daoPlan;
    @WebMethod(operationName = "listarPlanesDeEstudio")
    public ArrayList<PlanDeEstudio> listarPlanesDeEstudio() {
        ArrayList<PlanDeEstudio> planes = null;
        try{
            daoPlan = new PlanDeEstudioMySQL();
            planes = daoPlan.listarTodos();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return planes;
    }
    @WebMethod(operationName = "insertarPlanDeEstudio")
    public int insertarPlanDeEstudio(@WebParam(name = "planDeEstudio")
            PlanDeEstudio plan){
        int resultado = 0;
        try{
            daoPlan = new PlanDeEstudioMySQL();
            resultado = daoPlan.insertar(plan);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    @WebMethod(operationName = "eliminarPlanDeEstudio")
    public int eliminarPlanDeEstudio(@WebParam(name = "idPlanDeEstudio") int idPlanDeEstudio){
        int resultado = 0;
        try{
            daoPlan = new PlanDeEstudioMySQL();
            resultado = daoPlan.eliminar(idPlanDeEstudio);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    @WebMethod(operationName = "modificarPlanDeEstudio")
    public int modificarPlanDeEstudio(@WebParam(name = "planDeEstudio") PlanDeEstudio plan){
        int resultado = 0;
        try{
            daoPlan = new PlanDeEstudioMySQL();
            resultado = daoPlan.modificar(plan);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
}
