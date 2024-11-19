using AmorYPazBackend.ServicioWS;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace AmorYPazBackend
{
    public partial class RegistrarMatricula : System.Web.UI.Page
    {
        private BindingList<anioAcademico> aniosAcad;
        private AnioAcademicoWSClient daoAnioAcademico;
        private InstitucionEducativaWSClient daoInstitucion;
        private EstudianteWSClient daoEstudiante;

        private GradoWSClient daoGrado;
        private BindingList<grado> grados;

        private MatriculaWSClient daoMatricula;
        private matricula matricula;



        protected void Page_Load(object sender, EventArgs e)
        {
            matricula = new matricula();
            if (!IsPostBack)
            {
                if (Session["idDirector"] != null)
                {
                    daoInstitucion = new InstitucionEducativaWSClient();
                    int idDirector = Int32.Parse(Session["idDirector"].ToString());
                    institucionEducativa ie = daoInstitucion.obtenerIEPorIdDirector(idDirector);

                    if (ie != null)
                    {
                        try
                        {
                            txtVacantes.Enabled = false;
                            daoAnioAcademico = new AnioAcademicoWSClient();
                            aniosAcad = new BindingList<anioAcademico>(daoAnioAcademico.listarAnioAcademicoPorIdIE(ie.idInstitucion));
                            int anioActual = DateTime.Now.Year;

                            BindingList<anioAcademico> aniosFiltrados = new BindingList<anioAcademico>(
                            aniosAcad.Where(a => a.numero == anioActual || a.numero == anioActual + +1).ToList());

                            ddlAnio.DataSource = aniosFiltrados;
                            ddlAnio.DataValueField = "idAnio";
                            ddlAnio.DataTextField = "numero";
                            ddlAnio.DataBind();

                            //grados
                            daoGrado = new GradoWSClient();
                            grados = new BindingList<grado>(daoGrado.listarPorIdIE(ie.idInstitucion));
                            List<GradoNivelItem> listaFormateada = grados.Select(g => new GradoNivelItem
                            {
                                IdGrado = g.idGrado,
                                GradoNivel = $"{g.numero}° {g.nivel}"
                            }).ToList();
                            ddlGrado.DataSource = listaFormateada;
                            ddlGrado.DataValueField = "idGrado";
                            ddlGrado.DataTextField = "GradoNivel";
                            ddlGrado.DataBind();

                            dtpFecha.Value = DateTime.Now.ToString("yyyy-MM-dd");

                            int idGradoSeleccionado = Int32.Parse(ddlGrado.SelectedValue);
                            grado aux = grados.FirstOrDefault(g => g.idGrado == idGradoSeleccionado);
                            txtVacantes.Text = aux.vacantes.ToString();
                        }
                        catch (Exception ex)
                        {

                        }
                    }
                }
            }





        }
        protected void btnBuscar_Click(object sender, EventArgs e)
        {
            // Lógica para buscar el alumno por DNI
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            // Lógica para guardar
            if (Page.IsValid)
            {
                daoInstitucion = new InstitucionEducativaWSClient();
                int idDirector = Int32.Parse(Session["idDirector"].ToString());
                institucionEducativa ie = daoInstitucion.obtenerIEPorIdDirector(idDirector);

                daoGrado = new GradoWSClient();

                if (ie != null)
                {
                    int id = ie.idInstitucion;
                    matricula.institucion = ie;
                }
                anioAcademico anio = new anioAcademico();
                anio.idAnio = Int32.Parse(ddlAnio.SelectedValue);
                matricula.anioAcademico = anio;

                grado grado = new grado();
                grado.idGrado = Int32.Parse(ddlGrado.SelectedValue);
                matricula.grado = grado;

                matricula.fecha = DateTime.Parse(dtpFecha.Value);
                matricula.fechaSpecified = true;
                matricula.estado = "MATRICULADO";
                string tipoString = "APROBADO";
                matricula.tipoMatricula = tipoString;

                estudiante estudiante = new estudiante();
                estudiante.idPersona = 28;

                if (Session["alumnoEncontrado"] != null && (bool)Session["alumnoEncontrado"])
                {
                    if (Session["estudiantePosible"] != null)
                        matricula.estudiante = (estudiante)Session["estudiantePosible"];
                }

                int resultado;
                daoMatricula = new MatriculaWSClient();
                resultado = daoMatricula.insertarMatricula(matricula);
                String script = "";
                if (resultado != 0)
                    script = "mostrarModal('Se registró con éxito', 'GestionarMatricula.aspx');";
                else
                    script = "mostrarModal('No se pudo registrar', 'GestionarMatricula.aspx');";
                
                ClientScript.RegisterStartupScript(this.GetType(), "modal", script, true);

            }
        }

        protected void btnCancelar_Click(object sender, EventArgs e)
        {
            // Lógica para cancelar (quizás limpiar los campos o redirigir a otra página)
            Response.Redirect("GestionarMatricula.aspx");
        }

        private bool ValidarAlumno(string nombreCompleto)
        {
            // Suponiendo que tienes un servicio para obtener los alumnos por IE
            EstudianteWSClient daoEstudiante = new EstudianteWSClient();
            estudiante[] estudianteArray = daoEstudiante.listarEstudiantesPorIE(matricula.institucion.idInstitucion);
            List<estudiante> alumnos = new List<estudiante>(estudianteArray);

            // Dividir el nombre completo en partes
            string[] partesNombre = nombreCompleto.Trim().Split(' ');

            // Verificar si hay al menos 3 partes (nombres, apellido paterno y apellido materno)
            if (partesNombre.Length < 3)
            {
                return false; // No se puede validar si no hay suficientes partes
            }

            string nombres = string.Join(" ", partesNombre.Take(partesNombre.Length - 2)); // Todos menos los últimos dos
            string apellidoPaterno = partesNombre[partesNombre.Length - 2];
            string apellidoMaterno = partesNombre[partesNombre.Length - 1];

            // Buscar el alumno que coincide
            var alumnoCoincidente = alumnos.FirstOrDefault(a =>
                a.nombres.Equals(nombres, StringComparison.OrdinalIgnoreCase) &&
                a.apellidoPaterno.Equals(apellidoPaterno, StringComparison.OrdinalIgnoreCase) &&
                a.apellidoMaterno.Equals(apellidoMaterno, StringComparison.OrdinalIgnoreCase));

            if (alumnoCoincidente != null)
            {
                HttpContext.Current.Session["estudiantePosible"] = alumnoCoincidente;
                return true; // Se encontró un alumno y se guardó en la sesión
            }

            return false; // No se encontró ningún alumno coincidente
        }

        protected void btnBuscar_Estudiante_Click(object sender, EventArgs e)
        {
            string script = "mostrarModalCursos();";
            ScriptManager.RegisterStartupScript(this, GetType(), "MostrarModalCursos", script, true);
            int idDirector = Int32.Parse(Session["idDirector"].ToString());
            daoInstitucion = new InstitucionEducativaWSClient();
            daoEstudiante = new EstudianteWSClient();
            institucionEducativa ie = daoInstitucion.obtenerIEPorIdDirector(idDirector);
            BindingList<estudiante> estudiantes;
            estudiante[] ests = daoEstudiante.listarEstudiantesPorIE(ie.idInstitucion);
            estudiantes = new BindingList<estudiante>(ests);
            gvEstudiantes.DataSource = estudiantes;
            gvEstudiantes.DataBind();
            ViewState["estudiantes"] = estudiantes;
        }

        protected void lbBuscarEstudianteModal_Click(object sender, EventArgs e)
        {

        }

        protected void gvEstudiantes_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvEstudiantes.PageIndex = e.NewPageIndex;
            gvEstudiantes.DataSource = (BindingList<estudiante>)ViewState["estudiantes"];
            gvEstudiantes.DataBind();
        }

        protected void SeleccionarEstudiante_Click(object sender, EventArgs e)
        {
            LinkButton btn = (LinkButton)sender;
            int idPersona = Int32.Parse(btn.CommandArgument);
            BindingList<estudiante> estudiantes = (BindingList<estudiante>)ViewState["estudiantes"];
            estudiante estudianteEncontrado = null;
            if (estudiantes != null) estudianteEncontrado = estudiantes.FirstOrDefault(k => k.idPersona == idPersona);
            txtAlumno.Text = estudianteEncontrado.dni.ToString() + " - " + estudianteEncontrado.nombres + " " + estudianteEncontrado.apellidoPaterno;
            string script = @"$('#form-modal').modal('hide'); $('.modal-backdrop').remove();";
            ScriptManager.RegisterStartupScript(this, this.GetType(), "HideModal", script, true);
        }

        protected void gvEstudiantes_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                e.Row.Cells[0].Text = DataBinder.Eval(e.Row.DataItem, "dni").ToString();
                e.Row.Cells[1].Text = DataBinder.Eval(e.Row.DataItem, "nombres").ToString() + " " + DataBinder.Eval(e.Row.DataItem, "apellidoPaterno").ToString() + " " + DataBinder.Eval(e.Row.DataItem, "apellidoMaterno").ToString();
            }
        }
    }
}