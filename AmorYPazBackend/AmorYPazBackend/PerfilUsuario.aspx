<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="PerfilUsuario.aspx.cs" Inherits="AmorYPazBackend.PerfilUsuario" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Cambiar Contraseña</title>
    <link href="Content/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
    <form id="form1" runat="server">
        <div class="container mt-5">
            <div class="row">
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-body text-center">
                            <img src="profile-picture.png" alt="Profile Picture" class="img-fluid rounded-circle mb-3" />
                            <h5 class="card-title">Nivel</h5>
                            <p class="card-text text-muted">SUPERADMINISTRADOR</p>
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
                                <asp:TextBox ID="currentPassword" runat="server" CssClass="form-control" TextMode="Password" placeholder="Contraseña actual" />
                            </div>
                            <div class="form-group">
                                <label for="newPassword">Nueva contraseña</label>
                                <asp:TextBox ID="newPassword" runat="server" CssClass="form-control" TextMode="Password" placeholder="Nueva contraseña" />
                            </div>
                            <div class="form-group">
                                <label for="confirmPassword">Confirmar nueva contraseña</label>
                                <asp:TextBox ID="confirmPassword" runat="server" CssClass="form-control" TextMode="Password" placeholder="Confirmar nueva contraseña" />
                            </div>
                            <asp:Button ID="btnSave" runat="server" Text="Guardar" OnClick="btnSave_Click" CssClass="btn btn-primary" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
