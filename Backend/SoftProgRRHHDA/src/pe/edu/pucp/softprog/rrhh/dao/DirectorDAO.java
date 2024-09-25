
package pe.edu.pucp.softprog.rrhh.dao;

import java.util.ArrayList;
import pe.edu.pucp.softprog.rrhh.model.Director;


public interface DirectorDAO {
    int insertar(Director director);
    int modificar(Director director,int idUsuario);
    int eliminar(int id);
    Director obtenerPorId(int id);
    ArrayList<Director> listarTodos();
}
