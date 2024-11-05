package pe.edu.pucp.softprog.infraestructura.mysql;

import java.util.ArrayList;
import pe.edu.pucp.softprog.infraestructura.dao.InstitucionEducativaDAO;
import pe.edu.pucp.softprog.infraestructura.model.InstitucionEducativa;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import pe.edu.pucp.softprog.config.DBManager;
import pe.edu.pucp.softprog.infraestructura.model.UGEL;
import pe.edu.pucp.softprog.rrhh.model.Director;

public class InstitucionEducativaMySQL implements InstitucionEducativaDAO {

    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;

    @Override
    public int insertar(InstitucionEducativa institucion) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call INSERTAR_INSTITUCION_EDUCATIVA(?,?,?,?,?,?,?,?)}");
            cs.registerOutParameter("_id_institucion_educativa", java.sql.Types.INTEGER);
            cs.setString("_nombre", institucion.getNombre());
            cs.setString("_direccion", institucion.getDireccion());
            cs.setString("_telefono", institucion.getTelefono());
            cs.setString("_correo_electronico", institucion.getCorreoElectronico());
            cs.setInt("_fid_director", institucion.getDirector().getIdPersona());
            cs.setInt("_fid_ugel", institucion.getUgel().getIdUgel());
            cs.setBytes("_foto_institucion", institucion.getFotoInstitucion());
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
            cs = con.prepareCall("{call MODIFICAR_INSTITUCION_EDUCATIVA(?,?,?,?,?,?,?,?)}");
            cs.setInt("_id_institucion_educativa", institucion.getIdInstitucion());
            cs.setString("_nombre", institucion.getNombre());
            cs.setString("_direccion", institucion.getDireccion());
            cs.setString("_telefono", institucion.getTelefono());
            cs.setString("_correo_electronico", institucion.getCorreoElectronico());
            cs.setInt("_fid_director", institucion.getDirector().getIdPersona());
            cs.setInt("_fid_ugel", institucion.getUgel().getIdUgel());
            cs.setBytes("_foto_institucion", institucion.getFotoInstitucion());
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
                institucion.setCorreoElectronico(rs.getString("correo_electronico"));
                institucion.setTelefono(rs.getString("telefono"));
                institucion.setActivo(rs.getBoolean("institucion_activo"));
                institucion.setFotoInstitucion(rs.getBytes("foto_institucion"));
                
                institucion.setDirector(new Director());
                institucion.getDirector().setIdPersona(rs.getInt("fidPersona"));
                institucion.getDirector().setNombres(rs.getString("nombres"));
                institucion.getDirector().setApellidoPaterno(rs.getString("apellido_Paterno"));
                institucion.getDirector().setApellidoMaterno(rs.getString("apellido_Materno"));
                
                institucion.setUgel(new UGEL());
                institucion.getUgel().setIdUgel(rs.getInt("id_ugel"));
                institucion.getUgel().setCodigo(rs.getString("ugel_codigo"));
                institucion.getUgel().setDistrito(rs.getString("ugel_distrito"));
                institucion.getUgel().setCantidadIE(rs.getInt("ugel_cantidad_ie"));
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
                Director director = new Director();
                institucion.setIdInstitucion(rs.getInt("id_Institucion_Educativa"));
                institucion.setNombre(rs.getString("nombre"));
                institucion.setDireccion(rs.getString("direccion"));
                institucion.setCantidadGrados(rs.getInt("cantidad_grados"));
                institucion.setActivo(rs.getBoolean("activo"));
                director.setIdPersona(rs.getInt("id_Persona"));
                director.setNombres(rs.getString("nombres"));
                director.setApellidoPaterno(rs.getString("apellido_Paterno"));
                director.setApellidoMaterno(rs.getString("apellido_Materno"));
                institucion.setDirector(director);
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
    public ArrayList<InstitucionEducativa> listarInstitucionesPorNombreYUGEL(String idNombre, int idUgel) {
        ArrayList<InstitucionEducativa> instituciones = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_INSTITUCIONES_EDUCATIVAS_X_NOMBRE_Y_UGEL(?,?)}");
            cs.setString("_id_nombre", idNombre);
            cs.setInt("_id_ugel", idUgel);
            rs = cs.executeQuery();
            while (rs.next()) {
                InstitucionEducativa institucion = new InstitucionEducativa();
                Director director = new Director();
                institucion.setIdInstitucion(rs.getInt("id_Institucion_Educativa"));
                institucion.setNombre(rs.getString("nombre"));
                institucion.setDireccion(rs.getString("direccion"));
                institucion.setCantidadGrados(rs.getInt("cantidad_grados"));
                institucion.setActivo(rs.getBoolean("activo"));
                director.setIdPersona(rs.getInt("id_Persona"));
                director.setNombres(rs.getString("nombres"));
                director.setApellidoPaterno(rs.getString("apellido_Paterno"));
                director.setApellidoMaterno(rs.getString("apellido_Materno"));
                institucion.setDirector(director);
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
    public InstitucionEducativa obtenerPorDirector(int idDirector) {
        InstitucionEducativa institucion = null;
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call OBTENER_INSTITUCION_EDUCATIVA_X_DIRECTOR(?)}");
            cs.setInt("_id_director", idDirector);
            rs = cs.executeQuery();
            if (rs.next()) {
                institucion = new InstitucionEducativa();
                institucion.setIdInstitucion(rs.getInt("id_Institucion_Educativa"));
                institucion.setNombre(rs.getString("nombre"));
                institucion.setDireccion(rs.getString("direccion"));
                institucion.setCantidadGrados(rs.getInt("cantidad_grados"));
                institucion.setCorreoElectronico(rs.getString("correo_electronico"));
                institucion.setTelefono(rs.getString("telefono"));
                institucion.setActivo(true);
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

}
