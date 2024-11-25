using AmorYPazBackend.ServicioWS;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing.Drawing2D;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace AmorYPazBackend
{
    public partial class GestionarNotas : System.Web.UI.Page
    {
        private AnioAcademicoWSClient daoAnios;
        private EstudianteWSClient daoEstudiante;
        private MatriculaWSClient daoMatricula;
        private GradoWSClient daoGrado;
        private ResultadoPorCursoWSClient daoResultado;
        private BindingList<resultadoPorCurso> resultados;
        private BindingList<resultadoPorCurso> resultadosAnadir;
        private BindingList<anioAcademico> aniosAcad;
        private BindingList<grado> grados;
        private BindingList<estudiante> estudiantes;
        private matricula matric;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                matric = (matricula)Session["matricula"];
                cargarDDLs();
                if (matric != null)
                {
                    seleccionarDDLs(matric);
                    daoResultado = new ResultadoPorCursoWSClient();
                    resultadoPorCurso[] results = daoResultado.listarPorIdMatricula(matric.idMatricula);
                    if (results != null)
                    {
                        resultados = new BindingList<resultadoPorCurso>(results);
                        gvNotas.DataSource = resultados;
                        gvNotas.DataBind();
                        ViewState["resultadosAnadir"] = new BindingList<resultadoPorCurso>();
                        ViewState["matricula"] = matric;
                    }
                    else
                    {
                        lblMensaje.Text = "No se encontraron registros correspondientes a los criterios ingresados.";
                        lblMensaje.Visible = true;
                        ViewState["resultadosAnadir"] = null;
                        gvNotas.DataSource = null;
                        gvNotas.DataBind();
                    }
                }
            }
        }

        private void cargarDDLs()
        {
            //cargando los años
            daoAnios = new AnioAcademicoWSClient();
            aniosAcad = new BindingList<anioAcademico>(daoAnios.listarAnioAcademicoPorIdIE(matric.institucion.idInstitucion));
            aniosAcad = new BindingList<anioAcademico>(aniosAcad.OrderByDescending(a => a.numero).ToList());
            ddlAnio.DataSource = aniosAcad;
            ddlAnio.DataValueField = "idAnio";
            ddlAnio.DataTextField = "numero";
            ddlAnio.DataBind();
            //cargando los grados
            daoGrado = new GradoWSClient();
            grados = new BindingList<grado>(daoGrado.listarPorIdIE(matric.institucion.idInstitucion));
            List<GradoNivelItem> gradosTransformados = grados.Select(g => new GradoNivelItem
            {
                IdGrado = g.idGrado,
                GradoNivel = $"{g.numero}° - {g.nivel}"
            }).ToList();
            ddlGrado.DataSource = gradosTransformados;
            ddlGrado.DataValueField = "IdGrado";
            ddlGrado.DataTextField = "GradoNivel";
            ddlGrado.DataBind();
            //cargando los estudiantes
            daoEstudiante = new EstudianteWSClient();
            estudiantes = new BindingList<estudiante>(daoEstudiante.listarEstudiantesPorMatriculasIE(matric.institucion.idInstitucion));
            List<EstudianteItem> estudiantesTransformados = estudiantes.Select(e => new EstudianteItem
            {
                IdPersona = e.idPersona,
                NombreCompleto = $"{e.nombres} {e.apellidoPaterno} {e.apellidoMaterno}"
            }).ToList();
            ddlEstudiante.DataSource = estudiantesTransformados;
            ddlEstudiante.DataTextField = "NombreCompleto";
            ddlEstudiante.DataValueField = "IdPersona";
            ddlEstudiante.DataBind();
        }

        private void seleccionarDDLs(matricula matric)
        {
            ddlAnio.SelectedValue = matric.anioAcademico.idAnio.ToString();
            ddlGrado.SelectedValue = matric.grado.idGrado.ToString();
            ddlEstudiante.SelectedValue = matric.estudiante.idPersona.ToString();
        }

        protected void gvNotas_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvNotas.PageIndex = e.NewPageIndex;
            gvNotas.DataSource = null;
            gvNotas.DataBind();
        }

        protected void gvNotas_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                e.Row.Cells[0].Text = ((curso)(DataBinder.Eval(e.Row.DataItem, "curso"))).nombre;
                TextBox txtNotaCurso = (TextBox)e.Row.FindControl("txtNotaCurso");
                txtNotaCurso.Text = DataBinder.Eval(e.Row.DataItem, "calificacion").ToString();
            }
        }

        protected void btnAceptar_Click(object sender, EventArgs e)
        {
            daoResultado = new ResultadoPorCursoWSClient();
            if (ViewState["resultadosAnadir"] != null)
            {
                resultadosAnadir = (BindingList<resultadoPorCurso>)ViewState["resultadosAnadir"];
                foreach (resultadoPorCurso res in resultadosAnadir)
                    if (daoResultado.insertarResultado(res) == 0) break;
            }
            string script = "mostrarModal('Se realizó el registro de notas con éxito.', 'GestionarMatricula.aspx');";
            ScriptManager.RegisterStartupScript(this, GetType(), "modal", script, true);
        }

        protected void ddl_SelectedIndexChanged(object sender, EventArgs e)
        {
            int idAnio = Int32.Parse(ddlAnio.SelectedValue);
            int idGrado = Int32.Parse(ddlGrado.SelectedValue);
            int idEstudiante = Int32.Parse(ddlEstudiante.SelectedValue);
            daoMatricula = new MatriculaWSClient();
            matric = daoMatricula.obtenerPorCriterios(idAnio, idGrado, idEstudiante);
            if (matric != null)
            {
                seleccionarDDLs(matric);
                daoResultado = new ResultadoPorCursoWSClient();
                resultados = new BindingList<resultadoPorCurso>(daoResultado.listarPorIdMatricula(matric.idMatricula));
                gvNotas.DataSource = resultados;
                gvNotas.DataBind();
                lblMensaje.Text = "";
                lblMensaje.Visible = false;
            }
            else
            {
                lblMensaje.Text = "No se encontraron registros correspondientes a los criterios ingresados.";
                lblMensaje.Visible = true;
                ViewState["resultadosAnadir"] = null;
                gvNotas.DataSource = null;
                gvNotas.DataBind();
            }
        }

        protected void lbModificar_Click(object sender, EventArgs e)
        {
            LinkButton lb = (LinkButton)sender;
            GridViewRow row = (GridViewRow)lb.NamingContainer;
            TextBox txtNotaCurso = (TextBox)row.FindControl("txtNotaCurso");
            if (txtNotaCurso != null) txtNotaCurso.Enabled = true;
        }

        protected void lbGuardar_Click(object sender, EventArgs e)
        {
            LinkButton lb = (LinkButton)sender;
            GridViewRow row = (GridViewRow)lb.NamingContainer;
            TextBox txtNotaCurso = (TextBox)row.FindControl("txtNotaCurso");
            if (txtNotaCurso != null) txtNotaCurso.Enabled = false;
            if (ViewState["resultadosAnadir"] == null) ViewState["resultadosAnadir"] = new BindingList<resultadoPorCurso>();
            resultadosAnadir = (BindingList<resultadoPorCurso>)ViewState["resultadosAnadir"];

            matric = (matricula)ViewState["matricula"];
            resultadoPorCurso resultado = new resultadoPorCurso();
            resultado.matricula = new matricula();
            resultado.matricula.idMatricula = matric.idMatricula;
            resultado.curso = new curso();
            resultado.curso.idCurso = Int32.Parse(lb.CommandArgument);
            resultado.curso.nombre = row.Cells[0].Text;
            resultado.calificacion = Int32.Parse(txtNotaCurso.Text);

            resultadoPorCurso existente = resultadosAnadir.FirstOrDefault(r => r.curso.idCurso == resultado.curso.idCurso &&
                r.matricula.idMatricula == resultado.matricula.idMatricula);
            if (existente != null) resultadosAnadir.Remove(existente);

            resultadosAnadir.Add(resultado);
            ViewState["resultadosAnadir"] = resultadosAnadir;
        }

        protected void lbCancelar_Click(object sender, EventArgs e)
        {
            Response.Redirect("GestionarMatricula.aspx");
        }
    }
}