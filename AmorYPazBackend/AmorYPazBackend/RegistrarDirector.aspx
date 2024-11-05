<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="RegistrarDirector.aspx.cs" Inherits="AmorYPazBackend.RegistrarDirector" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphHead" runat="server">
    <script src="Scripts/scriptsMasterPage.js"></script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphTitulo" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <asp:ScriptManager ID="smDirector" runat="server" />
    <asp:UpdatePanel ID="upDirector" runat="server">
        <contenttemplate>
            <div class="container">
                <div class="card">
                    <div class="card-header">
                        <h2>
                            <asp:Label ID="lblTitulo" runat="server" Text="Directores UGEL N#"></asp:Label>
                        </h2>
                    </div>
                    <div class="card-body">
                        <div class="mb-3 row">
                            <asp:Label id="lblDNI" runat="server" CssClass="col-sm-2 col-form-label" Text="DNI: "></asp:Label>
                            <div class="col-sm-8">
                                <asp:TextBox id="txtDNI" runat="server" CssClass="form-control"></asp:TextBox>
                                <asp:RequiredFieldValidator ID="rfvDNI" runat="server" ControlToValidate="txtDNI" ValidationGroup="GrupoGuardarD"
                                    ErrorMessage="El DNI es obligatorio." ForeColor="Red"></asp:RequiredFieldValidator>
                                <asp:RegularExpressionValidator ID="revDNI" runat="server" ControlToValidate="txtDNI" ValidationGroup="GrupoGuardarD"
                                    ValidationExpression="^\d{8}$" ErrorMessage="El DNI debe contener solo 8 dígitos." ForeColor="Red"></asp:RegularExpressionValidator>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <asp:Label id="lblNombre" runat="server" Text="Nombre: " CssClass="col-sm-2 col-form-label"></asp:Label>
                            <div class="col-sm-8">
                                <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control"></asp:TextBox>
                                <asp:RequiredFieldValidator ID="rfvNombre" runat="server" ControlToValidate="txtNombre" ValidationGroup="GrupoGuardarD"
                                    ErrorMessage="El nombre es obligatorio." ForeColor="Red"></asp:RequiredFieldValidator>
                                <asp:RegularExpressionValidator ID="revNombre" runat="server" ControlToValidate="txtNombre" ValidationGroup="GrupoGuardarD"
                                    ValidationExpression="^[a-zA-Z\s]+$" ErrorMessage="Solo se permiten letras en el nombre." ForeColor="Red"></asp:RegularExpressionValidator>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <asp:Label id="lblApellidoPaterno" runat="server" Text="Apellido Paterno: " CssClass="col-sm-2 col-form-label"></asp:Label>
                            <div class="col-sm-8">
                                <asp:TextBox id="txtApellidoPaterno" runat="server" CssClass="form-control"></asp:TextBox>
                                <asp:RequiredFieldValidator ID="rfvApellidoPaterno" runat="server" ControlToValidate="txtApellidoPaterno" ValidationGroup="GrupoGuardarD"
                                    ErrorMessage="El apellido paterno es obligatorio." ForeColor="Red"></asp:RequiredFieldValidator>
                                <asp:RegularExpressionValidator ID="revApellidoPaterno" runat="server" ControlToValidate="txtApellidoPaterno" ValidationGroup="GrupoGuardarD"
                                    ValidationExpression="^[a-zA-Z\s]+$" ErrorMessage="Solo se permiten letras en el nombre." ForeColor="Red"></asp:RegularExpressionValidator>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <asp:Label id="lblApellidoMaterno" runat="server" Text="Apellido Materno: " CssClass="col-sm-2 col-form-label"></asp:Label>
                            <div class="col-sm-8">
                                <asp:TextBox id="txtApellidoMaterno" runat="server" CssClass="form-control"></asp:TextBox>
                                <asp:RequiredFieldValidator ID="rfvApellidoMaterno" runat="server" ControlToValidate="txtApellidoMaterno" ValidationGroup="GrupoGuardarD"
                                    ErrorMessage="El apellido materno es obligatorio." ForeColor="Red"></asp:RequiredFieldValidator>
                                <asp:RegularExpressionValidator ID="revApellidoMaterno" runat="server" ControlToValidate="txtApellidoMaterno" ValidationGroup="GrupoGuardarD"
                                    ValidationExpression="^[a-zA-Z\s]+$" ErrorMessage="Solo se permiten letras en el nombre." ForeColor="Red"></asp:RegularExpressionValidator>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <asp:Label id="lblDireccion" runat="server" Text="Direccion: " CssClass="col-sm-2 col-form-label"></asp:Label>
                            <div class="col-sm-8">
                                <asp:TextBox id="txtDireccion" runat="server" CssClass="form-control"></asp:TextBox>
                                <asp:RequiredFieldValidator ID="rfvDireccion" runat="server" ControlToValidate="txtDireccion" ValidationGroup="GrupoGuardarD"
                                ErrorMessage="La direccion es obligatoria." ForeColor="Red"></asp:RequiredFieldValidator>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <asp:Label ID="lblReligion" runat="server" Text="Religion: " CssClass="col-sm-2 col-form-label"></asp:Label>
                            <div class="col-sm-8">
                                <asp:TextBox ID="txtReligion" runat="server" CssClass="form-control"></asp:TextBox>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <asp:Label ID="lblLengua" runat="server" Text="Lengua: " CssClass="col-sm-2 col-form-label"></asp:Label>
                            <div class="col-sm-8">
                                <asp:TextBox ID="txtLengua" runat="server" CssClass="form-control"></asp:TextBox>
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <div class="col-sm-2">
                                <asp:Label id="lblSexo" runat="server" Text="Sexo: " CssClass="col-form-label"></asp:Label>
                            </div>
                            <div class="col-sm-8">
                                <div class="form-check form-check-inline">
                                    <input type="radio" runat="server" id="rbMasculino" class="form-check-input" />
                                    <label class="form-check-label" runat="server" for="cphContenido_rbMasculino">Masculino</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input type="radio" runat="server" id="rbFemenino" class="form-check-input" />
                                    <label class="form-check-label" runat="server" for="cphContenido_rbFemenino">Femenino</label>
                                </div>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <asp:Label runat="server" Text="Fecha Nacimiento: " CssClass="col-sm-2 col-form-label"></asp:Label>
                            <div class="col-sm-8">
                                <input id="dtpFechaNacimiento" class="form-control" type="date" runat="server" text="Label" />
                            </div>
                        </div>

                    <div class="mb-3 row">
                        <asp:Label ID="lblContrato" runat="server" Text="Tipo Contrato: " CssClass="col-sm-2 col-form-label"></asp:Label>
                        <div class="col-sm-8">
                            <asp:TextBox ID="txtContrato" runat="server" CssClass="form-control"></asp:TextBox>
                        </div>
                    </div>

                  
                    <div class="mb-3 row">
                        <asp:Label ID="lblEmail" runat="server" Text="Email: " CssClass="col-sm-2 col-form-label"></asp:Label>
                        <div class="col-sm-8">
                            <asp:TextBox ID="txtEmail" runat="server" CssClass="form-control"></asp:TextBox>
                            <asp:RequiredFieldValidator ID="rfvEmail" runat="server" ControlToValidate="txtEmail" ValidationGroup="GrupoGuardarD"
                                ErrorMessage="El correo electrónico es obligatorio." ForeColor="Red"></asp:RequiredFieldValidator>
                            <asp:RegularExpressionValidator ID="revEmail" runat="server" ControlToValidate="txtEmail" ValidationGroup="GrupoGuardarD"
                                ValidationExpression="^[^@\s]+@[^@\s]+\.[^@\s]+$" ErrorMessage="Ingrese un correo electrónico válido." ForeColor="Red"></asp:RegularExpressionValidator>
                        </div>
                    </div>

                    </div>
                    <div class="card-footer">
                        <asp:Button id="btnCancelar" runat="server" CssClass="float-start btn btn-primary" Text="Cancelar" OnClick="btnCancelar_Click" />
                        <asp:Button id="btnGuardar" runat="server" CssClass="float-end btn btn-primary" Text="Guardar" OnClick="btnGuardar_Click"  ValidationGroup="GrupoGuardarD"/>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="errorModal" tabindex="-1" aria-labelledby="errorModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header bg-danger text-white">
                            <h5 class="modal-title" id="errorModalLabel">
                                <i class="bi bi-exclamation-triangle-fill me-2"></i>Mensaje de Error
                            </h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <asp:Label ID="lblMensajeError" runat="server" Text="Mensaje de ejemplo..." CssClass="form-text text-danger"></asp:Label>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                        </div>
                    </div>
                </div>
            </div>
        </contenttemplate>
    </asp:UpdatePanel>

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

