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
        protected void Page_Init(object sender, EventArgs e)
        {
            anio = new anioAcademico();
            if (Session["idDirector"] != null)
            {
                daoInstitucion = new InstitucionEducativaWSClient();
                int idDirector = Int32.Parse(Session["idDirector"].ToString());
                institucionEducativa ie = daoInstitucion.obtenerIEPorIdDirector(idDirector); //revisar si es necesario ServicioUGEL o si se creo un servicio de más
                if (ie != null)
                {
                    lblNombreInstitucion.Text = $"{ie.nombre}";
                }

                string accion = Request.QueryString["accion"];
                if (accion == null && Session["idDirector"] != null)
                {
                    lblTipoOperacion.Text = "Registro de Año Académico";
                    estado = Estado.Nuevo;
                }
                else if (accion == "modificar" && accion != null && Session["anioAcademico"] != null)
                {
                    lblTipoOperacion.Text = "Modificación de Año Académico";
                    anio = (anioAcademico)Session["anioAcademico"];
                    estado = Estado.Modificar;
                    txtAnio.Text = anio.numero.ToString();
                    dtpFechaInicio.Value = anio.fechaInicio.ToString("yyyy-MM-dd");
                    dtpFechaFin.Value = anio.fechaFin.ToString("yyyy-MM-dd");
                    Deshabilitar_Componentes();
                }
            }
        }

        public void Deshabilitar_Componentes()
        {
            txtAnio.Enabled = false;
        }

        protected void lbGuardar_Click(object sender, EventArgs e)
        {
            if (Page.IsValid)
            {
                String script = "";
                daoAnioAcademico = new AnioAcademicoWSClient();
                anio.numero = int.Parse(txtAnio.Text);
                anio.fechaInicio = DateTime.Parse(dtpFechaInicio.Value);
                anio.fechaFin = DateTime.Parse(dtpFechaFin.Value);
                if (anio.fechaFin.Year != anio.numero || anio.fechaInicio.Year != anio.numero || anio.fechaInicio > anio.fechaFin) //validando que los datos ingresados sean correctos
                    script = "mostrarModal('Las fechas ingresadas no son correctas', 'RegistrarAnioAcademico.aspx');";
                else {
                    anio.fechaInicioSpecified = true;
                    anio.fechaFinSpecified = true;
                    if (anio.fechaFin < DateTime.Now) anio.estado = "FINALIZADO";
                    else if (anio.fechaInicio > DateTime.Now) anio.estado = "PLANIFICADO";
                    else anio.estado = "EN CURSO";
                    anio.activo = true;
                    daoInstitucion = new InstitucionEducativaWSClient();
                    int idDirector = Int32.Parse(Session["idDirector"].ToString());
                    institucionEducativa ie = daoInstitucion.obtenerIEPorIdDirector(idDirector);
                    if (ie != null) anio.idIE = ie.idInstitucion;
                    int resultado;
                    if (estado == Estado.Nuevo)
                        resultado = daoAnioAcademico.insertarAnioAcademico(anio);
                    else if (estado == Estado.Modificar)
                    {
                        resultado = daoAnioAcademico.modificarAnioAcademico(anio);
                        Session["anioAcademico"] = null;
                    }
                    if (estado == Estado.Nuevo)
                        script = "mostrarModal('Se realizó el registro con éxito', 'GestionarAniosAcademicos.aspx');";
                    else if (estado == Estado.Modificar)
                        script = "mostrarModal('Se modificó con éxito', 'GestionarAniosAcademicos.aspx');";
                }
                ClientScript.RegisterStartupScript(this.GetType(), "modal", script, true);
            }
        }

        protected void lbCancelar_Click(object sender, EventArgs e)
        {
            Response.Redirect("GestionarAniosAcademicos.aspx");
        }
    }
}