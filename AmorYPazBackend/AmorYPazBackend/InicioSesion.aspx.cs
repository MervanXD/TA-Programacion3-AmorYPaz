using AmorYPazBackend.ServicioUser;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace AmorYPazBackend
{
    public partial class InicioSesion : System.Web.UI.Page
    {
        private UsuarioWSClient daoUsuario;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                // Verifica si hay un mensaje de error en la URL
                if (Request.QueryString["error"] != null)
                {
                    lblMensaje.Text = Request.QueryString["error"];
                    lblMensaje.Visible = true;
                    txtUsername.CssClass += " input-error";
                    txtContrasenha.CssClass += " input-error";
                }
            }
        }

        protected void btnInicioSesion_Click(object sender, EventArgs e)
        {
            daoUsuario = new UsuarioWSClient();
            usuario user = new usuario();
            user.username = txtUsername.Text;
            user.contrasena = txtContrasenha.Text;
            user = daoUsuario.verificarUsuario(user);


            if (user.director != null)
            {
                Session["NombreUsuario"] = user.director.nombres + " " + 
                    user.director.apellidoPaterno;
                Session["idDirector"] = user.director.idPersona;

                FormsAuthenticationTicket tkt;
                string cookiestr;
                HttpCookie ck;
                tkt = new FormsAuthenticationTicket(1, user.username, DateTime.Now,
                DateTime.Now.AddMinutes(30), true, "datos adicionales del usuario");
                cookiestr = FormsAuthentication.Encrypt(tkt);
                ck = new HttpCookie(FormsAuthentication.FormsCookieName, cookiestr);
                ck.Expires = tkt.Expiration; //esto genera que la cookie se quede guardada
                ck.Path = FormsAuthentication.FormsCookiePath;
                Response.Cookies.Add(ck);

                string strRedirect;
                strRedirect = Request["ReturnUrl"];
                if (strRedirect == null)
                    strRedirect = "MenuPrincipal.aspx";
                Response.Redirect(strRedirect, true);
            }
            else
            {
                Response.Redirect("InicioSesion.aspx?error=Usuario o contraseña incorrectos.", true);
            }
        }
    }
}