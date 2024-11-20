package pe.edu.pucp.softprog.infraestructura.mysql;

import java.util.ArrayList;
import pe.edu.pucp.softprog.infraestructura.dao.GradoDAO;
import pe.edu.pucp.softprog.infraestructura.model.Grado;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import pe.edu.pucp.softprog.config.DBManager;
import pe.edu.pucp.softprog.infraestructura.model.InstitucionEducativa;
import pe.edu.pucp.softprog.infraestructura.model.TipoNivel;

public class GradoMySQL implements GradoDAO {

    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;

    @Override
    public int insertar(Grado grado) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call INSERTAR_GRADO(?,?,?,?,?,?)}");
            cs.registerOutParameter("_id_Grado", java.sql.Types.INTEGER);
            cs.setString("_numero", grado.getNumero());
            cs.setString("_tipo_nivel", grado.getNivel().toString());
            cs.setInt("_num_matriculados", grado.getAlumnosMatriculados());
            cs.setInt("_fid_Institucion_Educativa", grado.getInstitucion().getIdInstitucion());
            cs.setInt("_vacantes",grado.getVacantes());
            cs.executeUpdate();
            grado.setIdGrado(cs.getInt("_id_grado"));
            resultado = grado.getIdGrado();
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
    public int modificar(Grado grado) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call MODIFICAR_GRADO(?,?,?,?,?,?)}");
            cs.setInt("_id_Grado", grado.getIdGrado());
            cs.setString("_numero", grado.getNumero());
            cs.setString("_tipo_nivel", grado.getNivel().toString());
            cs.setInt("_num_matriculados", grado.getAlumnosMatriculados());
            cs.setInt("_fid_institucion_educativa", grado.getInstitucion().getIdInstitucion());
            cs.setInt("_vacantes",grado.getVacantes());
            cs.executeUpdate();
            resultado = grado.getIdGrado();
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
    public int eliminar(int idGrado) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ELIMINAR_GRADO(?)}");
            cs.setInt("_id_grado", idGrado);
            cs.executeUpdate();
            resultado = idGrado;
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
    public Grado obtenerPorId(int idGrado) {
        Grado grado = new Grado();
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call OBTENER_GRADO(?)}");
            cs.setInt("_id_grado", idGrado);
            rs = cs.executeQuery();
            if (rs.next()) {
                grado.setIdGrado(rs.getInt("id_Grado"));
                grado.setAlumnosMatriculados(rs.getInt("num_matriculados"));
                grado.setNivel(TipoNivel.valueOf(rs.getString("tipo_nivel")));
                grado.setNumero(rs.getString("numero"));
                grado.setVacantes(rs.getInt("vacantes"));
                grado.setActivo(rs.getBoolean("gra_activo"));
                grado.getInstitucion().setActivo(rs.getBoolean("ie_activo"));
                grado.getInstitucion().setCantidadGrados(rs.getInt("cantidad_grados"));
                grado.getInstitucion().setDireccion(rs.getString("direccion"));
                grado.getInstitucion().setIdInstitucion(rs.getInt("id_Institucion_Educativa"));
                grado.getInstitucion().setNombre(rs.getString("nombre"));
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
        return grado;
    }

    @Override
    public ArrayList<Grado> listarTodos() {
        ArrayList<Grado> grados = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_GRADOS_TODOS(?)}");
            rs = cs.executeQuery();
            while (rs.next()) {
                Grado grado = new Grado();
                grado.setIdGrado(rs.getInt("id_Grado"));
                grado.setAlumnosMatriculados(rs.getInt("num_matriculados"));
                grado.setNivel(TipoNivel.valueOf(rs.getString("tipo_nivel")));
                grado.setNumero(rs.getString("numero"));
                grado.setActivo(rs.getBoolean("gra_activo"));
                grado.getInstitucion().setActivo(rs.getBoolean("ie_activo"));
                grado.getInstitucion().setCantidadGrados(rs.getInt("cantidad_grados"));
                grado.getInstitucion().setDireccion(rs.getString("direccion"));
                grado.getInstitucion().setIdInstitucion(rs.getInt("id_Institucion_Educativa"));
                grado.getInstitucion().setNombre(rs.getString("nombre"));
                grados.add(grado);
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
        return grados;
    }
    
    @Override
    public int insertarTodosLosGrados(int cantGrados, int fid_IE,TipoNivel tipo){
        int resultado=0;
        Grado gradonuevo;
        InstitucionEducativa insti = new InstitucionEducativa();
        insti.setIdInstitucion(fid_IE);
        for(int i=1;i<=cantGrados;i++){
            gradonuevo=new Grado();
            gradonuevo.setActivo(true);
            gradonuevo.setAlumnosMatriculados(0);
            gradonuevo.setNivel(tipo);
            gradonuevo.setVacantes(60);
            gradonuevo.setNumero(String.valueOf(i));
            gradonuevo.setInstitucion(insti);
            resultado=this.insertar(gradonuevo);
        }
        return resultado;
    }

    @Override
    public Grado obtenerPorIdPlanEstudios(int idPlan) {
        Grado grado = new Grado();
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call OBTENER_GRADO_POR_PLAN_ESTUDIOS(?)}");
            cs.setInt("_fid_plan", idPlan);
            rs = cs.executeQuery();
            if (rs.next()) {
                grado.setIdGrado(rs.getInt("id_Grado"));
                grado.setAlumnosMatriculados(rs.getInt("num_matriculados"));
                grado.setNivel(TipoNivel.valueOf(rs.getString("tipo_nivel")));
                grado.setNumero(rs.getString("numero"));
                grado.setActivo(rs.getBoolean("gra_activo"));
                grado.setVacantes(rs.getInt("vacantes"));
                grado.getInstitucion().setActivo(rs.getBoolean("ie_activo"));
                grado.getInstitucion().setCantidadGrados(rs.getInt("cantidad_grados"));
                grado.getInstitucion().setDireccion(rs.getString("direccion"));
                grado.getInstitucion().setIdInstitucion(rs.getInt("id_Institucion_Educativa"));
                grado.getInstitucion().setNombre(rs.getString("nombre"));
            }
        } catch (SQLException ex) {
            grado = null;
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return grado;
    }

    @Override
    public ArrayList<Grado> obtenerPorIdIE(int idInstitucion) {
        ArrayList<Grado> grados = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call OBTENER_GRADO_POR_INSTITUCION(?)}");
            cs.setInt("_fid_institucion", idInstitucion);
            rs = cs.executeQuery();
            if (rs.next()) {
                Grado grado = new Grado();
                grado.setIdGrado(rs.getInt("id_Grado"));
                grado.setAlumnosMatriculados(rs.getInt("num_matriculados"));
                grado.setNivel(TipoNivel.valueOf(rs.getString("tipo_nivel")));
                grado.setNumero(rs.getString("numero"));
                grado.setActivo(rs.getBoolean("gra_activo"));
                grado.getInstitucion().setActivo(rs.getBoolean("ie_activo"));
                grado.getInstitucion().setCantidadGrados(rs.getInt("cantidad_grados"));
                grado.getInstitucion().setDireccion(rs.getString("direccion"));
                grado.getInstitucion().setIdInstitucion(rs.getInt("id_Institucion_Educativa"));
                grado.getInstitucion().setNombre(rs.getString("nombre"));
                grados.add(grado);
            }
        } catch (SQLException ex) {
            grados = null;
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return grados;
    }

    @Override
    public ArrayList<Grado> listarTodosPorIdIE(int idInstitucion) {
        ArrayList<Grado> grados = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_GRADOS_POR_IE(?)}");
            cs.setInt("_fid_institucion", idInstitucion);
            rs = cs.executeQuery();
            while (rs.next()) {
                Grado grado = new Grado();
                grado.setIdGrado(rs.getInt("id_Grado"));
                grado.setAlumnosMatriculados(rs.getInt("num_matriculados"));
                grado.setNivel(TipoNivel.valueOf(rs.getString("tipo_nivel")));
                grado.setNumero(rs.getString("numero"));
                grado.setActivo(rs.getBoolean("gra_activo"));
                grado.setVacantes(rs.getInt("vacantes"));
                grado.getInstitucion().setActivo(rs.getBoolean("ie_activo"));
                grado.getInstitucion().setCantidadGrados(rs.getInt("cantidad_grados"));
                grado.getInstitucion().setDireccion(rs.getString("direccion"));
                grado.getInstitucion().setIdInstitucion(rs.getInt("id_Institucion_Educativa"));
                grado.getInstitucion().setNombre(rs.getString("nombre"));
                grados.add(grado);
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
        return grados;        
    }

    @Override
    public int asignarPlan(int idGrado, int idPlan) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ASIGNAR_PLAN_ESTUDIO_GRADO(?,?)}");
            cs.setInt("_id_grado", idGrado);
            cs.setInt("_fid_plan_de_estudios", idPlan);
            cs.executeUpdate();
            resultado = 1;
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
    public ArrayList<Grado> listarTodosPorIdIENivel(int idInstitucion, String nivel) {
        ArrayList<Grado> grados = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_GRADOS_POR_IE_y_Nivel(?, ?)}");
            cs.setInt("_fid_institucion", idInstitucion);
            cs.setString("_nombre_nivel", nivel);
            rs = cs.executeQuery();
            while (rs.next()) {
                Grado grado = new Grado();
                grado.setIdGrado(rs.getInt("id_Grado"));
                grado.setNumero(rs.getString("numero_de_grado"));
                grados.add(grado);
            }
        }
         catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return grados;    
    }
}
