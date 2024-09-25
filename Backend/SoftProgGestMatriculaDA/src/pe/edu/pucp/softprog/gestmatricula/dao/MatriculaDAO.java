package pe.edu.pucp.softprog.gestmatricula.dao;

import java.util.ArrayList;
import pe.edu.pucp.softprog.gestmatricula.model.Matricula;

public interface MatriculaDAO {
    int insertar(Matricula matricula);
    int modificar(Matricula matricula);
    int eliminar(int id);
    Matricula obtenerPorId(int id);
    ArrayList<Matricula> listarTodos(); 
}
