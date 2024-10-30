package pe.edu.pucp.softprog.rrhh.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import pe.edu.pucp.softprog.gestusuarios.dao.UsuarioDAO;
import pe.edu.pucp.softprog.gestusuarios.model.Usuario;
import pe.edu.pucp.softprog.gestusuarios.mysql.UsuarioMySQL;

@WebService(serviceName = "UsuarioWS")
public class UsuarioWS {
    
    private UsuarioDAO daoUsuario;
    
    @WebMethod(operationName = "verificarUsuario")
    public Usuario verificarUsuario(@WebParam(name = "usuario") Usuario usuario) {
        Usuario nuevoUsuario = null;
        try{
            daoUsuario = new UsuarioMySQL();
            nuevoUsuario = daoUsuario.verificar(usuario);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return nuevoUsuario;
    }
    
    @WebMethod(operationName = "obtenerUgelDeUsuario")
    public int obtenerUgelDeDirector(@WebParam(name = "nombreCuentaUsuario") String nombreCuenta) {
        int id_ugel = 0;
        try{
            daoUsuario = new UsuarioMySQL();
            id_ugel = daoUsuario.obtenerUgelDeUsuario(nombreCuenta);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return id_ugel;
    }
   
}
