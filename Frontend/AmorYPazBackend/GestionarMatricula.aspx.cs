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

                    daoGrado = new GradoWSClient();
                    ie.grados = daoGrado.listarPorIdIE(ie.idInstitucion);
                    if (ie.grados != null)
                    {
                        //Formatear una lista
                        var gradosCompletos = ie.grados.Select(g => new {
                            gradoCompleto = g.numero + "° " + g.nivel,
                            idGrado = g.idGrado
                        }).ToList();

                        if (!IsPostBack)
                        {
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
                            lblInfoMatricula.Text = $"{ie.nombre}";

                            if(daoMatricula.listarMatriculasPorIdIE(ie.idInstitucion) == null)
                                matriculas = new BindingList<matricula>();
                            else
                                matriculas = new BindingList<matricula>(daoMatricula.listarMatriculasPorIdIE(ie.idInstitucion));
                            ViewState["matriculas"] = matriculas;
                            gvMatriculas.DataSource = matriculas;
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
                daoGrado = new GradoWSClient();
                
                e.Row.Cells[0].Text = ((anioAcademico)DataBinder.Eval(e.Row.DataItem, "anioAcademico")).numero.ToString();
                e.Row.Cells[1].Text = ((estudiante)DataBinder.Eval(e.Row.DataItem, "estudiante")).nombres + " " + ((estudiante)DataBinder.Eval(e.Row.DataItem, "estudiante")).apellidoPaterno + " " + ((estudiante)DataBinder.Eval(e.Row.DataItem, "estudiante")).apellidoMaterno;
                e.Row.Cells[2].Text = ((grado)DataBinder.Eval(e.Row.DataItem, "grado")).numero + "° - " + ((grado)DataBinder.Eval(e.Row.DataItem, "grado")).nivel;
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

        protected void lbResultados_Click(object sender, EventArgs e)
        {
            LinkButton lb = (LinkButton)sender;
            int idMatricula = Int32.Parse(lb.CommandArgument);
            matriculas = (BindingList<matricula>)ViewState["matriculas"];
            matricula matriculaSeleccionada = matriculas.FirstOrDefault(m => m.idMatricula == idMatricula);
            Session["matricula"] = matriculaSeleccionada;
            Response.Redirect("GestionarNotas.aspx");
        }

        protected void ddlGrados_SelectedIndexChanged(object sender, EventArgs e)
        {
            daoMatricula = new MatriculaWSClient();

            if (Int32.Parse(ddlGrados.SelectedValue) == 0) // Si selecciona "Selecciona un grado"
            {
                // Restaura las matrículas originales guardadas en ViewState
                matriculas = (BindingList<matricula>)ViewState["matriculas"];
                gvMatriculas.DataSource = matriculas;
                gvMatriculas.EmptyDataText = string.Empty;
            }
            else
            {
                // Filtra las matrículas según el grado seleccionado
                matricula[] filtrados = daoMatricula.listarMatriculaPorGrado(Int32.Parse(ddlGrados.SelectedValue));
                if (filtrados != null)
                {
                    matriculas = new BindingList<matricula>(filtrados);
                    gvMatriculas.EmptyDataText = string.Empty;
                }
                else
                {
                    matriculas = null;
                    gvMatriculas.EmptyDataText = "No hay matriculas para el grado seleccionado.";
                }
                gvMatriculas.DataSource = matriculas;
            }

            gvMatriculas.DataBind();
        }
    }
}