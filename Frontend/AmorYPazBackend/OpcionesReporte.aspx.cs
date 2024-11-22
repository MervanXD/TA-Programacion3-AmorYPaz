using AmorYPazBackend.ServicioWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace AmorYPazBackend
{
    public partial class OpcionesReporte : System.Web.UI.Page
    {
        private GradoWSClient daoGrado = new GradoWSClient();
        private AnioAcademicoWSClient daoAnioAcademico = new AnioAcademicoWSClient();
        private InstitucionEducativaWSClient daoInstitucion = new InstitucionEducativaWSClient();
        private grado grd;
        private int idDirector;
        private int idInstitucionE;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                idDirector = Int32.Parse(Session["idDirector"].ToString());
                idInstitucionE = daoInstitucion.obtenerIEPorIdDirector(idDirector).idInstitucion;

                anioAcademico[] anios = daoAnioAcademico.listarAnioAcademicoPorIdIE(idInstitucionE);
                if (anios != null)
                {
                    ddlAnioAcademico.DataSource = anios;
                    ddlAnioAcademico.DataTextField = "numero";
                    ddlAnioAcademico.DataValueField = "idAnio";
                    ddlAnioAcademico.DataBind();
                }

            }
        }
        protected void lbRepMatricula_Click(object sender, EventArgs e)
        {
            string script = "mostrarModalMatricula();";
            ScriptManager.RegisterStartupScript(this, GetType(), "", script, true);
        }

        protected void ddlNiveles_SelectedIndexChanged(object sender, EventArgs e)
        {
            idDirector = Int32.Parse(Session["idDirector"].ToString());
            idInstitucionE = daoInstitucion.obtenerIEPorIdDirector(idDirector).idInstitucion;
            string nivelSeleccionado = ddlNiveles.SelectedValue;
            grado[] grados = daoGrado.listarPorIdIENivel(idInstitucionE, nivelSeleccionado);
            if (grados != null)
            {
                ddlMatricula.DataSource = grados;
                ddlMatricula.DataTextField = "numero";
                ddlMatricula.DataValueField = "idGrado";
                ddlMatricula.DataBind();
            }
        }

        protected void lbClickGuardar(object sender, EventArgs e)
        {
            if (Page.IsValid)
            {
                idDirector = Int32.Parse(Session["idDirector"].ToString());
                //Asignamos los valores
                idInstitucionE = daoInstitucion.obtenerIEPorIdDirector(idDirector).idInstitucion;
                int idAnioAc = Int32.Parse(ddlAnioAcademico.SelectedValue);
                int idGrado = Int32.Parse(ddlMatricula.SelectedValue);
                Response.Redirect("ReporteMatriculas.aspx?anio=" + idAnioAc + "&institucion=" + idInstitucionE
                    + "&grado=" + idGrado);
            }
        }

        protected void ReporteGeneral_Click(object sender, EventArgs e)
        {
            idDirector = Int32.Parse(Session["idDirector"].ToString());
            idInstitucionE = daoInstitucion.obtenerIEPorIdDirector(idDirector).idInstitucion;
            string nombreIE = daoInstitucion.obtenerIEPorIdDirector(idDirector).nombre;
            Response.Redirect("ReporteGeneral.aspx?institucion=" + idInstitucionE + "&nombreIE=" + nombreIE);
        }
    }
}