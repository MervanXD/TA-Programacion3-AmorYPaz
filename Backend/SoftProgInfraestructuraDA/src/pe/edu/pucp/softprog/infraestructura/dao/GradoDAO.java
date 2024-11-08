package pe.edu.pucp.softprog.infraestructura.dao;

import java.util.ArrayList;
import pe.edu.pucp.softprog.infraestructura.model.Grado;
import pe.edu.pucp.softprog.infraestructura.model.TipoNivel;

public interface GradoDAO {

    int insertar(Grado grado);
    int modificar(Grado grado);
    int eliminar(int idGrado);
    Grado obtenerPorId(int idGrado);
    ArrayList<Grado> listarTodos();
    int insertarTodosLosGrados(int cantGrados,int fid_IE,TipoNivel tipo);
}
