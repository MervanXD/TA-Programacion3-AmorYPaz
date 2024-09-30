package pe.edu.pucp.softprog.gestcalendario.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.softprog.config.DBManager;
import pe.edu.pucp.softprog.gestcalendario.dao.AnioAcademicoDAO;
import pe.edu.pucp.softprog.gestcalendario.model.AnioAcademico;

public class AnioAcademicoMySQL implements AnioAcademicoDAO{
    
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    
    @Override
    public int insertar(AnioAcademico anioAcademico) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "{call INSERTAR_ANIO_ACADEMICO(?,?,?,?,?)}";
            cs = con.prepareCall(sql);
            cs.registerOutParameter("_id_Anio_Academico", java.sql.Types.INTEGER);
            cs.setInt("_numero", anioAcademico.getNumero());
            cs.setDate("_fecha_Inicio", new java.sql.Date(anioAcademico.getFechaInicio().getTime()));
            cs.setDate("_fecha_Fin", new java.sql.Date(anioAcademico.getFechaFin().getTime()));
            cs.setString("_estado", anioAcademico.getEstado());
            cs.executeUpdate();
            anioAcademico.setIdAnio(cs.getInt("_id_Anio_Academico"));
            resultado = anioAcademico.getIdAnio();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return resultado;
    }

    @Override
    public int modificar(AnioAcademico anioAcademico) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "{call MODIFICAR_ANIO_ACADEMICO(?,?,?,?,?)}";
            cs = con.prepareCall(sql);
            cs.setInt("_id_Anio_Academico", anioAcademico.getIdAnio());
            cs.setInt("_numero", anioAcademico.getNumero());
            cs.setDate("_fecha_Inicio", new java.sql.Date(anioAcademico.getFechaInicio().getTime()));
            cs.setDate("_fecha_Fin", new java.sql.Date(anioAcademico.getFechaFin().getTime()));
            cs.setString("_estado", anioAcademico.getEstado());
            cs.executeUpdate();
            resultado = anioAcademico.getIdAnio();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return resultado;
    }

    @Override
    public int eliminar(int id) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "{call ELIMINAR_ANIO_ACADEMICO(?)}";
            cs = con.prepareCall(sql);
            cs.setInt("_id_Anio_Academico", id);
            cs.executeUpdate();
            resultado = id;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return resultado;    
    }

    @Override
    public AnioAcademico obtenerPorId(int id) {
        AnioAcademico anioAcademico = new AnioAcademico();
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "{call OBTENER_ANIO_ACADEMICO(?)}";
            cs = con.prepareCall(sql);
            cs.setInt("_id_Anio_Academico", id);
            rs = cs.executeQuery();
            if (rs.next()) {
                anioAcademico.setIdAnio(rs.getInt("id_Anio_Academico"));
                anioAcademico.setNumero(rs.getInt("numero"));
                anioAcademico.setFechaInicio(rs.getDate("fecha_Inicio"));
                anioAcademico.setFechaFin(rs.getDate("fecha_Fin"));
                anioAcademico.setEstado(rs.getString("estado"));
                anioAcademico.setActivo(rs.getBoolean("activo"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return anioAcademico;        
    }

    @Override
    public ArrayList<AnioAcademico> listarTodos() {
        ArrayList<AnioAcademico> aniosAcademicos = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "{call LISTAR_CURSOS_TODOS()}";
            cs = con.prepareCall(sql);
            rs = cs.executeQuery();
            while (rs.next()) {
                AnioAcademico anioAcademico = new AnioAcademico();
                anioAcademico.setIdAnio(rs.getInt("id_Anio_Academico"));
                anioAcademico.setNumero(rs.getInt("numero"));
                anioAcademico.setFechaInicio(rs.getDate("fecha_Inicio"));
                anioAcademico.setFechaFin(rs.getDate("fecha_Fin"));
                anioAcademico.setEstado(rs.getString("estado"));
                anioAcademico.setActivo(rs.getBoolean("activo"));
                aniosAcademicos.add(anioAcademico);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return aniosAcademicos;    
    }
    
}
