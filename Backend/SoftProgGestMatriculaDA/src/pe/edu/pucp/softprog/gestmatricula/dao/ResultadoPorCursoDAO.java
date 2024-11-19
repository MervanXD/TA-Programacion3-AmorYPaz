package pe.edu.pucp.softprog.gestmatricula.dao;

import java.util.ArrayList;
import pe.edu.pucp.softprog.gestacademica.model.ResultadoPorCurso;

public interface ResultadoPorCursoDAO {
    ArrayList<ResultadoPorCurso> listarPorMatricula(int idMatricula);
    int insertar(ResultadoPorCurso resultado);
}
