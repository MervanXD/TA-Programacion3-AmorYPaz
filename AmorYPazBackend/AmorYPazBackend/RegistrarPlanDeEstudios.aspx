<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="RegistrarPlanDeEstudios.aspx.cs" Inherits="AmorYPazBackend.RegistrarPlanDeEstudios" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphHead" runat="server">
    <link href="Content/EstilosRegistrarPlanDeEstudios.css" rel="stylesheet"/>
    <link href="Content/estilosMasterPage.css" rel="stylesheet"/>
    <script src="Scripts/ScriptRegisPlan.js"></script>
    <script src="Scripts/scriptsMasterPage.js"></script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphTitulo" runat="server">
    Registrar Plan de Estudios
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container">
        <div class="card">
            <div class="header">
                <h1>
                    <asp:Label ID="lblNombreInstitucion" runat="server" Text=""></asp:Label>
                </h1>
                <img alt="Logo de la Institución Educativa" height="50" src="https://storage.googleapis.com/a1aa/image/rKEbxFzSEeUiIqGTB0DFrWqe0yI4e3gpY6IJzfj25XyfUSudC.jpg" width="50" />
            </div>
            <div class="card-body">
                <div class="row mb-3">
                    <div class="col-md-6">
                        <asp:Label ID="lblAnio" runat="server" Text="Año:" CssClass="col-form-label fw-bold"></asp:Label>
                        <asp:DropDownList ID="ddlAnio" runat="server" CssClass="form-select">
                            <asp:ListItem Text="Seleccione el año" Value="0"></asp:ListItem>
                                
                        </asp:DropDownList>
                        <asp:RequiredFieldValidator ID="rfvAnio" runat="server" ControlToValidate="ddlAnio" InitialValue="0"
                            ErrorMessage="Seleccione un año." ForeColor="Red"></asp:RequiredFieldValidator>
                    </div>
                    <div class="col-md-6">
                        <asp:Label ID="lblNivel" runat="server" Text="Nivel:" CssClass="col-form-label fw-bold"></asp:Label>
                        <asp:DropDownList ID="ddlNivel" runat="server" CssClass="form-select">
                            <asp:ListItem Text="Seleccione el nivel" Value="0"></asp:ListItem>
                            
                        </asp:DropDownList>
                        <asp:RequiredFieldValidator ID="rfvNivel" runat="server" ControlToValidate="ddlNivel"
                            ErrorMessage="El nivel es obligatorio." ForeColor="Red"></asp:RequiredFieldValidator>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-6">
                        <asp:Label ID="lblFechaInicio" runat="server" Text="Fecha de Inicio:" CssClass="col-form-label fw-bold"></asp:Label>
                        <asp:TextBox ID="txtFechaInicio" runat="server" CssClass="form-control" TextMode="Date"></asp:TextBox>
                        <asp:RequiredFieldValidator ID="rfvFechaInicio" runat="server" ControlToValidate="txtFechaInicio"
                            ErrorMessage="La fecha de inicio es obligatoria." ForeColor="Red"></asp:RequiredFieldValidator>
                    </div>
                    <div class="col-md-6">
                        <asp:Label ID="lblGrado" runat="server" Text="Grado:" CssClass="col-form-label fw-bold"></asp:Label>
                        <asp:DropDownList ID="ddlGrado" runat="server" CssClass="form-select">
                            <asp:ListItem Text="Seleccione el grado" Value="0"></asp:ListItem>
                            
                        </asp:DropDownList>
                        <asp:RequiredFieldValidator ID="rfvGrado" runat="server" ControlToValidate="ddlGrado"
                            ErrorMessage="El grado es obligatorio." ForeColor="Red"></asp:RequiredFieldValidator>
                    </div>

                </div>
                <div class="row mb-3">
                    <div class="col-md-6">
                        <asp:Label ID="lblFechaFin" runat="server" Text="Fecha de Fin:" CssClass="col-form-label fw-bold"></asp:Label>
                        <asp:TextBox ID="txtFechaFin" runat="server" CssClass="form-control" TextMode="Date"></asp:TextBox>
                        <asp:RequiredFieldValidator ID="rfvFechaFin" runat="server" ControlToValidate="txtFechaFin"
                            ErrorMessage="La fecha de fin es obligatoria." ForeColor="Red"></asp:RequiredFieldValidator>
                    </div>
                    <div class="col-md-6">
                        <asp:Label ID="lblDescripcion" runat="server" Text="Descripción:" CssClass="col-form-label fw-bold"></asp:Label>
                        <asp:TextBox ID="txtDescripcion" runat="server" CssClass="form-control" TextMode="MultiLine" Rows="4"></asp:TextBox>
                        <asp:RequiredFieldValidator ID="rfvDescripcion" runat="server" ControlToValidate="txtDescripcion"
                            ErrorMessage="La descripción es obligatoria." ForeColor="Red"></asp:RequiredFieldValidator>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-12">
                        <asp:Label ID="lblCursos" runat="server" Text="Cursos:" CssClass="col-form-label fw-bold"></asp:Label>
                        <asp:FileUpload ID="fuCursos" runat="server" CssClass="form-control"/>
                    </div>
                </div>
            </div>
            <div class="card-footer text-center">
                <asp:LinkButton ID="lbGuardar" runat="server" Text="Guardar" CssClass="btn btn-primary" OnClick="lbGuardar_Click"/>
                <asp:LinkButton ID="lbCancelar" runat="server" Text="Cancelar" CssClass="btn btn-secondary" OnClick="lbCancelar_Click"/>
            </div>
        </div>
    </div>

    
    <div id="modalMensaje" class="modal fade" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalLabel">Mensaje</h5>
                </div>
                <div class="modal-body">
                    <p id="mensajeTexto">El registro del plan de estudios se ha realizado con éxito.</p>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
