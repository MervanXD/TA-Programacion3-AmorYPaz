<%@ Page Title="" Language="C#" MasterPageFile="~/Director.Master" AutoEventWireup="true" CodeBehind="MenuPrincipalDirectores.aspx.cs" Inherits="AmorYPazBackend.MenuPrincipalDirectores" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphHead" runat="server">
    <link href="Content/EstilosMenuDirector.css" rel="stylesheet" />
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphTitulo" runat="server">
    <Tittle>Menú Principal</Tittle>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container bg-light min-vh-100">
    <div class="card">
        <div class="card-header">
            <h2>Noticias</h2>
        </div>
        <div class="card-body">
            <div id="carrusel" class="carousel slide" data-bs-ride="carousel" data-bs-interval="20000">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="img/img1.PNG" class="d-block w-100" width="300" height="300" alt="Imagen 1">
                    </div>
                    <div class="carousel-item">
                        <img src="img/img2.PNG" class="d-block w-100" width="300" height="300" alt="Imagen 2">
                    </div>
                    <div class="carousel-item">
                        <img src="img/img3.PNG" class="d-block w-100" width="300" height="300" alt="Imagen 3">
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" color="black" data-bs-target="#carrusel" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Anterior</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carrusel" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Siguiente</span>
                </button>
            </div>
        </div>
    </div>
     <div class="text-center mt-4">
         <!--Esto es para que muestre el nombre de la IE en la pagina principal del director-->
        <asp:Label ID="lblInfoIE" runat="server" CssClass="label-title"></asp:Label>
    </div>
    <div class="row">
    <div class="col-md-4 profile">
     <img alt="Placeholder image for profile" height="100" src="https://placehold.co/100x100" width="100"/>
     <p>
      Nombre
      <br/>
      Grado y seccion
     </p>
    </div>
    <div class="col-md-4 profile">
     <img alt="Placeholder image for profile" height="100" src="https://placehold.co/100x100" width="100"/>
     <p>
      Nombre
      <br/>
      Grado y seccion
     </p>
    </div>
    <div class="col-md-4 profile">
     <img alt="Placeholder image for profile" height="100" src="https://placehold.co/100x100" width="100"/>
     <p>
      Nombre
      <br/>
      Grado y seccion
     </p>
    </div>
   </div>
</div>
</asp:Content>
