
package pe.edu.pucp.softprog.rrhh.mysql;

import java.util.ArrayList;
import pe.edu.pucp.softprog.config.DBManager;
import pe.edu.pucp.softprog.rrhh.dao.DirectorDAO;
import pe.edu.pucp.softprog.rrhh.model.Director;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.ResultSet;


public class DirectorMySQL implements DirectorDAO{
    private CallableStatement cst;
    private Connection con;
    private ResultSet rs;
    @Override
    public int insertar(Director director) {
        int resultado=0;
        try{
            con=DBManager.getInstance().getConnection();
            //String sql="INSERT INTO persona(DNI,nombres,apellidoPaterno,apellidoMaterno,"
            //        + "fechaNacimiento,direccion,sexo,religion,lengua) VALUES (?,?,?,?,?,?,?,?,?)";
            
            String sql = "{call taProg3.INSERTAR_PERSONA(?, ?, ?, ? , ?, ?, ?, ?, ?, ?)}";
            cst=con.prepareCall(sql);
            cst.registerOutParameter(1, java.sql.Types.NUMERIC);
            cst.setInt("_dni",Integer.parseInt(director.getDni()));
            cst.setString("_nombres",director.getNombres());
            cst.setString("_apellido_paterno",director.getApellidoPaterno());
            cst.setString("_apellido_materno",director.getApellidoMaterno());
            cst.setDate("_fecha_nacimiento",new java.sql.Date(director.getFechaNacimiento().getTime()));
            cst.setString("_lengua",director.getLengua());
            cst.setString("_religion",director.getReligion());
            cst.setString("_sexo",String.valueOf(director.getSexo()));
            cst.setString("_direccion",director.getDireccion());
            cst.execute();
            
            sql="SELECT @@last_insert_id as id";
            cst=con.prepareCall(sql);
            rs=cst.executeQuery();
            rs.next();
            director.setIdPersona(rs.getInt("id"));
            
            sql = "{call taProg3.INSERTAR_DIRECTOR(?, ?, ?, ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            cst=con.prepareCall(sql);
            cst.registerOutParameter(1, java.sql.Types.NUMERIC);
            cst.registerOutParameter(2, java.sql.Types.NUMERIC);
            cst.setString("_tipo_contrato",director.getTipoContrato());
            cst.setDate("_fecha_nombramiento", (Date) director.getFechaNombramiento());
            cst.setString("_email",director.getEmail());
            cst.setInt("_dni",Integer.parseInt(director.getDni()));
            cst.setString("_nombres",director.getNombres());
            cst.setString("_apellido_paterno",director.getApellidoPaterno());
            cst.setString("_apellido_materno",director.getApellidoMaterno());
            cst.setDate("_fecha_nacimiento",new java.sql.Date(director.getFechaNacimiento().getTime()));
            cst.setString("_lengua",director.getLengua());
            cst.setString("_religion",director.getReligion());
            cst.setString("_sexo",String.valueOf(director.getSexo()));
            cst.setString("_direccion",director.getDireccion());
            
            if(cst.execute()) resultado=1;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());};
        }
        
        return resultado;
    }

    @Override
    public int modificar(Director director, int idUsuario) {
        int resultado=0;
        try{
            con=DBManager.getInstance().getConnection();
            String sql = "{call taProg3.MODIFICAR_PERSONA(?, ?, ?, ? , ?, ?, ?, ?, ?, ?)}";
            cst=con.prepareCall(sql);
            cst.setInt("_id_persona", director.getIdPersona());
            cst.setInt("_dni",Integer.parseInt(director.getDni()));
            cst.setString("_nombres",director.getNombres());
            cst.setString("_apellido_paterno",director.getApellidoPaterno());
            cst.setString("_apellido_materno",director.getApellidoMaterno());
            cst.setDate("_fecha_nacimiento",new java.sql.Date(director.getFechaNacimiento().getTime()));
            cst.setString("_lengua",director.getLengua());
            cst.setString("_religion",director.getReligion());
            cst.setString("_sexo",String.valueOf(director.getSexo()));
            cst.setString("_direccion",director.getDireccion());
            cst.execute();
            cst.executeUpdate();
            sql = "{call taProg3.MODIFICAR_DIRECTOR(?, ?, ?, ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            cst=con.prepareCall(sql);
            cst.setInt("_id_persona", director.getIdPersona());
            cst.setString("_tipo_contrato",director.getTipoContrato());
            cst.setDate("_fecha_nombramiento", (Date) director.getFechaNombramiento());
            cst.setString("_email",director.getEmail());
            cst.setInt("_fid_usuario", idUsuario);
            cst.setInt("_dni",Integer.parseInt(director.getDni()));
            cst.setString("_nombres",director.getNombres());
            cst.setString("_apellido_paterno",director.getApellidoPaterno());
            cst.setString("_apellido_materno",director.getApellidoMaterno());
            cst.setDate("_fecha_nacimiento",new java.sql.Date(director.getFechaNacimiento().getTime()));
            cst.setString("_lengua",director.getLengua());
            cst.setString("_religion",director.getReligion());
            cst.setString("_sexo",String.valueOf(director.getSexo()));
            cst.setString("_direccion",director.getDireccion());
            if(cst.execute()) resultado=1;
            
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
            String sql = "{call taProg3.ELIMINAR_DIRECTOR(?)}";
            cst=con.prepareCall(sql);
            cst.setInt("_id_director",id);
            //cst.setString(1,id);
            if(cst.execute()) resultado=1;
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
            String sql = "{call taProg3.OBTENER_DIRECTOR(?)}";
            cst =con.prepareCall(sql);
            cst.setInt(1, id);
            rs = cst.executeQuery();
            int idPersona=0;
            if(rs.next()){
                director.setIdPersona(rs.getInt("id_Persona"));
                director.setTipoContrato(rs.getString("tipo_Contrato"));
                director.setFechaNombramiento(rs.getDate("fecha_Nombramiento"));
                director.setEmail(rs.getString("email"));
                idPersona=rs.getInt("fid_persona");
            }
            sql = "{call taProg3.OBTENER_PERSONA(?)}";
            cst =con.prepareCall(sql);
            cst.setInt(1, idPersona);
            rs = cst.executeQuery();
            if(rs.next()){
                director.setDni(rs.getString("dni"));
                director.setNombres(rs.getString("nombres"));
                director.setApellidoPaterno(rs.getString("apellido_Paterno"));
                director.setApellidoMaterno(rs.getString("apellido_Materno"));
                director.setReligion(rs.getString("religion"));
                director.setLengua(rs.getString("lengua"));
                director.setSexo(rs.getString("sexo").charAt(0));
                director.setFechaNacimiento(rs.getDate("fecha_Nacimiento"));
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
            String sql = "{call taProg3.LISTAR_DIRECTORES_TODOS()}";
            cst=con.prepareCall(sql);
            rs = cst.executeQuery();
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
