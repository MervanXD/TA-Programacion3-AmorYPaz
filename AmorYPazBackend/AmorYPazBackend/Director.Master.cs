using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace AmorYPazBackend
{
    public partial class Director : System.Web.UI.MasterPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                if (Session["NombreUsuario"] != null)
                {
                    string nombreDirector = Session["NombreUsuario"].ToString();
                    litNombreUsuario.Text = nombreDirector;
                }
                else
                {
                    Response.Redirect("InicioSesion.aspx");
                }
            }
        }

        protected void lbCerrarSesion_Click(object sender, EventArgs e)
        {
            string script = "showModalConfirmation();";
            ScriptManager.RegisterStartupScript(this, GetType(), "ShowModalConfirmation", script, true);
        }
    }
}