package pe.edu.pucp.softprog.gestcalendario.mysql;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.softprog.gestcalendario.dao.PlanDeEstudioDAO;
import pe.edu.pucp.softprog.gestcalendario.model.PlanDeEstudio;
import pe.edu.pucp.softprog.config.DBManager;

public class PlanDeEstudioMYSQL implements PlanDeEstudioDAO{
    
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    
    @Override
    public int insertar(PlanDeEstudio plan) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call INSERTAR_PLAN_DE_ESTUDIO(?,?,?,?)}");
            cs.registerOutParameter("_id_Plan_Estudio", java.sql.Types.INTEGER);
            cs.setString("_descripcion",plan.getDescripcion());
            cs.setInt("_fid_Grado",plan.getGrado().getIdGrado());
            cs.setInt("_fid_Calendario_Academico",plan.getAnioAcademico().getIdAño());
            cs.executeUpdate();
            plan.setIdPlan(cs.getInt("_id_Plan_Estudio"));
            resultado = plan.getIdPlan();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int modificar(PlanDeEstudio plan) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            //con.setAutoCommit(false);
            cs = con.prepareCall("{call MODIFICAR_PLAN_DE_ESTUDIO(?,?,?,?)}");
            cs.registerOutParameter("_id_Plan_Estudio", java.sql.Types.INTEGER);
            cs.setString("_descripcion",plan.getDescripcion());
            cs.setInt("_fid_Grado",plan.getGrado().getIdGrado());
            cs.setInt("_fid_Calendario_Academico",plan.getAnioAcademico().getIdAño());
            cs.executeUpdate();
            resultado = plan.getIdPlan();
            //con.commit();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminar(int idPlan) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ELIMINAR_PLAN_DE_ESTUDIO(?)}");
            cs.setInt("_id_Plan_Estudio", idPlan);
            resultado = cs.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public PlanDeEstudio obtenerPorId(int idPlan) {
        PlanDeEstudio plan = new PlanDeEstudio();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_INSTITUCION_EDUCATIVA(?)}");
            cs.setInt("_id_Plan_Estudio", idPlan);
            rs = cs.executeQuery();
            if(rs.next()){
               plan.setIdPlan(rs.getInt("id_Plan_Estudio"));
               plan.setDescripcion(rs.getString("nombre"));
               //plan.setGrado(rs.getInt(""));
               //plan.setAnioAcademico(rs.get);
               plan.setActivo(1);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return plan;
    }

    @Override
    public ArrayList<PlanDeEstudio> listarTodos() {
        ArrayList<PlanDeEstudio> planes = new ArrayList<>();
//        try{
//            con = DBManager.getInstance().getConnection();
//            cs = con.prepareCall("{call LISTAR_PRODUCTOS_TODOS()}");
//            rs = cs.executeQuery();
//            while(rs.next()){
//                PlanDeEstudio plan = new PlanDeEstudio();
//                plan.setIdPlan(rs.getInt("id_producto"));
//                plan.setDescripcion(rs.getString("nombre"));
//                //plan.setAnioAcademico(rs.getInt(""));
//                //plan.setGrado(1);
//                plan.setActivo(1);
//                planes.add(plan);
//            }
//        }catch(SQLException ex){
//            System.out.println(ex.getMessage());
//        }finally{
//            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
//        }
        return planes;
    }
    
}
