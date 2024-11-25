using AmorYPazBackend.ServicioWS;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data.SqlClient;
using System.Linq;
using System.Runtime.ConstrainedExecution;
using System.ServiceModel.Channels;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace AmorYPazBackend
{
    public partial class RegistrarPlanDeEstudios : System.Web.UI.Page
    {
        private InstitucionEducativaWSClient daoInstitucion;
        private AnioAcademicoWSClient daoAnioAcademico;
        private GradoWSClient daoGrado;
        private BindingList<anioAcademico> aniosAcad;
        private BindingList<grado> grados;
        private PlanDeEstudioWSClient daoPlanEstudio;
        private BindingList<curso> cursos;
        private BindingList<curso> cursosSeleccionados;
        private BindingList<curso> cursosAnadir;
        private BindingList<curso> cursosEliminar;
        private CursoWSClient daoCurso;
        private curso nuevoCurso;
        private Estado estado;

        protected void Page_Load(object sender, EventArgs e)
        {
            string accion = Request.QueryString["accion"];
            daoGrado = new GradoWSClient();
            daoCurso = new CursoWSClient();

            if (accion == "visualizar") estado = Estado.Visualizar;
            else if (accion == "modificar") estado = Estado.Modificar;
            else estado = Estado.Nuevo;

            if (!IsPostBack)
            {    
                if (Session["idDirector"] != null)
                {
                    daoInstitucion = new InstitucionEducativaWSClient();
                    int idDirector = Int32.Parse(Session["idDirector"].ToString());
                    institucionEducativa ie = daoInstitucion.obtenerIEPorIdDirector(idDirector); //revisar si es necesario ServicioUGEL o si se creo un servicio de más
                    if (ie != null)
                    {
                        try
                        {
                            // Enlazando el ddl de años, considerando el actual y el siguiente únicamente
                            if (estado == Estado.Nuevo) {
                                daoAnioAcademico = new AnioAcademicoWSClient();
                                aniosAcad = new BindingList<anioAcademico>(daoAnioAcademico.listarAnioAcademicoPorIdIE(ie.idInstitucion));
                                int anioActual = DateTime.Now.Year;
                                BindingList<anioAcademico> aniosFiltrados = aniosAcad;
                                if (accion != "visualizar") aniosFiltrados = new BindingList<anioAcademico>(
                                        aniosAcad.Where(a => a.numero == anioActual || a.numero == anioActual + 1).ToList());
                                ddlAnio.DataSource = aniosFiltrados;
                                ddlAnio.DataValueField = "idAnio";
                                ddlAnio.DataTextField = "numero";
                                ddlAnio.DataBind();
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
                            }
                            if ((accion == "modificar" || accion == "visualizar") && Session["planEstudio"] != null)
                            {
                                planDeEstudio plan = (planDeEstudio)Session["planEstudio"];
                                txtDescripcion.Text = plan.descripcion;
                                List<string> listAux = new List<string>();
                                listAux.Add(plan.anioAcademico.numero.ToString());
                                ddlAnio.DataSource = listAux;
                                ddlAnio.DataBind();
                                grado gr = daoGrado.obtenerPorIdPlanEstudios(plan.idPlan);
                                listAux.Clear();
                                listAux.Add(gr.numero.ToString() + "° - " + gr.nivel);
                                ddlGrado.DataSource = listAux;
                                ddlGrado.DataBind();
                                curso[] cursosArray = daoCurso.listarPorIdPlan(plan.idPlan);
                                if (cursosArray != null) cursos = new BindingList<curso>(cursosArray);
                                else cursos = null;
                                gvCursos.DataSource = cursos;
                                gvCursos.DataBind();
                                ViewState["CursosSeleccionados"] = cursos;
                                ddlAnio.Enabled = false;
                                ddlGrado.Enabled = false;
                                if (accion == "modificar") lblTitulo.Text = "Modificar Plan de Estudios";
                                else {
                                    lblTitulo.Text = "Visualizar Plan de Estudios";
                                    lblCurso.Text = "Los cursos que contiene el Plan de Estudios son los siguientes:";
                                    txtDescripcion.Enabled = false;
                                    lbBuscarCurso.Enabled = false;
                                    lbBuscarCurso.Visible = false;
                                    lbAgregarCurso.Enabled = false;
                                    lbAgregarCurso.Visible = false;
                                    lbGuardar.Enabled = false;
                                    lbGuardar.Visible = false;
                                }
                            }
                        }
                        catch (Exception ex) {
                            Console.WriteLine(ex.Message);
                        }
                    }
                }
            }

        }
        protected void lbGuardar_Click(object sender, EventArgs e)
        {
            daoPlanEstudio = new PlanDeEstudioWSClient();
            string script = "";
            if (estado == Estado.Nuevo)
            {
                anioAcademico anio = new anioAcademico();
                planDeEstudio plan = new planDeEstudio();
                int idGrado = 0;
                if (!string.IsNullOrEmpty(ddlAnio.SelectedValue) && ddlAnio.SelectedValue != "0")
                {
                    anio.idAnio = Int32.Parse(ddlAnio.SelectedValue);
                    plan.anioAcademico = anio;
                }
                if (!string.IsNullOrEmpty(ddlGrado.SelectedValue) && ddlGrado.SelectedValue != "0")
                    idGrado = Int32.Parse(ddlGrado.SelectedValue);
                if (!string.IsNullOrEmpty(txtDescripcion.Text))
                    plan.descripcion = txtDescripcion.Text;

                if (ViewState["CursosSeleccionados"] != null)
                {
                    BindingList<curso> cursosSeleccionados = (BindingList<curso>)ViewState["CursosSeleccionados"];
                    plan.numCursos = cursosSeleccionados.Count();
                    plan.idPlan = daoPlanEstudio.insertarPlanDeEstudio(plan, idGrado);
                    if (plan.idPlan > 0)
                    {
                        daoGrado.asignarPlan(idGrado, plan.idPlan); //colocar el plan de estudio en el grado
                        foreach (curso cur in cursosSeleccionados)
                        { //aca se tiene que regisrar el curso
                            if (daoCurso.insertarCursoEnPlan(cur.idCurso, plan.idPlan) == 0) break;
                        }
                    }
                }
                else {
                    plan.numCursos = 0;
                    plan.idPlan = daoPlanEstudio.insertarPlanDeEstudio(plan, idGrado);
                } 
                script = "mostrarModal('Se realizó el registro con éxito', 'GestionarPlanesEstudio.aspx');";
            }
            else {
                planDeEstudio plan = (planDeEstudio)Session["planEstudio"];
                if (!string.IsNullOrEmpty(txtDescripcion.Text))
                    plan.descripcion = txtDescripcion.Text;
                cursosSeleccionados = (BindingList<curso>)ViewState["CursosSeleccionados"];
                if(cursosSeleccionados != null) plan.numCursos = cursosSeleccionados.Count();
                else plan.numCursos = 0;
                daoPlanEstudio.modificarPlanDeEstudio(plan);
                if (plan.idPlan > 0)
                {
                    cursosAnadir = (BindingList<curso>)ViewState["CursosAnadir"];
                    cursosEliminar = (BindingList<curso>)ViewState["CursosEliminar"];
                    if (cursosEliminar != null) {
                        foreach (curso cur in cursosEliminar)
                            if(daoCurso.eliminarCursoDePlan(cur.idCurso, plan.idPlan) == 0) break;
                    }
                    if (cursosAnadir != null) {
                        foreach (curso cur in cursosAnadir)
                            if(daoCurso.insertarCursoEnPlan(cur.idCurso, plan.idPlan) ==0) break;
                    }
                    
                }
                script = "mostrarModal('Se realizó la modificación con éxito', 'GestionarPlanesEstudio.aspx');";
            }
            ScriptManager.RegisterStartupScript(this, GetType(), "modal", script, true);
        }

        protected void lbCancelar_Click(object sender, EventArgs e)
        {
            Response.Redirect("GestionarPlanesEstudio.aspx");
        }

        protected void lbBuscarCurso_Click(object sender, EventArgs e)
        {

            string script = "window.onload = function() { showModalForm() };";
            ScriptManager.RegisterStartupScript(this, GetType(), "", script, true);
        }

        protected void gvCursos_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvCursos.PageIndex = e.NewPageIndex;
            gvCursos.DataSource = (BindingList<curso>)ViewState["CursosSeleccionados"];
            gvCursos.DataBind();
        }

        protected void gvCursosModal_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvCursosModal.PageIndex = e.NewPageIndex;
            gvCursosModal.DataSource = (BindingList<curso>)ViewState["cursosModal"];
            gvCursosModal.DataBind();
            upBusqCursos.Update();
        }

        protected void lbBuscarCursoModal_Click(object sender, EventArgs e)
        {
            string script = "mostrarModalCursos();";
            ScriptManager.RegisterStartupScript(this, GetType(), "MostrarModalCursos", script, true);
            string nombreCurso = txtNombreCursoModal.Text.Trim();

            daoCurso = new CursoWSClient();
            curso[] cursosArray = daoCurso.listarPorIdNombreCursos(nombreCurso, Int32.Parse(Session["idIE"].ToString()));
            if (cursosArray == null) cursos = null;
            else cursos = new BindingList<curso>(cursosArray);

            gvCursosModal.DataSource = cursos;
            gvCursosModal.DataBind();
            ViewState["cursosModal"] = cursos;
        }

        protected void SeleccionarCurso_Click(object sender, EventArgs e)
        {
            LinkButton btn = (LinkButton)sender;
            string nombreCursoSeleccionado = btn.CommandArgument;
            string[] values = nombreCursoSeleccionado.Split(',');
            string nombreCurso = values[0];
            int idCurso = Int32.Parse(values[1]);
            curso cur = new curso();
            cur.idCurso = idCurso;
            cur.nombre = nombreCurso;
            if (ViewState["CursosSeleccionados"] == null)
                ViewState["CursosSeleccionados"] = new BindingList<curso>();

            if (estado != Estado.Nuevo) cursosSeleccionados = new BindingList<curso>(((BindingList<curso>)ViewState["CursosSeleccionados"]).ToList()); //trabajando con uno nuevo para modificar
            else cursosSeleccionados = (BindingList<curso>)ViewState["CursosSeleccionados"];

            if (!cursosSeleccionados.Any(c => c.idCurso == cur.idCurso))
            {
                cursosSeleccionados.Add(cur);
                if (estado == Estado.Modificar) {
                    if (ViewState["CursosAnadir"] == null) ViewState["CursosAnadir"] = new BindingList<curso>();
                    cursosAnadir = (BindingList<curso>)ViewState["CursosAnadir"];
                    cursosAnadir.Add(cur);
                    ViewState["CursosAnadir"] = cursosAnadir;
                    if (ViewState["CursosEliminar"] != null) { 
                        cursosEliminar = (BindingList<curso>)ViewState["CursosEliminar"];
                        curso cursoAux = cursosEliminar.FirstOrDefault(c => c.idCurso == cur.idCurso);
                        if (cursoAux != null) cursosEliminar.Remove(cursoAux);
                    }
                }
            }

            ViewState["CursosSeleccionados"] = cursosSeleccionados;
            gvCursos.DataSource = cursosSeleccionados;
            gvCursos.DataBind();


            // agregado Actualizar el grid del modal
            gvCursosModal.DataSource = ViewState["cursosModal"];
            gvCursosModal.DataBind();
            upBusqCursos.Update();
        }

        protected void EliminarCurso_Click(object sender, EventArgs e)
        {
            LinkButton btn = (LinkButton)sender;
            int idCursoEliminar = Int32.Parse(btn.CommandArgument);

            if (ViewState["CursosSeleccionados"] == null)
                ViewState["CursosSeleccionados"] = new BindingList<curso>();

            if (estado != Estado.Nuevo) cursosSeleccionados = new BindingList<curso>(((BindingList<curso>)ViewState["CursosSeleccionados"]).ToList()); //trabajando con uno nuevo para modificar
            else cursosSeleccionados = (BindingList<curso>)ViewState["CursosSeleccionados"];

            if (cursosSeleccionados != null)
            {
                curso cursoEliminar = cursosSeleccionados.FirstOrDefault(curso => curso.idCurso == idCursoEliminar);
                if(cursoEliminar != null) cursosSeleccionados.Remove(cursoEliminar);
                if (estado == Estado.Modificar) {
                    if (ViewState["CursosEliminar"] == null) ViewState["CursosEliminar"] = new BindingList<curso>();
                    cursosEliminar = (BindingList<curso>)ViewState["CursosEliminar"];
                    cursosEliminar.Add(cursoEliminar);
                    ViewState["CursosEliminar"] = cursosEliminar;
                    if (ViewState["CursosAnadir"] != null)
                    {
                        cursosAnadir = (BindingList<curso>)ViewState["CursosAnadir"];
                        curso cursoAux = cursosAnadir.FirstOrDefault(c => c.idCurso == cursoEliminar.idCurso);
                        if (cursoAux != null) cursosAnadir.Remove(cursoAux);
                    }
                }
            }

            ViewState["CursosSeleccionados"] = cursosSeleccionados;
            gvCursos.DataSource = cursosSeleccionados;
            gvCursos.DataBind();
        }
        protected void btnGuardarCurso_Click(object sender, EventArgs e)
        {
            string nombreCurso = txtRegistroCurso.Text.Trim();

            // Validar que el nombre del curso no esté vacío
            if (string.IsNullOrEmpty(nombreCurso))
            {
                string script = @"
                                alert('El nombre del curso no puede ser vacio.');
                                $('#crearCursoModal').modal('hide');
                                $('.modal-backdrop').remove();";
                ScriptManager.RegisterStartupScript(this, this.GetType(), "alert", script, true);
            }
            else {
                // Crear el objeto curso
                nuevoCurso = new curso();
                nuevoCurso.nombre = nombreCurso;
                daoCurso = new CursoWSClient();
                nuevoCurso.idCurso = daoCurso.insertarCurso(nuevoCurso, Int32.Parse(Session["idIE"].ToString()));

                if (nuevoCurso.idCurso != 0)
                {
                    if (ViewState["CursosSeleccionados"] == null)
                        ViewState["CursosSeleccionados"] = new BindingList<curso>();

                    if (estado != Estado.Nuevo) cursosSeleccionados = new BindingList<curso>(((BindingList<curso>)ViewState["CursosSeleccionados"]).ToList()); //trabajando con uno nuevo para modificar
                    else cursosSeleccionados = (BindingList<curso>)ViewState["CursosSeleccionados"];
                    cursosSeleccionados.Add(nuevoCurso);
                    ViewState["CursosSeleccionados"] = cursosSeleccionados;
                    gvCursos.DataSource = cursosSeleccionados;
                    gvCursos.DataBind();

                    if (estado == Estado.Modificar) {
                        if (ViewState["CursosAnadir"] == null) ViewState["CursosAnadir"] = new BindingList<curso>();
                        cursosAnadir = (BindingList<curso>)ViewState["CursosAnadir"];
                        cursosAnadir.Add(nuevoCurso);
                        ViewState["CursosAnadir"] = cursosAnadir;
                        if (ViewState["CursosEliminar"] != null)
                        {
                            cursosEliminar = (BindingList<curso>)ViewState["CursosEliminar"];
                            curso cursoAux = cursosEliminar.FirstOrDefault(c => c.idCurso == nuevoCurso.idCurso);
                            if (cursoAux != null) cursosEliminar.Remove(cursoAux);
                        }
                    }

                    string script = @"
                                    $('#crearCursoModal').modal('hide'); 
                                    $('.modal-backdrop').remove();";
                    ScriptManager.RegisterStartupScript(this, GetType(), "alert", script, true);

                    txtRegistroCurso.Text = "";
                }
                else
                {
                    string script = @"
                                    alert('Error al crear el curso.');
                                    $('#crearCursoModal').modal('hide');
                                    $('.modal-backdrop').remove();";
                    ScriptManager.RegisterStartupScript(this, GetType(), "alert", script, true);
                }
            }
        }

        protected void lbAgregarCurso_Click1(object sender, EventArgs e)
        {
            string script = "showModalFormRegistrarCurso();";
            ScriptManager.RegisterStartupScript(this, GetType(), "showModal", script, true);
        }

        protected void lbBuscarCursoModal_Click1(object sender, EventArgs e)
        {
            string nombreCurso = txtNombreCursoModal.Text.Trim();
            daoCurso = new CursoWSClient();
            curso[] cursosArray = daoCurso.listarPorIdNombreCursos(nombreCurso, Int32.Parse(Session["idIE"].ToString()));
            cursos = new BindingList<curso>(cursosArray);
            gvCursosModal.DataSource = cursos;
            gvCursosModal.DataBind();
            ViewState["cursosModal"] = cursos;
            //agregado
            upBusqCursos.Update();
        }

        protected void gvCursos_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                e.Row.Cells[0].Text = DataBinder.Eval(e.Row.DataItem, "nombre").ToString();
                if (estado == Estado.Visualizar) {
                    LinkButton lbEliminarCurso = (LinkButton)e.Row.FindControl("lbEliminarCurso");
                    lbEliminarCurso.Enabled = false;
                    lbEliminarCurso.Visible = false;
                }
            }
        }


        //agregado para lo de seleccionar en el modal
        protected bool EstaSeleccionado(string idCurso)
        {
            if (ViewState["CursosSeleccionados"] == null)
                return false;

            var cursosSeleccionados = (BindingList<curso>)ViewState["CursosSeleccionados"];
            return cursosSeleccionados.Any(c => c.idCurso.ToString() == idCurso);
        }
    }
}