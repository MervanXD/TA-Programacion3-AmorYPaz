<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="InicioSesion.aspx.cs" Inherits="AmorYPazBackend.InicioSesion" %>

<!DOCTYPE html>
<html lang="es">
<head runat="server">
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Inicio de Sesión</title>
    <link rel="stylesheet" href="Content/bootstrap.css" />
    <link rel="stylesheet" href="Content/estilosInicioSesion.css" />
    <link rel="stylesheet" href="Content/estilosInicioSesion.css" />
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    <script src="Scripts/scriptsInicioSesion.js" ></script>
</head>
<body>
    <form id="form2" runat="server">
        <div class="login-container">
            <!-- Logo -->
            <div class="logo">
                <img src="img/logo.png" alt="Logo" />
            </div>

            <!-- Título y subtítulo -->
            <h2>¡Bienvenido!</h2>
            <p>Inicia sesión con tu usuario y contraseña</p>

            <!-- Campo de usuario -->
            <asp:TextBox ID="txtUsername" runat="server" CssClass="form-control" Placeholder="Nombre de usuario" OnFocus="this.classList.remove('input-error');"></asp:TextBox>

            <!-- Campo de contraseña -->
            <asp:TextBox ID="txtContrasenha" runat="server" CssClass="form-control" TextMode="Password" Placeholder="Contraseña" OnFocus="this.classList.remove('input-error');"></asp:TextBox>

            <div class="col-sm-12 mb-3">
                <div class="g-recaptcha" data-sitekey="6LfK7XEqAAAAAE5eB2rP-LazQTG6wEGmKaqnIlXd" onchange="ocultarMensajeError()"></div>
                <asp:Label ID="lblCaptchaError" runat="server" Text="Por favor, marque el CAPTCHA." CssClass="error-message" Visible="false"></asp:Label>
            </div>

            <!-- Botón de inicio de sesión -->
            <asp:Button ID="btnInicioSesion" runat="server" Text="INICIAR SESIÓN" CssClass="btn-primary" OnClick="btnInicioSesion_Click"/>

            <!-- Enlace para olvidó su contraseña -->
            <a href="#" class="forgot-password">¿Olvidó su contraseña?</a>
            
            <!-- Mensaje de error -->
            <asp:Label ID="lblMensaje" runat="server" CssClass="error-message" Visible="false"></asp:Label>
        </div>
    </form>
</body>
</html>
