using AmorYPazBackend.ServicioIE;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.ServiceModel.Channels;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace AmorYPazBackend
{
    public partial class GestionarInstituciones : System.Web.UI.Page
    {
        private InstitucionEducativaWSClient daoIEducativa = new InstitucionEducativaWSClient();
        protected void Page_Load(object sender, EventArgs e) //page init quizas
        {
            try
            {
                if ((Session["idDirector"] != null))
                {
                    int idDirector = Int32.Parse(Session["idDirector"].ToString());
                    gvInstituciones.DataSource = daoIEducativa.listarPorNombreYUgel("", idDirector);
                }
                else {
                    gvInstituciones.DataSource = daoIEducativa.listarPorIdNombre("");
                }
                gvInstituciones.DataBind();
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.ToString());//cambiar por un warning emergente
            }
            
        }

        protected void lbBuscar_Click(object sender, EventArgs e)
        {
            string idNombre = txtNombre.Text;
            if (idNombre == null) idNombre = "";
            if ((Session["idDirector"] != null))
            {
                int idDirector = Int32.Parse(Session["idDirector"].ToString());
                gvInstituciones.DataSource = daoIEducativa.listarPorNombreYUgel(idNombre, idDirector);
            }
            else {
                gvInstituciones.DataSource = daoIEducativa.listarPorIdNombre(idNombre);
            }
            gvInstituciones.DataBind();
        }

        protected void lbRegistrar_Click(object sender, EventArgs e)
        {
            Response.Redirect("RegistrarInstitucion.aspx");
        }

        protected void lbVisualizar_click(object sender, EventArgs e)
        {
            int idInstitucionEdu = Int32.Parse(((LinkButton)sender).CommandArgument);
            institucionEducativa institucionEdu = daoIEducativa.obtenerPorId(idInstitucionEdu);
            Session["institucion"] = institucionEdu;
            Response.Redirect("RegistrarInstitucion.aspx?accion=visualizar");
        }

        protected void gvInstituciones_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvInstituciones.PageIndex = e.NewPageIndex;
            gvInstituciones.DataBind();
        }
    }
}