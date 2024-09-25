package pe.edu.pucp.softprog.rrhh.mysql;

import pe.edu.pucp.softprog.rrhh.dao.EstudianteDAO;
import pe.edu.pucp.softprog.rrhh.model.Estudiante;
import pe.edu.pucp.softprog.config.DBManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public abstract class EstudianteMySQL implements EstudianteDAO {

    private Connection con;
    private Statement st;
    private String sql;

    @Override
    public int insertar(Estudiante estudiante) {
        int resultado = 0;
        try {
            // Registrar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Creamos la conexión
            con = DriverManager.getConnection(DBManager.getUrl(), DBManager.getUsername(), DBManager.getPassword());

            // Creamos un statement
            st = con.createStatement();

            // Creamos la instrucción SQL
            sql = "INSERT INTO estudiante(dni, nombres, apellidos, fecha_nacimiento, lengua, direccion, sexo, religion, id_estudiante, apoderado_dni, discapacidad, tipo_discapacidad, alergias, enfermedades_cronicas, estado, origen) VALUES("
                    + "'" + estudiante.getDni() + "',"
                    + "'" + estudiante.getNombres() + "',"
                    + "'" + estudiante.getApellidos() + "',"
                    + "'" + new java.sql.Date(estudiante.getFechaNacimiento().getTime()) + "',"
                    + "'" + estudiante.getLengua() + "',"
                    + "'" + estudiante.getDireccion() + "',"
                    + "'" + estudiante.getSexo() + "',"
                    + "'" + estudiante.getReligion() + "',"
                    + estudiante.getIdEstudiante() + ","
                    + "'" + estudiante.getApoderado().getDni() + "',"
                    + estudiante.isDiscapacidad() + ","
                    + "'" + estudiante.getTipoDiscapacidad() + "',"
                    + "'" + estudiante.getAlergias() + "',"
                    + "'" + estudiante.getEnfermedadesCronicas() + "',"
                    + "'" + estudiante.getEstado() + "',"
                    + "'" + estudiante.getOrigen() + "')";

            // Ejecutamos la instrucción
            resultado = st.executeUpdate(sql);

            // Cerramos la conexión
            con.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
	
	
	@Override
    public int eliminar(String dni) {
        int resultado = 0;
        try {
            // Registrar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Creamos la conexión
            con = DriverManager.getConnection(DBManager.getUrl(), DBManager.getUsername(), DBManager.getPassword());

            // Creamos un statement
            st = con.createStatement();

            // Creamos la instrucción SQL
            sql = "DELETE FROM estudiante WHERE dni = '" + dni + "'";

            // Ejecutamos la instrucción
            resultado = st.executeUpdate(sql);

            // Cerramos la conexión
            con.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
	
	
	/*
    @Override
    public Estudiante mostrar(String dni) {
        Estudiante estudiante = null;
        try {
            // Registrar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Creamos la conexión
            con = DriverManager.getConnection(DBManager.getUrl(), DBManager.getUsername(), DBManager.getPassword());

            // Creamos la instrucción SQL 
            sql = "SELECT * FROM estudiante WHERE dni = '" + dni + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                estudiante = new Estudiante(
                        rs.getString("dni"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getDate("fecha_nacimiento"),
                        rs.getString("lengua"),
                        rs.getString("direccion"),
                        rs.getString("sexo").charAt(0),
                        rs.getString("religion"),
                        rs.getInt("id_estudiante"),
                        null, // Aquí deberías obtener el objeto apoderado según tu diseño de relaciones
                        rs.getBoolean("discapacidad"),
                        rs.getString("tipo_discapacidad"),
                        rs.getString("alergias"),
                        rs.getString("enfermedades_cronicas"),
                        rs.getString("estado"),
                        rs.getString("origen")
                );
            }

            // Cerramos la conexión
            con.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return estudiante;
    }*/
}
