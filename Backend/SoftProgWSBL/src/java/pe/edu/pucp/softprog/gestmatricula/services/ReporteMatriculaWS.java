/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.softprog.gestmatricula.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.awt.Image;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import pe.edu.pucp.softprog.config.DBManager;
import pe.edu.pucp.softprog.servlet.Reporte;

/**
 *
 * @author sfeli
 */
@WebService(serviceName = "ReporteMatriculaWS")
public class ReporteMatriculaWS {

    /**
     * This is a sample web service operation
     */
    
    public ReporteMatriculaWS(){
        System.setProperty("user.language", "es");
        System.setProperty("user.country", "PE");
        System.setProperty("user.timezone", "GMT-5");
    }
    
    @WebMethod(operationName = "devolverReporte")
    public byte[] devolverReporte(@WebParam(name = "idAnioAcademico") int idAnioAcademico, 
            @WebParam(name = "idInstitucion") int idInstitucion, 
            @WebParam(name = "idGrado") int idGrado) {
        byte[] reporte = null;
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(Reporte.class.getResource(
                    "/pe/edu/pucp/softprog/reportes/Reporte1.jasper"));
            //Parametros de entrada
            URL rutaLogo = Reporte.class.getResource("/pe/edu/pucp/softprog/img/logo.PNG");
            String rutaArchivoLogo = URLDecoder.decode(rutaLogo.getPath(),"UTF-8");
            Image logo = (new ImageIcon(rutaArchivoLogo).getImage());
            
            URL rutaImagenTop = Reporte.class.getResource("/pe/edu/pucp/softprog/img/bordesup.png");
            String rutaArchivoImagenTop = URLDecoder.decode(rutaImagenTop.getPath(),"UTF-8");
            Image ImagenTop = (new ImageIcon(rutaArchivoImagenTop).getImage());
            
             URL rutaSubreporteEmpleados = Reporte.class.getResource("/pe/edu/pucp/softprog/reportes/SubReporteRetirados.jasper");
            String rutaArchivoSubreporteEmpleados = URLDecoder.decode(rutaSubreporteEmpleados.getPath(), "UTF-8");
            
             URL rutaSubreporteGrafico = Reporte.class.getResource("/pe/edu/pucp/softprog/reportes/GraficoMatriculados.jasper");
            String rutaArchivoSubreporteGrafico = URLDecoder.decode(rutaSubreporteGrafico.getPath(), "UTF-8");
            
            HashMap parametros = new HashMap();
            parametros.put("id_grado_num", idGrado);
            parametros.put("id_anio_academico", idAnioAcademico);
            parametros.put("idInstitucionEdu", idInstitucion);
            parametros.put("rutaSubreporteGrafico", rutaArchivoSubreporteGrafico);
            parametros.put("rutaSubreporteRetirados", rutaArchivoSubreporteEmpleados);
            parametros.put("logo", logo);
            parametros.put("imagenTop", ImagenTop);
            //parametros.put();
            //Poblar el reporte con los datos
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, DBManager.getInstance().getConnection());
            reporte = JasperExportManager.exportReportToPdf(jp);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return reporte;
    }
}
