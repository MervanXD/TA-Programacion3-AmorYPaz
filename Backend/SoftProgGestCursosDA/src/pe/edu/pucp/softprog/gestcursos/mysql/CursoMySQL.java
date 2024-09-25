package pe.edu.pucp.softprog.gestcursos.mysql;

import java.util.ArrayList;
import pe.edu.pucp.softprog.gestcursos.dao.CursoDAO;
import pe.edu.pucp.softprog.gestcursos.model.Curso;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import pe.edu.pucp.softprog.config.DBManager;

public class CursoMySQL implements CursoDAO{
    private PreparedStatement pst;
    private Connection con;
    private ResultSet rs;
    @Override
    public int insertar(Curso curso) {
        int resultado=0;
        try{
            con=DBManager.getInstance().getConnection();
            String sql="INSERT INTO curso(idCurso,nombre,notaFinal) VALUES (?,?,?)";
            pst=con.prepareStatement(sql);
            pst.setString(1,curso.getIdCurso());
            pst.setString(2,curso.getNombre());
            pst.setDouble(3,curso.getNotaFinal());
            pst.executeUpdate();
            resultado=1; //Insertó correctamente
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());};
        }
        
        return resultado;
    }

    @Override
    public int modificar(Curso curso) {
        int resultado=0;
        try{
            con=DBManager.getInstance().getConnection();
            String sql="UPDATE curso SET idCurso=?,nombre=?,notaFinal=? WHERE"
                    + "idCurso=?";
            pst=con.prepareStatement(sql);
            pst.setString(1,curso.getIdCurso());
            pst.setString(2,curso.getNombre());
            pst.setDouble(3,curso.getNotaFinal());
            pst.setString(4,curso.getIdCurso());
            resultado=pst.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        
        
        return resultado;
    }

    @Override
    public int eliminar(String id) {
        int resultado=0;
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "UPDATE curso SET activo = 0 WHERE idCurso = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1,id);
            resultado = pst.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public Curso obtenerPorId(String id) {
        Curso curso = new Curso();
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT c.idCurso, c.nombre,c.notaFinal from curso c WHERE m.idCurso = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();
            if(rs.next()){
                curso.setIdCurso(rs.getString("idCurso"));
                curso.setNombre(rs.getString("nombre"));
                curso.setNotaFinal(rs.getDouble("notaFinal"));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return curso;
    }

    @Override
    public ArrayList<Curso> listarTodos() {
        ArrayList<Curso> cursos=new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT c.idCurso, c.nombre,c.notaFinal from curso c WHERE m.idCurso = ?";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                Curso curso = new Curso();
                curso.setIdCurso(rs.getString("idCurso"));
                curso.setNombre(rs.getString("nombre"));
                curso.setNotaFinal(rs.getDouble("notaFinal"));
                cursos.add(curso);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return cursos;
    }
    
}
