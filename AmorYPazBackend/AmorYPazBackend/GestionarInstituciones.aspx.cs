using AmorYPazBackend.Servicio;
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
            gvInstituciones.DataSource = daoIEducativa.listarPorIdNombre("");
            gvInstituciones.DataBind();
        }

        protected void lbBuscar_Click(object sender, EventArgs e)
        {
            string idNombre = txtNombre.Text;
            gvInstituciones.DataSource = daoIEducativa.listarPorIdNombre(idNombre);
            gvInstituciones.DataBind();
        }
    }
}