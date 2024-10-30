<%@ Page Title="" Language="C#" MasterPageFile="~/SuperIntendente.Master" AutoEventWireup="true" CodeBehind="RegistrarInstitucion.aspx.cs" Inherits="AmorYPazBackend.RegistrarInstitucion" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphHead" runat="server">
    <link href="Content/EstilosRegisInsti.css" rel="stylesheet" />
    <script src="Scripts/ScriptRegisInsti.js"></script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphTitulo" runat="server">
    <title>Registrar Institución Educativa</title>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="content">
        <h1 class="text-center">UGEL04</h1>
        <div class="form-container">
            <h2 class="text-center">Añadir Institución</h2>
            <div class="form-group">
                <label for="nombre">Nombre de la Institución Educ</label>
                <input type="text" id="nombre" value="Colegio PUCP">
            </div>
            <div class="form-group">
                <label for="direccion">Dirección de la Institución Educ</label>
                <input type="text" id="direccion" value="Av. Universitaria 1801">
            </div>
            <div class="form-group">
                <label for="director">Asignar Director</label>
                <select id="director">
                    <option>Manuel Alejandro Sanchez La Rosa</option>
                </select>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="telefono">Número de teléfono</label>
                    <input type="text" id="telefono" value="+51 920 643 645">
                </div>
                <div class="form-group col-md-6">
                    <label for="email">Correo electrónico</label>
                    <input type="email" id="email" value="institucioin.educativa@insti.com">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="logo">Ingrese el logo de la institución</label>
                    <input type="file" id="logo" onchange="previewLogo(event)">
                </div>
                <div class="form-group col-md-6 d-flex justify-content-center align-items-center">
                    <div class="logo-placeholder" id="logo-placeholder">Logo</div>
                </div>
            </div>
            <div class="btn-group">
                <button class="btn btn-primary">Guardar</button>
                <button class="btn btn-secondary">Cancelar</button>
            </div>
        </div>
    </div>
</asp:Content>
