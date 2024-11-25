using AmorYPazBackend.ServicioWS;
using System;
using System.Collections.Generic;
using System.Drawing.Drawing2D;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace AmorYPazBackend
{
    public partial class PerfilUsuario : System.Web.UI.Page
    {
        private UsuarioWSClient daoUsuario;
        private DirectorWSClient daoDirector;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                if (Session["tipoUsuario"] != null && Session["idDirector"] != null && Session["NombreUsuario"] != null)
                {
                    string tipoUsuario = Session["tipoUsuario"].ToString();
                    if (tipoUsuario == "DIRECTOR_IE") lblTipoUsuario.Text = "DIRECTOR I.E.";
                    else lblTipoUsuario.Text = "DIRECTOR UGEL";
                    int idDirector = Int32.Parse(Session["idDirector"].ToString());
                    daoDirector = new DirectorWSClient();
                    director dir = daoDirector.obtenerDirectorPorId(idDirector);
                    lblNombre.Text = dir.nombres + " " + dir.apellidoPaterno;
                    cargarDatosPerfil(dir);
                    ViewState["direcPerfil"] = dir;
                }
            }
        }

        private void cargarDatosPerfil(director dir)
        {
            txtDNI.Enabled = false;
            txtDNI.Text = dir.dni;
            txtNombre.Text = dir.nombres;
            txtApellidoPaterno.Text = dir.apellidoPaterno;
            txtApellidoMaterno.Text = dir.apellidoMaterno;
            txtDireccion.Text = dir.direccion;
            txtEmail.Text = dir.email;
            txtReligion.Text = dir.religion;
            txtLengua.Text = dir.lengua;
            if (dir.sexo == 'M') rbMasculino.Checked = true;
            else rbFemenino.Checked = true;
            dtpFechaNacimiento.Value = dir.fechaNacimiento.ToString("yyyy-MM-dd");
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
                    script = "mostrarModal('Se ha actualizado la contraseña correctamente', 'PerfilUsuario.aspx');";
                }
            }
            ScriptManager.RegisterStartupScript(this, GetType(), "modal", script, true);
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            director director = (director)ViewState["direcPerfil"];
            director.dni = txtDNI.Text;
            director.nombres = txtNombre.Text;
            director.apellidoPaterno = txtApellidoPaterno.Text;
            director.apellidoMaterno = txtApellidoMaterno.Text;
            director.direccion = txtDireccion.Text;
            director.religion = txtReligion.Text;
            director.lengua = txtLengua.Text;
            if (rbMasculino.Checked) director.sexo = 'M';
            else director.sexo = 'F';
            director.fechaNacimiento =
                DateTime.Parse(dtpFechaNacimiento.Value);
            director.fechaNacimientoSpecified = true;
            director.email = txtEmail.Text;
            director.activo = true;
            daoDirector = new DirectorWSClient();
            daoDirector.modificarDirector(director);
            string script = "mostrarModal('Se ha actualizado la información del usuario correctamente', 'PerfilUsuario.aspx');";
            ScriptManager.RegisterStartupScript(this, GetType(), "modal", script, true);
            Session["NombreUsuario"] = director.nombres + " " + director.apellidoPaterno;
        }
    }
}