using AmorYPazBackend.ServicioWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace AmorYPazBackend
{
    public partial class RegistrarAnioAcademico : System.Web.UI.Page
    {
        private anioAcademico anio;
        private AnioAcademicoWSClient daoAnioAcademico;
        private InstitucionEducativaWSClient daoInstitucion;
        protected void Page_Load(object sender, EventArgs e)
        {
            anio = new anioAcademico();
            if (!IsPostBack)
            {
                if (Session["idDirector"] != null)
                {
                    daoInstitucion = new InstitucionEducativaWSClient();
                    int idDirector = Int32.Parse(Session["idDirector"].ToString());
                    institucionEducativa ie = daoInstitucion.obtenerIEPorIdDirector(idDirector); //revisar si es necesario ServicioUGEL o si se creo un servicio de más
                    if (ie != null)
                    {
                        lblNombreInstitucion.Text = $"{ie.nombre}";
                    }
                }
            }
        }

        protected void lbGuardar_Click(object sender, EventArgs e)
        {
            if (Page.IsValid)
            {
                daoAnioAcademico = new AnioAcademicoWSClient();
                //anio.activo = true;
                anio.numero = int.Parse(txtAnio.Text);
                anio.estado = "Activo";
                anio.fechaInicio = DateTime.Parse(dtpFechaInicio.Value);
                anio.fechaFin = DateTime.Parse(dtpFechaFin.Value);
                anio.fechaInicioSpecified = true;
                anio.fechaFinSpecified = true;
                daoInstitucion = new InstitucionEducativaWSClient();
                int idDirector = Int32.Parse(Session["idDirector"].ToString());
                institucionEducativa ie = daoInstitucion.obtenerIEPorIdDirector(idDirector);
                if(ie != null)
                {
                    anio.idIE = ie.idInstitucion;
                }

                int resultado = daoAnioAcademico.insertarAnioAcademico(anio);


                String script = "mostrarModal('Se realizó el registro con éxito', 'GestionarPlanesEstudio.aspx');";
                ClientScript.RegisterStartupScript(this.GetType(), "modal", script, true);
            }
        }

        protected void lbCancelar_Click(object sender, EventArgs e)
        {
            Response.Redirect("GestionarPlanesEstudio.aspx");
        }
    }
}