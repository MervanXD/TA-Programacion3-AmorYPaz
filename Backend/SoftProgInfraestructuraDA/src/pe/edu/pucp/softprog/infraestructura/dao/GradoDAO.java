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
    Grado obtenerPorIdPlanEstudios(int idPlan);
    ArrayList<Grado> obtenerPorIdIE(int idInstitucion);
    ArrayList<Grado> listarTodosPorIdIE(int idInstitucion);
    int asignarPlan(int idGrado, int idPlan);
    ArrayList<Grado> listarTodosPorIdIENivel(int idInstitucion, String nivel);
}
