<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="InicioSesion.aspx.cs" Inherits="AmorYPazBackend.InicioSesion" %>

<!DOCTYPE html>
<html lang="es">
<head runat="server">
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Inicio de Sesión</title>
    <link rel="stylesheet" href="Content/bootstrap.css" />
    <link rel="stylesheet" href="Content/estilos.css" />
    <style>
        /* Estilos generales para la página */
        body, html {
            height: 100%;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: Arial, sans-serif;
            background-image: url('img/BackgroundInicioSesion.jpg'); /* Cambia esta ruta a la ubicación de tu imagen */
            background-size: cover; /* Asegura que la imagen cubra todo el fondo */
            background-position: center; /* Centra la imagen */
            background-repeat: no-repeat; /* Evita que la imagen se repita */
        }

        /* Contenedor principal */
        .login-container {
            background-color: white;
            border-radius: 15px;
            box-shadow: 0px 6px 15px rgba(0, 0, 0, 0.15);
            max-width: 500px; /* Aumentar el tamaño */
            padding: 60px; /* Aumentar el espaciado */
            text-align: center;
        }

        /* Estilo del logo */
        .logo {
            background-color: white;
            border-radius: 100%;
            width: 150px; /* Aumentar tamaño del logo */
            height: 150px;
            display: inline-flex;
            align-items: center;
            justify-content: center;
            margin-bottom: 30px; /* Aumentar margen inferior */
        }

        .logo img {
            width: 100%; /* Ajustar el tamaño del logo dentro del círculo */
            height: auto;
        }

        /* Estilo del título */
        h2 {
            font-size: 28px; /* Tamaño de fuente más grande */
            font-weight: bold;
            color: #333;
            margin-bottom: 15px;
        }

        /* Estilos de entrada */
        .form-control {
            background-color: #f1f1f1;
            border: none;
            border-radius: 8px;
            padding: 15px;
            font-size: 18px; /* Aumentar el tamaño del texto */
            margin-bottom: 20px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        }

        /* Botón de inicio de sesión */
        .btn-primary {
            background-color: #458c8c;
            color: #fff;
            font-weight: bold;
            padding: 15px;
            border: none;
            border-radius: 8px;
            width: 100%;
            font-size: 18px; /* Aumentar el tamaño del texto */
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .btn-primary:hover {
            background-color: #474dbb;
        }

        /* Enlace para olvidó contraseña */
        .forgot-password {
            display: block;
            margin-top: 15px;
            color: #6c757d;
            font-size: 16px; /* Tamaño de fuente ligeramente mayor */
            text-decoration: none;
        }

        .forgot-password:hover {
            color: #5c63d3;
        }

        .error-message {
            color: #ea868f;
            font-weight: bold;
            margin-top: 10px;
            display: block;
        }

        .input-error {
            border: 2px solid #ea868f; /* Cambia el borde a rojo */
            background-color: #ffe6e6; /* Color de fondo suave */
        }
    </style>
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

            <!-- Enlace para olvidó su contraseña -->
            <a href="#" class="forgot-password">¿Olvidó su contraseña?</a>

            <!-- Botón de inicio de sesión -->
            <asp:Button ID="btnInicioSesion" runat="server" Text="INICIAR SESIÓN" CssClass="btn-primary" OnClick="btnInicioSesion_Click"/>
            
            <!-- Mensaje de error -->
            <asp:Label ID="lblMensaje" runat="server" CssClass="error-message" Visible="false"></asp:Label>
        </div>
    </form>
</body>
</html>
