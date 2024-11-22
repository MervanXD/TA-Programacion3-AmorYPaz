/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.softprog.infraest.services;

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
import java.time.LocalDate;

/**
 *
 * @author MARTIN
 */
@WebService(serviceName = "GradoReporteWS")
public class GradoReporteWS {
    
    public GradoReporteWS(){
        System.setProperty("user.language", "es");
        System.setProperty("user.country", "PE");
        System.setProperty("user.timezone", "GMT-5");
    }
    @WebMethod(operationName = "devolverReporteTodosGrados")
    public byte[] devolverReporteTodosGrados(@WebParam(name = "idInstitucion") int idInstitucion, 
            @WebParam(name = "nombreIE") String nombreIE
            ) {
        byte[] reporte = null;
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(Reporte.class.getResource(
                    "/pe/edu/pucp/softprog/reportes/ReporteGeneralGrados.jasper"));
            //Parametros de entrada
            URL rutaLogo = Reporte.class.getResource("/pe/edu/pucp/softprog/img/logo.PNG");
            String rutaArchivoLogo = URLDecoder.decode(rutaLogo.getPath(),"UTF-8");
            Image logo = (new ImageIcon(rutaArchivoLogo).getImage());
            
            URL rutaImagenTop = Reporte.class.getResource("/pe/edu/pucp/softprog/img/bordesup.png");
            String rutaArchivoImagenTop = URLDecoder.decode(rutaImagenTop.getPath(),"UTF-8");
            Image ImagenTop = (new ImageIcon(rutaArchivoImagenTop).getImage());
            
             URL rutaSubreporteGraficoBarras = Reporte.class.getResource("/pe/edu/pucp/softprog/reportes/GraficoBarras.jasper");
            String rutaArchivoSubreporteGraficoBarras = URLDecoder.decode(rutaSubreporteGraficoBarras.getPath(), "UTF-8");
           
            int year = LocalDate.now().getYear();
            
            HashMap parametros = new HashMap();
            parametros.put("nombreIE", nombreIE);
            parametros.put("id_IE", idInstitucion);
            parametros.put("rutasubreporteGraficoBarras", rutaArchivoSubreporteGraficoBarras);
            parametros.put("Logo", logo);
            parametros.put("Encabezado", ImagenTop);
            parametros.put("anho", year);
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
