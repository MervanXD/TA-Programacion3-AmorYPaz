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
    public partial class AdministrarDirectores : System.Web.UI.Page
    {
        private DirectorWSClient daoDirector;
        private BindingList<director> directores;
        private director director;
        private InstitucionEducativaWSClient daoInstitucion;
        protected void Page_Load(object sender, EventArgs e)
        {
            daoDirector = new DirectorWSClient();
            try
            {
                directores = new BindingList<director>(daoDirector.listarDirectoresTodas());
                dgvDirectores.DataSource = directores;
                dgvDirectores.DataBind();
            }
            catch (Exception ex)
            {
                dgvDirectores.DataBind();
            }
        }
        protected void dgvDirectores_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            string nombreCompleto;
            string correo;
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                nombreCompleto = DataBinder.Eval(e.Row.DataItem, "nombres").ToString() + " " + DataBinder.Eval(e.Row.DataItem, "apellidoPaterno").ToString();
                e.Row.Cells[0].Text = nombreCompleto.Length > 25
                    ? nombreCompleto.Substring(0, 22) + "..."
                    : nombreCompleto;//esto con el fin de que si el texto es muy grande, que ocupe un espacio máximo
                e.Row.Cells[1].Text = Convert.ToChar(DataBinder.Eval(e.Row.DataItem, "sexo")).ToString();
                DateTime fechaNombramiento = DateTime.Parse(DataBinder.Eval(e.Row.DataItem, "fechaNombramiento").ToString());
                e.Row.Cells[2].Text = fechaNombramiento.ToString("dd/MM/yyyy");
                correo = (DataBinder.Eval(e.Row.DataItem, "email").ToString());
                e.Row.Cells[3].Text = correo.Length > 26
                    ? correo.Substring(0, 23) + "..."
                    : correo;//esto con el fin de que si el texto es muy grande, que ocupe un espacio máximo
            }
        }
        protected void dgvDirectores_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            dgvDirectores.PageIndex = e.NewPageIndex;
            dgvDirectores.DataBind();
        }
        protected void lbBuscar_Click(object sender, EventArgs e)
        {
            director[] direct = daoDirector.listarDirectoresPorNombre(txtNombre.Text);
            if (direct != null) directores = new BindingList<director>(direct);
            else directores = null;
            dgvDirectores.DataSource = directores;
            dgvDirectores.DataBind();
        }
        protected void lbModificar_Click(object sender, EventArgs e)
        {
            int idDirector = Int32.Parse(((LinkButton)sender).CommandArgument);
            director director = directores.SingleOrDefault(x => x.idPersona == idDirector);
            Session["director"] = director;
            Response.Redirect("RegistrarDirector.aspx?accion=modificar");
        }

        protected void lbEliminar_Click(object sender, EventArgs e)
        {

            int idDirector = Int32.Parse(((LinkButton)sender).CommandArgument);
            daoInstitucion = new InstitucionEducativaWSClient();

            institucionEducativa[] institus = daoInstitucion.listarPorNombreYUgel("", Int32.Parse(Session["idUGEL"].ToString()));
            if (institus != null) {
                BindingList<institucionEducativa> instituciones = new BindingList<institucionEducativa>(institus);
                foreach (var institucion in instituciones)
                {
                    if (institucion.director.idPersona == idDirector)
                    {
                        string script = "mostrarModal('Debe asignar otro director a la Institución Educativa que lo necesite, antes de eliminar el director', 'AdministrarDirectores.aspx');";
                        ScriptManager.RegisterStartupScript(this, GetType(), "modal", script, true);
                        return;
                    }
                }
            }
            daoDirector.eliminarDirector(idDirector);
            Response.Redirect("AdministrarDirectores.aspx");
        }

        protected void lbRegistrar_Click(object sender, EventArgs e)
        {
            Response.Redirect("RegistrarDirector.aspx");
        }
    }
}