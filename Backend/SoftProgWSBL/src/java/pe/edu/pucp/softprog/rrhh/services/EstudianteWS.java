/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.softprog.rrhh.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.softprog.rrhh.dao.EstudianteDAO;
import pe.edu.pucp.softprog.rrhh.model.Estudiante;
import pe.edu.pucp.softprog.rrhh.mysql.EstudianteMySQL;

@WebService(serviceName = "EstudianteWS", targetNamespace = "services.softprog.pucp.edu.pe")
public class EstudianteWS {

    private EstudianteDAO daoEstudiante;
    @WebMethod(operationName = "listarEstudiantesPorGrado")
    public ArrayList<Estudiante> listarEstudiantesPorGrado(@WebParam(name = "idGrado")
            int idGrado) {
        ArrayList<Estudiante> estudiantes = null;
        try{
            daoEstudiante = new EstudianteMySQL();
            estudiantes = daoEstudiante.listarEstudiantesPorGrado(idGrado);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return estudiantes;
    }
    @WebMethod(operationName = "insertarEstudiante")
    public int insertarEstudiante(@WebParam(name = "estudiante")
            Estudiante estudiante){
        int resultado = 0;
        try{
            daoEstudiante = new EstudianteMySQL();
            resultado = daoEstudiante.insertar(estudiante);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    @WebMethod(operationName = "eliminarEstudiante")
    public int eliminarEstudiante(@WebParam(name = "idEstudiante") int idEstudiante){
        int resultado = 0;
        try{
            daoEstudiante = new EstudianteMySQL();
            resultado = daoEstudiante.eliminar(idEstudiante);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    @WebMethod(operationName = "modificarEstudiante")
    public int modificarEstudiante(@WebParam(name = "estudiante") Estudiante estudiante){
        int resultado = 0;
        try{
            daoEstudiante = new EstudianteMySQL();
            resultado = daoEstudiante.modificar(estudiante);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
}
