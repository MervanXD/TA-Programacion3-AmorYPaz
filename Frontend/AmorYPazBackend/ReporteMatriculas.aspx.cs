using AmorYPazBackend.ServicioWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace AmorYPazBackend
{
    public partial class ReporteMatriculas : System.Web.UI.Page
    {
        private ReporteMatriculaWSClient daoReporte;
        protected void Page_Load(object sender, EventArgs e)
        {
            daoReporte = new ReporteMatriculaWSClient();
            int idAnioAc = Int32.Parse(Request.QueryString["anio"]);
            int idInstitucion = Int32.Parse(Request.QueryString["institucion"]);
            int idGrado = Int32.Parse(Request.QueryString["grado"]);

            byte[] reporte = daoReporte.devolverReporte(idAnioAc, idInstitucion, idGrado);
            Response.Clear();
            Response.ContentType = "application/pdf";
            Response.AddHeader("Content-Disposition", "inline;filename=ReporteMatriculas.pdf");
            Response.BinaryWrite(reporte);
            Response.End();
        }
    }
}