package pe.edu.pucp.softprog.gestusuarios.mysql;

import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import pe.edu.pucp.softprog.config.DBManager;
import pe.edu.pucp.softprog.gestusuarios.dao.UsuarioDAO;
import pe.edu.pucp.softprog.gestusuarios.model.TipoUsuario;
import pe.edu.pucp.softprog.gestusuarios.model.Usuario;
import pe.edu.pucp.softprog.rrhh.model.Director;

public class UsuarioMySQL implements UsuarioDAO {

    private CallableStatement cs;
    private Connection con;
    private ResultSet rs;

    @Override
    public int insertar(Usuario usuario) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "{call INSERTAR_USUARIO(?,?,?,?,?)}";
            cs = con.prepareCall(sql);
            cs.registerOutParameter("_id_usuario", java.sql.Types.INTEGER);
            cs.setString("_nombre_usuario", usuario.getUsername());
            cs.setString("_contrasenha", usuario.getContrasena());
            cs.setString("_tipo_usuario", usuario.getTipoUsuario().toString());
            cs.setInt("_fid_director", usuario.getDirector().getIdPersona());
            cs.executeUpdate();
            usuario.setIdUsuario(cs.getInt("_id_usuario"));
            resultado = usuario.getIdUsuario();
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
    public int modificar(Usuario usuario) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "{call MODIFICAR_USUARIO(?,?,?,?)}";
            cs = con.prepareCall(sql);
            cs.setInt("_id_usuario", usuario.getIdUsuario());
            cs.setString("_nombre_usuario", usuario.getUsername());
            cs.setString("_contrasenha", usuario.getContrasena());
            cs.setString("_tipo_usuario", usuario.getTipoUsuario().toString());
            cs.executeUpdate();
            resultado = usuario.getIdUsuario();
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
            String sql = "{call ELIMINAR_USUARIO(?)}";
            cs = con.prepareCall(sql);
            cs.setInt("_id_usuario", id);
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
    public Usuario obtenerPorId(int id) {
        Usuario usuario = new Usuario();
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "{call OBTENER_USUARIO(?)}";
            cs = con.prepareCall(sql);
            cs.setInt("_id_usuario", id);
            rs = cs.executeQuery();
            if (rs.next()) {
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setUsername(rs.getString("nombre_usuario"));
                usuario.setTipoUsuario(TipoUsuario.valueOf(rs.getString("tipo_usuario")));
                usuario.setContrasena(rs.getString("contrasenha"));
                usuario.setActivo(rs.getBoolean("activo"));
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
        return usuario;
    }

    @Override
    public ArrayList<Usuario> listarTodos() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "{call LISTAR_USUARIOS_TODOS()}";
            cs = con.prepareCall(sql);
            rs = cs.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setUsername(rs.getString("nombre_usuario"));
                usuario.setContrasena("XXXXXXXX");
                usuario.setTipoUsuario(TipoUsuario.valueOf(rs.getString("tipo_usuario")));
                usuario.setActivo(rs.getBoolean("activo"));
                usuarios.add(usuario);
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
        return usuarios;
    }

    @Override
    public Usuario verificar(Usuario usuario) {
        usuario.setDirector(null);
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "{call VERIFICAR_CUENTA_USUARIO(?,?)}";
            cs = con.prepareCall(sql);
            cs.setString("_usuario", usuario.getUsername());
            cs.setString("_password", usuario.getContrasena());
            rs = cs.executeQuery();
            if (rs.next()) {
                Director director = new Director();
                director.setIdPersona(rs.getInt("fid_Director"));
                director.setNombres(rs.getString("nombres"));
                director.setApellidoPaterno(rs.getString("apellido_Paterno"));
                usuario.setTipoUsuario(TipoUsuario.valueOf(rs.getString("tipo_Usuario")));
                usuario.setDirector(director);
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
        return usuario;
    }

    @Override
    public int obtenerUgelDeUsuario(String nombreCuentaUsuario) {
        int resultado = 0;
        try {
            con=DBManager.getInstance().getConnection();
            String sql="{call OBTENER_UGEL_POR_USUARIO_DIRECTOR(?)}";
            cs = con.prepareCall(sql);
            cs.setString("_nombre_usuario", nombreCuentaUsuario);
            rs = cs.executeQuery();
            if (rs.next()) {
                resultado = rs.getInt("id_ugel");
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
        return resultado;  
    }
    
    @Override
    public int obtenerIEDeUsuario(int id) {
        int resultado = 0;
        try {
            con=DBManager.getInstance().getConnection();
            String sql="{call OBTENER_IE_X_DIRECTOR (?)}";
            cs = con.prepareCall(sql);
            cs.setInt("_id_director", id);
            rs = cs.executeQuery();
            if (rs.next()) {
                resultado = rs.getInt("id_Institucion_Educativa");
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
        return resultado;  
    }
    
    @Override
    public Usuario obtenerPorIdDirector(int idDirector) {
        Usuario usuario = new Usuario();
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "{call OBTENER_USUARIO_POR_ID_DIRECTOR(?)}";
            cs = con.prepareCall(sql);
            cs.setInt("_fid_director", idDirector);
            rs = cs.executeQuery();
            if (rs.next()) {
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setUsername(rs.getString("nombre_usuario"));
                usuario.setTipoUsuario(TipoUsuario.valueOf(rs.getString("tipo_usuario")));
                usuario.setContrasena(rs.getString("contrasenha"));
                usuario.setActivo(rs.getBoolean("activo"));
            } else usuario = null;
        } catch (SQLException ex) {
            usuario = null;
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return usuario;
    }

}
