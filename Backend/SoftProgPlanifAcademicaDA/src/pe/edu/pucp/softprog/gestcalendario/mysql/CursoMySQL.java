package pe.edu.pucp.softprog.gestcalendario.mysql;

import java.util.ArrayList;
import pe.edu.pucp.softprog.gestcursos.dao.CursoDAO;
import pe.edu.pucp.softprog.gestcursos.model.Curso;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import pe.edu.pucp.softprog.config.DBManager;

public class CursoMySQL implements CursoDAO{
    private CallableStatement cst;
    private Connection con;
    private ResultSet rs;
    @Override
    public int insertar(Curso curso) {
        int resultado=0;
        try{
            con=DBManager.getInstance().getConnection();
            String sql = "{call taProg3.INSERTAR_CURSO(?, ?)}";
            cst=con.prepareCall(sql);
            cst.registerOutParameter(1, java.sql.Types.INTEGER);
            //cst.setInt("_id_curso",Integer.parseInt(curso.getIdCurso()));
            cst.setString("_nombre",curso.getNombre());
            cst.execute();
            resultado=cst.getInt("_id_curso"); //Insertó correctamente 
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());};
        }
        
        return resultado;
    }

    @Override
    public int modificar(Curso curso, int idPlanDeEstudios) {
        int resultado=0;
        try{
            con=DBManager.getInstance().getConnection();
            String sql = "{call taProg3.MODIFICAR_CURSO(?, ?, ?)}";
            cst=con.prepareCall(sql);
            cst.setInt("_id_curso",Integer.parseInt(curso.getIdCurso()));
            cst.setString(2,curso.getNombre());
            cst.setInt("_fid_plan_estudio",idPlanDeEstudios);
            //cst.setString(4,curso.getIdCurso());
            if(cst.execute()) resultado=1;
            
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
            String sql = "{call taProg3.ELIMINAR_CURSO(?)}";
            cst=con.prepareCall(sql);
            cst.setInt("_id_curso",Integer.parseInt(id));
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
    public Curso obtenerPorId(String id) {
        Curso curso = new Curso();
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "{call taProg3.OBTENER_CURSO(?)}";
            cst=con.prepareCall(sql);
            cst.setInt("_id_curso",Integer.parseInt(id));
            rs = cst.executeQuery();
            if(rs.next()){
                curso.setIdCurso(Integer.toString(rs.getInt("id_Curso")));
                curso.setNombre(rs.getString("nombre"));
                //curso.setNotaFinal(rs.getInt("fid_PLan_Estudio"));
                //aqui falta pedir la nota
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
            String sql = "{call taProg3.LISTAR_CURSOS_TODOS()}";
            cst=con.prepareCall(sql);
            rs = cst.executeQuery();
            while(rs.next()){
                Curso curso = new Curso();
                curso.setNombre(rs.getString("nombre"));
                cursos.add(curso);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return cursos;
    }
    @Override
    public int insertarNota(String idCurso, String idEstudiante,double nota){
        int resultado=0;
        try{
            con=DBManager.getInstance().getConnection();
            //String sql="INSERT INTO curso(idCurso,nombre,notaFinal) VALUES (?,?,?)";
            String sql = "{call taProg3.INSERTAR_CURSO_ESTUDIANTE(?, ?, ?)}";
            cst=con.prepareCall(sql);
            cst.setInt(Integer.parseInt(idCurso), java.sql.Types.NUMERIC);
            cst.setInt(Integer.parseInt(idEstudiante), java.sql.Types.NUMERIC);
            //cst.setInt("_id_curso",Integer.parseInt(curso.getIdCurso()));
            cst.setDouble(3,nota);
            cst.execute();
            if(cst.execute()) resultado=1;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());};
        }
        
        return resultado;
    }
    @Override
    public int modificarNota(String idCurso, String idEstudiante,double nota){
        int resultado=0;
        try{
            con=DBManager.getInstance().getConnection();
            String sql = "{call taProg3.MODIFICAR_CURSO_ESTUDIANTE(?, ?, ?)}";
            cst=con.prepareCall(sql);
            cst.setInt("_fid_curso",Integer.parseInt(idCurso));
            cst.setInt("_fid_Estudiante",Integer.parseInt(idEstudiante));
            cst.setDouble("_valor",nota);
            //cst.setString(4,curso.getIdCurso());
            if(cst.execute()) resultado=1;
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        
        
        return resultado;
    }
    @Override
    public int eliminarNota(String idCurso,String idEstudiante){
        int resultado=0;
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "{call taProg3.ELIMINAR_CURSO_ESTUDIANTE(?, ?)}";
            cst=con.prepareCall(sql);
            cst.setInt("_fid_curso",Integer.parseInt(idCurso));
            cst.setInt("_fid_Estudiante",Integer.parseInt(idEstudiante));
            //cst.setString(1,id);
            if(cst.execute()) resultado=1;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }
    
}
