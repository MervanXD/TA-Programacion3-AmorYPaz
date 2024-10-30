using AmorYPazBackend.servicioDirectorWS;
using AmorYPazBackend.ServicioIE;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using static System.Net.Mime.MediaTypeNames;

namespace AmorYPazBackend
{
    public partial class RegistrarInstitucion : System.Web.UI.Page
    {
        private DirectorWSClient daoDirector = new DirectorWSClient();
        private InstitucionEducativaWSClient daoInstitucion = new InstitucionEducativaWSClient();
        private institucionEducativa institucionEdu;
        protected void Page_Init(object sender, EventArgs e)
        {
            var directores = daoDirector.listarDirectoresTodas();

            // Crea una lista anónima combinando nombres y apellidoPaterno
            var directoresConNombreCompleto = directores.Select(d => new {
                NombreCompleto = d.nombres + " " + d.apellidoPaterno + " " + d.apellidoMaterno,
                d.idPersona
            }).ToList();

            // Asigna la lista combinada al DropDownList
            ddlDirector.DataSource = directoresConNombreCompleto;
            ddlDirector.DataTextField = "NombreCompleto";
            ddlDirector.DataValueField = "idPersona";
            ddlDirector.DataBind();

            //Verificamos si es una acción de modificación
            string accion = Request.QueryString["accion"];
            if (accion == null)
            {
                lblDirector.Text = "Asignar Director:";

                //lblTitulo.Text = "UGEL04 - Añadir Institución";
                lblTitulo.Text = "Añadir Institución";
                institucionEdu = new institucionEducativa();
                //estado = Estado.Nuevo;
                if (!IsPostBack)
                {
                    Session["institucion"] = null;
                }
            }
            else if (accion == "visualizar" && accion != null && Session["institucion"] != null)
            {
                lblDirector.Text = "Director Asignado:";
                institucionEdu = (institucionEducativa)Session["institucion"];
                lblTitulo.Text = institucionEdu.ugel.codigo + " - Modificar Institución";
                //estado = Estado.Modificar;
                txtNombre.Text = institucionEdu.nombre;
                txtDireccion.Text = institucionEdu.direccion;
                string nombre_director = institucionEdu.director.nombres.ToString() +
                    institucionEdu.director.apellidoPaterno.ToString() +
                    institucionEdu.director.apellidoMaterno.ToString();
                ddlDirector.SelectedValue = nombre_director;
                txtTelefono.Text = institucionEdu.telefono;
                txtEmail.Text = institucionEdu.correo_electronico;

                Deshabilitar_Componentes();
                //falta la imagen!


            }

        }

        public void Deshabilitar_Componentes()
        {
            txtNombre.Enabled = false;
            txtDireccion.Enabled = false;
            txtTelefono.Enabled = false;
            txtEmail.Enabled = false;
            lbGuardar.Visible = false;
            ddlDirector.Enabled = false;
            fuLogo.Enabled = false;
        }

        protected void lbGuardar_Click(object sender, EventArgs e)
        {

            //Asignamos los valores
            institucionEdu.nombre = txtNombre.Text;
            institucionEdu.direccion = txtDireccion.Text;
            institucionEdu.correo_electronico = txtEmail.Text;
            institucionEdu.telefono = txtTelefono.Text;
            institucionEdu.director = new ServicioIE.director();
            institucionEdu.director.idPersona = Int32.Parse(ddlDirector.SelectedValue);
            //faltan los siguientes datos

            //if (estado == Estado.Nuevo)
            //    daoEmpleado.insertar(empleado);
            //else if (estado == Estado.Modificar)
            //    daoEmpleado.modificar(empleado);

            daoInstitucion.insertarInstitucion(institucionEdu);

            Response.Redirect("ListarEmpleados.aspx");
        }

        protected void lbCancelar_Click(object sender, EventArgs e)
        {

            Response.Redirect("GestionarInstituciones.aspx");
        }
    }
}