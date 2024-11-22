<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="PerfilUsuario.aspx.cs" Inherits="AmorYPazBackend.PerfilUsuario" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphHead" runat="server">
    <link href="Content/estilosMasterPage.css" rel="stylesheet"/>
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
                        <img src="img/imagenUser.png" alt="Profile Picture" class="img-fluid rounded-circle mb-3" />
                        <div style="background-color: #78bfbf; padding: 10px; border-radius: 5px;">
                            <asp:Label ID="lblNombre" runat="server" CssClass="card-title text-white font-weight-bold" Style="font-size: 1.5rem; font-weight: bold;"></asp:Label>
                        </div>
                        <p class="card-text">
                            <asp:Label ID="lblTipoUsuario" runat="server" CssClass="text-muted"></asp:Label>
                        </p>
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item"><a href="#">Mi perfil</a></li>
                        <li class="list-group-item"><a href="#">Seguridad</a></li>
                        <li class="list-group-item"><a href="#">Cerrar sesión</a></li>
                    </ul>
                </div>
            </div>

            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">
                        Cambiar contraseña
                    </div>
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