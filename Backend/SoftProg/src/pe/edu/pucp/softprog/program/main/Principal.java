package pe.edu.pucp.softprog.program.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import pe.edu.pucp.softprog.rrhh.dao.EstudianteDAO;
import pe.edu.pucp.softprog.rrhh.dao.PersonaDAO;
import pe.edu.pucp.softprog.rrhh.dao.UsuarioDAO;
import pe.edu.pucp.softprog.rrhh.model.Estudiante;
import pe.edu.pucp.softprog.rrhh.model.Persona;
import pe.edu.pucp.softprog.rrhh.model.Usuario;
import pe.edu.pucp.softprog.rrhh.mysql.EstudianteMySQL;
import pe.edu.pucp.softprog.rrhh.mysql.PersonaMySQL;
import pe.edu.pucp.softprog.rrhh.mysql.UsuarioMySQL;


public class Principal {
    public static void main(String[]args)throws Exception{
//        probarUsuarios();
//        probarInstitucionEducativa();
//        probarEstudiante();
//        probarCurso();
    }
    
    public static void probarUsuarios(){
        System.out.println("--------------PRUEBA DE USUARIOS--------------\n\n");
        ArrayList<Usuario> usuarios;
        Usuario usuario = new Usuario("admin", "123456");
        UsuarioDAO daoUsuario = new UsuarioMySQL();
        //INSERT DE USUARIOS
        if(daoUsuario.insertar(usuario)==0) System.out.println("Error en registro\n");
        else System.out.println("Registrado exitosamente: " + usuario.getUsername() +"\n");
        String anterior = "juangp";
        usuario.setUsername(anterior);
        usuario.setContrasena("123789");
        if(daoUsuario.insertar(usuario)==0) System.out.println("Error en registro\n");
        else System.out.println("Registrado exitosamente: " + usuario.getUsername() +"\n");
        //OBTENER POR ID Y MODIFICAR
        usuario = daoUsuario.obtenerPorId(usuario.getIdUsuario());
        usuario.setUsername("adminroot");
        if(daoUsuario.modificar(usuario) == 0) System.out.println("Error en modificar\n");
        else System.out.println("Modificado exitosamente: " + anterior + " -> " + usuario.getUsername() +"\n");
        //LISTAR TODOS
        usuarios = daoUsuario.listarTodos();
        System.out.println("\n\nLISTA DE USUARIOS");
        for(Usuario us : usuarios){
            System.out.println("ID: " + us.getIdUsuario() + " - Username: " + us.getUsername());
        }
        //ELIMINAR
        if(daoUsuario.eliminar(usuario.getIdUsuario()) == 0) System.out.println("Error en eliminar\n");
        else System.out.println("Eliminado exitosamente: " + usuario.getUsername() +"\n");
        //LISTAR TODOS
        System.out.println("\n\nLISTA DE USUARIOS");
        usuarios = daoUsuario.listarTodos();
        for(Usuario us : usuarios){
            System.out.println("ID: " + us.getIdUsuario() + " - Username: " + us.getUsername());
        }    
    }
    
    public static void probarInstitucionEducativa(){
        System.out.println("--------------PRUEBA DE I.E--------------\n\n");
    }

    public static void probarEstudiante() throws ParseException{
        System.out.println("--------------PRUEBA DE ESTUDIANTE--------------\n\n");
        //Creando al padre apoderado
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Persona apoderado = new Persona("78906281", "Jose", "Zegarra", "Berrocal", sdf.parse("12-14-1990"), "español", "católico", 'M', "Av. La Merced");
        PersonaDAO daoPersona = new PersonaMySQL();
        if(daoPersona.insertar(apoderado)==0) System.out.println("Error en registro\n");
        else System.out.println("Registrado exitosamente (apoderado): " + apoderado.getNombres() + " " + apoderado.getApellidoPaterno()+"\n");
        //Creando al padre apoderado
        ArrayList<Estudiante> estudiantes;
        Estudiante estudiante = new Estudiante(apoderado, "-", "retirado", "-", "98452837", "Luis", "Zegarra", "Avalo", sdf.parse("12-14-2010"), "español", "católico", 'M', "Av. La Merced");
        EstudianteDAO daoEstudiante = new EstudianteMySQL();
        //INSERT DE USUARIOS
        if(daoEstudiante.insertar(estudiante)==0) System.out.println("Error en registro\n");
        else System.out.println("Registrado exitosamente: " + estudiante.getNombres()+ " "+ estudiante.getApellidoPaterno()+ "\n");
        String anterior = "Natalia";
        estudiante.setNombres(anterior);
        estudiante.setDni("96782640");
        if(daoEstudiante.insertar(estudiante)==0) System.out.println("Error en registro\n");
        else System.out.println("Registrado exitosamente: " + estudiante.getNombres()+ " "+ estudiante.getApellidoPaterno()+ "\n");
        //OBTENER POR ID Y MODIFICAR
        estudiante = daoEstudiante.obtenerPorId(estudiante.getIdEstudiante());
        estudiante.setNombres("Nataly"); //se le puede cambiar cualquier campo
        if(daoEstudiante.modificar(estudiante) == 0) System.out.println("Error en modificar\n");
        else System.out.println("Modificado exitosamente: " + anterior + " -> " + estudiante.getNombres()+"\n");
        //LISTAR TODOS
        estudiantes = daoEstudiante.listarTodos();
        System.out.println("\n\nLISTA DE ESTUDIANTES");
        for(Estudiante es : estudiantes){
            System.out.println("DNI: " + es.getDni()+ " - Nombre y Apellido: " + es.getNombres() + " " + es.getApellidoPaterno() +"\n");
        }
        //ELIMINAR
        if(daoEstudiante.eliminar(estudiante.getIdEstudiante()) == 0) System.out.println("Error en eliminar\n");
        else System.out.println("Eliminado exitosamente: " + estudiante.getDni()+"\n");
        //LISTAR TODOS
        estudiantes = daoEstudiante.listarTodos();
        System.out.println("\n\nLISTA DE ESTUDIANTES");
        for(Estudiante es : estudiantes){
            System.out.println("DNI: " + es.getDni()+ " - Nombre y Apellido: " + es.getNombres() + " " + es.getApellidoPaterno() +"\n");
        }      
    }
    
    public static void probarCurso(){
        System.out.println("--------------PRUEBA DE CURSO--------------\n\n");
    }
}
