package pe.edu.pucp.softprog.rrhh.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.softprog.config.DBManager;
import pe.edu.pucp.softprog.rrhh.dao.PersonaDAO;
import pe.edu.pucp.softprog.rrhh.model.Persona;

public class PersonaMySQL implements PersonaDAO {

    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;

    @Override
    public int insertar(Persona persona) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "{call INSERTAR_PERSONA(?,?,?,?,?,?,?,?,?,?)}";
            cs = con.prepareCall(sql);
            cs.registerOutParameter("_id_persona", java.sql.Types.INTEGER);
            cs.setString("_dni", persona.getDni());
            cs.setString("_nombres", persona.getNombres());
            cs.setString("_apellido_paterno", persona.getApellidoPaterno());
            cs.setString("_apellido_materno", persona.getApellidoMaterno());
            cs.setDate("_fecha_nacimiento", new java.sql.Date(persona.getFechaNacimiento().getTime()));
            cs.setString("_lengua", persona.getLengua());
            cs.setString("_religion", persona.getReligion());
            cs.setString("_sexo", String.valueOf(persona.getSexo()));
            cs.setString("_direccion", persona.getDireccion());
            cs.executeUpdate();
            persona.setIdPersona(cs.getInt("_id_persona"));
            resultado = persona.getIdPersona();
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
    public int modificar(Persona persona) {
            int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "{call MODIFICAR_PERSONA(?,?,?,?,?,?,?,?,?,?)}";
            cs = con.prepareCall(sql);
            cs.setInt("_id_persona", persona.getIdPersona());
            cs.setString("_dni", persona.getDni());
            cs.setString("_nombres", persona.getNombres());
            cs.setString("_apellido_paterno", persona.getApellidoPaterno());
            cs.setString("_apellido_materno", persona.getApellidoMaterno());
            cs.setDate("_fecha_nacimiento", new java.sql.Date(persona.getFechaNacimiento().getTime()));
            cs.setString("_lengua", persona.getLengua());
            cs.setString("_religion", persona.getReligion());
            cs.setString("_sexo", String.valueOf(persona.getSexo()));
            cs.setString("_direccion", persona.getDireccion());
            cs.executeUpdate();
            resultado = persona.getIdPersona();
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
            String sql = "{call ELIMINAR_PERSONA(?)}";
            cs = con.prepareCall(sql);
            cs.setInt("_id_persona", id);
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
    public Persona obtenerPorId(int id) {
        Persona persona = new Persona();
        try {
            con=DBManager.getInstance().getConnection();
            String sql="{call OBTENER_PERSONA(?)}";
            cs=con.prepareCall(sql);
            cs.setInt("_id_persona", id);
            rs = cs.executeQuery();
            if (rs.next()) {
                persona.setIdPersona(rs.getInt("id_persona"));
                persona.setDni(rs.getString("DNI"));
                persona.setNombres(rs.getString("nombres"));
                persona.setApellidoPaterno(rs.getString("apellido_paterno"));
                persona.setApellidoMaterno(rs.getString("apellido_materno"));
                persona.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                persona.setLengua(rs.getString("lengua"));
                persona.setReligion(rs.getString("religion"));
                persona.setSexo(rs.getString("sexo").charAt(0));
                persona.setDireccion(rs.getString("direccion"));
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
        return persona;
    }

    @Override
    public ArrayList<Persona> listarTodos() {
        ArrayList<Persona> personas = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            String sql="{call LISTAR_ESTUDIANTES_TODOS()}";
            cs = con.prepareCall(sql);
            rs = cs.executeQuery();
            while(rs.next()){
                Persona persona = new Persona();       
                persona.setIdPersona(rs.getInt("id_persona"));
                persona.setDni(rs.getString("dni"));
                persona.setNombres(rs.getString("nombres"));
                persona.setApellidoPaterno(rs.getString("apellido_paterno"));
                persona.setApellidoMaterno(rs.getString("apellido_materno"));
                persona.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                persona.setLengua(rs.getString("lengua"));
                persona.setReligion(rs.getString("religion"));
                persona.setSexo(rs.getString("sexo").charAt(0));
                persona.setDireccion(rs.getString("direccion"));
                personas.add(persona);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return personas;      
    }

}
