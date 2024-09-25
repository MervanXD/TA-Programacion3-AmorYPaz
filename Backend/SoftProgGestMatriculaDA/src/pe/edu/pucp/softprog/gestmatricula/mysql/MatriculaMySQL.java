package pe.edu.pucp.softprog.gestmatricula.mysql;

import java.util.ArrayList;
import pe.edu.pucp.softprog.gestmatricula.model.Matricula;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import pe.edu.pucp.softprog.config.DBManager;
import pe.edu.pucp.softprog.gestmatricula.dao.MatriculaDAO;

public class MatriculaMySQL implements MatriculaDAO{
    private PreparedStatement pst;
    private Connection con;
    private ResultSet rs;
    @Override
    public int insertar(Matricula matricula) {
        int resultado=0;
        try{
            con=DBManager.getInstance().getConnection();
            String sql="INSERT INTO matricula(anho,estado) VALUES (?,?)";
            pst=con.prepareStatement(sql);
            pst.setInt(1,matricula.getAnioMatricula());
            pst.setString(2,matricula.getEstado());
            pst.executeUpdate();
            sql="SELECT @@last_insert_id as id";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            rs.next();
            matricula.setIdMatricula(rs.getInt("id"));
            resultado=matricula.getIdMatricula();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());};
        }
        
        return resultado;
    }

    @Override
    public int modificar(Matricula matricula) {
        int resultado=0;
        try{
            con=DBManager.getInstance().getConnection();
            String sql="UPDATE matricula SET anho=?,estado=? WHERE"
                    + "idMatricula=?";
            pst=con.prepareStatement(sql);
            pst.setInt(1,matricula.getAnioMatricula());
            pst.setString(2,matricula.getEstado());
            pst.setInt(3,matricula.getIdMatricula());
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
            String sql = "UPDATE matricula SET activo = 0 WHERE idMatricula = ?";
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
    public Matricula obtenerPorId(int id) {
        Matricula matricula = new Matricula();
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT m.anho, m.estado from matricula m WHERE m.idMatricula = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if(rs.next()){
                matricula.setAnioMatricula(rs.getInt("anho"));
                matricula.setEstado(rs.getString("estado"));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return matricula;
    }

    @Override
    public ArrayList<Matricula> listarTodos() {
        ArrayList<Matricula> matriculas=new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT m.anho, m.estado from matricula m WHERE m.idMatricula = ?";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                Matricula matricula = new Matricula();
                matricula.setAnioMatricula(rs.getInt("anho"));
                matricula.setEstado(rs.getString("estado"));
                matriculas.add(matricula);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return matriculas;
    }
    
}
