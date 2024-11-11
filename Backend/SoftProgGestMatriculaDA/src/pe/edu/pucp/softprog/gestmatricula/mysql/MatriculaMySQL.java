package pe.edu.pucp.softprog.gestmatricula.mysql;

import java.util.ArrayList;
import pe.edu.pucp.softprog.gestacademica.model.Matricula;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Set;
import pe.edu.pucp.softprog.config.DBManager;
//import pe.edu.pucp.softprog.gestacademica.model.TipoMatricula;
import pe.edu.pucp.softprog.gestcalendario.model.AnioAcademico;
import pe.edu.pucp.softprog.gestmatricula.dao.MatriculaDAO;
import pe.edu.pucp.softprog.infraestructura.model.Grado;
import pe.edu.pucp.softprog.infraestructura.model.InstitucionEducativa;
import pe.edu.pucp.softprog.rrhh.model.Estudiante;

public class MatriculaMySQL implements MatriculaDAO{
    private CallableStatement cs;
    private Connection con;
    private ResultSet rs;
    
    @Override
    public int insertar(Matricula matricula) {
        int resultado=0;
        try{
            con=DBManager.getInstance().getConnection();
            con.setAutoCommit(false);
            cs = con.prepareCall("{call INSERTAR_MATRICULA(?,?,?,?,?,?,?,?)}");
            cs.registerOutParameter("_id_Matricula", java.sql.Types.INTEGER);
            
            cs.setDate("_fecha",new java.sql.Date(matricula.getFecha().getTime()));
            cs.setString("_estado", matricula.getEstado());
            cs.setString("_tipo", matricula.getTipoMatricula()); //enum en la bd?)
            cs.setInt("_fid_Institucion_Educativa", matricula.getInstitucion().getIdInstitucion());
            cs.setInt("_fid_Estudiante", matricula.getEstudiante().getIdPersona());
            cs.setInt("_fid_AnhoAcademico", matricula.getAnioAcademico().getIdAnio());
            cs.setInt("_fid_Grado", matricula.getGrado().getIdGrado());
            cs.executeUpdate();
            matricula.setIdMatricula(cs.getInt("_id_Matricula"));
            resultado= matricula.getIdMatricula();
            con.commit();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int modificar(Matricula matricula) {
        int resultado=0;
        try{
            con=DBManager.getInstance().getConnection();
            con.setAutoCommit(false);
            cs = con.prepareCall("{call MODIFICAR_MATRICULA(?,?,?,?,?,?,?,?)}");
            
            cs.setInt("_id_Matricula", matricula.getIdMatricula());
            cs.setDate("_fecha",new java.sql.Date(matricula.getFecha().getTime()));
            cs.setString("_estado", matricula.getEstado());
            cs.setString("_tipo", String.valueOf(matricula.getTipoMatricula())); //enum en la bd?)
            cs.setInt("_fid_Institucion_Educativa", matricula.getInstitucion().getIdInstitucion());
            cs.setInt("_fid_Estudiante", matricula.getEstudiante().getIdPersona());
            cs.setInt("_fid_AnhoAcademico", matricula.getAnioAcademico().getIdAnio());
            cs.setInt("_fid_Grado", matricula.getGrado().getIdGrado());
            cs.executeUpdate();
            resultado= matricula.getIdMatricula();
            con.commit();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        
        return resultado;
    }

    @Override
    public int eliminar(int idMatricula) {
        int resultado=0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ELIMINAR_MATRICULA}");
            cs.setInt("_id_Matricula", idMatricula);
            resultado = cs.executeUpdate(); //resultado de si se pudo o no eliminar
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

//    @Override
//    public Matricula obtenerPorId(int id) {
//        Matricula matricula = new Matricula();
//        try{
//            con = DBManager.getInstance().getConnection();
//            cs = con.prepareCall("{call }")
//            pst = con.prepareStatement(sql);
//            pst.setInt(1, id);
//            rs = pst.executeQuery();
//            if(rs.next()){
//                matricula.setAnioMatricula(rs.getInt("anho"));
//                matricula.setEstado(rs.getString("estado"));
//            }
//        }catch(SQLException ex){
//            System.out.println(ex.getMessage());
//        }finally{
//            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
//        }
//        return matricula;
//    }

    @Override
    public ArrayList<Matricula> listarTodos() {
        ArrayList<Matricula> matriculas=new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_MATRICULA}");
            rs = cs.executeQuery(); //de select
            while(rs.next()){
                Matricula matricula = new Matricula();
                matricula.setAnioAcademico(new AnioAcademico());
                matricula.setInstitucion(new InstitucionEducativa());
                matricula.getInstitucion().setIdInstitucion(rs.getInt("id_Institucion_Educativa"));
                matricula.getInstitucion().setNombre(rs.getString("nombre"));
                matricula.getInstitucion().setDireccion(rs.getString("direccion"));
                matricula.getInstitucion().setCantidadGrados(rs.getInt("cantidad_grados"));
                
                matricula.setEstudiante(new Estudiante());
                matricula.getEstudiante().setIdPersona(rs.getInt("id_Estudiante"));
                matricula.getEstudiante().setDni(rs.getString("dni"));
                matricula.getEstudiante().setNombres(rs.getString("nombres"));
                matricula.getEstudiante().setApellidoPaterno(rs.getString("apellido_paterno"));
                matricula.getEstudiante().setApellidoMaterno(rs.getString("apellido_materno"));
                matricula.setEstado(rs.getString("estado"));
                
            
                matricula.setIdMatricula(rs.getInt("id_Matricula"));
                matricula.setFecha(new java.util.Date(rs.getDate("fecha").getTime()));
                matricula.setEstado(rs.getString("estado"));
                matricula.setTipoMatricula(rs.getString("tipo"));
                matriculas.add(matricula);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return matriculas;
    }
    
    @Override
    public ArrayList<Matricula> listarPorIdIE(int idInstitucion){
        ArrayList<Matricula> matriculas = new ArrayList<>();
        try{
           con = DBManager.getInstance().getConnection();
            String sql = "{call LISTAR_MATRICULAS_POR_IE(?)}";
            cs = con.prepareCall(sql);
            cs.setInt("_fid_institucion", idInstitucion);
            rs = cs.executeQuery(); 
            while(rs.next()){
                Matricula matricula = new Matricula();
                matricula.setAnioAcademico(new AnioAcademico());
                matricula.getAnioAcademico().setIdAnio(rs.getInt("fid_AnhoAcademico"));
                matricula.getAnioAcademico().setNumero(rs.getInt("numero"));
                matricula.setInstitucion(new InstitucionEducativa());
                matricula.getInstitucion().setIdInstitucion(rs.getInt("fid_Institucion_Educativa"));
                //matricula.getInstitucion().setNombre(rs.getString("nombre"));
                //matricula.getInstitucion().setDireccion(rs.getString("direccion"));
                //matricula.getInstitucion().setCantidadGrados(rs.getInt("cantidad_grados"));
                
                matricula.setEstudiante(new Estudiante());
                matricula.getEstudiante().setIdPersona(rs.getInt("fid_Estudiante"));
                //matricula.getEstudiante().setDni(rs.getString("dni"));
                //matricula.getEstudiante().setNombres(rs.getString("nombres"));
                //matricula.getEstudiante().setApellidoPaterno(rs.getString("apellido_paterno"));
                //matricula.getEstudiante().setApellidoMaterno(rs.getString("apellido_materno"));
                matricula.setEstado(rs.getString("estado"));
                
                matricula.setGrado(new Grado());
                matricula.getGrado().setIdGrado(rs.getInt("fid_Grado"));
                
                matricula.setIdMatricula(rs.getInt("id_Matricula"));
                matricula.setFecha(new java.util.Date(rs.getDate("fecha").getTime()));
                matricula.setEstado(rs.getString("estado"));
                matricula.setTipoMatricula(rs.getString("tipo"));
                matriculas.add(matricula);
                
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        return matriculas;
    }
}
