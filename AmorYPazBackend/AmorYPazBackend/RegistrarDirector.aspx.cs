using AmorYPazBackend.servicioDirectorWS;
//using AmorYPazBackend.ServicioDisco;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace AmorYPazBackend
{
    public partial class RegistrarDirector : System.Web.UI.Page
    {
        private director director;
        private DirectorWSClient daoDirector;
        private Estado estado;

        protected void Page_Init(object sender, EventArgs e)
        {
            string accion = Request.QueryString["accion"];

            if (accion == null)
            {
                lblTitulo.Text = "Registrar Director";
                director = new director();
                estado = Estado.Nuevo;
            }
            else if (accion == "modificar" && Session["director"] != null)
            {
                lblTitulo.Text = "Modificar Director";
                estado = Estado.Modificar;
                director = (director)Session["director"];
                txtDNI.Text = director.dni;
                txtNombre.Text = director.nombres;
                txtApellidoPaterno.Text = director.apellidoPaterno;
                txtApellidoMaterno.Text = director.apellidoMaterno;
                txtEmail.Text = director.email;
                txtDireccion.Text = director.direccion;
                txtLengua.Text = director.lengua;
                txtReligion.Text = director.religion;
                txtContrato.Text = director.tipoContrato;
                if (director.sexo.Equals('M')) rbMasculino.Checked = true;
                else rbFemenino.Checked = true;
                dtpFechaNacimiento.Value = director.fechaNacimiento.ToString("yyyy-MM-dd");
            }
        }
        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            if (txtNombre.Text.Trim() == "")
            {
                Response.Write("Debe ingresar un nombre...");
                return;
            }
            //Inicializamos las variables
            daoDirector = new DirectorWSClient();
            //Asignamos los valores
            director.dni = txtDNI.Text;
            director.nombres = txtNombre.Text;
            director.apellidoPaterno = txtApellidoPaterno.Text;
            director.apellidoMaterno = txtApellidoMaterno.Text;
            director.direccion = txtDireccion.Text;
            director.religion = txtReligion.Text;
            director.lengua = txtLengua.Text;
            if (rbMasculino.Checked)
                director.sexo = 'M';
            else
                director.sexo = 'F';
            director.fechaNacimiento =
                DateTime.Parse(dtpFechaNacimiento.Value);
            director.fechaNacimientoSpecified = true;
            director.tipoContrato = txtContrato.Text;
            director.email = txtEmail.Text;
            director.fechaNombramiento = DateTime.Now;
            director.fechaNombramientoSpecified = true;
            director.activo = true;
            //Dependiendo de la acción registramos o modificamos

            if (estado == Estado.Nuevo)
                daoDirector.insertarDirector(director);
            else if (estado == Estado.Modificar)
            {
                daoDirector.modificarDirector(director);
                Session["director"] = null;
            }

            //Redireccionamos a otra página
            Response.Redirect("AdministrarDirectores.aspx");
        }
        protected void btnCancelar_Click(object sender, EventArgs e)
        {
            Response.Redirect("AdministrarDirectores.aspx");
        }

    }

}