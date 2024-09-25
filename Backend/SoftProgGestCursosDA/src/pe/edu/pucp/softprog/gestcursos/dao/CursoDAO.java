package pe.edu.pucp.softprog.gestcursos.dao;

import java.util.ArrayList;
import pe.edu.pucp.softprog.gestcursos.model.Curso;

public interface CursoDAO {
    int insertar(Curso curso);
    int modificar(Curso curso, int idPlanDeEstudios);
    int eliminar(String id);
    Curso obtenerPorId(String id);
    ArrayList<Curso> listarTodos();
    int insertarNota(String idCurso, String idEstudiante,double nota);
    int modificarNota(String idCurso, String idEstudiante,double nota);
    int eliminarNota(String idCurso,String idEstudiante);
}
