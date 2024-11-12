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
}
