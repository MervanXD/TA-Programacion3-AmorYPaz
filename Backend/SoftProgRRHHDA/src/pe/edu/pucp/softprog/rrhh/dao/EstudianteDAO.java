package pe.edu.pucp.softprog.rrhh.dao;

import pe.edu.pucp.softprog.rrhh.model.Estudiante;

public interface EstudianteDAO {
    int insertar(Estudiante estudiante);
	int eliminar(String dni);
	//int actualizar(Estudiante estudiante);
	Estudiante mostrar(String dni);
	
}
