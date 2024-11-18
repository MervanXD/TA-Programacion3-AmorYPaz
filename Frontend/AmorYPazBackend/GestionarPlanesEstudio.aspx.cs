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
    public partial class GestionarPlanesEstudio : System.Web.UI.Page
    {
        private InstitucionEducativaWSClient daoInstitucion;
        private PlanDeEstudioWSClient daoPlan;
        private BindingList<planDeEstudio> planes;
        private GradoWSClient daoGrado = new GradoWSClient();

        protected void Page_Load(object sender, EventArgs e) {
            BindingList<planDeEstudio> planesFiltrados = (BindingList<planDeEstudio>)ViewState["planesFiltrados"];
            if (planesFiltrados != null) {
                gvPlanes.DataSource = planesFiltrados;
                gvPlanes.DataBind();
            }
        }
        protected void Page_Init(object sender, EventArgs e)
        {
            planes = (BindingList<planDeEstudio>)ViewState["planesFiltrados"];
            if (planes == null)
            {
                if (Session["idDirector"] != null)
                {
                    daoInstitucion = new InstitucionEducativaWSClient();
                    int idDirector = Int32.Parse(Session["idDirector"].ToString());
                    institucionEducativa ie = daoInstitucion.obtenerIEPorIdDirector(idDirector); //revisar si es necesario ServicioUGEL o si se creo un servicio de más
                    Session["idIE"] = ie.idInstitucion;
                    if (ie != null)
                    {
                        try
                        {//listando únicamente los planes de estudio que le pertenecen a una determinada institucion
                            lblInfoIE.Text = $"{ie.nombre}";
                            daoPlan = new PlanDeEstudioWSClient();
                            planes = new BindingList<planDeEstudio>(daoPlan.listarPlanesDeEstudioPorIdIE(ie.idInstitucion));
                            ViewState["planesEstudio"] = planes;
                            BindingList<planDeEstudio> planesIni;

                            string idAnioString = Request.QueryString["idAnioAcademico"];
                            if (idAnioString == null)
                            {
                                planesIni = FiltrarPlanesEstudio("actual");
                                btnActual.CssClass = "btn btn-primary active";
                                btnFinalizados.CssClass = "btn btn-primary";
                            }
                            else {
                                int idAnioAcademico = Int32.Parse(idAnioString);
                                planesIni = new BindingList<planDeEstudio>(planes.Where(plan => plan.anioAcademico.idAnio == idAnioAcademico).ToList());
                            }

                            gvPlanes.DataSource = planesIni;
                            ViewState["planesFiltrados"] = planesIni;
                            gvPlanes.DataBind();
                        }
                        catch (Exception ex)
                        {
                            gvPlanes.DataBind();
                        }
                    }
                }
            }
            else {
                gvPlanes.DataSource = planes;
                gvPlanes.DataBind();
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
                int anioActual = DateTime.Now.Year;
                int anioPlan = ((anioAcademico)DataBinder.Eval(e.Row.DataItem, "anioAcademico")).numero;
                grado gr = daoGrado.obtenerPorIdPlanEstudios(idPlan);
                e.Row.Cells[0].Text = anioPlan.ToString();
                e.Row.Cells[1].Text = gr.numero + "° - " + gr.nivel;
                e.Row.Cells[2].Text = ((anioAcademico)DataBinder.Eval(e.Row.DataItem, "anioAcademico")).fechaInicio.ToString("dd-MM-yyyy");
                e.Row.Cells[3].Text = ((anioAcademico)DataBinder.Eval(e.Row.DataItem, "anioAcademico")).fechaFin.ToString("dd-MM-yyyy");
                e.Row.Cells[4].Text = Int32.Parse(DataBinder.Eval(e.Row.DataItem, "numCursos").ToString()).ToString();
                LinkButton lbModificar = (LinkButton)e.Row.FindControl("lbModificar");
                if (anioPlan < anioActual)
                {
                    lbModificar.Text = "<i class='fa-solid fa-eye'></i>";
                    lbModificar.CssClass = "btn btn-info";
                    lbModificar.Click -= lbModificar_Click;
                    lbModificar.Click += VerDetalles_Click;
                    lbModificar.CommandArgument = idPlan.ToString();   
                }
            }
        }

        protected void VerDetalles_Click(object sender, EventArgs e)
        {
            LinkButton btn = (LinkButton)sender;
            int idPlan = int.Parse(btn.CommandArgument);
            planDeEstudio plan = planes.SingleOrDefault(x => x.idPlan == idPlan);
            Session["planEstudio"] = plan;
            Response.Redirect("RegistrarPlanDeEstudios.aspx?accion=visualizar");
        }

        protected void lbModificar_Click(object sender, EventArgs e)
        {
            int idPlan = Int32.Parse(((LinkButton)sender).CommandArgument);
            planDeEstudio plan = planes.SingleOrDefault(x => x.idPlan == idPlan);
            if (plan.anioAcademico.numero >= DateTime.Now.Year)
            {
                Session["planEstudio"] = plan;
                Response.Redirect("RegistrarPlanDeEstudios.aspx?accion=modificar");
            }
            else
            {
                Session["planEstudio"] = plan;
                Response.Redirect("RegistrarPlanDeEstudios.aspx?accion=visualizar");
            }
            
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

            BindingList<planDeEstudio> planesFiltrados = FiltrarPlanesEstudio(tipoFiltro);

            gvPlanes.DataSource = planesFiltrados;
            gvPlanes.DataBind();
            ViewState["planesFiltrados"] = planesFiltrados;

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
            planes = (BindingList<planDeEstudio>)ViewState["planesEstudio"];
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