using AmorYPazBackend.ServicioWS;
using System;
using System.Collections.Generic;
using System.EnterpriseServices;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace AmorYPazBackend
{
    public partial class RegistrarAlumno : System.Web.UI.Page
    {
        private estudiante estudiante;
        private EstudianteWSClient daoEstudiante;
        private Estado estado;
        protected void Page_Init(object sender, EventArgs e)
        {
            string accion = Request.QueryString["accion"];
            if (accion == null)
            {
                lblTitulo.Text = "Registrar Alumno";
                estudiante = new estudiante();
                estado = Estado.Nuevo;
            }
            else if (accion == "modificar" && Session["estudiante"] != null)
            {
                lblTitulo.Text = "Modificar Alumno";
                estado = Estado.Modificar;
                estudiante = (estudiante)Session["estudiante"];
                txtDNI.Text = estudiante.dni;
                txtNombre.Text = estudiante.nombres;
                txtApellidoPaterno.Text = estudiante.apellidoPaterno;
                txtApellidoMaterno.Text = estudiante.apellidoMaterno;
                txtDireccion.Text = estudiante.direccion;
                txtLengua.Text = estudiante.lengua;
                txtReligion.Text = estudiante.religion;
                if (estudiante.sexo.Equals('M')) rbMasculino.Checked = true;
                else rbFemenino.Checked = true;
                dtpFechaNacimiento.Value = estudiante.fechaNacimiento.ToString("yyyy-MM-dd");
                txtCondicionesMedicas.Text = estudiante.condicionesMedicas;
                txtDiscapacidad.Text = estudiante.discapacidades;
            }
        }
        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            if (Page.IsValid)
            {
                
                //Inicializamos las variables
                daoEstudiante = new EstudianteWSClient();
                //Asignamos los valores
                estudiante.dni = txtDNI.Text;
                estudiante.nombres = txtNombre.Text;
                estudiante.apellidoPaterno = txtApellidoPaterno.Text;
                estudiante.apellidoMaterno = txtApellidoMaterno.Text;
                estudiante.direccion = txtDireccion.Text;
                estudiante.religion = txtReligion.Text;
                estudiante.lengua = txtLengua.Text;
                if (rbMasculino.Checked)
                    estudiante.sexo = 'M';
                else
                    estudiante.sexo = 'F';
                estudiante.fechaNacimiento =
                    DateTime.Parse(dtpFechaNacimiento.Value);
                estudiante.fechaNacimientoSpecified = true;
                estudiante.condicionesMedicas = txtCondicionesMedicas.Text;
                estudiante.discapacidades = txtDiscapacidad.Text;
                estudiante.estado = "NO MATRICULADO";
                estudiante.cantCursos = 0;
                estudiante.promedio = 0;
                estudiante.activo = true;
                //Dependiendo de la acción registramos o modificamos
                if (estado == Estado.Nuevo)
                    daoEstudiante.insertarEstudiante(estudiante);
                
                else if (estado == Estado.Modificar)
                {
                    daoEstudiante.modificarEstudiante(estudiante);
                    Session["estudiante"] = null;
                }

                //Redireccionamos a otra página
                string script = "";
                if (estado == Estado.Nuevo)
                {
                    script = "mostrarModal('Se realizó el registro con éxito', 'GestionarAlumnos.aspx');";
                }
                else if (estado == Estado.Modificar)
                {
                    script = "mostrarModal('Se realizó la modificación con éxito', 'GestionarAlumnos.aspx');";
                }
                ScriptManager.RegisterStartupScript(this, this.GetType(), "modal", script, true);
            }
        }
        protected void btnCancelar_Click(object sender, EventArgs e)
        {
            // Lógica para cancelar (quizás limpiar los campos o redirigir a otra página)
            Response.Redirect("GestionarAlumnos.aspx");
        }
    }
}