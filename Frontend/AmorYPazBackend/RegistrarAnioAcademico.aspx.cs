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
        private Estado estado;
        protected void Page_Init (object sender, EventArgs e)
        {
            anio = new anioAcademico();
            //if (!IsPostBack)
            //{
                string accion = Request.QueryString["accion"];
                if (accion == null && Session["idDirector"] != null)
                {
                    daoInstitucion = new InstitucionEducativaWSClient();
                    int idDirector = Int32.Parse(Session["idDirector"].ToString());
                    institucionEducativa ie = daoInstitucion.obtenerIEPorIdDirector(idDirector); //revisar si es necesario ServicioUGEL o si se creo un servicio de más
                    if (ie != null)
                    {
                        lblNombreInstitucion.Text = $"{ie.nombre}";
                    }
                    estado = Estado.Nuevo;
                }
                else if (accion == "modificar" && accion != null && Session["anioAcademico"] != null)
                {
                    anio = (anioAcademico)Session["anioAcademico"];
                    estado = Estado.Modificar;
                    txtAnio.Text = anio.numero.ToString();
                    dtpFechaInicio.Value = anio.fechaInicio.ToString("yyyy-MM-dd");
                    dtpFechaFin.Value = anio.fechaFin.ToString("yyyy-MM-dd");
                    //Deshabilitar_Componentes();
                }
            //}
        }

        public void Deshabilitar_Componentes()
        {
            txtAnio.Enabled = false;
            dtpFechaInicio.EnableTheming = false;
            dtpFechaFin.EnableTheming = false;
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
                if (ie != null)
                {
                    anio.idIE = ie.idInstitucion;
                }

                int resultado;
                if (estado == Estado.Nuevo)
                    resultado = daoAnioAcademico.insertarAnioAcademico(anio);
                else if (estado == Estado.Modificar)
                {
                    resultado = daoAnioAcademico.modificarAnioAcademico(anio);
                    Session["anioAcademico"] = null;
                }

                String script="";
                if (estado == Estado.Nuevo)
                     script = "mostrarModal('Se realizó el registro con éxito', 'GestionarAniosAcademicos.aspx');";
                else if (estado == Estado.Modificar)
                    script = "mostrarModal('Se modificó con éxito', 'GestionarAniosAcademicos.aspx');";
                ClientScript.RegisterStartupScript(this.GetType(), "modal", script, true);
            }
        }

        protected void lbCancelar_Click(object sender, EventArgs e)
        {
            Response.Redirect("GestionarAniosAcademicos.aspx");
        }
    }
}