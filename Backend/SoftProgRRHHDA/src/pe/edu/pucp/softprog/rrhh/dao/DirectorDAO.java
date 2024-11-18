
package pe.edu.pucp.softprog.rrhh.dao;

import java.util.ArrayList;
import pe.edu.pucp.softprog.rrhh.model.Director;


public interface DirectorDAO {
    int insertar(Director director);
    int modificar(Director director);
    int eliminar(int id);
    Director obtenerPorId(int id);
    ArrayList<Director> listarTodos();
    ArrayList<Director> listarDirectoresPorNombre(String nombre);
    ArrayList<Director> listarTodosDisponibles(int idDirector);
}
