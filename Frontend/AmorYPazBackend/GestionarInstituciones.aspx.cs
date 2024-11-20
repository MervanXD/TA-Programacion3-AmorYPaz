using AmorYPazBackend.ServicioWS;
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
        private BindingList<institucionEducativa> instituciones;
        //private institucionEducativa institucion;
        protected void Page_Load(object sender, EventArgs e) //page init quizas
        {
            try
            {
                if ((Session["idUGEL"] != null))
                {
                    int idUGEL = Int32.Parse(Session["idUGEL"].ToString());
                    instituciones = new BindingList<institucionEducativa>(daoIEducativa.listarPorNombreYUgel("", idUGEL));
                    gvInstituciones.DataSource = instituciones;
                    gvInstituciones.DataBind();
                }
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
            if ((Session["idUGEL"] != null))
            {
                int idUGEL = Int32.Parse(Session["idUGEL"].ToString());
                gvInstituciones.DataSource = daoIEducativa.listarPorNombreYUgel(idNombre, idUGEL);
                gvInstituciones.DataBind();
            }
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

        protected void lbModificar_click(object sender, EventArgs e)
        {
            int idInstitucion = Int32.Parse(((LinkButton)sender).CommandArgument);
            institucionEducativa institucion = daoIEducativa.obtenerPorId(idInstitucion);
            Session["institucion"] = institucion;
            Response.Redirect("RegistrarInstitucion.aspx?accion=modificar");
        }
    }
}