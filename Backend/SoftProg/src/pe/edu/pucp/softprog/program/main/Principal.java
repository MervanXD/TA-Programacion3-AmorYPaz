package pe.edu.pucp.softprog.program.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import pe.edu.pucp.softprog.gestcursos.dao.CursoDAO;
import pe.edu.pucp.softprog.gestcursos.model.Curso;
import pe.edu.pucp.softprog.gestcursos.mysql.CursoMySQL;
import pe.edu.pucp.softprog.rrhh.dao.EstudianteDAO;
import pe.edu.pucp.softprog.rrhh.dao.PersonaDAO;
import pe.edu.pucp.softprog.gestusuarios.dao.UsuarioDAO;
import pe.edu.pucp.softprog.gestusuarios.model.TipoUsuario;
import pe.edu.pucp.softprog.rrhh.model.Estudiante;
import pe.edu.pucp.softprog.rrhh.model.Persona;
import pe.edu.pucp.softprog.gestusuarios.model.Usuario;
import pe.edu.pucp.softprog.rrhh.mysql.EstudianteMySQL;
import pe.edu.pucp.softprog.rrhh.mysql.PersonaMySQL;
import pe.edu.pucp.softprog.gestusuarios.mysql.UsuarioMySQL;

public class Principal {

    public static void main(String[] args) throws Exception {
//        probarUsuarios();
//        probarInstitucionEducativa();
//        probarEstudiante();
        probarCurso();
    }

    public static void probarUsuarios() {
        System.out.println("--------------PRUEBA DE USUARIOS--------------\n\n");
        ArrayList<Usuario> usuarios;
        Usuario usuario = new Usuario("master", "987656", TipoUsuario.DIRECTOR_IE);
        UsuarioDAO daoUsuario = new UsuarioMySQL();
        //INSERT DE USUARIOS
        if (daoUsuario.insertar(usuario) == 0) {
            System.out.println("Error en registro\n");
        } else {
            System.out.println("Registrado exitosamente: " + usuario.getUsername() + "\n");
        }
        String anterior = "admin";
        usuario.setUsername(anterior);
        usuario.setContrasena("151515");
        usuario.setTipoUsuario(TipoUsuario.DIRECTOR_UGEL);
        if (daoUsuario.insertar(usuario) == 0) {
            System.out.println("Error en registro\n");
        } else {
            System.out.println("Registrado exitosamente: " + usuario.getUsername() + "\n");
        }
        //OBTENER POR ID Y MODIFICAR
        usuario = daoUsuario.obtenerPorId(usuario.getIdUsuario());
        usuario.setUsername("adminroot");
        if (daoUsuario.modificar(usuario) == 0) {
            System.out.println("Error en modificar\n");
        } else {
            System.out.println("Modificado exitosamente: " + anterior + " -> " + usuario.getUsername() + "\n");
        }
        //LISTAR TODOS
        usuarios = daoUsuario.listarTodos();
        System.out.println("\n\nLISTA DE USUARIOS");
        for (Usuario us : usuarios) {
            System.out.println("ID: " + us.getIdUsuario() + " - Username: " + us.getUsername() + " - TipoUser: " + us.getTipoUsuario());
        }
        //ELIMINAR
        if (daoUsuario.eliminar(usuario.getIdUsuario()) == 0) {
            System.out.println("Error en eliminar\n");
        } else {
            System.out.println("\n\nEliminado exitosamente: " + usuario.getUsername() + "\n");
        }
        //LISTAR TODOS
        System.out.println("\n\nLISTA DE USUARIOS");
        usuarios = daoUsuario.listarTodos();
        for (Usuario us : usuarios) {
            System.out.println("ID: " + us.getIdUsuario() + " - Username: " + us.getUsername() + " - TipoUser: " + us.getTipoUsuario());
        }
    }

