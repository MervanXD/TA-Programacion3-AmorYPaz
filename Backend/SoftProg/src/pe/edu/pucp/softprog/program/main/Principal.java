package pe.edu.pucp.softprog.program.main;

import java.util.ArrayList;
import pe.edu.pucp.softprog.rrhh.dao.UsuarioDAO;
import pe.edu.pucp.softprog.rrhh.model.Usuario;
import pe.edu.pucp.softprog.rrhh.mysql.UsuarioMySQL;


public class Principal {
    public static void main(String[]args)throws Exception{   
        
        probarUsuarios();
        probarInstitucionEducativa();
        probarEstudiante();
        probarCurso();
    }
    
    public static void probarUsuarios(){
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
        else System.out.println("Registrado exitosamente: " + usuario.getUsername() +"\n");
        //LISTAR TODOS
        System.out.println("\n\nLISTA DE USUARIOS");
        usuarios = daoUsuario.listarTodos();
        for(Usuario us : usuarios){
            System.out.println("ID: " + us.getIdUsuario() + " - Username: " + us.getUsername());
        }    
    }
    
    public static void probarInstitucionEducativa(){
    
    }

    public static void probarEstudiante(){
    
    }
    
    public static void probarCurso(){
    
    }
}
