package pe.edu.pucp.softprog.rrhh.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import pe.edu.pucp.softprog.gestusuarios.dao.UsuarioDAO;
import pe.edu.pucp.softprog.gestusuarios.model.Usuario;
import pe.edu.pucp.softprog.gestusuarios.mysql.UsuarioMySQL;

@WebService(serviceName = "UsuarioWS", targetNamespace = "services.softprog.pucp.edu.pe")
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
    public int obtenerIDUgel(@WebParam(name = "nombreCuentaUsuario") String nombreCuenta) {
        int id_ugel = 0;
        try{
            daoUsuario = new UsuarioMySQL();
            id_ugel = daoUsuario.obtenerUgelDeUsuario(nombreCuenta);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return id_ugel;
    }
    
    @WebMethod(operationName = "obtenerTipoUsuario")
    public String obtenerTipoUsuario(@WebParam(name = "idUsuario") int id) {
        Usuario nuevoUsuario=null;
        try{
            daoUsuario = new UsuarioMySQL();
            nuevoUsuario = daoUsuario.obtenerPorId(id);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        if(nuevoUsuario!=null) return nuevoUsuario.getTipoUsuario().toString();
        return null;
    }
    
    @WebMethod(operationName = "obtenerIEDeUsuario")
    public int obtenerIDIE(@WebParam(name = "idDirector") int id) {
        int id_IE = 0;
        try{
            daoUsuario = new UsuarioMySQL();
            id_IE = daoUsuario.obtenerIEDeUsuario(id);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return id_IE;
    }
    
    @WebMethod(operationName = "obtenerPorIdDirector")
    public Usuario obtenerPorIdDirector(@WebParam(name = "idDirector") int idDirector) {
        Usuario nuevoUsuario = null;
        try{
            daoUsuario = new UsuarioMySQL();
            nuevoUsuario = daoUsuario.obtenerPorIdDirector(idDirector);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return nuevoUsuario;
    } 

   
}
