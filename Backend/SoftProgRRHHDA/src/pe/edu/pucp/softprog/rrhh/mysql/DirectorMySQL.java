
package pe.edu.pucp.softprog.rrhh.mysql;

import java.util.ArrayList;
import pe.edu.pucp.softprog.config.DBManager;
import pe.edu.pucp.softprog.rrhh.dao.DirectorDAO;
import pe.edu.pucp.softprog.rrhh.model.Director;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;


public class DirectorMySQL implements DirectorDAO{
    private PreparedStatement pst;
    private Connection con;
    private ResultSet rs;
    @Override
    public int insertar(Director director) {
        int resultado=0;
        try{
            con=DBManager.getInstance().getConnection();
            String sql="INSERT INTO persona(DNI,nombres,apellidoPaterno,apellidoMaterno,"
                    + "fechaNacimiento,direccion,sexo,religion,lengua) VALUES (?,?,?,?,?,?,?,?,?)";
            pst=con.prepareStatement(sql);
            pst.setString(1,director.getDni());
            pst.setString(2,director.getNombres());
            pst.setString(3,director.getApellidoPaterno());
            pst.setString(4,director.getApellidoMaterno());
            pst.setDate(5,new java.sql.Date(director.getFechaNacimiento().getTime()));
            pst.setString(6,director.getDireccion());
            pst.setString(7,String.valueOf(director.getSexo()));
            pst.setString(8,director.getReligion());
            pst.setString(9,director.getLengua());
            pst.executeUpdate();
            sql="SELECT @@last_insert_id as id";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            
            rs.next();
            director.setIdPersona(rs.getInt("id"));
            sql="INSERT INTO director(idDirector,tipoContrato,"
                    + "fechaNombramiento,email,activo) VALUES(?,?,?,?,?)";
            pst=con.prepareStatement(sql);
            pst.setInt(1,director.getIdPersona());
            pst.setInt(2,director.getIdPersona());
            pst.setDate(3,new java.sql.Date(director.getFechaNombramiento().getTime()));
            pst.setString(4,director.getEmail());
            pst.setBoolean(5,true);
            pst.executeUpdate();
            resultado=director.getIdPersona();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());};
        }
        
        return resultado;
    }

    @Override
    public int modificar(Director director) {
        int resultado=0;
        try{
            con=DBManager.getInstance().getConnection();
            String sql="UPDATE persona SET DNI=?,nombres=?,apellidoPaterno=?,apellidoMaterno=?,"
                    + "fechaNacimiento=?,direccion=?,sexo=?,religion=?,lengua=? WHERE"
                    + "idPersona=?";
            pst=con.prepareStatement(sql);
            pst.setString(1,director.getDni());
            pst.setString(2,director.getNombres());
            pst.setString(3,director.getApellidoPaterno());
            pst.setString(4,director.getApellidoMaterno());
            pst.setDate(5,new java.sql.Date(director.getFechaNacimiento().getTime()));
            pst.setString(6,director.getDireccion());
            pst.setString(7,String.valueOf(director.getSexo()));
            pst.setString(8,director.getReligion());
            pst.setString(9,director.getLengua());
            pst.setInt(10,director.getIdPersona());
            pst.executeUpdate();
            sql="UPDATE director SET tipoContrato=?,fechaNombramiento=?,email=? WHERE idDirector=?";
            pst=con.prepareStatement(sql);
            pst.setString(1,director.getTipoContrato());
            pst.setDate(2,new java.sql.Date(director.getFechaNombramiento().getTime()));
            pst.setString(3,director.getEmail());
            pst.setInt(4,director.getIdPersona());
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
            String sql = "UPDATE director SET activo = 0 WHERE idDirector = ?";
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
    public Director obtenerPorId(int id) {
        Director director = new Director();
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT p.id_persona, p.DNI, p.nombres, p.apellidoPaterno,p.apellidoMaterno, "
                    + "p.fecha_nacimiento,p.lengua,p.religion,p.sexo, s.tipoContrato, s.fechaNombramiento,s.email FROM persona p INNER JOIN director s ON p.idPersona = s.idDirector WHERE p.id_persona = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if(rs.next()){
                director.setIdPersona(rs.getInt("idPersona"));
                director.setDni(rs.getString("DNI"));
                director.setNombres(rs.getString("nombres"));
                director.setApellidoPaterno(rs.getString("apellidoPaterno"));
                director.setApellidoMaterno(rs.getString("apellidoMaterno"));
                director.setReligion(rs.getString("religion"));
                director.setLengua(rs.getString("lengua"));
                director.setSexo(rs.getString("sexo").charAt(0));
                director.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                director.setTipoContrato(rs.getString("tipoContrato"));
                director.setFechaNombramiento(rs.getDate("fechaNombramiento"));
                director.setEmail(rs.getString("email"));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return director;
    }

    @Override
    public ArrayList<Director> listarTodos() {
        ArrayList<Director> directores=new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT p.id_persona, p.DNI, p.nombres, p.apellidoPaterno,p.apellidoMaterno, "
                    + "p.fecha_nacimiento,p.lengua,p.religion,p.sexo, s.tipoContrato, s.fechaNombramiento,s.email FROM persona p INNER JOIN director s ON p.idPersona = s.idDirector WHERE p.id_persona = ?";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                Director director = new Director();
                director.setIdPersona(rs.getInt("idPersona"));
                director.setDni(rs.getString("DNI"));
                director.setNombres(rs.getString("nombres"));
                director.setApellidoPaterno(rs.getString("apellidoPaterno"));
                director.setApellidoMaterno(rs.getString("apellidoMaterno"));
                director.setReligion(rs.getString("religion"));
                director.setLengua(rs.getString("lengua"));
                director.setSexo(rs.getString("sexo").charAt(0));
                director.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                director.setTipoContrato(rs.getString("tipoContrato"));
                director.setFechaNombramiento(rs.getDate("fechaNombramiento"));
                director.setEmail(rs.getString("email"));
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
