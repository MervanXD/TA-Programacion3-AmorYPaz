<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="RegistrarAnioAcademico.aspx.cs" Inherits="AmorYPazBackend.RegistrarAnioAcademico" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphHead" runat="server">
    <script src="Scripts/scriptsMasterPage.js"></script>
    <link href="Content/estilosMasterPage.css" rel="stylesheet"/>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphTitulo" runat="server">
    Registrar Anio Academico
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container">
        <div class="card">
            <div class="header">
                <!--
                <h1>-->
                    <asp:Label ID="lblNombreInstitucion" runat="server" Text=""  CssClass="label-title"></asp:Label>
                <!--
                </h1>-->
                <img alt="Logo de la Institución Educativa" height="50" src="https://storage.googleapis.com/a1aa/image/rKEbxFzSEeUiIqGTB0DFrWqe0yI4e3gpY6IJzfj25XyfUSudC.jpg" width="50" />
            </div>
            <div class="card-body">
                <div class="row mb-3">
                    <div class="col-md-6">
                        <asp:Label ID="lblAnio" runat="server" Text="Año:" CssClass="col-form-label fw-bold"></asp:Label>
                        <asp:TextBox ID="txtAnio" runat="server" CssClass="form-control"></asp:TextBox>
                        <asp:RequiredFieldValidator ID="rfvDescripcion" runat="server" ControlToValidate="txtAnio" ValidationGroup="ValidacionGuardar"
                            ErrorMessage="El año es obligatorio." ForeColor="Red"></asp:RequiredFieldValidator>
                    </div>

                </div>
                <div class="row mb-3">
                    <div class="col-md-6">
                        <asp:Label ID="lblFechaInicio" runat="server" Text="Fecha de Inicio:" CssClass="col-form-label fw-bold"></asp:Label>
                        <input id="dtpFechaInicio" class="form-control" type="date" runat="server" text="Label" />
                        <asp:RequiredFieldValidator ID="rfvFechaInicio" runat="server" ControlToValidate="dtpFechaInicio" ValidationGroup="ValidacionGuardar"
                            ErrorMessage="La fecha de inicio es obligatoria." ForeColor="Red"></asp:RequiredFieldValidator>
                    </div>


                </div>
                <div class="row mb-3">
                    <div class="col-md-6">
                        <asp:Label ID="lblFechaFin" runat="server" Text="Fecha de Fin:" CssClass="col-form-label fw-bold"></asp:Label>
                        <input id="dtpFechaFin" class="form-control" type="date" runat="server" text="Label" />
                        <asp:RequiredFieldValidator ID="rfvFechaFin" runat="server" ControlToValidate="dtpFechaFin" ValidationGroup="ValidacionGuardar"
                            ErrorMessage="La fecha de fin es obligatoria." ForeColor="Red"></asp:RequiredFieldValidator>
                    </div>

                </div>

            </div>
            <div class="card-footer text-center">
                <asp:LinkButton ID="lbCancelar" runat="server" Text="Cancelar" CssClass="btn btn-secondary" OnClick="lbCancelar_Click" />
                <asp:LinkButton ID="lbGuardar" runat="server" Text="Guardar" CssClass="btn btn-primary" OnClick="lbGuardar_Click" ValidationGroup="ValidacionGuardar" />

            </div>
        </div>
    </div>

    <!--Mensaje de confirmacion-->
    <div id="modalMensaje" class="modal fade" tabindex="-1" aria-labelledby="confirmLogoutModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalLabel">Mensaje</h5>
                </div>
                <div class="modal-body">
                    <p id="mensajeTexto">Se ha realizado el registro con éxito</p>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
