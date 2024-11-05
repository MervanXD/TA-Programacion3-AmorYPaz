using AmorYPazBackend.ServicioWS;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;

namespace AmorYPazBackend
{
    public partial class RegistrarInstitucion : System.Web.UI.Page
    {
        private DirectorWSClient daoDirector = new DirectorWSClient();
        private InstitucionEducativaWSClient daoInstitucion = new InstitucionEducativaWSClient();
        private institucionEducativa institucionEdu;
        private Estado estado;
        protected void Page_Init (object sender, EventArgs e)
        {
            var directores = daoDirector.listarDirectoresTodas();

            // Crea una lista anónima combinando nombres y apellidoPaterno
            var directoresConNombreCompleto = directores.Select(d => new {
                NombreCompleto = d.nombres + " " + d.apellidoPaterno + " " + d.apellidoMaterno,
                d.idPersona
            }).ToList();

            // Asigna la lista combinada al DropDownList
            if (!IsPostBack)
            {
                ddlDirector.DataSource = directoresConNombreCompleto;
                ddlDirector.DataTextField = "NombreCompleto";
                ddlDirector.DataValueField = "idPersona";
                ddlDirector.DataBind();
            }
            //Verificamos si es una acción de modificación
            string accion = Request.QueryString["accion"];
            if (accion == null)
            {
                lblDirector.Text = "Asignar Director:";

                //lblTitulo.Text = "UGEL04 - Añadir Institución";
                lblTitulo.Text = "Añadir Institución";
                institucionEdu = new institucionEducativa();
                Cargar_Foto(sender, e);
                //estado = Estado.Nuevo;
                if (!IsPostBack)
                {
                    Session["institucion"] = null;
                }
            }
            else if (accion == "visualizar" && accion != null && Session["institucion"] != null)
            {
                lblDirector.Text = "Director Asignado:";
                institucionEdu = (institucionEducativa)Session["institucion"];
                lblTitulo.Text = institucionEdu.ugel.codigo + " - Visualizar Institución";
                //estado = Estado.Modificar;
                txtNombre.Text = institucionEdu.nombre;
                txtDireccion.Text = institucionEdu.direccion;
                string nombre_director = institucionEdu.director.nombres.ToString() +
                    institucionEdu.director.apellidoPaterno.ToString() +
                    institucionEdu.director.apellidoMaterno.ToString();
                ddlDirector.SelectedValue = nombre_director;
                txtTelefono.Text = institucionEdu.telefono;
                txtEmail.Text = institucionEdu.correoElectronico;

                //imagen
                if (institucionEdu.fotoInstitucion != null)
                {
                    string base64String = Convert.ToBase64String(institucionEdu.fotoInstitucion);
                    string imageUrl = "data:img/jpeg;base64," + base64String;
                    imgLogoPlaceholder.ImageUrl = imageUrl;
                }

                Deshabilitar_Componentes();


            }
            else if(accion == "modificar" && accion != null && Session["institucion"] != null)
            {
                institucionEdu = (institucionEducativa)Session["institucion"];
                estado = Estado.Modificar;
                lblTitulo.Text = institucionEdu.ugel.codigo + " - Modificar Institución";
                txtNombre.Text = institucionEdu.nombre;
                txtDireccion.Text = institucionEdu.direccion;
                string nombre_director = institucionEdu.director.nombres.ToString() +
                    institucionEdu.director.apellidoPaterno.ToString() +
                    institucionEdu.director.apellidoMaterno.ToString();
                ddlDirector.SelectedValue = nombre_director;
                txtTelefono.Text = institucionEdu.telefono;
                txtEmail.Text = institucionEdu.correoElectronico;
                if (!IsPostBack && institucionEdu.fotoInstitucion != null )
                {
                    string base64String = Convert.ToBase64String(institucionEdu.fotoInstitucion);
                    string imageUrl = "data:img/jpeg;base64," + base64String;
                    imgLogoPlaceholder.ImageUrl = imageUrl;
                }
                Cargar_Foto(sender, e);
            }

        }

        public void Deshabilitar_Componentes()
        {
            txtNombre.Enabled = false;
            txtDireccion.Enabled = false;
            txtTelefono.Enabled = false;
            txtEmail.Enabled = false;
            lbGuardar.Visible = false;
            ddlDirector.Enabled = false;
            fuLogo.Enabled = false;
            fuLogo.Visible= false;
        }

        protected void lbGuardar_Click(object sender, EventArgs e)
        {
            if (Page.IsValid)
            {
                //Asignamos los valores
                institucionEdu.nombre = txtNombre.Text;
                institucionEdu.direccion = txtDireccion.Text;
                institucionEdu.correoElectronico = txtEmail.Text;
                institucionEdu.telefono = txtTelefono.Text;
                institucionEdu.director = new director { idPersona = Int32.Parse(ddlDirector.SelectedValue) };
                institucionEdu.ugel = new ugel();
                int id_ugel = (int)Session["id_Director"];
                institucionEdu.ugel.idUgel = id_ugel;
                institucionEdu.fotoInstitucion = (byte[])Session["foto"];

                //if (estado == Estado.Nuevo)
                //    daoEmpleado.insertar(empleado);
                //else if (estado == Estado.Modificar)
                //    daoEmpleado.modificar(empleado);

                string script = ""; 
                if (estado == Estado.Nuevo)
                    daoInstitucion.insertarInstitucion(institucionEdu);
                else if (estado == Estado.Modificar)
                {
                    daoInstitucion.modificarInstitucion(institucionEdu);
                    Session["institucion"] = null;
                }


                //int resultado = daoInstitucion.insertarInstitucion(institucionEdu);
                if (estado == Estado.Nuevo)
                    script = "mostrarModal('Se realizó el registro con éxito', 'GestionarInstituciones.aspx');";
                else if (estado == Estado.Modificar)
                {
                    script = "mostrarModal('Se modificó con éxito', 'GestionarInstituciones.aspx');";
                }
                
                ClientScript.RegisterStartupScript(this.GetType(), "modal", script, true);
            }

            
        }

        protected void Cargar_Foto(object sender, EventArgs e)
        {
            if (IsPostBack && fuLogo.PostedFile != null && fuLogo.HasFile)
            {
                string extension = System.IO.Path.GetExtension(fuLogo.FileName);
                if (extension.ToLower() == ".jpg" || extension.ToLower() == ".jpeg" || extension.ToLower() == ".png" || extension.ToLower() == ".gif")
                {
                    string filename = Guid.NewGuid().ToString() + extension;
                    string filePath = Server.MapPath("~/Uploads/") + filename;
                    fuLogo.SaveAs(Server.MapPath("~/Uploads/") + filename);
                    imgLogoPlaceholder.ImageUrl = "~/Uploads/" + filename;
                    imgLogoPlaceholder.Visible = true;
                    FileStream fs = new FileStream(filePath, FileMode.Open, FileAccess.Read);
                    BinaryReader br = new BinaryReader(fs);
                    Session["foto"] = br.ReadBytes((int)fs.Length);
                    fs.Close();
                }
                else
                {
                    Response.Write("Por favor, selecciona un archivo de imagen válido.");
                }
            }
        }

        protected void lbCancelar_Click(object sender, EventArgs e)
        {

            Response.Redirect("GestionarInstituciones.aspx");
        }
    }
}