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
    }
}