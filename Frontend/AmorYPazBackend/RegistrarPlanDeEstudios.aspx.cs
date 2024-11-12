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
    public partial class RegistrarPlanDeEstudios : System.Web.UI.Page
    {
        private InstitucionEducativaWSClient daoInstitucion;
        private AnioAcademicoWSClient daoAnioAcademico;
        private GradoWSClient daoGrado;
        private BindingList<anioAcademico> aniosAcad;
        private BindingList<grado> grados;
        private PlanDeEstudioWSClient daoPlanEstudio;
        private List<curso> cursos;
        private CursoWSClient daoCurso;
        private curso nuevoCurso;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                if(Session["idDirector"] != null)
                {
                    daoInstitucion = new InstitucionEducativaWSClient();
                    int idDirector = Int32.Parse(Session["idDirector"].ToString());
                    institucionEducativa ie = daoInstitucion.obtenerIEPorIdDirector(idDirector); //revisar si es necesario ServicioUGEL o si se creo un servicio de más
                    if (ie != null)
                    {
                        try
                        {
                            //lblNombreInstitucion.Text = $"{ie.nombre}";
                            // Enlazando el ddl de años, considerando el actual y el siguiente únicamente
                            daoAnioAcademico = new AnioAcademicoWSClient();
                            aniosAcad = new BindingList<anioAcademico>(daoAnioAcademico.listarAnioAcademicoPorIdIE(ie.idInstitucion));
                            int anioActual = DateTime.Now.Year;
                            BindingList<anioAcademico> aniosFiltrados = new BindingList<anioAcademico>(
                            aniosAcad.Where(a => a.numero == anioActual || a.numero == anioActual + 1).ToList());
                            ddlAnio.DataSource = aniosFiltrados;
                            ddlAnio.DataValueField = "idAnio";
                            ddlAnio.DataTextField = "numero";
                            ddlAnio.DataBind();
                            // Cargando grados y niveles de la institucion, creando lista temporal para mostrar grado y nivel juntos
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
                        }
                        catch (Exception ex) { }

                    }
                }
                //listar todos los cursos


            }
        }

        protected void lbGuardar_Click(object sender, EventArgs e)
        {
            daoPlanEstudio = new PlanDeEstudioWSClient();

        }

        protected void lbCancelar_Click(object sender, EventArgs e)
        {
            Response.Redirect("GestionarPlanesEstudio.aspx");
        }

        protected void lbBuscarCurso_Click(object sender, EventArgs e)
        {
            string script = "window.onload = function() { showModalForm() };";
            ScriptManager.RegisterStartupScript(this, GetType(), "", script, true);
        }

        protected void gvCursos_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvCursos.PageIndex = e.NewPageIndex;
            gvCursos.DataBind();
        }

        protected void gvCursosModal_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvCursos.PageIndex = e.NewPageIndex;
            gvCursos.DataBind();
        }

        protected void lbBuscarCursoModal_Click(object sender, EventArgs e)
        {
            string script = "window.onload = function() { showModalForm() };";
            ScriptManager.RegisterStartupScript(this, GetType(), "", script, true);
            string nombreCurso = txtNombreCursoModal.Text.Trim();

            // Llamar al método del servicio para obtener los cursos
            curso[] cursosArray = daoCurso.listarPorIdNombreCursos(nombreCurso);
            cursos=cursosArray.ToList();

            // Asignar la lista de cursos al GridView en el modal
            gvCursosModal.DataSource = cursos;
            gvCursosModal.DataBind();
        }

        protected void SeleccionarCurso_Click(object sender, EventArgs e)
        {
            LinkButton btn = (LinkButton)sender;
            string nombreCursoSeleccionado = btn.CommandArgument;

            // Aquí podrías agregar la lógica para almacenar los cursos seleccionados
            if (ViewState["CursosSeleccionados"] == null)
            {
                ViewState["CursosSeleccionados"] = new List<string>();
            }

            List<string> cursosSeleccionados = (List<string>)ViewState["CursosSeleccionados"];
            if (!cursosSeleccionados.Contains(nombreCursoSeleccionado))
            {
                cursosSeleccionados.Add(nombreCursoSeleccionado);
            }

            ViewState["CursosSeleccionados"] = cursosSeleccionados;

            // Actualizar el GridView principal
            gvCursos.DataSource = cursosSeleccionados.Select(c => new { nombre = c }).ToList();
            gvCursos.DataBind();

            // Cerrar el modal
            string script = "window.onload = function() { closeModalForm() };";
            ScriptManager.RegisterStartupScript(this, GetType(), "", script, true);
        }

        protected void EliminarCurso_Click(object sender, EventArgs e)
        {
            LinkButton btn = (LinkButton)sender;
            string nombreCursoAEliminar = btn.CommandArgument;

            // Obtener la lista de cursos seleccionados
            List<string> cursosSeleccionados = (List<string>)ViewState["CursosSeleccionados"];
            if (cursosSeleccionados != null && cursosSeleccionados.Contains(nombreCursoAEliminar))
            {
                cursosSeleccionados.Remove(nombreCursoAEliminar);
            }

            ViewState["CursosSeleccionados"] = cursosSeleccionados;

            // Actualizar el GridView principal
            gvCursos.DataSource = cursosSeleccionados.Select(c => new { nombre = c }).ToList();
            gvCursos.DataBind();
        }
        protected void btnGuardarCurso_Click(object sender, EventArgs e)
        {
            string nombreCurso = txtNombreCurso.Text.Trim();

            // Validar que el nombre del curso no esté vacío
            if (string.IsNullOrEmpty(nombreCurso))
            {
                return; // O manejar el error de otra manera
            }

            // Crear el objeto curso
            nuevoCurso = new curso();
            nuevoCurso.nombre = nombreCurso;
            int resultado = daoCurso.insertarCurso(nuevoCurso); // Asegúrate de que este método exista en tu DAO

            if (resultado!=0)
            {
                // Añadir el curso al GridView de cursos seleccionados
                if (ViewState["CursosSeleccionados"] == null)
                {
                    ViewState["CursosSeleccionados"] = new List<curso>();
                }

                BindingList<curso> cursosSeleccionados = (BindingList<curso>)ViewState["CursosSeleccionados"];
                cursosSeleccionados.Add(nuevoCurso);
                ViewState["CursosSeleccionados"] = cursosSeleccionados;

                // Actualizar el GridView
                gvCursos.DataSource = cursosSeleccionados;
                gvCursos.DataBind();

                // Mostrar mensaje de éxito y cerrar el modal
                string script = "alert('Curso creado exitosamente.'); $('#crearCursoModal').modal('hide');";
                ScriptManager.RegisterStartupScript(this, GetType(), "alert", script, true);
            }
            else
            {
                // Manejar el error si no se pudo insertar el curso
                string script = "alert('Error al crear el curso.');";
                ScriptManager.RegisterStartupScript(this, GetType(), "alert", script, true);
            }
        }

        protected void lbAgregarCurso_Click(object sender, EventArgs e)
        {
            
        }


        protected void lbAgregarCurso_Click1(object sender, EventArgs e)
        {
            string script = "showModalFormRegistrarCurso();"; // Llama directamente a la función
            ScriptManager.RegisterStartupScript(this, GetType(), "showModal", script, true);
        }
    }
}