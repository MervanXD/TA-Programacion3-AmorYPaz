package pe.edu.pucp.softprog.program.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import pe.edu.pucp.softprog.rrhh.dao.EstudianteDAO;
import pe.edu.pucp.softprog.rrhh.dao.PersonaDAO;
import pe.edu.pucp.softprog.rrhh.dao.UsuarioDAO;
import pe.edu.pucp.softprog.rrhh.model.Estudiante;
import pe.edu.pucp.softprog.rrhh.model.Persona;
import pe.edu.pucp.softprog.gestusuarios.model.Usuario;
import pe.edu.pucp.softprog.rrhh.mysql.EstudianteMySQL;
import pe.edu.pucp.softprog.rrhh.mysql.PersonaMySQL;
import pe.edu.pucp.softprog.rrhh.mysql.UsuarioMySQL;

public class Principal {

    public static void main(String[] args) throws Exception {
//        probarUsuarios();
        probarInstitucionEducativa();
//        probarEstudiante();
//        probarCurso();
    }

    public static void probarUsuarios() {
        System.out.println("--------------PRUEBA DE USUARIOS--------------\n\n");
        ArrayList<Usuario> usuarios;
        Usuario usuario = new Usuario("admin1", "987656");
        UsuarioDAO daoUsuario = new UsuarioMySQL();
        //INSERT DE USUARIOS
        if (daoUsuario.insertar(usuario) == 0) {
            System.out.println("Error en registro\n");
        } else {
            System.out.println("Registrado exitosamente: " + usuario.getUsername() + "\n");
        }
        String anterior = "rauljp";
        usuario.setUsername(anterior);
        usuario.setContrasena("151515");
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
            System.out.println("ID: " + us.getIdUsuario() + " - Username: " + us.getUsername());
        }
        //ELIMINAR
        if (daoUsuario.eliminar(usuario.getIdUsuario()) == 0) {
            System.out.println("Error en eliminar\n");
        } else {
            System.out.println("Eliminado exitosamente: " + usuario.getUsername() + "\n");
        }
        //LISTAR TODOS
        System.out.println("\n\nLISTA DE USUARIOS");
        usuarios = daoUsuario.listarTodos();
        for (Usuario us : usuarios) {
            System.out.println("ID: " + us.getIdUsuario() + " - Username: " + us.getUsername());
        }
    }
