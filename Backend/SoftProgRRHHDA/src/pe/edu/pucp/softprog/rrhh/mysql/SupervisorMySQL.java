
package pe.edu.pucp.softprog.rrhh.mysql;

import java.util.ArrayList;
import pe.edu.pucp.softprog.rrhh.dao.SupervisorDAO;
import pe.edu.pucp.softprog.rrhh.model.Supervisor;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import pe.edu.pucp.softprog.config.DBManager;

public class SupervisorMySQL implements SupervisorDAO {
    private PreparedStatement pst;
    private Connection con;
    private ResultSet rs;
    
    @Override
    public int insertar(Supervisor supervisor) {
        int resultado=0;
        try{
            con=DBManager.getInstance().getConnection();
            String sql="INSERT INTO persona(DNI,nombres,apellidoPaterno,apellidoMaterno,"
                    + "fechaNacimiento,direccion,sexo,religion,lengua) VALUES (?,?,?,?,?,?,?,?,?)";
            pst=con.prepareStatement(sql);
            pst.setString(1,supervisor.getDni());
            pst.setString(2,supervisor.getNombres());
            pst.setString(3,supervisor.getApellidoPaterno());
            pst.setString(4,supervisor.getApellidoMaterno());
            pst.setDate(5,new java.sql.Date(supervisor.getFechaNacimiento().getTime()));
            pst.setString(6,supervisor.getDireccion());
            pst.setString(7,String.valueOf(supervisor.getSexo()));
            pst.setString(8,supervisor.getReligion());
            pst.setString(9,supervisor.getLengua());
            pst.executeUpdate();
            sql="SELECT @@last_insert_id as id";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            
            rs.next();
            supervisor.setIdPersona(rs.getInt("id"));
            sql="INSERT INTO supervisor(idSupervisor,email,"
                    + "anhosExperiencia,distrito,activo) VALUES(?,?,?,?,?)";
            pst=con.prepareStatement(sql);
            pst.setInt(1,supervisor.getIdPersona());
            pst.setString(2,supervisor.getEmail());
            pst.setInt(3,supervisor.getAniosExperienciaSupervisor());
            pst.setString(4,supervisor.getDistrito());
            pst.setBoolean(5,true);
            pst.executeUpdate();
            resultado=supervisor.getIdPersona();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());};
        }
        
        return resultado;
    }

    @Override
    public int modificar(Supervisor supervisor) {
        int resultado=0;
        try{
            con=DBManager.getInstance().getConnection();
            String sql="UPDATE persona SET DNI=?,nombres=?,apellidoPaterno=?,apellidoMaterno=?,"
                    + "fechaNacimiento=?,direccion=?,sexo=?,religion=?,lengua=? WHERE"
                    + "idPersona=?";
            pst=con.prepareStatement(sql);
            pst.setString(1,supervisor.getDni());
            pst.setString(2,supervisor.getNombres());
            pst.setString(3,supervisor.getApellidoPaterno());
            pst.setString(4,supervisor.getApellidoMaterno());
            pst.setDate(5,new java.sql.Date(supervisor.getFechaNacimiento().getTime()));
            pst.setString(6,supervisor.getDireccion());
            pst.setString(7,String.valueOf(supervisor.getSexo()));
            pst.setString(8,supervisor.getReligion());
            pst.setString(9,supervisor.getLengua());
            pst.setInt(10,supervisor.getIdPersona());
            pst.executeUpdate();
            sql="UPDATE supervisor SET email=?,anhosExperiencia=?,distrito=? WHERE idSupervisor=?";
            pst=con.prepareStatement(sql);
            pst.setString(1,supervisor.getEmail());
            pst.setInt(2,supervisor.getAniosExperienciaSupervisor());
            pst.setString(3,supervisor.getDistrito());
            pst.setInt(4,supervisor.getIdPersona());
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
            String sql = "UPDATE supervisor SET activo = 0 WHERE idSupervisor = ?";
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
    public Supervisor obtenerPorId(int id) {
        Supervisor supervisor = new Supervisor();
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT p.id_persona, p.DNI, p.nombres, p.apellidoPaterno,p.apellidoMaterno, "
                    + "p.fecha_nacimiento,p.lengua,p.religion,p.sexo, s.email, s.anhosExperiencia,s.distrito FROM persona p INNER JOIN supervisor s ON p.idPersona = s.idSupervisor WHERE p.id_persona = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if(rs.next()){
                supervisor.setIdPersona(rs.getInt("idPersona"));
                supervisor.setDni(rs.getString("DNI"));
                supervisor.setNombres(rs.getString("nombres"));
                supervisor.setApellidoPaterno(rs.getString("apellidoPaterno"));
                supervisor.setApellidoMaterno(rs.getString("apellidoMaterno"));
                supervisor.setReligion(rs.getString("religion"));
                supervisor.setLengua(rs.getString("lengua"));
                supervisor.setSexo(rs.getString("sexo").charAt(0));
                supervisor.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                supervisor.setEmail(rs.getString("email"));
                supervisor.setAniosExperienciaSupervisor(rs.getInt("anhoNacimiento"));
                supervisor.setDireccion(rs.getString("direccion"));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return supervisor;
    }

    @Override
    public ArrayList<Supervisor> listarTodos() {
        ArrayList<Supervisor> supervisores=new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT p.id_persona, p.DNI, p.nombres, p.apellidoPaterno,p.apellidoMaterno, p.fecha_nacimiento,"
                    + "p.lengua,p.religion,p.sexo, s.email, s.anhosExperiencia,s.distrito FROM persona p INNER JOIN supervisor e ON p.idPersona = s.idSupervisor WHERE p.id_persona = ?";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                Supervisor supervisor = new Supervisor();
                supervisor.setIdPersona(rs.getInt("idPersona"));
                supervisor.setDni(rs.getString("DNI"));
                supervisor.setNombres(rs.getString("nombres"));
                supervisor.setApellidoPaterno(rs.getString("apellidoPaterno"));
                supervisor.setApellidoMaterno(rs.getString("apellidoMaterno"));
                supervisor.setReligion(rs.getString("religion"));
                supervisor.setLengua(rs.getString("lengua"));
                supervisor.setSexo(rs.getString("sexo").charAt(0));
                supervisor.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                supervisor.setEmail(rs.getString("email"));
                supervisor.setAniosExperienciaSupervisor(rs.getInt("anhoNacimiento"));
                supervisor.setDireccion(rs.getString("direccion"));
                supervisores.add(supervisor);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return supervisores;
    }
}
