package pe.edu.pucp.softprog.gestcalendario.dao;

import java.util.ArrayList;
import pe.edu.pucp.softprog.gestcalendario.model.AnioAcademico;

public interface AnioAcademicoDAO {
    int insertar(AnioAcademico anioAcademico);
    int modificar(AnioAcademico anioAcademico);
    int eliminar(int id);
    AnioAcademico obtenerPorId(int id);
    ArrayList<AnioAcademico> listarPorIdIE(int idInstitucion);
}
