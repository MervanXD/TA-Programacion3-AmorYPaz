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
        }
        protected void lbBuscar_Click(object sender, EventArgs e)
        {
            if (txtNombreDNI.Text != "")
            {
                director = daoDirector.buscarDirector(Int32.Parse(txtNombreDNI.Text));
                directores = new BindingList<director>();
                directores.Add(director);
                dgvDirectores.DataSource = directores;
                dgvDirectores.DataBind();
            }
            else {
                directores = new BindingList<director>(daoDirector.listarDirectoresTodas());
                dgvDirectores.DataSource = directores;
                dgvDirectores.DataBind();
            }
            
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