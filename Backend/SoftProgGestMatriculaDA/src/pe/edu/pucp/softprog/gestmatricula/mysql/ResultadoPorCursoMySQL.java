package pe.edu.pucp.softprog.gestmatricula.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.softprog.config.DBManager;
import pe.edu.pucp.softprog.gestacademica.model.ResultadoPorCurso;
import pe.edu.pucp.softprog.gestcursos.model.Curso;
import pe.edu.pucp.softprog.gestmatricula.dao.ResultadoPorCursoDAO;

public class ResultadoPorCursoMySQL implements ResultadoPorCursoDAO{
    
    private CallableStatement cs;
    private Connection con;
    private ResultSet rs;
    
    @Override
    public ArrayList<ResultadoPorCurso> listarPorMatricula(int idMatricula) {
        ArrayList<ResultadoPorCurso> resultados = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            String sql="{call LISTAR_RESULTADOS_POR_MATRICULA(?)}";
            cs = con.prepareCall(sql);
            cs.setInt("_fid_matricula", idMatricula);
            rs = cs.executeQuery();
            while(rs.next()){
                ResultadoPorCurso resultado = new ResultadoPorCurso();
                Curso curso = new Curso();
                resultado.setCalificacion(rs.getInt("Calificacion"));
                curso.setIdCurso(rs.getInt("IdCurso"));
                curso.setNombre(rs.getString("NombreCurso"));
                curso.setActivo(rs.getBoolean("Activo"));
                resultado.setCurso(curso);
                resultados.add(resultado);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultados;
    }

    @Override
    public int insertar(ResultadoPorCurso resultado) {
        int result = 0;
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call INSERTAR_CURSO_ESTUDIANTE(?,?,?)}");
            cs.setInt("_fid_Curso", resultado.getCurso().getIdCurso());
            cs.setInt("_valor", resultado.getCalificacion());
            cs.setInt("_fid_matricula", resultado.getMatricula().getIdMatricula());
            cs.executeUpdate();
            result = 1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return result;
    }
    
}
