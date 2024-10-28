package pe.edu.pucp.softprog.infraestructura.mysql;

import java.util.ArrayList;
import pe.edu.pucp.softprog.infraestructura.dao.InstitucionEducativaDAO;
import pe.edu.pucp.softprog.infraestructura.model.InstitucionEducativa;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import pe.edu.pucp.softprog.config.DBManager;

public class InstitucionEducativaMySQL implements InstitucionEducativaDAO {

    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;

    @Override
    public int insertar(InstitucionEducativa institucion) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call INSERTAR_INSTITUCION_EDUCATIVA(?,?,?,?)}");
            cs.registerOutParameter("_id_institucion_educativa", java.sql.Types.INTEGER);
            cs.setString("_nombre", institucion.getNombre());
            cs.setString("_direccion", institucion.getDireccion());
            cs.setInt("_cantidad_grados", institucion.getCantidadGrados());
            cs.executeUpdate();
            institucion.setIdInstitucion(cs.getInt("_id_institucion_educativa"));
            resultado = institucion.getIdInstitucion();
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
    public int modificar(InstitucionEducativa institucion) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call MODIFICAR_INSTITUCION_EDUCATIVA(?,?,?,?)}");
            cs.setInt("_id_institucion_educativa", institucion.getIdInstitucion());
            cs.setString("_nombre", institucion.getNombre());
            cs.setString("_direccion", institucion.getDireccion());
            cs.setInt("_cantidad_grados", institucion.getCantidadGrados());
            cs.executeUpdate();
            resultado = institucion.getIdInstitucion();
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
    public int eliminar(int idInstitucionEducativa) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ELIMINAR_INSTITUCION_EDUCATIVA(?)}");
            cs.setInt("_id_institucion_educativa", idInstitucionEducativa);
            cs.executeUpdate();
            resultado = idInstitucionEducativa;
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
    public InstitucionEducativa obtenerPorId(int idInstitucionEducativa) {
        InstitucionEducativa institucion = new InstitucionEducativa();
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call OBTENER_INSTITUCION_EDUCATIVA(?)}");
            cs.setInt("_id_institucion_educativa", idInstitucionEducativa);
            rs = cs.executeQuery();
            if (rs.next()) {
                institucion.setIdInstitucion(rs.getInt("id_Institucion_Educativa"));
                institucion.setNombre(rs.getString("nombre"));
                institucion.setDireccion(rs.getString("direccion"));
                institucion.setCantidadGrados(rs.getInt("cantidad_grados"));
                institucion.setActivo(rs.getBoolean("activo"));
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
        return institucion;
    }

    @Override
    public ArrayList<InstitucionEducativa> listarTodos() {
        ArrayList<InstitucionEducativa> instituciones = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_INSTITUCIONES_EDUCATIVAS_TODAS()}");
            rs = cs.executeQuery();
            while (rs.next()) {
                InstitucionEducativa institucion = new InstitucionEducativa();
                institucion.setIdInstitucion(rs.getInt("id_Institucion_Educativa"));
                institucion.setNombre(rs.getString("nombre"));
                institucion.setDireccion(rs.getString("direccion"));
                institucion.setCantidadGrados(rs.getInt("cantidad_grados"));
                institucion.setActivo(rs.getBoolean("activo"));
                instituciones.add(institucion);
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
        return instituciones;
    }

    @Override
    public ArrayList<InstitucionEducativa> listarInstitucionesPorIdNombre(String idNombre) {
        ArrayList<InstitucionEducativa> instituciones = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_INSTITUCIONES_EDUCATIVAS_X_ID_NOMBRE(?)}");
            cs.setString("_id_nombre", idNombre);
            rs = cs.executeQuery();
            while (rs.next()) {
                InstitucionEducativa institucion = new InstitucionEducativa();
                institucion.setIdInstitucion(rs.getInt("id_Institucion_Educativa"));
                institucion.setNombre(rs.getString("nombre"));
                institucion.setDireccion(rs.getString("direccion"));
                institucion.setCantidadGrados(rs.getInt("cantidad_grados"));
                institucion.setActivo(rs.getBoolean("activo"));
                instituciones.add(institucion);
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
        return instituciones;        
    }

}
