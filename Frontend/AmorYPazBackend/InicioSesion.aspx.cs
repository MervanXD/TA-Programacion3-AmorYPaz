using AmorYPazBackend.ServicioWS;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.IO;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Xml.Linq;

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
            //string captchaResponse = Request.Form["g-recaptcha-response"];

            //if (string.IsNullOrEmpty(captchaResponse))
            //{
            //    lblCaptchaError.Visible = true; // Mostrar mensaje de error si el CAPTCHA no está marcado
            //    return;
            //}
            //Si está validado el CAPTCHA recién se procede a llamar al back del proyecto
            daoUsuario = new UsuarioWSClient();
            usuario user = new usuario();
            user.username = txtUsername.Text;
            user.contrasena = txtContrasenha.Text;
            user = daoUsuario.verificarUsuario(user);

            if (user.director != null)
            {
                if (true/*IsReCaptchValid()*/){
                    Session["NombreUsuario"] = user.director.nombres + " " +
                        user.director.apellidoPaterno;
                    Session["idDirector"] = user.director.idPersona;
                    Session["tipoUsuario"] = user.tipoUsuario.ToString();

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
                    {
                        if (user.tipoUsuario == tipoUsuario.DIRECTOR_UGEL)
                            strRedirect = "MenuPrincipal.aspx";
                        else if (user.tipoUsuario == tipoUsuario.DIRECTOR_IE)
                            strRedirect = "MenuPrincipalDirectores.aspx";
                        Session["idDirector"] = user.director.idPersona;

                    }
                    Response.Redirect(strRedirect, true);
                } 
                else
                {
                    ScriptManager.RegisterClientScriptBlock(this, typeof(string),
                    "MsgAlert", "alert('Validación Captcha incorrecta');window.location ='InicioSesion.aspx';", true);
                }
            }
            else
            {
                Response.Redirect("InicioSesion.aspx?error=Usuario o contraseña incorrectos.", true);
            }
        }

        //Método para validar Captcha
        public bool IsReCaptchValid()
        {
            var result = false;
            var captchaResponse = Request.Form["g-recaptcha-response"];
            var secretKey = ConfigurationManager.AppSettings["SecretKey"];
            var apiUrl = "https://www.google.com/recaptcha/api/siteverify?secret={0}&response={1}";
            var requestUri = string.Format(apiUrl, secretKey, captchaResponse);
            var request = (HttpWebRequest)WebRequest.Create(requestUri);

            using (WebResponse response = request.GetResponse())
            {
                using (StreamReader stream = new StreamReader(response.GetResponseStream()))
                {
                    JObject jResponse = JObject.Parse(stream.ReadToEnd());
                    var isSuccess = jResponse.Value<bool>("success");
                    result = (isSuccess) ? true : false;
                }
            }
            return result;
        }
    }
}