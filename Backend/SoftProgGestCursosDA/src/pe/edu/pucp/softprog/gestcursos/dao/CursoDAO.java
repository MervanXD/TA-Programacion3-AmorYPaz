package pe.edu.pucp.softprog.gestcursos.dao;

import java.util.ArrayList;
import pe.edu.pucp.softprog.gestcursos.model.Curso;

public interface CursoDAO {
    int insertar(Curso curso);
    int modificar(Curso curso);
    int eliminar(String id);
    Curso obtenerPorId(String id);
    ArrayList<Curso> listarTodos();
}