<<<<<<< HEAD

    public static void probarInstitucionEducativa() throws ParseException {
//        System.out.println("--------------PRUEBA DE I.E--------------\n\n");
//        ArrayList<InstitucionEducativa> instituciones;
//        InstitucionEducativa institucion = new InstitucionEducativa("San Antonio de Padua", "Av. San Felipe 802", new ArrayList<>());
//        InstitucionEducativaDAO daoInstitucion = new InstitucionEducativaMySQL();
//        //INSERT DE 2 INSTITUCIONES EDUCATIVAS
//        if(daoInstitucion.insertar(institucion)==0) System.out.println("Error en registro\n");
//        else System.out.println("Registrado exitosamente: " + institucion.getNombre()+"\n");
//        String nombreNuevo  = "San Fransisco de Asis";
//        String direccionNuevo  = "Av. Girasoles Rosarios";
//        institucion.setNombre(nombreNuevo);
//        institucion.setDireccion(direccionNuevo);
//        if(daoInstitucion.insertar(institucion)==0) System.out.println("Error en registro\n");
//        else System.out.println("Registrado exitosamente: " + institucion.getNombre() +"\n");
//        
//        //OBTENER POR ID Y MODIFICAR
//        institucion = daoInstitucion.obtenerPorId(institucion.getIdSede());
//        System.out.println("Obtenido: "+ institucion.getIdSede() + "\n");
//        institucion.setNombre("Salesianos");
//        institucion.setDireccion("Av. Brasil");
////        if(daoInstitucion.modificar(institucion) == 0) System.out.println("Error en modificar\n");
////        else System.out.println("Modificado exitosamente: " + nombreNuevo + " -> " + institucion.getNombre()+"\n");
////No se puede modificar porque el procedure pide fid de otras dos clases que en los demas procedures no pide. 
//        
//        //LISTAR TODOS
//        instituciones = daoInstitucion.listarTodos();
//        System.out.println("\n\nLISTA DE INSTITUCIONES");
//        for(InstitucionEducativa ins : instituciones){
//            System.out.println("ID: " + ins.getIdSede()+ " - Nombre: " + ins.getNombre());
//        }
//        
//        //ELIMINAR
//        if(daoInstitucion.eliminar(institucion.getIdSede()) == 0) System.out.println("Error en eliminar\n");
//        else System.out.println("Eliminado exitosamente: " + institucion.getNombre()+ " con ID: "+ institucion.getIdSede()+ "\n");
//        //Falta tener un id de calendario para "eliminar" la institucion educativa
//         == == ==
//                = System.out.println("--------------PRUEBA DE I.E--------------\n\n");
//        ArrayList<InstitucionEducativa> instituciones;
//        InstitucionEducativa institucion = new InstitucionEducativa("San Antonio de Padua", "Av. San Felipe 802", new ArrayList<>());
//        InstitucionEducativaDAO daoInstitucion = new InstitucionEducativaMySQL();
//        //INSERT DE 2 INSTITUCIONES EDUCATIVAS
//        if (daoInstitucion.insertar(institucion) == 0) {
//            System.out.println("Error en registro\n");
//        } else {
//            System.out.println("Registrado exitosamente: " + institucion.getNombre() + "\n");
//        }
//        String nombreNuevo = "San Fransisco de Asis";
//        String direccionNuevo = "Av. Girasoles Rosarios";
//        institucion.setNombre(nombreNuevo);
//        institucion.setDireccion(direccionNuevo);
//        if (daoInstitucion.insertar(institucion) == 0) {
//            System.out.println("Error en registro\n");
//        } else {
//            System.out.println("Registrado exitosamente: " + institucion.getNombre() + "\n");
//        }
//
//        //OBTENER POR ID Y MODIFICAR
//        institucion = daoInstitucion.obtenerPorId(institucion.getIdSede());
//        System.out.println("Obtenido: " + institucion.getIdSede() + "\n");
//        institucion.setNombre("Salesianos");
//        institucion.setDireccion("Av. Brasil");
//        if (daoInstitucion.modificar(institucion) == 0) {
//            System.out.println("Error en modificar\n");
//        } else {
//            System.out.println("Modificado exitosamente: " + nombreNuevo + " -> " + institucion.getNombre() + "\n");
//        }
////No se puede modificar porque el procedure pide fid de otras dos clases que en los demas procedures no pide. 
//
//        //LISTAR TODOS
//        instituciones = daoInstitucion.listarTodos();
//        System.out.println("\n\nLISTA DE INSTITUCIONES");
//        for (InstitucionEducativa ins : instituciones) {
//            System.out.println("ID: " + ins.getIdSede() + " - Nombre: " + ins.getNombre());
//        }
//
////        ELIMINAR
//        if (daoInstitucion.eliminar(institucion.getIdSede()) == 0) {
//            System.out.println("Error en eliminar\n");
//        } else {
//            System.out.println("Eliminado exitosamente: " + institucion.getNombre() + " con ID: " + institucion.getIdSede() + "\n");
//        }
//        //Falta tener un id de calendario para "eliminar" la institucion educativa
=======
    
    public static void probarInstitucionEducativa() throws ParseException{

        System.out.println("--------------PRUEBA DE I.E--------------\n\n");
        ArrayList<InstitucionEducativa> instituciones;
        InstitucionEducativa institucion = new InstitucionEducativa("San Antonio de Padua", "Av. San Felipe 802");
        InstitucionEducativaDAO daoInstitucion = new InstitucionEducativaMySQL();
        //INSERT DE 2 INSTITUCIONES EDUCATIVAS
        if(daoInstitucion.insertar(institucion)==0) System.out.println("Error en registro\n");
        else System.out.println("Registrado exitosamente: " + institucion.getNombre()+"\n");
        String nombreNuevo  = "San Fransisco de Asis";
        String direccionNuevo  = "Av. Girasoles Rosarios";
        institucion.setNombre(nombreNuevo);
        institucion.setDireccion(direccionNuevo);
        if(daoInstitucion.insertar(institucion)==0) System.out.println("Error en registro\n");
        else System.out.println("Registrado exitosamente: " + institucion.getNombre() +"\n");
        
        //OBTENER POR ID Y MODIFICAR
        institucion = daoInstitucion.obtenerPorId(institucion.getIdInstitucion());
        System.out.println("Obtenido: "+ institucion.getIdInstitucion() + "\n");
        institucion.setNombre("Salesianos");
        institucion.setDireccion("Av. Brasil");
        if(daoInstitucion.modificar(institucion) == 0) {
            System.out.println("Error en modificar\n");
        }
        else System.out.println("Modificado exitosamente: " + nombreNuevo + " -> " + institucion.getNombre()+"\n");
        
        //LISTAR TODOS
        instituciones = daoInstitucion.listarTodos();
        System.out.println("\n\nLISTA DE INSTITUCIONES");
        for(InstitucionEducativa ins : instituciones){
            System.out.println("ID: " + ins.getIdInstitucion()+ " - Nombre: " + ins.getNombre());
        }
        
//        ELIMINAR
        if(daoInstitucion.eliminar(institucion.getIdInstitucion()) == 0) System.out.println("Error en eliminar\n");
        else System.out.println("Eliminado exitosamente: " + institucion.getNombre()+ " con ID: "+ institucion.getIdInstitucion()+ "\n");
        //Falta tener un id de calendario para "eliminar" la institucion educativa
>>>>>>> 914ac6326b59b0e5e8371f373f0e8252dba37469
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
            System.out.println("Eliminado exitosamente: " + estudiante.getDni() + "\n");
        }
        //LISTAR TODOS
        estudiantes = daoEstudiante.listarTodos();
        System.out.println("\n\nLISTA DE ESTUDIANTES");
        for (Estudiante es : estudiantes) {
            System.out.println("DNI: " + es.getDni() + " - Nombre y Apellido: " + es.getNombres() + " " + es.getApellidoPaterno() + "\n");
        }
    }

    public static void probarCurso() {
        System.out.println("--------------PRUEBA DE CURSO--------------\n\n");
    }
}
