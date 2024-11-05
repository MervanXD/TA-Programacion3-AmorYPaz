/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.softprog.gestmatricula.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.softprog.gestacademica.model.Matricula;
import pe.edu.pucp.softprog.gestmatricula.dao.MatriculaDAO;
import pe.edu.pucp.softprog.gestmatricula.mysql.MatriculaMySQL;

@WebService(serviceName = "MatriculaWS", 
        targetNamespace = "services.softprog.pucp.edu.pe")
public class MatriculaWS {

    
    private MatriculaDAO daoMatricula;
    @WebMethod(operationName = "listarMatriculas")
    public ArrayList<Matricula> listarMatriculas() {
        ArrayList<Matricula> matriculas = null;
        try{
            daoMatricula = new MatriculaMySQL();
            matriculas = daoMatricula.listarTodos();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return matriculas;
    }
    @WebMethod(operationName = "insertarMatricula")
    public int insertarMatricula(@WebParam(name = "matricula")
            Matricula matricula){
        int resultado = 0;
        try{
            daoMatricula = new MatriculaMySQL();
            resultado = daoMatricula.insertar(matricula);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    @WebMethod(operationName = "eliminarMatricula")
    public int eliminarMatricula(@WebParam(name = "matricula") int idMatric){
        int resultado = 0;
        try{
            daoMatricula = new MatriculaMySQL();
            resultado = daoMatricula.eliminar(idMatric);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    @WebMethod(operationName = "modificarMatricula")
    public int modificarMatricula(@WebParam(name = "matricula") Matricula matricula){
        int resultado = 0;
        try{
            daoMatricula = new MatriculaMySQL();
            resultado = daoMatricula.modificar(matricula);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
}
