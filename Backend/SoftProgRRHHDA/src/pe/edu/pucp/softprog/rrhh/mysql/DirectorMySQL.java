package pe.edu.pucp.softprog.rrhh.mysql;

import java.util.ArrayList;
import pe.edu.pucp.softprog.config.DBManager;
import pe.edu.pucp.softprog.rrhh.dao.DirectorDAO;
import pe.edu.pucp.softprog.rrhh.model.Director;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softprog.gestusuarios.dao.UsuarioDAO;
import pe.edu.pucp.softprog.gestusuarios.model.TipoUsuario;
import pe.edu.pucp.softprog.gestusuarios.model.Usuario;
import pe.edu.pucp.softprog.gestusuarios.mysql.UsuarioMySQL;

public class DirectorMySQL implements DirectorDAO {

    private CallableStatement cs;
    private Connection con;
    private ResultSet rs;

    @Override
    public int insertar(Director director) {
        int resultado = 0;
        Usuario usuario=new Usuario();
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "{call INSERTAR_DIRECTOR(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            cs = con.prepareCall(sql);
            cs.registerOutParameter("_fid_persona", java.sql.Types.INTEGER);
            cs.setString("_tipo_contrato", director.getTipoContrato());
            cs.setDate("_fecha_nombramiento", new java.sql.Date(director.getFechaNombramiento().getTime()));
            cs.setString("_email", director.getEmail());
            cs.setString("_dni", director.getDni());
            cs.setString("_nombres", director.getNombres());
            cs.setString("_apellido_paterno", director.getApellidoPaterno());
            cs.setString("_apellido_materno", director.getApellidoMaterno());
            cs.setDate("_fecha_nacimiento", new java.sql.Date(director.getFechaNacimiento().getTime()));
            cs.setString("_lengua", director.getLengua());
            cs.setString("_religion", director.getReligion());
            cs.setString("_sexo", String.valueOf(director.getSexo()));
            cs.setString("_direccion", director.getDireccion());
            cs.executeUpdate();
            director.setIdPersona(cs.getInt("_fid_Persona"));
            resultado = director.getIdPersona();
            if(resultado != 0){
                //Ahora la parte de insertar el director
                usuario.setActivo(true);
                usuario.setContrasena("09876");
                usuario.setDirector(director);
                usuario.setTipoUsuario(TipoUsuario.DIRECTOR_IE);
                usuario.setUsername("DIR"+director.getApellidoPaterno());
                UsuarioDAO daoUsuario = new UsuarioMySQL();
                resultado = daoUsuario.insertar(usuario);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(EstudianteMySQL.class.getName()).log(Level.SEVERE, null, ex1);
            }
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
    public int modificar(Director director) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "{call MODIFICAR_DIRECTOR(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            cs = con.prepareCall(sql);
            cs.setInt("_fid_persona", director.getIdPersona());
            cs.setString("_tipo_contrato", director.getTipoContrato());
            cs.setDate("_fecha_nombramiento", new java.sql.Date(director.getFechaNombramiento().getTime()));
            cs.setString("_email", director.getEmail());
            cs.setString("_dni", director.getDni());
            cs.setString("_nombres", director.getNombres());
            cs.setString("_apellido_paterno", director.getApellidoPaterno());
            cs.setString("_apellido_materno", director.getApellidoMaterno());
            cs.setDate("_fecha_nacimiento", new java.sql.Date(director.getFechaNacimiento().getTime()));
            cs.setString("_lengua", director.getLengua());
            cs.setString("_religion", director.getReligion());
            cs.setString("_sexo", String.valueOf(director.getSexo()));
            cs.setString("_direccion", director.getDireccion());
            cs.executeUpdate();
            resultado = director.getIdPersona();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(EstudianteMySQL.class.getName()).log(Level.SEVERE, null, ex1);
            }
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
            String sql = "{call ELIMINAR_DIRECTOR(?)}";
            cs = con.prepareCall(sql);
            cs.setInt("_fid_persona", id);
            cs.executeUpdate();
            resultado = id;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(EstudianteMySQL.class.getName()).log(Level.SEVERE, null, ex1);
            }
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
    public Director obtenerPorId(int id) {
        Director director = new Director();
        try {
            con=DBManager.getInstance().getConnection();
            String sql="{call OBTENER_DIRECTOR(?)}";
            cs = con.prepareCall(sql);
            cs.setInt("_fid_persona", id);
            rs = cs.executeQuery();
            if (rs.next()) {
                director.setTipoContrato(rs.getString("tipo_contrato"));
                director.setFechaNombramiento(rs.getDate("fecha_nombramiento"));
                director.setEmail(rs.getString("email"));
                director.setActivo(rs.getBoolean("activo"));     
                director.setIdPersona(rs.getInt("fid_persona"));
                director.setDni(rs.getString("DNI"));
                director.setNombres(rs.getString("nombres"));
                director.setApellidoPaterno(rs.getString("apellido_paterno"));
                director.setApellidoMaterno(rs.getString("apellido_materno"));
                director.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                director.setLengua(rs.getString("lengua"));
                director.setReligion(rs.getString("religion"));
                director.setSexo(rs.getString("sexo").charAt(0));
                director.setDireccion(rs.getString("direccion"));
            } else director = null;
        } catch (SQLException ex) {
            director = null;
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return director;
    }

    @Override
    public ArrayList<Director> listarTodos() {
        ArrayList<Director> directores = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            String sql="{call LISTAR_DIRECTORES_TODOS()}";
            cs = con.prepareCall(sql);
            rs = cs.executeQuery();
            while(rs.next()){
                Director director = new Director();
                director.setTipoContrato(rs.getString("tipo_contrato"));
                director.setFechaNombramiento(rs.getDate("fecha_nombramiento"));
                director.setEmail(rs.getString("email"));
                director.setActivo(rs.getBoolean("activo"));     
                director.setIdPersona(rs.getInt("fid_persona"));
                director.setDni(rs.getString("DNI"));
                director.setNombres(rs.getString("nombres"));
                director.setApellidoPaterno(rs.getString("apellido_paterno"));
                director.setApellidoMaterno(rs.getString("apellido_materno"));
                director.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                director.setLengua(rs.getString("lengua"));
                director.setReligion(rs.getString("religion"));
                director.setSexo(rs.getString("sexo").charAt(0));
                director.setDireccion(rs.getString("direccion"));
                directores.add(director);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return directores;  
    }
    
    @Override
    public ArrayList<Director> listarTodosDisponibles(int idDirector) {
        ArrayList<Director> directores = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            String sql="{call LISTAR_DIRECTORES_DISPONIBLES(?)}";
            cs = con.prepareCall(sql);
            cs.setInt("_fid_director", idDirector);
            rs = cs.executeQuery();
            while(rs.next()){
                Director director = new Director();
                director.setTipoContrato(rs.getString("tipo_contrato"));
                director.setFechaNombramiento(rs.getDate("fecha_nombramiento"));
                director.setEmail(rs.getString("email"));
                director.setActivo(rs.getBoolean("activo"));     
                director.setIdPersona(rs.getInt("fid_persona"));
                director.setDni(rs.getString("DNI"));
                director.setNombres(rs.getString("nombres"));
                director.setApellidoPaterno(rs.getString("apellido_paterno"));
                director.setApellidoMaterno(rs.getString("apellido_materno"));
                director.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                director.setLengua(rs.getString("lengua"));
                director.setReligion(rs.getString("religion"));
                director.setSexo(rs.getString("sexo").charAt(0));
                director.setDireccion(rs.getString("direccion"));
                directores.add(director);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return directores; 
    }

    @Override
    public ArrayList<Director> listarDirectoresPorNombre(String nombre) {
        ArrayList<Director> directores = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            String sql="{call LISTAR_DIRECTORES_POR_NOMBRE(?)}";
            cs = con.prepareCall(sql);
            cs.setString("_nombre", nombre);
            rs = cs.executeQuery();
            while(rs.next()){
                Director director = new Director();
                director.setTipoContrato(rs.getString("tipo_contrato"));
                director.setFechaNombramiento(rs.getDate("fecha_nombramiento"));
                director.setEmail(rs.getString("email"));
                director.setActivo(rs.getBoolean("activo"));     
                director.setIdPersona(rs.getInt("fid_persona"));
                director.setDni(rs.getString("DNI"));
                director.setNombres(rs.getString("nombres"));
                director.setApellidoPaterno(rs.getString("apellido_paterno"));
                director.setApellidoMaterno(rs.getString("apellido_materno"));
                director.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                director.setLengua(rs.getString("lengua"));
                director.setReligion(rs.getString("religion"));
                director.setSexo(rs.getString("sexo").charAt(0));
                director.setDireccion(rs.getString("direccion"));
                directores.add(director);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return directores;
    }
}
