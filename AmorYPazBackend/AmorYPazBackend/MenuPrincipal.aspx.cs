using AmorYPazBackend.ServicioWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace AmorYPazBackend
{
    public partial class MenuPrincipal : System.Web.UI.Page
    {
        private UGELWSClient daoUGEL;
        protected void Page_Init(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                if (Session["idDirector"] != null)
                {
                    daoUGEL = new UGELWSClient();
                    int idDirector = Int32.Parse(Session["idDirector"].ToString());
                    ugel ug = daoUGEL.obtenerUGELPorIdDirector(idDirector); //revisar si es necesario ServicioUGEL o si se creo un servicio de más
                    if (ug != null) {
                        lblInfoUGEL.Text = $"{ug.codigo} - {ug.distrito}";
                    }
                }
                else
                {
                    Response.Redirect("InicioSesion.aspx");
                }
            }
        }
    }
}