using AmorYPazBackend.ServicioWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace AmorYPazBackend
{
    public partial class ReporteGeneral : System.Web.UI.Page
    {
        private GradoReporteWSClient daoReporte;
        protected void Page_Load(object sender, EventArgs e)
        {
            daoReporte = new GradoReporteWSClient();
            int idInstitucion = Int32.Parse(Request.QueryString["institucion"]);
            string nombreIE = Request.QueryString["grado"];

            byte[] reporte = daoReporte.devolverReporteTodosGrados(idInstitucion, nombreIE);
            Response.Clear();
            Response.ContentType = "application/pdf";
            Response.AddHeader("Content-Disposition", "inline;filename=ReporteMatriculas.pdf");
            //Response.BinaryWrite(reporte);
            Response.End();
        }
    }
}