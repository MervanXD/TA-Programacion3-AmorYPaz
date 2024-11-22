package pe.edu.pucp.softprog.rrhh.mysql;

import pe.edu.pucp.softprog.rrhh.dao.EstudianteDAO;
import pe.edu.pucp.softprog.rrhh.model.Estudiante;
import pe.edu.pucp.softprog.config.DBManager;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EstudianteMySQL implements EstudianteDAO {

    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;

    @Override
    public int insertar(Estudiante estudiante) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "{call INSERTAR_ESTUDIANTE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            cs = con.prepareCall(sql);
            cs.registerOutParameter("_fid_Persona", java.sql.Types.INTEGER);
            cs.setInt("_cantidad_cursos", estudiante.getCantCursos());
            cs.setDouble("_promedio", estudiante.getPromedio());
            cs.setString("_condiciones_med", estudiante.getCondicionesMedicas());
            cs.setString("_discapacidades", estudiante.getDiscapacidades());
            cs.setString("_estado", estudiante.getEstado());
            cs.setString("_dni", estudiante.getDni());
            /*cs.setInt("_fid_apoderado", estudiante.getApoderado().getIdPersona());¨*/
            cs.setString("_nombres", estudiante.getNombres());
            cs.setString("_apellido_paterno", estudiante.getApellidoPaterno());
            cs.setString("_apellido_materno", estudiante.getApellidoMaterno());
            cs.setDate("_fecha_nacimiento", new java.sql.Date(estudiante.getFechaNacimiento().getTime()));
            cs.setString("_lengua", estudiante.getLengua());
            cs.setString("_religion", estudiante.getReligion());
            cs.setString("_sexo", String.valueOf(estudiante.getSexo()));
            cs.setString("_direccion", estudiante.getDireccion());
            cs.executeUpdate();
            estudiante.setIdPersona(cs.getInt("_fid_Persona"));
            resultado = estudiante.getIdPersona();
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
    public int modificar(Estudiante estudiante) {//si se quiere modificar al padre, modificar a esa persona primero
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "{call MODIFICAR_ESTUDIANTE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            cs = con.prepareCall(sql);
            cs.setInt("_fid_persona", estudiante.getIdPersona());
            cs.setInt("_cantidad_cursos", estudiante.getCantCursos());
            cs.setDouble("_promedio", estudiante.getPromedio());
            cs.setString("_condiciones_med", estudiante.getCondicionesMedicas());
            cs.setString("_discapacidades", estudiante.getDiscapacidades());
            cs.setString("_estado", estudiante.getEstado());
            /*cs.setInt("_fid_apoderado", estudiante.getApoderado().getIdPersona());*/
            cs.setString("_dni", estudiante.getDni());
            cs.setString("_nombres", estudiante.getNombres());
            cs.setString("_apellido_paterno", estudiante.getApellidoPaterno());
            cs.setString("_apellido_materno", estudiante.getApellidoMaterno());
            cs.setDate("_fecha_nacimiento", new java.sql.Date(estudiante.getFechaNacimiento().getTime()));
            cs.setString("_lengua", estudiante.getLengua());
            cs.setString("_religion", estudiante.getReligion());
            cs.setString("_sexo", String.valueOf(estudiante.getSexo()));
            cs.setString("_direccion", estudiante.getDireccion());
            cs.executeUpdate();
            resultado = estudiante.getIdPersona();
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
            String sql = "{call ELIMINAR_ESTUDIANTE(?)}";
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
    public Estudiante obtenerPorId(int id) {
        Estudiante estudiante = new Estudiante();
        try {
            con=DBManager.getInstance().getConnection();
            String sql="{call OBTENER_ESTUDIANTE(?)}";
            cs=con.prepareCall(sql);
            cs.setInt("_fid_persona", id);
            rs = cs.executeQuery();
            if (rs.next()) {
                /*
                estudiante.getApoderado().setDni(rs.getString("apdni"));
                estudiante.getApoderado().setNombres(rs.getString("apnom"));
                estudiante.getApoderado().setApellidoPaterno(rs.getString("apa"));
                estudiante.getApoderado().setApellidoMaterno(rs.getString("ama"));
                estudiante.getApoderado().setIdPersona(rs.getInt("apid"));
                estudiante.getApoderado().setLengua(rs.getString("aplen"));
                estudiante.getApoderado().setReligion(rs.getString("aprel"));
                estudiante.getApoderado().setSexo(rs.getString("apsex").charAt(0));
                estudiante.getApoderado().setDireccion(rs.getString("apdir"));
                estudiante.getApoderado().setFechaNacimiento(rs.getDate("apfec"));*/
                estudiante.setCantCursos(rs.getInt("cantidad_cursos"));
                estudiante.setPromedio(rs.getDouble("promedio"));
                estudiante.setCondicionesMedicas(rs.getString("condiciones_med"));
                estudiante.setDiscapacidades(rs.getString("discapacidades"));
                estudiante.setEstado(rs.getString("estado"));        
                estudiante.setIdPersona(rs.getInt("id_persona"));
                estudiante.setDni(rs.getString("DNI"));
                estudiante.setNombres(rs.getString("nombres"));
                estudiante.setApellidoPaterno(rs.getString("apellido_paterno"));
                estudiante.setApellidoMaterno(rs.getString("apellido_materno"));
                estudiante.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                estudiante.setLengua(rs.getString("lengua"));
                estudiante.setReligion(rs.getString("religion"));
                estudiante.setSexo(rs.getString("sexo").charAt(0));
                estudiante.setDireccion(rs.getString("direccion"));
                estudiante.setActivo(rs.getBoolean("activo"));
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
        return estudiante;
    }

    @Override
    public ArrayList<Estudiante> listarTodos() {
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            String sql="{call LISTAR_ESTUDIANTES_TODOS()}";
            cs = con.prepareCall(sql);
            rs = cs.executeQuery();
            while(rs.next()){
                Estudiante estudiante = new Estudiante();
                /*
                estudiante.getApoderado().setDni(rs.getString("apdni"));
                estudiante.getApoderado().setNombres(rs.getString("apnom"));
                estudiante.getApoderado().setApellidoPaterno(rs.getString("apa"));
                estudiante.getApoderado().setApellidoMaterno(rs.getString("ama"));
                estudiante.getApoderado().setIdPersona(rs.getInt("apid"));
                estudiante.getApoderado().setLengua(rs.getString("aplen"));
                estudiante.getApoderado().setReligion(rs.getString("aprel"));
                estudiante.getApoderado().setSexo(rs.getString("apsex").charAt(0));
                estudiante.getApoderado().setDireccion(rs.getString("apdir"));
                estudiante.getApoderado().setFechaNacimiento(rs.getDate("apfec"));*/
                estudiante.setCantCursos(rs.getInt("cantidad_cursos"));
                estudiante.setPromedio(rs.getDouble("promedio"));
                estudiante.setCondicionesMedicas(rs.getString("condiciones_med"));
                estudiante.setDiscapacidades(rs.getString("discapacidades"));
                estudiante.setEstado(rs.getString("estado"));        
                estudiante.setIdPersona(rs.getInt("fid_persona"));
                estudiante.setDni(rs.getString("DNI"));
                estudiante.setNombres(rs.getString("nombres"));
                estudiante.setApellidoPaterno(rs.getString("apellido_paterno"));
                estudiante.setApellidoMaterno(rs.getString("apellido_materno"));
                estudiante.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                estudiante.setLengua(rs.getString("lengua"));
                estudiante.setReligion(rs.getString("religion"));
                estudiante.setSexo(rs.getString("sexo").charAt(0));
                estudiante.setDireccion(rs.getString("direccion"));
                estudiante.setActivo(rs.getBoolean("activo"));
                estudiantes.add(estudiante);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return estudiantes;  
    } 
    @Override
    public ArrayList<Estudiante> listarEstudiantesPorGrado(int idGrado) {
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_ESTUDIANTES_X_GRADO(?)}");
            cs.setInt("_id_ie", idGrado);
            rs = cs.executeQuery();
            while (rs.next()) {
                Estudiante estudiante = new Estudiante();
                /*
                estudiante.getApoderado().setDni(rs.getString("apdni"));
                estudiante.getApoderado().setNombres(rs.getString("apnom"));
                estudiante.getApoderado().setApellidoPaterno(rs.getString("apa"));
                estudiante.getApoderado().setApellidoMaterno(rs.getString("ama"));
                estudiante.getApoderado().setIdPersona(rs.getInt("apid"));
                estudiante.getApoderado().setLengua(rs.getString("aplen"));
                estudiante.getApoderado().setReligion(rs.getString("aprel"));
                estudiante.getApoderado().setSexo(rs.getString("apsex").charAt(0));
                estudiante.getApoderado().setDireccion(rs.getString("apdir"));
                estudiante.getApoderado().setFechaNacimiento(rs.getDate("apfec"));*/
                estudiante.setCantCursos(rs.getInt("cantidad_cursos"));
                estudiante.setPromedio(rs.getDouble("promedio"));
                estudiante.setCondicionesMedicas(rs.getString("condiciones_med"));
                estudiante.setDiscapacidades(rs.getString("discapacidades"));
                estudiante.setEstado(rs.getString("estado"));        
                estudiante.setIdPersona(rs.getInt("fid_persona"));
                estudiante.setDni(rs.getString("DNI"));
                estudiante.setNombres(rs.getString("nombres"));
                estudiante.setApellidoPaterno(rs.getString("apellido_paterno"));
                estudiante.setApellidoMaterno(rs.getString("apellido_materno"));
                estudiante.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                estudiante.setLengua(rs.getString("lengua"));
                estudiante.setReligion(rs.getString("religion"));
                estudiante.setSexo(rs.getString("sexo").charAt(0));
                estudiante.setDireccion(rs.getString("direccion"));
                estudiante.setActivo(rs.getBoolean("activo"));
                estudiantes.add(estudiante);
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
        return estudiantes;        
    }
    @Override
    public ArrayList<Estudiante> listarEstudiantesPorIE(int idIE) {
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_ESTUDIANTES_X_NOMBRE_IE(?)}");
            cs.setInt("_id_ie", idIE);
            rs = cs.executeQuery();
            while (rs.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setCantCursos(rs.getInt("cantidad_cursos"));
                estudiante.setPromedio(rs.getDouble("promedio"));
                estudiante.setCondicionesMedicas(rs.getString("condiciones_med"));
                estudiante.setDiscapacidades(rs.getString("discapacidades"));
                estudiante.setEstado(rs.getString("estado"));        
                estudiante.setIdPersona(rs.getInt("fid_persona"));
                estudiante.setDni(rs.getString("DNI"));
                estudiante.setNombres(rs.getString("nombres"));
                estudiante.setApellidoPaterno(rs.getString("apellido_paterno"));
                estudiante.setApellidoMaterno(rs.getString("apellido_materno"));
                estudiante.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                estudiante.setLengua(rs.getString("lengua"));
                estudiante.setReligion(rs.getString("religion"));
                estudiante.setSexo(rs.getString("sexo").charAt(0));
                estudiante.setDireccion(rs.getString("direccion"));
                estudiante.setActivo(rs.getBoolean("activo"));
                estudiantes.add(estudiante);
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
        return estudiantes;        
    }

    @Override
    public ArrayList<Estudiante> listarEstPorIEYNombreDNI(int idIE, String nombreDNI) {
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_ESTUDIANTES_X_DNI_NOMBRE_IE(?, ?)}");
            cs.setInt("_id_ie", idIE);
            cs.setString("_nombreDNI", nombreDNI);
            rs = cs.executeQuery();
            while (rs.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setCantCursos(rs.getInt("cantidad_cursos"));
                estudiante.setPromedio(rs.getDouble("promedio"));
                estudiante.setCondicionesMedicas(rs.getString("condiciones_med"));
                estudiante.setDiscapacidades(rs.getString("discapacidades"));
                estudiante.setEstado(rs.getString("estado"));        
                estudiante.setIdPersona(rs.getInt("fid_persona"));
                estudiante.setDni(rs.getString("DNI"));
                estudiante.setNombres(rs.getString("nombres"));
                estudiante.setApellidoPaterno(rs.getString("apellido_paterno"));
                estudiante.setApellidoMaterno(rs.getString("apellido_materno"));
                estudiante.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                estudiante.setLengua(rs.getString("lengua"));
                estudiante.setReligion(rs.getString("religion"));
                estudiante.setSexo(rs.getString("sexo").charAt(0));
                estudiante.setDireccion(rs.getString("direccion"));
                estudiante.setActivo(rs.getBoolean("activo"));
                estudiantes.add(estudiante);
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
        return estudiantes;
    }
}
