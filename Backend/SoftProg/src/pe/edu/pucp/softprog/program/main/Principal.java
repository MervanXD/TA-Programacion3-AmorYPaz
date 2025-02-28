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
import pe.edu.pucp.softprog.infraestructura.dao.InstitucionEducativaDAO;
import pe.edu.pucp.softprog.infraestructura.model.InstitucionEducativa;
import pe.edu.pucp.softprog.infraestructura.mysql.InstitucionEducativaMySQL;

public class Principal {

    public static void main(String[] args) throws Exception {
        //probarUsuarios();
//        probarInstitucionEducativa();
//        probarEstudiante();
//        probarCurso();
    }
    
    public static void probarPlanDeEstudios(){
        
    }
    public static void probarUsuarios() {
//        System.out.println("--------------PRUEBA DE USUARIOS--------------\n\n");
//        ArrayList<Usuario> usuarios;
//        Usuario usuario = new Usuario("master2", "987658", TipoUsuario.DIRECTOR_IE);
//        UsuarioDAO daoUsuario = new UsuarioMySQL();
//        //INSERT DE USUARIOS
//        if (daoUsuario.insertar(usuario) == 0) {
//            System.out.println("Error en registro\n");
//        } else {
//            System.out.println("Registrado exitosamente: " + usuario.getUsername() + "\n");
//        }
//        String anterior = "admin";
//        usuario.setUsername(anterior);
//        usuario.setContrasena("153334");
//        usuario.setTipoUsuario(TipoUsuario.DIRECTOR_UGEL);
//        if (daoUsuario.insertar(usuario) == 0) {
//            System.out.println("Error en registro\n");
//        } else {
//            System.out.println("Registrado exitosamente: " + usuario.getUsername() + "\n");
//        }
//        //OBTENER POR ID Y MODIFICAR
//        usuario = daoUsuario.obtenerPorId(usuario.getIdUsuario());
//        usuario.setUsername("adminroot3");
//        if (daoUsuario.modificar(usuario) == 0) {
//            System.out.println("Error en modificar\n");
//        } else {
//            System.out.println("Modificado exitosamente: " + anterior + " -> " + usuario.getUsername() + "\n");
//        }
//        //LISTAR TODOS
//        usuarios = daoUsuario.listarTodos();
//        System.out.println("\n\nLISTA DE USUARIOS");
//        for (Usuario us : usuarios) {
//            System.out.println("ID: " + us.getIdUsuario() + " - Username: " + us.getUsername() + " - TipoUser: " + us.getTipoUsuario() + " " + us.getContrasena());
//        }
//        //ELIMINAR
//        if (daoUsuario.eliminar(usuario.getIdUsuario()) == 0) {
//            System.out.println("Error en eliminar\n");
//        } else {
//            System.out.println("\n\nEliminado exitosamente: " + usuario.getUsername() + "\n");
//        }
//        //LISTAR TODOS
//        System.out.println("\n\nLISTA DE USUARIOS");
//        usuarios = daoUsuario.listarTodos();
//        for (Usuario us : usuarios) {
//            System.out.println("ID: " + us.getIdUsuario() + " - Username: " + us.getUsername() + " - TipoUser: " + us.getTipoUsuario());
//        }
//        System.out.println("\n\n");
//    }
//
//    public static void probarEstudiante() throws ParseException {
//        System.out.println("--------------PRUEBA DE ESTUDIANTE--------------\n\n");
//        //Creando al padre apoderado
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//        Persona apoderado = new Persona("78906281", "Jose", "Zegarra", "Berrocal", sdf.parse("12-14-1990"), "español", "católico", 'M', "Av. La Merced");
//        PersonaDAO daoPersona = new PersonaMySQL();
//        if (daoPersona.insertar(apoderado) == 0) {
//            System.out.println("Error en registro\n");
//        } else {
//            System.out.println("Registrado exitosamente (apoderado): " + apoderado.getNombres() + " " + apoderado.getApellidoPaterno() + "\n");
//        }
//        //Creando al padre apoderado
//        ArrayList<Estudiante> estudiantes;
//        Estudiante estudiante = new Estudiante(apoderado, "-", "retirado", "-", "98452837", "Luis", "Zegarra", "Avalo", sdf.parse("12-14-2010"), "español", "católico", 'M', "Av. La Merced");
//        EstudianteDAO daoEstudiante = new EstudianteMySQL();
//        //INSERT DE USUARIOS
//        if (daoEstudiante.insertar(estudiante) == 0) {
//            System.out.println("Error en registro\n");
//        } else {
//            System.out.println("Registrado exitosamente: " + estudiante.getNombres() + " " + estudiante.getApellidoPaterno() + "\n");
//        }
//        String anterior = "Natalia";
//        estudiante.setNombres(anterior);
//        estudiante.setDni("96782640");
//        if (daoEstudiante.insertar(estudiante) == 0) {
//            System.out.println("Error en registro\n");
//        } else {
//            System.out.println("Registrado exitosamente: " + estudiante.getNombres() + " " + estudiante.getApellidoPaterno() + "\n");
//        }
//        //OBTENER POR ID Y MODIFICAR
//        estudiante = daoEstudiante.obtenerPorId(estudiante.getIdPersona());
//        estudiante.setNombres("Nataly"); //se le puede cambiar cualquier campo
//        if (daoEstudiante.modificar(estudiante) == 0) {
//            System.out.println("Error en modificar\n");
//        } else {
//            System.out.println("Modificado exitosamente: " + anterior + " -> " + estudiante.getNombres() + "\n");
//        }
//        //LISTAR TODOS
//        estudiantes = daoEstudiante.listarTodos();
//        System.out.println("\n\nLISTA DE ESTUDIANTES");
//        for (Estudiante es : estudiantes) {
//            System.out.println("DNI: " + es.getDni() + " - Nombre y Apellido: " + es.getNombres() + " " + es.getApellidoPaterno() + "\n");
//        }
//        //ELIMINAR
//        if (daoEstudiante.eliminar(estudiante.getIdPersona()) == 0) {
//            System.out.println("Error en eliminar\n");
//        } else {
//            System.out.println("\n\nEliminado exitosamente: " + estudiante.getDni() + "\n");
//        }
//        //LISTAR TODOS
//        estudiantes = daoEstudiante.listarTodos();
//        System.out.println("\n\nLISTA DE ESTUDIANTES");
//        for (Estudiante es : estudiantes) {
//            System.out.println("DNI: " + es.getDni() + " - Nombre y Apellido: " + es.getNombres() + " " + es.getApellidoPaterno() + "\n");
//        }
//        System.out.println("\n\n");
//    }
//    
//    public static void probarInstitucionEducativa(){
//        System.out.println("--------------PRUEBA DE I.E--------------\n\n");
//        ArrayList<InstitucionEducativa> institucionesEducativas;
//        InstitucionEducativa institucionEducativa = new InstitucionEducativa("Colegio Marcelino Champagnat", "Av. Surco 1290");
//        InstitucionEducativaDAO daoInst = new InstitucionEducativaMySQL();
//        //INSERT DE USUARIOS
//        if (daoInst.insertar(institucionEducativa) == 0) {
//            System.out.println("Error en registro\n");
//        } else {
//            System.out.println("Registrado exitosamente: " + institucionEducativa.getNombre()+ " - " + institucionEducativa.getDireccion() + "\n");
//        }
//        String anterior = "Roosevlete";
//        institucionEducativa.setNombre(anterior);
//        institucionEducativa.setDireccion("Av. La Palmeras 325 Camacho");
//        if (daoInst.insertar(institucionEducativa) == 0) {
//            System.out.println("Error en registro\n");
//        } else {
//            System.out.println("Registrado exitosamente: " + institucionEducativa.getNombre()+ " - " + institucionEducativa.getDireccion() + "\n");
//        }
//        //OBTENER POR ID Y MODIFICAR
//        institucionEducativa = daoInst.obtenerPorId(institucionEducativa.getIdInstitucion());
//        institucionEducativa.setNombre("Roosevelt");
//        if (daoInst.modificar(institucionEducativa) == 0) {
//            System.out.println("Error en modificar\n");
//        } else {
//            System.out.println("Modificado exitosamente: " + anterior + " -> " + institucionEducativa.getNombre()+ "\n");
//        }
//        //LISTAR TODOS
//        institucionesEducativas = daoInst.listarTodos();
//        System.out.println("\n\nLISTA DE INSTITUCIONES");
//        for (InstitucionEducativa ie : institucionesEducativas) {
//            System.out.println("ID: " + ie.getIdInstitucion()+ " - Nombre Colegio: " + ie.getNombre()+ " - Direccion: " + ie.getDireccion());
//        }
//        //ELIMINAR
//        if (daoInst.eliminar(institucionEducativa.getIdInstitucion()) == 0) {
//            System.out.println("Error en eliminar\n");
//        } else {
//            System.out.println("\n\nEliminado exitosamente: " + institucionEducativa.getNombre()+ "\n");
//        }
//        //LISTAR TODOS
//        institucionesEducativas = daoInst.listarTodos();
//        System.out.println("\n\nLISTA DE INSTITUCIONES");
//        for (InstitucionEducativa ie : institucionesEducativas) {
//            System.out.println("ID: " + ie.getIdInstitucion()+ " - Nombre Colegio: " + ie.getNombre()+ " - Direccion: " + ie.getDireccion());
//        }
//        System.out.println("\n\n");
//    }
//
//    public static void probarCurso() {
//        System.out.println("--------------PRUEBA DE CURSO--------------\n\n");
//        ArrayList<Curso> cursos;
//        Curso curso = new Curso("Matematica 1er grado");
//        CursoDAO daoCurso = new CursoMySQL();
//        //INSERT DE USUARIOS
//        if (daoCurso.insertar(curso) == 0) {
//            System.out.println("Error en registro\n");
//        } else {
//            System.out.println("Registrado exitosamente: ID: " + curso.getIdCurso() + " - Nombre: "+ curso.getNombre()+ "\n");
//        }
//        String anterior = "Fasica 4to grado";
//        curso.setNombre(anterior);
//        if (daoCurso.insertar(curso) == 0) {
//            System.out.println("Error en registro\n");
//        } else {
//            System.out.println("Registrado exitosamente: ID: " + curso.getIdCurso() + " - Nombre: "+ curso.getNombre()+ "\n");
//        }
//        //OBTENER POR ID Y MODIFICAR
//        curso = daoCurso.obtenerPorId(curso.getIdCurso());
//        curso.setNombre("Fisica 4to grado");
//        if (daoCurso.modificar(curso) == 0) {
//            System.out.println("Error en modificar\n");
//        } else {
//            System.out.println("Modificado exitosamente: " + anterior + " -> " + curso.getNombre()+ "\n");
//        }
//        //LISTAR TODOS
//        cursos = daoCurso.listarTodos();
//        System.out.println("\n\nLISTA DE CURSOS");
//        for (Curso cur : cursos) {
//            System.out.println("ID: " + cur.getIdCurso()+ " - Nombre: " + cur.getNombre());
//        }
//        //ELIMINAR
//        if (daoCurso.eliminar(curso.getIdCurso()) == 0) {
//            System.out.println("Error en eliminar\n");
//        } else {
//            System.out.println("\n\nEliminado exitosamente: " + curso.getNombre()+ "\n");
//        }
//        //LISTAR TODOS
//        System.out.println("\n\nLISTA DE CURSOS");
//        cursos = daoCurso.listarTodos();
//        for (Curso cur : cursos) {
//            System.out.println("ID: " + cur.getIdCurso()+ " - Nombre: " + cur.getNombre());
//        }
//        System.out.println("\n\n");
    }
}