    public static void probarEstudiante() throws ParseException {
        System.out.println("--------------PRUEBA DE ESTUDIANTE--------------\n\n");
        //Creando al padre apoderado
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Persona apoderado = new Persona("78906281", "Jose", "Zegarra", "Berrocal", sdf.parse("12-14-1990"), "español", "católico", 'M', "Av. La Merced");
        PersonaDAO daoPersona = new PersonaMySQL();
        if (daoPersona.insertar(apoderado) == 0) {
            System.out.println("Error en registro\n");
        } else {
            System.out.println("Registrado exitosamente (apoderado): " + apoderado.getNombres() + " " + apoderado.getApellidoPaterno() + "\n");
        }
        //Creando al padre apoderado
        ArrayList<Estudiante> estudiantes;
        Estudiante estudiante = new Estudiante(apoderado, "-", "retirado", "-", "98452837", "Luis", "Zegarra", "Avalo", sdf.parse("12-14-2010"), "español", "católico", 'M', "Av. La Merced");
        EstudianteDAO daoEstudiante = new EstudianteMySQL();
        //INSERT DE USUARIOS
        if (daoEstudiante.insertar(estudiante) == 0) {
            System.out.println("Error en registro\n");
        } else {
            System.out.println("Registrado exitosamente: " + estudiante.getNombres() + " " + estudiante.getApellidoPaterno() + "\n");
        }
        String anterior = "Natalia";
        estudiante.setNombres(anterior);
        estudiante.setDni("96782640");
        if (daoEstudiante.insertar(estudiante) == 0) {
            System.out.println("Error en registro\n");
        } else {
            System.out.println("Registrado exitosamente: " + estudiante.getNombres() + " " + estudiante.getApellidoPaterno() + "\n");
        }
        //OBTENER POR ID Y MODIFICAR
        estudiante = daoEstudiante.obtenerPorId(estudiante.getIdPersona());
        estudiante.setNombres("Nataly"); //se le puede cambiar cualquier campo
        if (daoEstudiante.modificar(estudiante) == 0) {
            System.out.println("Error en modificar\n");
        } else {
            System.out.println("Modificado exitosamente: " + anterior + " -> " + estudiante.getNombres() + "\n");
        }
        //LISTAR TODOS
        estudiantes = daoEstudiante.listarTodos();
        System.out.println("\n\nLISTA DE ESTUDIANTES");
        for (Estudiante es : estudiantes) {
            System.out.println("DNI: " + es.getDni() + " - Nombre y Apellido: " + es.getNombres() + " " + es.getApellidoPaterno() + "\n");
        }
        //ELIMINAR
        if (daoEstudiante.eliminar(estudiante.getIdPersona()) == 0) {
            System.out.println("Error en eliminar\n");
        } else {
            System.out.println("\n\nEliminado exitosamente: " + estudiante.getDni() + "\n");
        }
        //LISTAR TODOS
        estudiantes = daoEstudiante.listarTodos();
        System.out.println("\n\nLISTA DE ESTUDIANTES");
        for (Estudiante es : estudiantes) {
            System.out.println("DNI: " + es.getDni() + " - Nombre y Apellido: " + es.getNombres() + " " + es.getApellidoPaterno() + "\n");
        }
    }
    
    public static void probarInstitucionEducativa(){
        System.out.println("--------------PRUEBA DE I.E--------------\n\n");
    }

    public static void probarCurso() {
        System.out.println("--------------PRUEBA DE CURSO--------------\n\n");
        ArrayList<Curso> cursos;
        Curso curso = new Curso("Matemática 1er grado");
        CursoDAO daoCurso = new CursoMySQL();
        //INSERT DE USUARIOS
        if (daoCurso.insertar(curso) == 0) {
            System.out.println("Error en registro\n");
        } else {
            System.out.println("Registrado exitosamente: ID: " + curso.getIdCurso() + " - Nombre: "+ curso.getNombre()+ "\n");
        }
        String anterior = "Fasica 4to grado";
        curso.setNombre(anterior);
        if (daoCurso.insertar(curso) == 0) {
            System.out.println("Error en registro\n");
        } else {
            System.out.println("Registrado exitosamente: ID: " + curso.getIdCurso() + " - Nombre: "+ curso.getNombre()+ "\n");
        }
        //OBTENER POR ID Y MODIFICAR
        curso = daoCurso.obtenerPorId(curso.getIdCurso());
        curso.setNombre("Física 4to grado");
        if (daoCurso.modificar(curso) == 0) {
            System.out.println("Error en modificar\n");
        } else {
            System.out.println("Modificado exitosamente: " + anterior + " -> " + curso.getNombre()+ "\n");
        }
        //LISTAR TODOS
        cursos = daoCurso.listarTodos();
        System.out.println("\n\nLISTA DE CURSOS");
        for (Curso cur : cursos) {
            System.out.println("ID: " + cur.getIdCurso()+ " - Nombre: " + cur.getNombre());
        }
        //ELIMINAR
        if (daoCurso.eliminar(curso.getIdCurso()) == 0) {
            System.out.println("Error en eliminar\n");
        } else {
            System.out.println("\n\nEliminado exitosamente: " + curso.getNombre()+ "\n");
        }
        //LISTAR TODOS
        System.out.println("\n\nLISTA DE USUARIOS");
        cursos = daoCurso.listarTodos();
        for (Curso cur : cursos) {
            System.out.println("ID: " + cur.getIdCurso()+ " - Nombre: " + cur.getNombre());
        }
    }
}
