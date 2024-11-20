package pe.edu.pucp.softprog.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.Image;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import pe.edu.pucp.softprog.config.DBManager;

public class Reporte extends HttpServlet {
    public Reporte(){
        System.setProperty("user.language", "es");
        System.setProperty("user.country", "PE");
        System.setProperty("user.timezone", "GMT-5");
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        //Objeto JasperReport
        JasperReport jr = (JasperReport) 
                JRLoader.loadObject(Reporte.class.getResource(
                        "/pe/edu/pucp/softprog/reportes/Reporte1.jasper"));
        
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
        parametros.put("id_grado_num", 1);
        parametros.put("id_anio_academico", 2);
        parametros.put("idInstitucionEdu", 13);
        parametros.put("rutaSubreporteGrafico", rutaArchivoSubreporteGrafico);
        parametros.put("rutaSubreporteRetirados", rutaArchivoSubreporteEmpleados);
        parametros.put("logo", logo);
        parametros.put("imagenTop", ImagenTop);
       
        //Poblar el reporte con los datos
        JasperPrint jp = JasperFillManager.fillReport
            (jr, parametros, DBManager.getInstance().getConnection());
        JasperExportManager.exportReportToPdfStream(jp, response.getOutputStream());
        
//        DBManager.getInstance().
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
