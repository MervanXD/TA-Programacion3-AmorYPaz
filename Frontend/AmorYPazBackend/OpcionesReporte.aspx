<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="OpcionesReporte.aspx.cs" Inherits="AmorYPazBackend.OpcionesReporte" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphHead" runat="server">
    <link href="Content/EstilosOpcionesReporte.css" rel="stylesheet" />
    <script src="Scripts/scriptsReporteMatricula.js"></script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphTitulo" runat="server">
    Reportes
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-sLNwh+oaQnfIJ0sQ4y+6JYntI6hp0GV6RUV/Dhr0Oua+mv2qlvB9Ip6glt5lgSKg" crossorigin="anonymous"></script>
    <div class="content">
        <div class="card">
            <span>Mejores alumnos</span>
            <i class="fas fa-user-graduate text-primary"></i>
        </div>
        <div class="card">
            <asp:LinkButton ID="ReporteGeneral"
                runat="server"
                Text="ReporteGeneral"
                CssClass="btn w-100 h-100 text-decoration-none text-dark d-flex align-items-center justify-content-between"
                OnClick="ReporteGeneral_Click">
                <span>Estadísticas</span>
                <i class="fas fa-chart-line text-success"></i>
            </asp:LinkButton>
        </div>
        <div class="card btn d-flex align-items-center justify-content-center"
            style="cursor: pointer;"
            data-bs-toggle="modal"
            data-bs-target="#modalGradosAnios">
            <asp:LinkButton ID="lbRepMatricula"
                runat="server"
                Text="Matrículas"
                CssClass="btn w-100 h-100 text-decoration-none text-dark d-flex align-items-center justify-content-between"
                OnClick="lbRepMatricula_Click">
                <span>Matrículas</span>
                <i class="fas fa-chart-line text-success"></i>
            </asp:LinkButton>
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

    <asp:ScriptManager ID="ScriptManager1" runat="server" />
    <div class="modal" id="form-modal-Matricula">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Configuración del Reporte</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="container">
                        <asp:UpdatePanel ID="UpdatePanel1" runat="server">
                            <ContentTemplate>
                                <div class="row">
                                    <!-- Primera columna: Año Académico y Nivel -->
                                    <div class="col-md-6">
                                        <div class="mb-3">
                                            <asp:Label ID="lblAnioAcademico" runat="server" CssClass="col-form-label fw-bold" Text="Seleccione un Año Académico"></asp:Label>
                                            <asp:DropDownList ID="ddlAnioAcademico" runat="server" CssClass="form-select" Style="max-width: 300px;"></asp:DropDownList>
                                        </div>
                                        <div class="mb-3 mt-4">
                                            <asp:Label ID="lblNiveles" runat="server" CssClass="col-form-label fw-bold" Text="Seleccione un Nivel"></asp:Label>
                                            <asp:DropDownList ID="ddlNiveles" runat="server" CssClass="form-select" AutoPostBack="True" OnSelectedIndexChanged="ddlNiveles_SelectedIndexChanged" Style="max-width: 300px;">
                                                <asp:ListItem Value="0" Text="Seleccione un nivel"></asp:ListItem>
                                                <asp:ListItem Value="INICIAL" Text="INICIAL"></asp:ListItem>
                                                <asp:ListItem Value="PRIMARIA" Text="PRIMARIA"></asp:ListItem>
                                                <asp:ListItem Value="SECUNDARIA" Text="SECUNDARIA"></asp:ListItem>
                                            </asp:DropDownList>
                                        </div>
                                    </div>
                                    <!-- Segunda columna: Grado -->
                                    <div class="col-md-6">
                                        <div class="mb-3">
                                            <asp:Label ID="lblMatricula" runat="server" CssClass="col-form-label fw-bold" Text="Seleccione un Grado"></asp:Label>
                                            <asp:DropDownList ID="ddlMatricula" runat="server" CssClass="form-select" Style="max-width: 300px;">
                                                <asp:ListItem Value="0" Text="Seleccione un grado"></asp:ListItem>
                                            </asp:DropDownList>
                                        </div>
                                    </div>
                                </div>
                            </ContentTemplate>
                        </asp:UpdatePanel>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    <asp:LinkButton ID="LinkButton1" class="btn btn-primary" runat="server" OnClick="lbClickGuardar">Generar</asp:LinkButton>
                </div>
            </div>
        </div>
    </div>



</asp:Content>
