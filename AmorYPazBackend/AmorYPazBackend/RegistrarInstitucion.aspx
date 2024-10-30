<%@ Page Title="" Language="C#" MasterPageFile="~/SuperIntendente.Master" AutoEventWireup="true" CodeBehind="RegistrarInstitucion.aspx.cs" Inherits="AmorYPazBackend.RegistrarInstitucion" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphHead" runat="server">
    <link href="Content/EstilosRegisInsti.css" rel="stylesheet" />
    <script src="Scripts/ScriptRegisInsti.js"></script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphTitulo" runat="server">
    Registrar Institución Educativa
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container">
        <div class="card">
            <div class="card-header text-center">
                <h2>
                    <asp:Label ID="lblTitulo" runat="server"></asp:Label>
                </h2>
            </div>
            <div class="card-body">
                <div class="row mb-3">
                    <div class="col-md-12">
                        <asp:Label ID="lblNombre" runat="server" Text="Nombre de la Institución Educativa:" CssClass="col-form-label fw-bold"></asp:Label>
                        <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control"></asp:TextBox>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-12">
                        <asp:Label ID="lblDireccion" runat="server" Text="Dirección de la Institución:" CssClass="col-form-label fw-bold"></asp:Label>
                        <asp:TextBox ID="txtDireccion" runat="server" CssClass="form-control"></asp:TextBox>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-12">
                        <asp:Label ID="lblDirector" runat="server" CssClass="col-form-label fw-bold"></asp:Label>
                        <asp:DropDownList ID="ddlDirector" runat="server" CssClass="form-select"></asp:DropDownList>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-6">
                        <asp:Label ID="lblTelefono" runat="server" Text="Número de teléfono:" CssClass="col-form-label fw-bold"></asp:Label>
                        <asp:TextBox ID="txtTelefono" runat="server" CssClass="form-control"></asp:TextBox>
                    </div>
                    <div class="col-md-6">
                        <asp:Label ID="lblEmail" runat="server" Text="Correo electrónico:" CssClass="col-form-label fw-bold"></asp:Label>
                        <asp:TextBox ID="txtEmail" runat="server" CssClass="form-control" TextMode="Email"></asp:TextBox>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-6">
                        <asp:Label ID="lblLogo" runat="server" Text="Logo de la institución:" CssClass="col-form-label fw-bold"></asp:Label>
                        <asp:FileUpload ID="fuLogo" runat="server" CssClass="form-control" onchange="previewLogo(event)" />
                    </div>
                    <div class="col-md-6 d-flex justify-content-center align-items-center">
                        <asp:Image ID="imgLogoPlaceholder" runat="server" CssClass="img-thumbnail" ImageUrl="~/Images/placeholder.jpg" AlternateText="Logo" Height="100" Width="100" />
                    </div>
                </div>
            </div>
            <div class="card-footer text-center">
                <asp:LinkButton ID="lbGuardar" runat="server" Text="Guardar" CssClass="btn btn-primary" OnClick="lbGuardar_Click"/>
                <asp:LinkButton ID="lbCancelar" runat="server" Text="Cancelar" CssClass="btn btn-secondary" OnClick="lbCancelar_Click"/>
            </div>
        </div>
    </div>
</asp:Content>

