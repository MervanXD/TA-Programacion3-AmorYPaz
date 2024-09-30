package pe.edu.pucp.softprog.gestcalendario.dao;

import java.util.ArrayList;
import pe.edu.pucp.softprog.gestcalendario.model.PlanDeEstudio;

public interface PlanDeEstudioDAO {
    int insertar(PlanDeEstudio plan);
    int modificar(PlanDeEstudio plan);
    int eliminar(int idPlan);
    PlanDeEstudio obtenerPorId(int idPlan);
    ArrayList<PlanDeEstudio> listarTodos();
}
