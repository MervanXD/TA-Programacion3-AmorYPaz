
package pe.edu.pucp.softprog.Infraestructura.mysql;
import java.util.ArrayList;
import pe.edu.pucp.softprog.Infraestructura.dao.InstitucionEducativaDAO;
import pe.edu.pucp.softprog.infraestructura.model.InstitucionEducativa;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import pe.edu.pucp.softprog.config.DBManager;

public class InstitucionEducativaMySQL implements InstitucionEducativaDAO{
    
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    
    @Override
    public int insertar(InstitucionEducativa institucion) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call INSERTAR_INSTITUCION_EDUCATIVA(?,?,?)}");
            cs.registerOutParameter("_id_Institucion_Educativa", java.sql.Types.INTEGER);
            cs.setString("_nombre",institucion.getNombre());
            cs.setString("_ubicacion",institucion.getDireccion());
            //cs.setInt("_fid_Superintendente", institucion.get());
            cs.executeUpdate();
            institucion.setIdSede(cs.getInt("_id_Institucion_Educativa"));
            resultado = institucion.getIdSede();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int modificar(InstitucionEducativa institucion) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            con.setAutoCommit(false);
            cs = con.prepareCall("{call MODIFICAR_INSTITUCION_EDUCATIVA(?,?,?,?)}");
            cs.setInt("_id_Institucion_Educativa", institucion.getIdSede());
            //cs.setInt("_fid_Superintendente", institucion.get());
            cs.setString("_nombre",institucion.getNombre());
            cs.setString("_ubicacion",institucion.getDireccion());
            cs.executeUpdate();
            resultado = institucion.getIdSede();
            con.commit();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminar(int idInstitucionEducativa) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ELIMINAR_INSTITUCION_EDUCATIVA(?)}");
            cs.setInt("_id_Institucion_Educativa", idInstitucionEducativa);
            resultado = cs.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public InstitucionEducativa obtenerPorId(int idInstitucionEducativa) {
        InstitucionEducativa institucion = new InstitucionEducativa();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call OBTENER_INSTITUCION_EDUCATIVA(?)}");
            cs.setInt("_id_Institucion_Educativa", idInstitucionEducativa);
            rs = cs.executeQuery();
            if(rs.next()){
               institucion.setIdSede(rs.getInt("id_producto"));
               institucion.setNombre(rs.getString("nombre"));
               institucion.setDireccion(rs.getString("unidad_medida"));
               institucion.setActivo(1);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return institucion;
    }

    @Override
    public ArrayList<InstitucionEducativa> listarTodos() {
        ArrayList<InstitucionEducativa> instituciones = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_INSTITUCIONES_EDUCATIVAS_TODAS(?,?,?)}");
            rs = cs.executeQuery();
            while(rs.next()){
                InstitucionEducativa institucion = new InstitucionEducativa();
                institucion.setIdSede(rs.getInt("id_producto"));
                institucion.setNombre(rs.getString("nombre"));
                institucion.setDireccion(rs.getString("unidad_medida"));
                institucion.setActivo(1);
                instituciones.add(institucion);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return instituciones;
    }
    
}
