using AmorYPazBackend.ServicioWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace AmorYPazBackend
{
    public partial class PerfilUsuario : System.Web.UI.Page
    {
        private UsuarioWSClient daoUsuario;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["tipoUsuario"] != null && Session["idDirector"] != null && Session["NombreUsuario"] != null)
            {
                string tipoUsuario = Session["tipoUsuario"].ToString();
                if (tipoUsuario == "DIRECTOR_IE") lblTipoUsuario.Text = "DIRECTOR I.E.";
                else lblTipoUsuario.Text = "DIRECTOR UGEL";
                int idDirector = Int32.Parse(Session["idDirector"].ToString());
                lblNombre.Text = Session["NombreUsuario"].ToString();
            }
        }

        protected void btnSave_Click(object sender, EventArgs e)
        {
            string script = "";
            if (txtPassAct.Text == "" || txtPassNew.Text == "" || txtPassCon.Text == "")
                script = "mostrarModal('Todos los campos son necesarios', 'PerfilUsuario.aspx');";
            else if (txtPassCon.Text != txtPassNew.Text)
                script = "mostrarModal('La nueva contraseña no coincide con su verificación', 'PerfilUsuario.aspx');";
            else
            {
                daoUsuario = new UsuarioWSClient();
                int idDirector = Int32.Parse(Session["idDirector"].ToString());
                usuario user = daoUsuario.obtenerPorIdDirector(idDirector);
                user.contrasena = txtPassAct.Text;
                user = daoUsuario.verificarUsuario(user);
                if (user.director == null) script = "mostrarModal('La contraseña anterior no coincide con el usuario', 'PerfilUsuario.aspx');";
                else
                {
                    user.contrasena = txtPassNew.Text;
                    //user.username = txtUsername.Text;
                    daoUsuario.modificar(user);
                    script = "mostrarModal('Se ha actualizado la información del usuario correctamente', 'PerfilUsuario.aspx');";
                }
            }
            ScriptManager.RegisterStartupScript(this, GetType(), "modal", script, true);
        }
    }
}