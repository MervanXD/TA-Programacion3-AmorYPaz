package pe.edu.pucp.softprog.infraestructura.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.softprog.config.DBManager;
import pe.edu.pucp.softprog.infraestructura.dao.UGELDAO;
import pe.edu.pucp.softprog.infraestructura.model.UGEL;

public class UGELMySQL implements UGELDAO {
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    
    @Override
    public int insertar(UGEL ugel) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call INSERTAR_UGEL(?,?,?,?)}");
            cs.registerOutParameter("_id_ugel", java.sql.Types.INTEGER);
            cs.setString("_codigo", ugel.getCodigo());
            cs.setString("_distrito", ugel.getDistrito());
            cs.setInt("_cantidad_ie", ugel.getCantidadIE());
            cs.executeUpdate();
            ugel.setIdUgel(cs.getInt("_id_ugel"));
            resultado = ugel.getIdUgel();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return resultado;
    }

    @Override
    public int modificar(UGEL ugel) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call MODIFICAR_UGEL(?,?,?,?)}");
            cs.setInt("_id_ugel", ugel.getIdUgel());
            cs.setString("_codigo", ugel.getCodigo());
            cs.setString("_distrito", ugel.getDistrito());
            cs.setInt("_cantidad_ie", ugel.getCantidadIE());
            cs.executeUpdate();
            resultado = ugel.getIdUgel();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return resultado;
    }

    @Override
    public int eliminar(int idUgel) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ELIMINAR_UGEL(?)}");
            cs.setInt("_id_ugel", idUgel);
            resultado = cs.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public UGEL obtenerPorId(int idUgel) {
        UGEL ugel = new UGEL();
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call OBTENER_UGEL(?)}");
            cs.setInt("_id_ugel", idUgel);
            rs = cs.executeQuery();
            if (rs.next()) {
                ugel.setIdUgel(rs.getInt("id_ugel"));
                ugel.setCodigo(rs.getString("codigo"));
                ugel.setDistrito(rs.getString("distrito"));
                ugel.setCantidadIE(rs.getInt("cantidad_ie"));
                ugel.setActivo(rs.getBoolean("activo"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return ugel;
    }

    @Override
    public ArrayList<UGEL> listarTodos() {
        ArrayList<UGEL> ugeles = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_UGELES_TODAS()}");
            rs = cs.executeQuery();
            while (rs.next()) {
                UGEL ugel = new UGEL();
                ugel.setIdUgel(rs.getInt("id_ugel"));
                ugel.setCodigo(rs.getString("codigo"));
                ugel.setDistrito(rs.getString("distrito"));
                ugel.setCantidadIE(rs.getInt("cantidad_ie"));
                ugel.setActivo(rs.getBoolean("activo"));
                ugeles.add(ugel);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return ugeles;        
    }

    @Override
    public UGEL obtenerPorDirector(int idDirector) {
        UGEL ugel = null;
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call OBTENER_UGEL_X_DIRECTOR(?)}");
            cs.setInt("_id_director", idDirector);
            rs = cs.executeQuery();
            if (rs.next()) {
                ugel = new UGEL();
                ugel.setIdUgel(rs.getInt("id_ugel"));
                ugel.setCodigo(rs.getString("codigo"));
                ugel.setDistrito(rs.getString("distrito"));
                ugel.setCantidadIE(rs.getInt("cantidad_ie"));
                ugel.setActivo(rs.getBoolean("activo"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return ugel;
    }

}
