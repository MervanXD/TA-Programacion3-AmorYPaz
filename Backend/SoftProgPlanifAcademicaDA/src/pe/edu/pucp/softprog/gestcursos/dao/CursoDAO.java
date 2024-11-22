package pe.edu.pucp.softprog.gestcursos.dao;

import java.util.ArrayList;
import pe.edu.pucp.softprog.gestcursos.model.Curso;

public interface CursoDAO {
    int insertar(Curso curso, int idIE);
    int insertarCursoPlanEstudios(int idCurso, int idPlan);
    int eliminarCursoDePlanEstudios(int idCurso, int idPlan);
    int modificar(Curso curso);
    int eliminar(int id);
    Curso obtenerPorId(int id);
    ArrayList<Curso> listarTodos();
    ArrayList<Curso> listarPorIdNombre(String idNombre, int idIE);
    ArrayList<Curso> listarPorIdIE(int idIE);
    ArrayList<Curso> listarPorIdPlan(int idPlan);
    ArrayList<Curso> listarPorIdGrado(int idGrado);
}
