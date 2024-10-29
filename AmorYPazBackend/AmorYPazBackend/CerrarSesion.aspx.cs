using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace AmorYPazBackend
{
    public partial class CerrarSesion : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            // Elimina la cookie de autenticación
            FormsAuthentication.SignOut();
            // Redirigir a la página de inicio de sesión
            Response.Redirect("InicioSesion.aspx");
        }
    }
}