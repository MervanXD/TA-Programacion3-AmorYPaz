package pe.edu.pucp.softprog.gestcursos.mysql;

import java.util.ArrayList;
import pe.edu.pucp.softprog.gestcursos.dao.CursoDAO;
import pe.edu.pucp.softprog.gestcursos.model.Curso;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import pe.edu.pucp.softprog.config.DBManager;

public class CursoMySQL implements CursoDAO {

    private CallableStatement cs;
    private Connection con;
    private ResultSet rs;

    @Override
    public int insertar(Curso curso, int idIE) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "{call INSERTAR_CURSO(?,?, ?)}";
            cs = con.prepareCall(sql);
            cs.registerOutParameter("_id_curso", java.sql.Types.INTEGER);
            cs.setString("_nombre", curso.getNombre());
            cs.setInt("_fid_institucion", idIE);
            cs.executeUpdate();
            curso.setIdCurso(cs.getInt("_id_curso"));
            resultado = curso.getIdCurso();
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
    public int modificar(Curso curso) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "{call MODIFICAR_CURSO(?,?)}";
            cs = con.prepareCall(sql);
            cs.setInt("_id_curso", curso.getIdCurso());
            cs.setString("_nombre", curso.getNombre());
            cs.executeUpdate();
            resultado = curso.getIdCurso();
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
            String sql = "{call ELIMINAR_CURSO(?)}";
            cs = con.prepareCall(sql);
            cs.setInt("_id_curso", id);
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
    public Curso obtenerPorId(int id) {
        Curso curso = new Curso();
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "{call OBTENER_CURSO(?)}";
            cs = con.prepareCall(sql);
            cs.setInt("_id_curso", id);
            rs = cs.executeQuery();
            if (rs.next()) {
                curso.setIdCurso(rs.getInt("id_curso"));
                curso.setNombre(rs.getString("nombre"));
                curso.setActivo(rs.getBoolean("activo"));
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
        return curso;
    }

    @Override
    public ArrayList<Curso> listarTodos() {
        ArrayList<Curso> cursos = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "{call LISTAR_CURSOS_TODOS()}";
            cs = con.prepareCall(sql);
            rs = cs.executeQuery();
            while (rs.next()) {
                Curso curso = new Curso();
                curso.setIdCurso(rs.getInt("id_curso"));
                curso.setNombre(rs.getString("nombre"));
                curso.setActivo(rs.getBoolean("activo"));
                cursos.add(curso);
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
        return cursos;
    }
    
    @Override
    public ArrayList<Curso> listarPorIdNombre(String idNombre, int idIE){
        ArrayList<Curso> cursos = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "{call LISTAR_POR_ID_NOMBRE_CURSO(?, ?)}";
            cs = con.prepareCall(sql);
            cs.setString("_id_nombre", idNombre);
            cs.setInt("_fid_institucion", idIE);
            rs = cs.executeQuery();
            while (rs.next()) {
                Curso curso = new Curso();
                curso.setIdCurso(rs.getInt("id_curso"));
                curso.setNombre(rs.getString("nombre"));
                curso.setActivo(rs.getBoolean("activo"));
                cursos.add(curso);
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
        return cursos;
    }

    @Override
    public ArrayList<Curso> listarPorIdIE(int idIE) {
        ArrayList<Curso> cursos = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "{call LISTAR_CURSOS_POR_IE(?)}";
            cs = con.prepareCall(sql);
            cs.setInt("_fid_institucion", idIE);
            rs = cs.executeQuery();
            while (rs.next()) {
                Curso curso = new Curso();
                curso.setIdCurso(rs.getInt("id_curso"));
                curso.setNombre(rs.getString("nombre"));
                curso.setActivo(rs.getBoolean("activo"));
                cursos.add(curso);
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
        return cursos;
    }

    @Override
    public int insertarCursoPlanEstudios(int idCurso, int idPlan) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "{call INSERTAR_CURSO_PLAN_ESTUDIO(?,?,?)}";
            cs = con.prepareCall(sql);
            cs.registerOutParameter("_id_Plan_Estudio_Curso", java.sql.Types.INTEGER);
            cs.setInt("_fid_plan_estudio", idPlan);
            cs.setInt("_fid_curso", idCurso);
            cs.executeUpdate();
            resultado = cs.getInt("_id_Plan_Estudio_Curso");
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
    public ArrayList<Curso> listarPorIdPlan(int idPlan) {
        ArrayList<Curso> cursos = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "{call LISTAR_CURSOS_POR_ID_PLAN(?)}";
            cs = con.prepareCall(sql);
            cs.setInt("_fid_plan_de_estudio", idPlan);
            rs = cs.executeQuery();
            while (rs.next()) {
                Curso curso = new Curso();
                curso.setIdCurso(rs.getInt("id_curso"));
                curso.setNombre(rs.getString("nombre"));
                curso.setActivo(rs.getBoolean("activo"));
                cursos.add(curso);
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
        return cursos;
    }

    @Override
    public int eliminarCursoDePlanEstudios(int idCurso, int idPlan) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "{call ElIMINAR_CURSO_PLAN_ESTUDIO(?,?)}";
            cs = con.prepareCall(sql);
            cs.setInt("_fid_plan_estudio", idPlan);
            cs.setInt("_fid_curso", idCurso);
            cs.executeUpdate();
            resultado = 1;
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

}
