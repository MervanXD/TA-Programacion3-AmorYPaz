<%@ Page Title="" Language="C#" MasterPageFile="~/SuperIntendente.Master" AutoEventWireup="true" CodeBehind="RegistrarInstitucion.aspx.cs" Inherits="AmorYPazBackend.RegistrarInstitucion" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphHead" runat="server">
    <link href="Content/EstilosRegisInsti.css" rel="stylesheet"/>
    <link href="Content/estilosMasterPage.css" rel="stylesheet"/>
    <script src="Scripts/ScriptRegisInsti.js"></script>
    <script src="Scripts/scriptsMasterPage.js"></script>
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
                        <asp:RequiredFieldValidator ID="rfvNombre" runat="server" ControlToValidate="txtNombre"
                            ErrorMessage="El nombre es obligatorio." ForeColor="Red"></asp:RequiredFieldValidator>
                        <asp:RegularExpressionValidator ID="revNombre" runat="server" ControlToValidate="txtNombre"
                            ValidationExpression="^[a-zA-Z\s]+$" ErrorMessage="Solo se permiten letras en el nombre." ForeColor="Red"></asp:RegularExpressionValidator>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-12">
                        <asp:Label ID="lblDireccion" runat="server" Text="Dirección de la Institución:" CssClass="col-form-label fw-bold"></asp:Label>
                        <asp:TextBox ID="txtDireccion" runat="server" CssClass="form-control"></asp:TextBox>
                        <asp:RequiredFieldValidator ID="rfvDireccion" runat="server" ControlToValidate="txtDireccion"
                            ErrorMessage="La dirección es obligatoria." ForeColor="Red"></asp:RequiredFieldValidator>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-12">
                        <asp:Label ID="lblDirector" runat="server" CssClass="col-form-label fw-bold"></asp:Label>
                        <asp:DropDownList ID="ddlDirector" runat="server" CssClass="form-select"></asp:DropDownList>
                        <asp:RequiredFieldValidator ID="rfvDirector" runat="server" ControlToValidate="ddlDirector"
                            InitialValue="0" ErrorMessage="Seleccione un director." ForeColor="Red"></asp:RequiredFieldValidator>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-6">
                        <asp:Label ID="lblTelefono" runat="server" Text="Número de teléfono:" CssClass="col-form-label fw-bold"></asp:Label>
                        <asp:TextBox ID="txtTelefono" runat="server" CssClass="form-control"></asp:TextBox>
                        <asp:RequiredFieldValidator ID="rfvTelefono" runat="server" ControlToValidate="txtTelefono"
                            ErrorMessage="El teléfono es obligatorio." ForeColor="Red"></asp:RequiredFieldValidator>
                        <asp:RegularExpressionValidator ID="revTelefono" runat="server" ControlToValidate="txtTelefono"
                            ValidationExpression="^\d{9}$" ErrorMessage="El teléfono debe contener solo 9 dígitos." ForeColor="Red"></asp:RegularExpressionValidator>
                    </div>
                    <div class="col-md-6">
                        <asp:Label ID="lblEmail" runat="server" Text="Correo electrónico:" CssClass="col-form-label fw-bold"></asp:Label>
                        <asp:TextBox ID="txtEmail" runat="server" CssClass="form-control" TextMode="Email"></asp:TextBox>
                        <asp:RequiredFieldValidator ID="rfvEmail" runat="server" ControlToValidate="txtEmail"
                            ErrorMessage="El correo electrónico es obligatorio." ForeColor="Red"></asp:RequiredFieldValidator>
                        <asp:RegularExpressionValidator ID="revEmail" runat="server" ControlToValidate="txtEmail"
                            ValidationExpression="^[^@\s]+@[^@\s]+\.[^@\s]+$" ErrorMessage="Ingrese un correo electrónico válido." ForeColor="Red"></asp:RegularExpressionValidator>
                    </div>
                <div class="row mb-3">
                    <div class="col-md-6">
                        <asp:Label ID="lblLogo" runat="server" Text="Logo de la institución:" CssClass="col-form-label fw-bold"></asp:Label>
                        <asp:FileUpload ID="fuLogo" runat="server" CssClass="form-control" onchange="this.form.submit()" ClientIDMode="Static"/>
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

