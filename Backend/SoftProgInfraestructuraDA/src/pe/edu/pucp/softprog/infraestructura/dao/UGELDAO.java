package pe.edu.pucp.softprog.infraestructura.dao;

import java.util.ArrayList;
import pe.edu.pucp.softprog.infraestructura.model.UGEL;

public interface UGELDAO {
    int insertar(UGEL ugel);
    int modificar(UGEL ugel);
    int eliminar(int idUgel);
    UGEL obtenerPorId(int idUgel);
    UGEL obtenerPorDirector(int idDirector);
    ArrayList<UGEL> listarTodos();
}
