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
    public partial class AdministrarDirectores : System.Web.UI.Page
    {
        private DirectorWSClient daoDirector;
        private BindingList<director> directores;
        private director director;
        protected void Page_Load(object sender, EventArgs e)
        {
            daoDirector = new DirectorWSClient();
            directores = new BindingList<director>(daoDirector.listarTodosDirectores());
            dgvDirectores.DataSource = directores;
            dgvDirectores.DataBind();
        }
        protected void dgvDirectores_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                e.Row.Cells[0].Text = DataBinder.Eval(e.Row.DataItem, "nombres").ToString() + " " + DataBinder.Eval(e.Row.DataItem, "apellidoPaterno").ToString();
                e.Row.Cells[1].Text = Convert.ToChar(DataBinder.Eval(e.Row.DataItem, "sexo")).ToString();
                DateTime fechaNombramiento = DateTime.Parse(DataBinder.Eval(e.Row.DataItem, "fechaNombramiento").ToString());
                e.Row.Cells[2].Text = fechaNombramiento.ToString("dd/MM/yyyy");
                e.Row.Cells[3].Text = (DataBinder.Eval(e.Row.DataItem, "email").ToString());
            }
        }
        protected void dgvDirectores_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            dgvDirectores.PageIndex = e.NewPageIndex;
        }
        protected void lbBuscar_Click(object sender, EventArgs e)
        {
            director = daoDirector.buscarDirector(Int32.Parse(txtNombreDNI.Text));
            directores = new BindingList<director>();
            directores.Add(director);
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
            daoDirector.eliminarDirector(idDirector);

            Response.Redirect("AdministrarDirectores.aspx");
        }

        protected void lbRegistrar_Click(object sender, EventArgs e)
        {
            Response.Redirect("RegistrarDirector.aspx");
        }
    }
}