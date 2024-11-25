<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="PerfilUsuario.aspx.cs" Inherits="AmorYPazBackend.PerfilUsuario" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphHead" runat="server">
    <link href="Content/estilosMasterPage.css" rel="stylesheet" />
    <script src="Scripts/scriptsPerfil.js"></script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphTitulo" runat="server">
    Perfil de Usuario
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container">
        <div class="card-header mb-3">
            <h2>
                <asp:Label ID="lblTitulo" runat="server" Text="Perfil del Usuario" CssClass="label-title"></asp:Label>
            </h2>
        </div>
        <div class="row">
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body text-center">
                        <img src="img/imagenUser.png" alt="Profile Picture" class="img-fluid mb-3" />
                        <div style="background-color: #78bfbf; padding: 10px; border-radius: 5px;">
                            <asp:Label ID="lblNombre" runat="server" CssClass="card-title text-white font-weight-bold" Style="font-size: 1.5rem; font-weight: bold;"></asp:Label>
                        </div>
                        <p class="card-text">
                            <asp:Label ID="lblTipoUsuario" runat="server" CssClass="text-muted"></asp:Label>
                        </p>
                    </div>
                </div>
            </div>

            <div class="col-md-8">
                <ul class="nav nav-tabs mb-3" id="myTab" role="tablist">
                    <li class="nav-item" role="presentation">
                        <button class="nav-link active" id="tab-details-tab" data-bs-toggle="tab" data-bs-target="#tab-details" type="button" role="tab" aria-controls="tab-details" aria-selected="true">
                            Detalles del usuario
                        </button>
                    </li>
                    <li class="nav-item" role="presentation">
                        <button class="nav-link" id="tab-password-tab" data-bs-toggle="tab" data-bs-target="#tab-password" type="button" role="tab" aria-controls="tab-password" aria-selected="false">
                            Cambiar contraseña
                   
                        </button>
                    </li>
                </ul>

                <div class="tab-content" id="myTabContent">

                    <div class="tab-pane fade show active" id="tab-details" role="tabpanel" aria-labelledby="tab-details-tab">
                        <div class="card">
                            <div class="card-header">Detalles del usuario</div>
                            <div class="card-body">
                                <div class="mb-3 row">
                                    <asp:Label ID="lblDNI" runat="server" CssClass="col-sm-2 col-form-label" Text="DNI: "></asp:Label>
                                    <div class="col-sm-8">
                                        <asp:TextBox ID="txtDNI" runat="server" CssClass="form-control"></asp:TextBox>
                                        <asp:RequiredFieldValidator ID="rfvDNI" runat="server" ControlToValidate="txtDNI" ValidationGroup="GrupoGuardarD"
                                            ErrorMessage="El DNI es obligatorio." ForeColor="Red"></asp:RequiredFieldValidator>
                                        <asp:RegularExpressionValidator ID="revDNI" runat="server" ControlToValidate="txtDNI" ValidationGroup="GrupoGuardarD"
                                            ValidationExpression="^\d{8}$" ErrorMessage="El DNI debe contener solo 8 dígitos." ForeColor="Red"></asp:RegularExpressionValidator>
                                    </div>
                                </div>
                                <div class="mb-3 row">
                                    <asp:Label ID="Label1" runat="server" Text="Nombre: <span class='text-danger'>*</span>" CssClass="col-sm-2 col-form-label"></asp:Label>
                                    <div class="col-sm-8">
                                        <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control"></asp:TextBox>
                                        <asp:RequiredFieldValidator ID="rfvNombre" runat="server" ControlToValidate="txtNombre" ValidationGroup="GrupoGuardarD"
                                            ErrorMessage="El nombre es obligatorio." ForeColor="Red"></asp:RequiredFieldValidator>
                                        <asp:RegularExpressionValidator ID="revNombre" runat="server" ControlToValidate="txtNombre" ValidationGroup="GrupoGuardarD"
                                            ValidationExpression="^[a-zA-Z\s]+$" ErrorMessage="Solo se permiten letras en el nombre." ForeColor="Red"></asp:RegularExpressionValidator>
                                    </div>
                                </div>
                                <div class="mb-3 row">
                                    <asp:Label ID="lblApellidoPaterno" runat="server" Text="Apellido Paterno: <span class='text-danger'>*</span>" CssClass="col-sm-2 col-form-label"></asp:Label>
                                    <div class="col-sm-8">
                                        <asp:TextBox ID="txtApellidoPaterno" runat="server" CssClass="form-control"></asp:TextBox>
                                        <asp:RequiredFieldValidator ID="rfvApellidoPaterno" runat="server" ControlToValidate="txtApellidoPaterno" ValidationGroup="GrupoGuardarD"
                                            ErrorMessage="El apellido paterno es obligatorio." ForeColor="Red"></asp:RequiredFieldValidator>
                                        <asp:RegularExpressionValidator ID="revApellidoPaterno" runat="server" ControlToValidate="txtApellidoPaterno" ValidationGroup="GrupoGuardarD"
                                            ValidationExpression="^[a-zA-Z\s]+$" ErrorMessage="Solo se permiten letras en el nombre." ForeColor="Red"></asp:RegularExpressionValidator>
                                    </div>
                                </div>
                                <div class="mb-3 row">
                                    <asp:Label ID="lblApellidoMaterno" runat="server" Text="Apellido Materno: <span class='text-danger'>*</span>" CssClass="col-sm-2 col-form-label"></asp:Label>
                                    <div class="col-sm-8">
                                        <asp:TextBox ID="txtApellidoMaterno" runat="server" CssClass="form-control"></asp:TextBox>
                                        <asp:RequiredFieldValidator ID="rfvApellidoMaterno" runat="server" ControlToValidate="txtApellidoMaterno" ValidationGroup="GrupoGuardarD"
                                            ErrorMessage="El apellido materno es obligatorio." ForeColor="Red"></asp:RequiredFieldValidator>
                                        <asp:RegularExpressionValidator ID="revApellidoMaterno" runat="server" ControlToValidate="txtApellidoMaterno" ValidationGroup="GrupoGuardarD"
                                            ValidationExpression="^[a-zA-Z\s]+$" ErrorMessage="Solo se permiten letras en el nombre." ForeColor="Red"></asp:RegularExpressionValidator>
                                    </div>
                                </div>
                                <div class="mb-3 row">
                                    <asp:Label ID="lblEmail" runat="server" Text="Email: <span class='text-danger'>*</span>" CssClass="col-sm-2 col-form-label"></asp:Label>
                                    <div class="col-sm-8">
                                        <asp:TextBox ID="txtEmail" runat="server" CssClass="form-control"></asp:TextBox>
                                        <asp:RequiredFieldValidator ID="rfvEmail" runat="server" ControlToValidate="txtEmail" ValidationGroup="GrupoGuardarD"
                                            ErrorMessage="El correo electrónico es obligatorio." ForeColor="Red"></asp:RequiredFieldValidator>
                                        <asp:RegularExpressionValidator ID="revEmail" runat="server" ControlToValidate="txtEmail" ValidationGroup="GrupoGuardarD"
                                            ValidationExpression="^[^@\s]+@[^@\s]+\.[^@\s]+$" ErrorMessage="Ingrese un correo electrónico válido." ForeColor="Red"></asp:RegularExpressionValidator>
                                    </div>
                                </div>
                                <div class="mb-3 row">
                                    <asp:Label ID="lblDireccion" runat="server" Text="Direccion: " CssClass="col-sm-2 col-form-label"></asp:Label>
                                    <div class="col-sm-8">
                                        <asp:TextBox ID="txtDireccion" runat="server" CssClass="form-control"></asp:TextBox>
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
                                        <asp:Label ID="lblSexo" runat="server" Text="Sexo: " CssClass="col-form-label"></asp:Label>
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
                            </div>
                            <div class="card-footer">
                                <asp:Button ID="btnGuardar" runat="server" CssClass="float-end btn btn-primary" Text="Guardar" OnClick="btnGuardar_Click" ValidationGroup="GrupoGuardarD" />
                            </div>
                        </div>
                    </div>

                    <div class="tab-pane fade" id="tab-password" role="tabpanel" aria-labelledby="tab-password-tab">
                        <div class="card">
                            <div class="card-header">Cambiar contraseña</div>
                            <div class="card-body">
                                <div class="form-group">
                                    <label for="currentPassword">Contraseña actual</label>
                                    <asp:TextBox ID="txtPassAct" runat="server" CssClass="form-control" TextMode="Password" placeholder="Contraseña actual" />
                                </div>
                                <div class="form-group">
                                    <label for="newPassword">Nueva contraseña</label>
                                    <asp:TextBox ID="txtPassNew" runat="server" CssClass="form-control" TextMode="Password" placeholder="Nueva contraseña" />
                                </div>
                                <div class="form-group">
                                    <label for="confirmPassword">Confirmar nueva contraseña</label>
                                    <asp:TextBox ID="txtPassCon" runat="server" CssClass="form-control" TextMode="Password" placeholder="Confirmar nueva contraseña" />
                                </div>
                                <asp:Button ID="btnSave" runat="server" Text="Guardar" OnClick="btnSave_Click" CssClass="btn btn-primary" />
                            </div>
                        </div>
                    </div>
                </div>
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
                    <p id="mensajeTexto">La modificación de contraseña se ha realizado con éxito.</p>
                </div>
            </div>
        </div>
    </div>

</asp:Content>
