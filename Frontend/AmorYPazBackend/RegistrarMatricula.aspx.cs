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
    public partial class RegistrarMatricula : System.Web.UI.Page
    {
        private BindingList<anioAcademico> aniosAcad;
        private AnioAcademicoWSClient daoAnioAcademico;
        private InstitucionEducativaWSClient daoInstitucion;

        private GradoWSClient daoGrado;
        private BindingList<grado> grados;

        private MatriculaWSClient daoMatricula;
        private matricula matricula;

        

        protected void Page_Load(object sender, EventArgs e)
        {
            matricula = new matricula();
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
                            txtVacantes.Enabled = false;
                            daoAnioAcademico = new AnioAcademicoWSClient();
                            aniosAcad = new BindingList<anioAcademico>(daoAnioAcademico.listarAnioAcademicoPorIdIE(ie.idInstitucion));
                            //int anioActual = DateTime.Now.Year;

                            BindingList<anioAcademico> aniosFiltrados = new BindingList<anioAcademico>(
                            aniosAcad.Where(a => a.estado == "Activo").ToList());

                            ddlAnio.DataSource = aniosFiltrados;
                            ddlAnio.DataValueField = "idAnio";
                            ddlAnio.DataTextField = "numero";
                            ddlAnio.DataBind();

                            //grados
                            daoGrado = new GradoWSClient();
                            grados = new BindingList<grado>(daoGrado.listarPorIdIE(ie.idInstitucion));
                            List<GradoNivelItem> listaFormateada = grados.Select(g => new GradoNivelItem
                            {
                                IdGrado = g.idGrado,
                                GradoNivel = $"{g.numero}° {g.nivel}"
                            }).ToList();
                            ddlGrado.DataSource = listaFormateada;
                            ddlGrado.DataValueField = "idGrado";
                            ddlGrado.DataTextField = "GradoNivel";
                            ddlGrado.DataBind();

                            dtpFecha.Value = DateTime.Now.ToString("yyyy-MM-dd");
                            

                        }
                        catch (Exception ex)
                        {

                        }
                    }
                }
            }




            
        }
        protected void btnBuscar_Click(object sender, EventArgs e)
        {
            // Lógica para buscar el alumno por DNI
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            // Lógica para guardar
            if (Page.IsValid)
            {
                daoInstitucion = new InstitucionEducativaWSClient();
                int idDirector = Int32.Parse(Session["idDirector"].ToString());
                institucionEducativa ie = daoInstitucion.obtenerIEPorIdDirector(idDirector);

                daoGrado = new GradoWSClient();

                if (ie != null)
                {
                    int id = ie.idInstitucion;
                    matricula.institucion = ie;
                }
                anioAcademico anio = new anioAcademico();
                anio.idAnio = Int32.Parse(ddlAnio.SelectedValue);
                matricula.anioAcademico = anio;

                grado grado = new grado();
                grado.idGrado = Int32.Parse(ddlGrado.SelectedValue);
                matricula.grado = grado;

                matricula.fecha = DateTime.Parse(dtpFecha.Value);
                matricula.fechaSpecified = true;
                matricula.estado = "MATRICULADO";
                string tipoString = "APROBADO";
                matricula.tipoMatricula = tipoString;

                estudiante estudiante = new estudiante();
                estudiante.idPersona = 28;
                matricula.estudiante = estudiante;

                int resultado;
                daoMatricula = new MatriculaWSClient();
                resultado = daoMatricula.insertarMatricula(matricula);
                String script = "";
                if (resultado != 0)
                {
                    script = "mostrarModal('Se registró con éxito', 'GestionarMatricula.aspx');";
                }
                else
                {
                    script = "mostrarModal('No se pudo registrar', 'GestionarMatricula.aspx');";
                }
                ClientScript.RegisterStartupScript(this.GetType(), "modal", script, true);


            }
        }

        protected void btnCancelar_Click(object sender, EventArgs e)
        {
            // Lógica para cancelar (quizás limpiar los campos o redirigir a otra página)
            Response.Redirect("GestionarMatricula.aspx");
        }

    }
}