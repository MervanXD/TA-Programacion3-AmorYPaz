﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;

namespace AmorYPazBackend
{
    public partial class SuperIntendente : System.Web.UI.MasterPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                if (Session["NombreUsuario"] != null)
                {
                    string nombreDirector = Session["NombreUsuario"].ToString();
                    if (nombreDirector.Length > 17) nombreDirector = nombreDirector.Substring(0, 14) + "...";
                    GenerateSidebarMenu();
                    litNombreUsuario.Text = nombreDirector;
                }
                else
                {
                    Response.Redirect("InicioSesion.aspx");
                }
            }
            else
            {
                GenerateSidebarMenu();
            }
        }

        private void GenerateSidebarMenu()
        {
            List<(string Href, string IconClass, string Text)> itemsMenu = new List<(string, string, string)>();
            string tipoUsuario = Session["tipoUsuario"].ToString();
            string currentPage = System.IO.Path.GetFileName(Request.Path);
            if (tipoUsuario == "DIRECTOR_IE")
            {
                itemsMenu.Add(("MenuPrincipalDirectores.aspx", "fa-solid fa-school", "Menu Principal"));
                itemsMenu.Add(("GestionarAniosAcademicos.aspx", "fa-solid fa-calendar", "Año Académico"));
                itemsMenu.Add(("GestionarPlanesEstudio.aspx", "fa-solid fa-calendar-lines-pen", "Planes de Estudio"));
                itemsMenu.Add(("GestionarAlumnos.aspx", "fa-solid fa-person", "Alumnos"));
                itemsMenu.Add(("GestionarMatricula.aspx", "fa-solid fa-graduation-cap", "Matrícula"));
                itemsMenu.Add(("OpcionesReporte.aspx", "fa-solid fa-chart-line", "Reportes"));
            }
            else if (tipoUsuario == "DIRECTOR_UGEL")
            {
                itemsMenu.Add(("MenuPrincipal.aspx", "fa-solid fa-house", "Menu Principal"));
                itemsMenu.Add(("GestionarInstituciones.aspx", "fa-solid fa-building", "Instituciones"));
                itemsMenu.Add(("AdministrarDirectores.aspx", "fa-solid fa-people-group", "Administrar Directores"));
                itemsMenu.Add(("MenuPrincipal.aspx", "fa-solid fa-chart-line", "Reportes"));
            }

            foreach (var item in itemsMenu)
            {
                HtmlGenericControl li = new HtmlGenericControl("li");

                // Agregar la clase "active" si la página coincide con el enlace
                if (currentPage == System.IO.Path.GetFileName(item.Href))
                {
                    li.Attributes["class"] = "nav-item mb-1 active"; // Clase "active" para resaltar
                }
                else
                {
                    li.Attributes["class"] = "nav-item mb-1";
                }

                HtmlAnchor anchor = new HtmlAnchor
                {
                    HRef = item.Href
                };
                anchor.InnerHtml = $"<i class='{item.IconClass} pe-2'></i> {item.Text}";
                li.Controls.Add(anchor);
                menuPlaceHolder.Controls.Add(li);
            }

        }

        protected void lbCerrarSesion_Click(object sender, EventArgs e)
        {
            string script = "showModalConfirmation();";
            ScriptManager.RegisterStartupScript(this, GetType(), "ShowModalConfirmation", script, true);
        }
    }
}