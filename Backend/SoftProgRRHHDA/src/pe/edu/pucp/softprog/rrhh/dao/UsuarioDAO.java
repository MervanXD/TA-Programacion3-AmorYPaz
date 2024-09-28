package pe.edu.pucp.softprog.rrhh.dao;
import pe.edu.pucp.softprog.rrhh.model.Usuario;
import java.util.ArrayList;

public interface UsuarioDAO {
    int insertar(Usuario usuario);
    int modificar(Usuario usuario);
    int eliminar(int id);
    Usuario obtenerPorId(int id);
    ArrayList<Usuario> listarTodos();
}

