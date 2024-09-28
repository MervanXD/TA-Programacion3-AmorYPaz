package pe.edu.pucp.softprog.rrhh.dao;

import java.util.ArrayList;
import pe.edu.pucp.softprog.rrhh.model.Persona;

public interface PersonaDAO {
    int insertar(Persona persona);
    int modificar(Persona persona);
    int eliminar(int id);
    Persona obtenerPorId(int id);
    ArrayList<Persona> listarTodos();   
}
