package pe.edu.pucp.softprog.gestmatricula.dao;

import java.util.ArrayList;
import pe.edu.pucp.softprog.gestacademica.model.Matricula;

public interface MatriculaDAO {
    int insertar(Matricula matricula);
    int modificar(Matricula matricula);
    int eliminar(int id);
    Matricula obtenerPorAnioGradoEstudiante(int idAnio, int idGrado, int idEstudiante);
    ArrayList<Matricula> listarTodos(); 
    ArrayList<Matricula> listarPorIdIE(int idInstitucion);
    ArrayList<Matricula> listarMatriculasPorGrado(int idGrado);
}
