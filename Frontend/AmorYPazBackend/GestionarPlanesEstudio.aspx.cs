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
    public partial class GestionarPlanesEstudio : System.Web.UI.Page
    {
        private InstitucionEducativaWSClient daoInstitucion;
        private PlanDeEstudioWSClient daoPlan;
        private BindingList<planDeEstudio> planes;
        private GradoWSClient daoGrado = new GradoWSClient();
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                if (Session["idDirector"] != null)
                {
                    daoInstitucion = new InstitucionEducativaWSClient();
                    int idDirector = Int32.Parse(Session["idDirector"].ToString());
                    institucionEducativa ie = daoInstitucion.obtenerIEPorIdDirector(idDirector); //revisar si es necesario ServicioUGEL o si se creo un servicio de más
                    if (ie != null) {
                        try
                        {//listando únicamente los planes de estudio que le pertenecen a una determinada institucion
                            lblInfoIE.Text = $"{ie.nombre}";
                            daoPlan = new PlanDeEstudioWSClient();
                            planes = new BindingList<planDeEstudio>(daoPlan.listarPlanesDeEstudioPorIdIE(ie.idInstitucion));
                            gvPlanes.DataSource = planes;
                            gvPlanes.DataBind();
                        }
                        catch (Exception ex)
                        {
                            gvPlanes.DataBind();
                        }
                    }
                }
            }
        }

        protected void AddButton_Click(object sender, EventArgs e)
        {
            Response.Redirect("RegistrarPlanDeEstudios.aspx");
        }

        protected void gvPlanes_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if(e.Row.RowType == DataControlRowType.DataRow) {
                int idPlan = Int32.Parse(DataBinder.Eval(e.Row.DataItem, "idPlan").ToString());
                grado gr = daoGrado.obtenerPorIdPlanEstudios(idPlan);
                e.Row.Cells[0].Text = ((anioAcademico)DataBinder.Eval(e.Row.DataItem, "anioAcademico")).numero.ToString();
                e.Row.Cells[1].Text = gr.numero + "° - " + gr.nivel;
                e.Row.Cells[2].Text = ((anioAcademico)DataBinder.Eval(e.Row.DataItem, "anioAcademico")).fechaInicio.ToString("yyyy-MM-dd");
                e.Row.Cells[3].Text = ((anioAcademico)DataBinder.Eval(e.Row.DataItem, "anioAcademico")).fechaFin.ToString("yyyy-MM-dd");
                e.Row.Cells[4].Text = Int32.Parse(DataBinder.Eval(e.Row.DataItem, "numCursos").ToString()).ToString();
            }
        }

        protected void lbModificar_Click(object sender, EventArgs e)
        {

        }

        protected void gvPlanes_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            if (e.CommandName == "MostrarDescripcion")
            {
                string descripcion = e.CommandArgument.ToString();
                string script = $"showDescriptionModal('{descripcion}');";
                ScriptManager.RegisterStartupScript(this, GetType(), "ShowModalScript", script, true);
            }
        }

        protected void btnFiltrar_Click(object sender, EventArgs e)
        {
            string tipoFiltro = ((Button)sender).CommandArgument;

            var planesFiltrados = FiltrarPlanesEstudio(tipoFiltro);

            gvPlanes.DataSource = planesFiltrados;
            gvPlanes.DataBind();

            if (tipoFiltro == "actual")
            {
                btnActual.CssClass = "btn btn-primary active";
                btnFinalizados.CssClass = "btn btn-primary";
            }
            else if (tipoFiltro == "finalizados")
            {
                btnActual.CssClass = "btn btn-primary";
                btnFinalizados.CssClass = "btn btn-primary active";
            }
        }

        private BindingList<planDeEstudio> FiltrarPlanesEstudio(string tipoFiltro)
        {
            List<planDeEstudio> planesFiltrados;
            daoPlan = new PlanDeEstudioWSClient();
            planes = new BindingList<planDeEstudio>(daoPlan.listarPlanesDeEstudio());
            if (tipoFiltro == "actual")
                planesFiltrados = planes.Where(p => p.anioAcademico.fechaInicio <= DateTime.Now && p.anioAcademico.fechaFin >= DateTime.Now).ToList();
            else if (tipoFiltro == "finalizados")
                planesFiltrados = planes.Where(p => p.anioAcademico.fechaFin < DateTime.Now).ToList();
            else
                planesFiltrados = new List<planDeEstudio>();

            return new BindingList<planDeEstudio>(planesFiltrados);
        }


    }
}