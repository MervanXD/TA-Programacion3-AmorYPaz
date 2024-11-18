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
    public partial class GestionarAniosAcademicos : System.Web.UI.Page
    {
        private anioAcademico anio;
        private AnioAcademicoWSClient daoAnioAcademico;
        private BindingList<anioAcademico> anios;
        private InstitucionEducativaWSClient daoInstitucion;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["idDirector"] != null)
            {
                daoInstitucion = new InstitucionEducativaWSClient();
                int idDirector = Int32.Parse(Session["idDirector"].ToString());
                institucionEducativa ie = daoInstitucion.obtenerIEPorIdDirector(idDirector);
                Session["idInstitucion"] = ie.idInstitucion;
                if (ie != null)
                {
                    try
                    {//listando únicamente los anios que le pertenecen a una determinada institucion
                        lblInfoIE.Text = $"{ie.nombre}";
                        daoAnioAcademico = new AnioAcademicoWSClient();
                        anios = new BindingList<anioAcademico>(daoAnioAcademico.listarAnioAcademicoPorIdIE(ie.idInstitucion));
                        anios = new BindingList<anioAcademico>(anios.OrderByDescending(anio => anio.numero).ToList());
                        actualizarEstadoAnios(anios);
                        gvAnios.DataSource = anios;
                        gvAnios.DataBind();
                    }
                    catch (Exception ex)
                    {
                        gvAnios.DataBind();
                    }
                }
            }
        }

        protected void actualizarEstadoAnios(BindingList<anioAcademico> anios)
        { //revisar fecha actua l y chequear año anterior, actual y siguiente, años ordenados de más reciente a menos reciente
            int anioActual = DateTime.Now.Year;
            daoAnioAcademico = new AnioAcademicoWSClient();
            foreach (anioAcademico a in anios) {
                if (a.fechaInicio <= DateTime.Now && a.fechaFin >= DateTime.Now && a.estado != "EN CURSO") {
                    a.estado = "EN CURSO";
                    daoAnioAcademico.modificarAnioAcademico(a);
                }
                if (a.numero == anioActual - 1 && a.fechaFin < DateTime.Now && a.estado == "EN CURSO") {
                    a.estado = "FINALIZADO";
                    daoAnioAcademico.modificarAnioAcademico(a);
                }
                if (a.numero <= anioActual - 1) break;
            }
        }

        protected void gvAnios_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                e.Row.Cells[0].Text = (DataBinder.Eval(e.Row.DataItem, "numero")).ToString();
                DateTime fechaInicio = Convert.ToDateTime(DataBinder.Eval(e.Row.DataItem, "fechaInicio"));
                e.Row.Cells[1].Text = fechaInicio.ToString("dd-MM-yyyy");
                DateTime fechaFin = Convert.ToDateTime(DataBinder.Eval(e.Row.DataItem, "fechaFin"));
                e.Row.Cells[2].Text = fechaFin.ToString("dd-MM-yyyy");
                e.Row.Cells[3].Text = (DataBinder.Eval(e.Row.DataItem, "estado").ToString()).ToString();
            }
        }

        protected void lbModificar_Click(object sender, EventArgs e)
        {
            daoAnioAcademico = new AnioAcademicoWSClient();
            int idAnioAcademico = Int32.Parse(((LinkButton)sender).CommandArgument);
            anioAcademico anioAc = daoAnioAcademico.obtenerAnioAcademico(idAnioAcademico);
            Session["anioAcademico"] = anioAc;
            Response.Redirect("RegistrarAnioAcademico.aspx?accion=modificar");
        }

        protected void lbVerPlanes_Click(object sender, EventArgs e)
        {
            int idAnioAcademico = Int32.Parse(((LinkButton)sender).CommandArgument);
            Response.Redirect($"GestionarPlanesEstudio.aspx?idAnioAcademico={idAnioAcademico}");
        }

        protected void AddButton_Click(object sender, EventArgs e)
        {
            Response.Redirect("RegistrarAnioAcademico.aspx");
        }

        protected void btnActual_Click(object sender, EventArgs e)
        {
            string tipoFiltro = ((Button)sender).CommandArgument;

            var planesFiltrados = FiltrarPlanesEstudio(tipoFiltro);

            gvAnios.DataSource = planesFiltrados;
            gvAnios.DataBind();

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

        private BindingList<anioAcademico> FiltrarPlanesEstudio(string tipoFiltro)
        {
            List<anioAcademico> aniosFiltrados;
            daoAnioAcademico = new AnioAcademicoWSClient();
            int idInstitucionAnio = Int32.Parse(Session["idInstitucion"].ToString());
            anios = new BindingList<anioAcademico>(daoAnioAcademico.listarAnioAcademicoPorIdIE(idInstitucionAnio));
            if (tipoFiltro == "actual")
                aniosFiltrados = anios.Where(p => ((p.fechaInicio <= DateTime.Now && p.fechaFin >= DateTime.Now) || p.fechaInicio >=DateTime.Now)).ToList();
            else if (tipoFiltro == "finalizados")
                aniosFiltrados = anios.Where(p => p.fechaFin < DateTime.Now).ToList();
            else
                aniosFiltrados = new List<anioAcademico>();
            BindingList<anioAcademico> aniosOrdenados = new BindingList<anioAcademico>(aniosFiltrados.OrderByDescending(anio => anio.numero).ToList());
            return aniosOrdenados;
        }

        protected void gvAnios_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvAnios.PageIndex = e.NewPageIndex;
            gvAnios.DataBind();
        }
    }
}