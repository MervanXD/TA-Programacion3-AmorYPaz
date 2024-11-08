package pe.edu.pucp.softprog.gestcalendario.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.softprog.gestcalendario.dao.PlanDeEstudioDAO;
import pe.edu.pucp.softprog.gestcalendario.model.PlanDeEstudio;
import pe.edu.pucp.softprog.config.DBManager;
import pe.edu.pucp.softprog.gestcalendario.model.AnioAcademico;

public class PlanDeEstudioMySQL implements PlanDeEstudioDAO {

    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;

    @Override
    public int insertar(PlanDeEstudio plan) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "{call INSERTAR_PLAN_DE_ESTUDIO(?,?,?,?,?,?,?,?)}";
            cs = con.prepareCall(sql);
            cs.registerOutParameter("_id_Plan_Estudio", java.sql.Types.INTEGER);
            cs.registerOutParameter("_fid_Anio_Academico", java.sql.Types.INTEGER);
            cs.setString("_descripcion", plan.getDescripcion());
            cs.setInt("_num_cursos", plan.getNumCursos());
            cs.setInt("_numero", plan.getAnioAcademico().getNumero());
            cs.setDate("_fecha_Inicio", new java.sql.Date(plan.getAnioAcademico().getFechaInicio().getTime()));
            cs.setDate("_fecha_Fin", new java.sql.Date(plan.getAnioAcademico().getFechaFin().getTime()));
            cs.setString("_estado", plan.getAnioAcademico().getEstado());
            cs.executeUpdate();
            plan.setIdPlan(cs.getInt("_id_Plan_Estudio"));
            resultado = plan.getIdPlan();
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
    public int modificar(PlanDeEstudio plan) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "{call MODIFICAR_PLAN_DE_ESTUDIO(?,?,?,?,?,?,?,?)}";
            cs = con.prepareCall(sql);
            cs.setInt("_id_Plan_Estudio", plan.getIdPlan());
            cs.setInt("_fid_Anio_Academico", plan.getAnioAcademico().getIdAnio());
            cs.setString("_descripcion", plan.getDescripcion());
            cs.setInt("_num_cursos", plan.getNumCursos());
            cs.setInt("_numero", plan.getAnioAcademico().getNumero());
            cs.setDate("_fecha_Inicio", new java.sql.Date(plan.getAnioAcademico().getFechaInicio().getTime()));
            cs.setDate("_fecha_Fin", new java.sql.Date(plan.getAnioAcademico().getFechaFin().getTime()));
            cs.setString("_estado", plan.getAnioAcademico().getEstado());
            cs.executeUpdate();
            resultado = plan.getIdPlan();
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
    public int eliminar(int idPlan) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ELIMINAR_PLAN_DE_ESTUDIO(?)}");
            cs.setInt("_id_Plan_Estudio", idPlan);
            resultado = cs.executeUpdate();
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
    public PlanDeEstudio obtenerPorId(int idPlan) {
        PlanDeEstudio plan = new PlanDeEstudio();
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call OBTENER_PLAN_DE_ESTUDIO(?)}");
            cs.setInt("_id_plan_estudio", idPlan);
            rs = cs.executeQuery();
            if (rs.next()) {
                plan.setIdPlan(rs.getInt("id_Plan_Estudio"));
                plan.setDescripcion(rs.getString("descripcion"));
                plan.setNumCursos(rs.getInt("num_cursos"));
                plan.setActivo(rs.getBoolean("activo"));
                plan.getAnioAcademico().setActivo(rs.getBoolean("activo"));
                plan.getAnioAcademico().setIdAnio(rs.getInt("id_anio_Academico"));
                plan.getAnioAcademico().setEstado(rs.getString("estado"));
                plan.getAnioAcademico().setFechaInicio(rs.getDate("fecha_Inicio"));
                plan.getAnioAcademico().setFechaFin(rs.getDate("fecha_Fin"));
                plan.getAnioAcademico().setNumero(rs.getInt("numero"));
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
        return plan;
    }

    @Override
    public ArrayList<PlanDeEstudio> listarTodos() {
        ArrayList<PlanDeEstudio> planesDeEstudio = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "{call LISTAR_PLANES_DE_ESTUDIO_TODOS()}";
            cs = con.prepareCall(sql);
            rs = cs.executeQuery();
            while (rs.next()) {
                PlanDeEstudio plan = new PlanDeEstudio();
                plan.setIdPlan(rs.getInt("id_Plan_Estudio"));
                plan.setDescripcion(rs.getString("descripcion"));
                plan.setNumCursos(rs.getInt("num_cursos"));
                plan.setActivo(rs.getBoolean("activo"));
                AnioAcademico anio=new AnioAcademico();
                anio.setActivo(rs.getBoolean("activo"));
                anio.setIdAnio(rs.getInt("id_anio_Academico"));
                anio.setEstado(rs.getString("estado"));
                anio.setFechaInicio(rs.getDate("fecha_Inicio"));
                anio.setFechaFin(rs.getDate("fecha_Fin"));
                anio.setNumero(rs.getInt("numero"));
                plan.setAnioAcademico(anio);
                planesDeEstudio.add(plan);
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
        return planesDeEstudio; 
    }

}
