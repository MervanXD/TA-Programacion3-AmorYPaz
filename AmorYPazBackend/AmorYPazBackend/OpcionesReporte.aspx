<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="OpcionesReporte.aspx.cs" Inherits="AmorYPazBackend.OpcionesReporte" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphHead" runat="server">
    <link href="Content/EstilosOpcionesReporte.css" rel="stylesheet" />
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphTitulo" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="content">
        <div class="card">
            <span>Mejores alumnos</span>
            <i class="fas fa-user-graduate text-primary"></i>
        </div>
        <div class="card">
            <span>Estadísticas</span>
            <i class="fas fa-chart-line text-success"></i>
        </div>
        <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
            </div>
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <div class="d-block w-100">Slide 1: Ejemplo de contenido</div>
                </div>
                <div class="carousel-item">
                    <div class="d-block w-100">Slide 2: Ejemplo de contenido</div>
                </div>
                <div class="carousel-item">
                    <div class="d-block w-100">Slide 3: Ejemplo de contenido</div>
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-sLNwh+oaQnfIJ0sQ4y+6JYntI6hp0GV6RUV/Dhr0Oua+mv2qlvB9Ip6glt5lgSKg" crossorigin="anonymous"></script>

</asp:Content>
