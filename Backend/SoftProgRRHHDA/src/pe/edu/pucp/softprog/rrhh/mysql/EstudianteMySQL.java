package pe.edu.pucp.softprog.rrhh.mysql;

import pe.edu.pucp.softprog.rrhh.dao.EstudianteDAO;
import pe.edu.pucp.softprog.rrhh.model.Estudiante;
import pe.edu.pucp.softprog.config.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class EstudianteMySQL implements EstudianteDAO {

    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    @Override
    public int insertar(Estudiante estudiante) {
        int resultado=0;
        try{
            con=DBManager.getInstance().getConnection();
            String sql="INSERT INTO persona(DNI,nombres,apellidoPaterno,apellidoMaterno,"
                    + "fechaNacimiento,direccion,sexo,religion,lengua) VALUES (?,?,?,?,?,?,?,?,?)";
            pst=con.prepareStatement(sql);
            pst.setString(1,estudiante.getDni());
            pst.setString(2,estudiante.getNombres());
            pst.setString(3,estudiante.getApellidoPaterno());
            pst.setString(4,estudiante.getApellidoMaterno());
            pst.setDate(5,new java.sql.Date(estudiante.getFechaNacimiento().getTime()));
            pst.setString(6,estudiante.getDireccion());
            pst.setString(7,String.valueOf(estudiante.getSexo()));
            pst.setString(8,estudiante.getReligion());
            pst.setString(9,estudiante.getLengua());
            pst.executeUpdate();
            sql="SELECT @@last_insert_id as id";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            
            rs.next();
            estudiante.setIdPersona(rs.getInt("id"));
            sql="INSERT INTO estudiante(idEstudiante,discapacidad,"
                    + "tipoDiscapacidad,alergias,enfermedadesCronicas,estado,origen,activo) VALUES(?,?,?,?,?)";
            pst=con.prepareStatement(sql);
            pst.setInt(1,estudiante.getIdPersona());
            pst.setBoolean(2,estudiante.isDiscapacidad());
            pst.setString(3,estudiante.getTipoDiscapacidad());
            pst.setString(4,estudiante.getAlergias());
            pst.setString(5,estudiante.getEnfermedadesCronicas());
            pst.setString(6,estudiante.getEstado());
            pst.setString(7,estudiante.getOrigen());
            pst.setBoolean(8,true);
            pst.executeUpdate();
            resultado=estudiante.getIdPersona();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());};
        }
        
        return resultado;
    }

    @Override
    public int modificar(Estudiante estudiante) {
        int resultado=0;
        try{
            con=DBManager.getInstance().getConnection();
            String sql="UPDATE persona SET DNI=?,nombres=?,apellidoPaterno=?,apellidoMaterno=?,"
                    + "fechaNacimiento=?,direccion=?,sexo=?,religion=?,lengua=? WHERE"
                    + "idPersona=?";
            pst=con.prepareStatement(sql);
            pst.setString(1,estudiante.getDni());
            pst.setString(2,estudiante.getNombres());
            pst.setString(3,estudiante.getApellidoPaterno());
            pst.setString(4,estudiante.getApellidoMaterno());
            pst.setDate(5,new java.sql.Date(estudiante.getFechaNacimiento().getTime()));
            pst.setString(6,estudiante.getDireccion());
            pst.setString(7,String.valueOf(estudiante.getSexo()));
            pst.setString(8,estudiante.getReligion());
            pst.setString(9,estudiante.getLengua());
            pst.setInt(10,estudiante.getIdPersona());
            pst.executeUpdate();
            sql="UPDATE estudiante SET discapacidad=?,tipoDiscapacidad=?,alergias=?,enfermedadesCronicas=?,estado=?,origen=? WHERE idEstudiante=?";
            pst=con.prepareStatement(sql);
            pst.setBoolean(1,estudiante.isDiscapacidad());
            pst.setString(2,estudiante.getTipoDiscapacidad());
            pst.setString(3,estudiante.getAlergias());
            pst.setString(4,estudiante.getEnfermedadesCronicas());
            pst.setString(5,estudiante.getEstado());
            pst.setString(6,estudiante.getOrigen());
            pst.setInt(7,estudiante.getIdPersona());
            resultado=pst.executeUpdate();
            
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
            con = DBManager.getInstance().getConnection();
            String sql = "UPDATE estudiante SET activo = 0 WHERE idEstudiante = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1,id);
            resultado = pst.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public Estudiante obtenerPorId(int id) {
        Estudiante estudiante = new Estudiante();
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT p.id_persona, p.DNI, p.nombres, p.apellidoPaterno,p.apellidoMaterno, "
                    + "p.fecha_nacimiento,p.lengua,p.religion,p.sexo, s.discapacidad, s.tipoDiscapacidad,s.alergias,s.enfermedadesCronicas,s.estado,s.origen FROM persona p INNER JOIN estudiante s ON p.idPersona = s.idEstudiante WHERE p.id_persona = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if(rs.next()){
                estudiante.setIdPersona(rs.getInt("idPersona"));
                estudiante.setDni(rs.getString("DNI"));
                estudiante.setNombres(rs.getString("nombres"));
                estudiante.setApellidoPaterno(rs.getString("apellidoPaterno"));
                estudiante.setApellidoMaterno(rs.getString("apellidoMaterno"));
                estudiante.setReligion(rs.getString("religion"));
                estudiante.setLengua(rs.getString("lengua"));
                estudiante.setSexo(rs.getString("sexo").charAt(0));
                estudiante.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                estudiante.setDiscapacidad(rs.getBoolean("discapacidad"));
                estudiante.setTipoDiscapacidad(rs.getString("tipoDiscapacidad"));
                estudiante.setAlergias(rs.getString("alergias"));
                estudiante.setEnfermedadesCronicas(rs.getString("enfermedadesCronicas"));
                estudiante.setEstado(rs.getString("estado"));
                estudiante.setOrigen(rs.getString("origen"));
                
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return estudiante;
    }

    @Override
    public ArrayList<Estudiante> listarTodos() {
        ArrayList<Estudiante> estudiantes=new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT p.id_persona, p.DNI, p.nombres, p.apellidoPaterno,p.apellidoMaterno, "
                    + "p.fecha_nacimiento,p.lengua,p.religion,p.sexo, s.discapacidad, s.tipoDiscapacidad,s.alergias,s.enfermedadesCronicas,s.estado,s.origen FROM persona p INNER JOIN estudiante s ON p.idPersona = s.idEstudiante WHERE p.id_persona = ?";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                Estudiante estudiante = new Estudiante();
                estudiante.setIdPersona(rs.getInt("idPersona"));
                estudiante.setDni(rs.getString("DNI"));
                estudiante.setNombres(rs.getString("nombres"));
                estudiante.setApellidoPaterno(rs.getString("apellidoPaterno"));
                estudiante.setApellidoMaterno(rs.getString("apellidoMaterno"));
                estudiante.setReligion(rs.getString("religion"));
                estudiante.setLengua(rs.getString("lengua"));
                estudiante.setSexo(rs.getString("sexo").charAt(0));
                estudiante.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                estudiante.setDiscapacidad(rs.getBoolean("discapacidad"));
                estudiante.setTipoDiscapacidad(rs.getString("tipoDiscapacidad"));
                estudiante.setAlergias(rs.getString("alergias"));
                estudiante.setEnfermedadesCronicas(rs.getString("enfermedadesCronicas"));
                estudiante.setEstado(rs.getString("estado"));
                estudiante.setOrigen(rs.getString("origen"));
                estudiantes.add(estudiante);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return estudiantes;
    }
}
	
    
    
    
