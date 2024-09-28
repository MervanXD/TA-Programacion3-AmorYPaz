package pe.edu.pucp.softprog.rrhh.dao;
import pe.edu.pucp.softprog.rrhh.model.Supervisor;
import java.util.ArrayList;

public interface SupervisorDAO {
    int insertar(Supervisor supervisor);
    int modificar(Supervisor supervisor);
    int eliminar(int id);
    Supervisor obtenerPorId(int id);
    ArrayList<Supervisor> listarTodos();
}
