using AmorYPazBackend.ServicioWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace AmorYPazBackend
{
    public partial class RegistrarPlanDeEstudios : System.Web.UI.Page
    {
        private InstitucionEducativaWSClient daoInstitucion;

        private AnioAcademicoWSClient daoAnioAcademico;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                if(Session["idDirector"] != null)
                {
                    daoInstitucion = new InstitucionEducativaWSClient();
                    int idDirector = Int32.Parse(Session["idDirector"].ToString());
                    institucionEducativa ie = daoInstitucion.obtenerIEPorIdDirector(idDirector); //revisar si es necesario ServicioUGEL o si se creo un servicio de más
                    if (ie != null)
                    {
                        lblNombreInstitucion.Text = $"{ie.nombre}";
                    }
                }

            }
        }

        protected void lbGuardar_Click(object sender, EventArgs e)
        {

        }

        protected void lbCancelar_Click(object sender, EventArgs e)
        {
            Response.Redirect("GestionarPlanesEstudio.aspx");
        }
    }
}