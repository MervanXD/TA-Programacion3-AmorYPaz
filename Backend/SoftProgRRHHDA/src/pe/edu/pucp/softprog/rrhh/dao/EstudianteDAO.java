package pe.edu.pucp.softprog.rrhh.dao;

import java.util.ArrayList;
import pe.edu.pucp.softprog.rrhh.model.Estudiante;

public interface EstudianteDAO {
    int insertar(Estudiante estudiante);
    int modificar(Estudiante estudiante);
    int eliminar(int id);
    Estudiante obtenerPorId(int id);
    ArrayList<Estudiante> listarTodos();
    ArrayList<Estudiante> listarEstudiantesPorGrado(int idGrado);
    ArrayList<Estudiante> listarEstudiantesPorIE(int idIE);
    ArrayList<Estudiante> listarEstPorIEYNombreDNI(int idIE, String nombreDNI);
    ArrayList<Estudiante> listarEstudiantesPorInstitucionEducativa(int idIE);
    ArrayList<Estudiante> listarEstudiantesParaMatricula(int idIE);
}
