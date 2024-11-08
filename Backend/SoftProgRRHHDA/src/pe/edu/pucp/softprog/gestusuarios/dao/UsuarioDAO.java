package pe.edu.pucp.softprog.gestusuarios.dao;
import pe.edu.pucp.softprog.gestusuarios.model.Usuario;
import java.util.ArrayList;

public interface UsuarioDAO {
    int insertar(Usuario usuario);
    int modificar(Usuario usuario);
    int eliminar(int id);
    Usuario obtenerPorId(int id);
    ArrayList<Usuario> listarTodos();
    Usuario verificar(Usuario usuario);
    int obtenerUgelDeUsuario (String nombreCuentaUsuario);
    int obtenerIEDeUsuario(int id);
}

