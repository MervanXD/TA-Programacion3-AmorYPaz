using AmorYPazBackend.ServicioWS;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace AmorYPazBackend
{
    public partial class GestionarAlumnos : System.Web.UI.Page
    {
        private InstitucionEducativaWSClient daoInstitucion;
        private EstudianteWSClient daoEstudiante=new EstudianteWSClient();
        private BindingList<estudiante> estudiantes;
        private GradoWSClient daoGrado;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["idDirector"] != null)
            {
                daoInstitucion = new InstitucionEducativaWSClient();
                int idDirector = Int32.Parse(Session["idDirector"].ToString());
                institucionEducativa ie = daoInstitucion.obtenerIEPorIdDirector(idDirector);
                daoGrado = new GradoWSClient();
                ie.grados = daoGrado.listarPorIdIE(ie.idInstitucion);

                if (ie.grados != null)
                {
                    //Formatear una lista
                    var gradosCompletos = ie.grados.Select(g => new {
                        gradoCompleto = g.numero + "° " + g.nivel,
                        idGrado = g.idGrado
                    }).ToList();

                    if (!IsPostBack) {
                        gradosCompletos.Insert(0, new { gradoCompleto = "Selecciona un grado", idGrado = 0 });
                        ddlGrados.DataSource = gradosCompletos;
                        ddlGrados.DataTextField = "gradoCompleto";
                        ddlGrados.DataValueField = "idGrado";
                        ddlGrados.DataBind();
                    }
                }
                else
                {
                    ddlGrados.Items.Clear();
                    ddlGrados.Items.Add(new ListItem("No hay grados disponibles", "0"));
                }

                if (ie != null)
                {
                    try
                    {
                        lblInfoIE.Text = $"{ie.nombre}";
                        estudiantes = new BindingList<estudiante>(daoEstudiante.listarEstudiantesPorInstitucionEducativa(ie.idInstitucion));
                        gvAlumnos.DataSource = estudiantes;
                        gvAlumnos.DataBind();
                    }
                    catch (Exception ex) {
                        Console.WriteLine(ex.Message);
                    }
                }
            }
        }

        protected void gvAlumnos_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                e.Row.Cells[0].Text = (DataBinder.Eval(e.Row.DataItem, "dni")).ToString();
                e.Row.Cells[1].Text = (DataBinder.Eval(e.Row.DataItem, "nombres")).ToString();
                e.Row.Cells[2].Text = (DataBinder.Eval(e.Row.DataItem, "apellidoPaterno")).ToString();
                e.Row.Cells[3].Text = (DataBinder.Eval(e.Row.DataItem, "apellidoMaterno")).ToString();
            }
        }

        protected void lbRegistrar_Click(object sender, EventArgs e)
        {
            Response.Redirect("RegistrarAlumno.aspx");
        }

        protected void lbEliminar_Click(object sender, EventArgs e)
        {
            
            int idEstudiante = Int32.Parse(((LinkButton)sender).CommandArgument);
            daoEstudiante.eliminarEstudiante(idEstudiante);
            
            Response.Redirect("GestionarAlumnos.aspx");
        }
        protected void lbModificar_Click(object sender, EventArgs e)
        {
            int idEstudiante = Int32.Parse(((LinkButton)sender).CommandArgument);
            estudiante estudiante = estudiantes.SingleOrDefault(x => x.idPersona == idEstudiante);
            Session["estudiante"] = estudiante;
            Response.Redirect("RegistrarAlumno.aspx?accion=modificar");
        }
        protected void lbBuscar_Click(object sender, EventArgs e)
        {
            List<estudiante> estudianteBuscados = estudiantes.Where(x => x.dni.Contains(txtDNI.Text)).ToList();
            if (estudianteBuscados.Count > 0)
                gvAlumnos.DataSource = estudianteBuscados;
            else
                gvAlumnos.DataSource = null;
            gvAlumnos.DataBind();
        }

        protected void ddlGrados_SelectedIndexChanged(object sender, EventArgs e)
        {
            estudiante[] filtrados;
            daoEstudiante = new EstudianteWSClient();
            if (Int32.Parse(ddlGrados.SelectedValue) != 0)
                filtrados = daoEstudiante.listarEstudiantesPorGrado(Int32.Parse(ddlGrados.SelectedValue));
            else {
                gvAlumnos.DataSource = estudiantes;
                gvAlumnos.DataBind();
                return;
            }
            if (filtrados != null) estudiantes = new BindingList<estudiante>(filtrados);
            else estudiantes = null;
            if (estudiantes == null)
            {
                gvAlumnos.EmptyDataText = "No hay estudiantes para el grado seleccionado.";
                gvAlumnos.DataSource = null;
            }
            else
            {
                gvAlumnos.EmptyDataText = string.Empty; // Limpia el mensaje si hay estudiantes
                gvAlumnos.DataSource = estudiantes;
                Session["estudiantes"] = estudiantes;
            }
            gvAlumnos.DataBind();
        }
    }
}