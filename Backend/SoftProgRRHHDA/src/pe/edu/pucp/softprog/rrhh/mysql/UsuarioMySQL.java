package pe.edu.pucp.softprog.rrhh.mysql;

import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import pe.edu.pucp.softprog.config.DBManager;
import pe.edu.pucp.softprog.rrhh.dao.UsuarioDAO;
import pe.edu.pucp.softprog.rrhh.model.Usuario;

public class UsuarioMySQL implements UsuarioDAO{

    private CallableStatement cs;
    private Connection con;
    private ResultSet rs;
    
    @Override
    public int insertar(Usuario usuario) {
        int resultado=0;
        try{
            con=DBManager.getInstance().getConnection();
            String sql="{call INSERTAR_USUARIO(?,?,?)}";
            cs=con.prepareCall(sql);
            cs.registerOutParameter("_id_usuario", java.sql.Types.INTEGER);
            cs.setString("_nombre_usuario", usuario.getUsername());
            cs.setString("_contrasenha", usuario.getContrasena());
            cs.executeUpdate();
            usuario.setIdUsuario(cs.getInt("_id_usuario"));
            resultado = usuario.getIdUsuario();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int modificar(Usuario usuario) {
        int resultado=0;
        try{
            con=DBManager.getInstance().getConnection();
            String sql="{call MODIFICAR_USUARIO(?,?,?)}";
            cs=con.prepareCall(sql);
            cs.setInt("_id_usuario", usuario.getIdUsuario());
            cs.setString("_nombre_usuario", usuario.getUsername());
            cs.setString("_contrasenha", usuario.getContrasena());
            cs.executeUpdate();
            resultado = usuario.getIdUsuario();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminar(int id) {
        int resultado=0;
        try{
            con=DBManager.getInstance().getConnection();
            String sql="{call ELIMINAR_USUARIO(?)}";
            cs=con.prepareCall(sql);
            cs.setInt("_id_usuario", id);
            cs.executeUpdate();
            resultado = id;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public Usuario obtenerPorId(int id) {
        Usuario usuario = new Usuario();
        try{
            con=DBManager.getInstance().getConnection();
            String sql="{call OBTENER_USUARIO(?)}";
            cs=con.prepareCall(sql);
            cs.setInt("_id_usuario", id);
            rs = cs.executeQuery();
            if(rs.next()){
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setUsername(rs.getString("nombre_usuario"));
                usuario.setContrasena("XXXXXXXX");
            } else System.out.println("No se encontró al usuario");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return usuario;
    }

    @Override
    public ArrayList<Usuario> listarTodos() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            String sql="{call LISTAR_USUARIOS_TODOS()}";
            cs = con.prepareCall(sql);
            rs = cs.executeQuery();
            while(rs.next()){
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setUsername(rs.getString("nombre_usuario"));
                usuario.setContrasena("XXXXXXXX");
                usuarios.add(usuario);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return usuarios;        
    }
    
}
