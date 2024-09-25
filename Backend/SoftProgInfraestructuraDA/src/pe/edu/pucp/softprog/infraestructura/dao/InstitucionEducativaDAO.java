
package pe.edu.pucp.softprog.Infraestructura.dao;
import java.util.ArrayList;
import pe.edu.pucp.softprog.infraestructura.model.InstitucionEducativa;

public interface InstitucionEducativaDAO {
    int insertar(InstitucionEducativa institucion);
    int modificar(InstitucionEducativa institucion);
    int eliminar(int idInstitucionEducativa);
    InstitucionEducativa obtenerPorId(int idInstitucionEducativa);
    ArrayList<InstitucionEducativa> listarTodos();
}
