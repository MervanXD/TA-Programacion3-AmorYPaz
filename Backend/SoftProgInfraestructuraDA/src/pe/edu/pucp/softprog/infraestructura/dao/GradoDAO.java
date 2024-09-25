
package pe.edu.pucp.softprog.Infraestructura.dao;
import java.util.ArrayList;
import pe.edu.pucp.softprog.infraestructura.model.Grado;

public interface GradoDAO {
    int insertar(Grado grado);
    int modificar(Grado grado);
    int eliminar(int idGrado);
    Grado obtenerPorId(int idGrado);
    ArrayList<Grado> listarTodos();
}
