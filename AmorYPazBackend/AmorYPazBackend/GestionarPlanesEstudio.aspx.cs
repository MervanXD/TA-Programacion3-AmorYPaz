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
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                if (Session["idDirector"] != null)
                {
                    daoInstitucion = new InstitucionEducativaWSClient();
                    int idDirector = Int32.Parse(Session["idDirector"].ToString());
                    institucionEducativa ie = daoInstitucion.obtenerIEPorIdDirector(idDirector); //revisar si es necesario ServicioUGEL o si se creo un servicio de más
                    if (ie != null)
                    {
                        lblInfoIE.Text = $"{ie.nombre}";
                    }
                }
                try
                {
                    daoPlan = new PlanDeEstudioWSClient();
                    planes = new BindingList<planDeEstudio>(daoPlan.listarPlanesDeEstudio());
                    gvPlanes.DataSource = planes;
                    gvPlanes.DataBind();
                }
                catch (Exception ex)
                {
                    gvPlanes.DataBind();
                }
            }
        }

        protected void AddButton_Click(object sender, EventArgs e)
        {
            Response.Redirect("RegistrarPlanDeEstudios.aspx");
        }

        protected void gvPlanes_RowDataBound(object sender, GridViewRowEventArgs e)
        {

            e.Row.Cells[0].Text = "2023";  //((anioAcademico)DataBinder.Eval(e.Row.DataItem, "anioAcademico")).numero.ToString();
            e.Row.Cells[1].Text = "12/03/2023";//((anioAcademico)DataBinder.Eval(e.Row.DataItem, "anioAcademico")).fechaInicio.ToString();
            e.Row.Cells[2].Text = "12/12/2023";//((anioAcademico)DataBinder.Eval(e.Row.DataItem, "anioAcademico")).fechaFin.ToString();
            e.Row.Cells[3].Text = "4";//Int32.Parse(DataBinder.Eval(e.Row.DataItem, "numCursos").ToString()).ToString();
        }
    }
}