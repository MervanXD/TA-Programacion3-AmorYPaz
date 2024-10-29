<%@ Page Title="" Language="C#" MasterPageFile="~/SuperIntendente.Master" AutoEventWireup="true" CodeBehind="MenuPrincipal.aspx.cs" Inherits="AmorYPazBackend.MenuPrincipal" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Menu Principal
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
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
            <asp:Label ID="lblInfoUGEL" runat="server" CssClass="label-title"></asp:Label>
        </div>
    </div>
    
</asp:Content>

