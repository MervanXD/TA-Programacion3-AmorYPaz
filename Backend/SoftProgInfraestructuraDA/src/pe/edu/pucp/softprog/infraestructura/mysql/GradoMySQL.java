package pe.edu.pucp.softprog.infraestructura.mysql;

import java.util.ArrayList;
import pe.edu.pucp.softprog.infraestructura.dao.GradoDAO;
import pe.edu.pucp.softprog.infraestructura.model.Grado;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import pe.edu.pucp.softprog.config.DBManager;
import pe.edu.pucp.softprog.infraestructura.model.TipoNivel;

public class GradoMySQL implements GradoDAO {

    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;

    @Override
    public int insertar(Grado grado) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call INSERTAR_GRADO(?,?,?,?,?)}");
            cs.registerOutParameter("_id_grado", java.sql.Types.INTEGER);
            cs.setString("_numero", grado.getNumero());
            cs.setString("_tipo_nivel", grado.getNivel().toString());
            cs.setInt("_num_matriculados", grado.getAlumnosMatriculados());
            cs.setInt("_fid_institucion_educativa", grado.getInstitucion().getIdInstitucion());
            cs.executeUpdate();
            grado.setIdGrado(cs.getInt("_id_grado"));
            resultado = grado.getIdGrado();
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
    public int modificar(Grado grado) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call MODIFICAR_GRADO(?,?,?,?,?)}");
            cs.setInt("_id_grado", grado.getIdGrado());
            cs.setString("_numero", grado.getNumero());
            cs.setString("_tipo_nivel", grado.getNivel().toString());
            cs.setInt("_num_matriculados", grado.getAlumnosMatriculados());
            cs.setInt("_fid_institucion_educativa", grado.getInstitucion().getIdInstitucion());
            cs.executeUpdate();
            resultado = grado.getIdGrado();
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
    public int eliminar(int idGrado) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ELIMINAR_GRADO(?)}");
            cs.setInt("_id_grado", idGrado);
            cs.executeUpdate();
            resultado = idGrado;
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
    public Grado obtenerPorId(int idGrado) {
        Grado grado = new Grado();
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call OBTENER_GRADO(?)}");
            cs.setInt("_id_grado", idGrado);
            rs = cs.executeQuery();
            if (rs.next()) {
                grado.setIdGrado(rs.getInt("idGrado"));
                grado.setAlumnosMatriculados(rs.getInt("num_matriculados"));
                grado.setNivel(TipoNivel.valueOf(rs.getString("tipo_nivel")));
                grado.setNumero(rs.getString("numero"));
                grado.setActivo(rs.getBoolean("gra_activo"));
                grado.getInstitucion().setActivo(rs.getBoolean("ie_activo"));
                grado.getInstitucion().setCantidadGrados(rs.getInt("cantidad_grados"));
                grado.getInstitucion().setDireccion(rs.getString("direccion"));
                grado.getInstitucion().setIdInstitucion(rs.getInt("id_Institucion_Educativa"));
                grado.getInstitucion().setNombre(rs.getString("nombre"));
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
        return grado;
    }

    @Override
    public ArrayList<Grado> listarTodos() {
        ArrayList<Grado> grados = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_GRADOS_TODOS(?)}");
            rs = cs.executeQuery();
            while (rs.next()) {
                Grado grado = new Grado();
                grado.setIdGrado(rs.getInt("idGrado"));
                grado.setAlumnosMatriculados(rs.getInt("num_matriculados"));
                grado.setNivel(TipoNivel.valueOf(rs.getString("tipo_nivel")));
                grado.setNumero(rs.getString("numero"));
                grado.setActivo(rs.getBoolean("gra_activo"));
                grado.getInstitucion().setActivo(rs.getBoolean("ie_activo"));
                grado.getInstitucion().setCantidadGrados(rs.getInt("cantidad_grados"));
                grado.getInstitucion().setDireccion(rs.getString("direccion"));
                grado.getInstitucion().setIdInstitucion(rs.getInt("id_Institucion_Educativa"));
                grado.getInstitucion().setNombre(rs.getString("nombre"));
                grados.add(grado);
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
        return grados;
    }

}
