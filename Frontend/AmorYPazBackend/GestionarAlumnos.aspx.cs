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
    public partial class GestionarAlumnos : System.Web.UI.Page
    {
        private InstitucionEducativaWSClient daoInstitucion;
        private BindingList<estudiante> estudiantes;
        protected void Page_Load(object sender, EventArgs e)
        {
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
                            lblInfoIE.Text = $"{ie.nombre}";
                            estudiantes = new BindingList<estudiante>();
                            gvAlumnos.DataSource = estudiantes;
                            gvAlumnos.DataBind();

                        }
                        catch (Exception ex)
                        {

                        }
                    }
                }
            }
        }

        protected void AddButton_Click(object sender, EventArgs e)
        {

        }

        protected void gvAlumnos_RowDataBound(object sender, GridViewRowEventArgs e)
        {

        }

        protected void gvAlumnos_RowCommand(object sender, GridViewCommandEventArgs e)
        {

        }
    }
}