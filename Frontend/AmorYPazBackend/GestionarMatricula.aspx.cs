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
    public partial class GestionarMatricula : System.Web.UI.Page
    {
        private InstitucionEducativaWSClient daoInstitucion;
        private BindingList<matricula> matriculas;

        private MatriculaWSClient daoMatricula;

        private GradoWSClient daoGrado = new GradoWSClient();

        protected void Page_Load(object sender, EventArgs e)
        {
            daoMatricula = new MatriculaWSClient();
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
                            lblInfoMatricula.Text = $"{ie.nombre}";

                            if(daoMatricula.listarMatriculasPorIdIE(ie.idInstitucion) == null)
                            {
                                matriculas = new BindingList<matricula>();
                                
                            }
                            else
                            {
                                matriculas = new BindingList<matricula>(daoMatricula.listarMatriculasPorIdIE(ie.idInstitucion));
                            }
                            gvMatriculas.DataSource = matriculas;
                            gvMatriculas.DataBind();



                            gvMatriculas.DataBind();



                        }
                        catch (Exception ex)
                        {
                            matriculas = new BindingList<matricula>();
                            gvMatriculas.DataSource = matriculas;
                            gvMatriculas.DataBind();
                        }
                    }
                }
            }
        }

        protected void lbModificar_Click(object sender, EventArgs e)
        {

        }

        protected void lbEliminar_Click(object sender, EventArgs e)
        {

        }

        protected void btnActual_Click(object sender, EventArgs e)
        {

        }

        protected void btnHistorial_Click(object sender, EventArgs e)
        {

        }

        protected void gvMatriculas_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                int idMatricula = Int32.Parse(DataBinder.Eval(e.Row.DataItem, "idMatricula").ToString());
                grado grado;
                e.Row.Cells[0].Text = ((anioAcademico)DataBinder.Eval(e.Row.DataItem, "anioAcademico")).numero.ToString();
                e.Row.Cells[1].Text = string.Format("{0} {1} {2}",
                    DataBinder.Eval(e.Row.DataItem, "estudiante.nombres"),
                    DataBinder.Eval(e.Row.DataItem, "estudiante.apellidoPaterno"),
                    DataBinder.Eval(e.Row.DataItem, "estudiante.apellidoMaterno"));
                e.Row.Cells[3].Text = ((DateTime)DataBinder.Eval(e.Row.DataItem, "fecha")).ToString("dd/MM/yyyy");

            }
        }

        protected void gvMatriculas_RowCommand(object sender, GridViewCommandEventArgs e)
        {

        }

        protected void btnAgregar_Click(object sender, EventArgs e)
        {
            Response.Redirect("RegistrarMatricula.aspx");
        }

        protected void btnConfirmarEliminar_Click(object sender, EventArgs e)
        {

        }
    }
}