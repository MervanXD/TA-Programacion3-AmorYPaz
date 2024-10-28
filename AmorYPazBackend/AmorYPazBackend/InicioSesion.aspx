<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="InicioSesion.aspx.cs" Inherits="AmorYPazBackend.InicioSesion" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="Content/bootstrap.css" rel="stylesheet" />
    <link href="Content/estilos.css" rel="stylesheet" />
    <link href="Content/Fonts/css/all.css" rel="stylesheet" />


    <script src="Scripts/bootstrap.js"></script>
    <script src="Scripts/bootstrap.bundle.js"></script>
    <script src="Scripts/jquery-3.7.1.js"></script>
    <title></title>
</head>
<body>
    <div class="left-section">
        <img alt="Placeholder image for left section" class="img-fluid" height="800" src="" width="600"/>
    </div>
    <div class="right-section">
        <div class="login-form">
        <h2>
            Iniciar Sesión
        </h2>
        <form>
            <div class="mb-3">
                <label class="form-label" for="username">
                    Usuario:
                </label>
                <input class="form-control" id="username" type="text"/>
            </div>
            <div class="mb-3">
                <label class="form-label" for="password">
                    Contraseña:
                </label>
                <input class="form-control" id="password" type="password"/>
            </div>
            <a href="#">
                Te olvidaste la contraseña?
            </a>
        </form>
    </div>
</body>
</html>
    

