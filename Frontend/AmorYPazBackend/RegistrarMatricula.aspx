<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="RegistrarMatricula.aspx.cs" Inherits="AmorYPazBackend.RegistrarMatricula" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphHead" runat="server">
    <script src="Scripts/scriptsMasterPage.js"></script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphTitulo" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <!-- División 1: Búsqueda del alumno -->
    <div class="container my-4">
        <div class="row mb-3">
            <div class="col-auto">
                <asp:Label ID="lblIngreseDNI" CssClass="form-label" runat="server" Text="Ingrese el DNI del alumno:"></asp:Label>
            </div>
            <div class="col-sm-3">
                <asp:TextBox ID="txtDNI" CssClass="form-control" runat="server"></asp:TextBox>
            </div>
            <div class="col-sm-2">
                <asp:Button ID="btnBuscar" CssClass="btn btn-primary" runat="server" Text="Buscar" OnClick="btnBuscar_Click" />
            </div>
        </div>

        <!-- División 2: Datos del alumno -->
        <div class="row mb-3">
            <!-- Primera fila: Nombres y Lengua -->
            <div class="col-md-6">
                <asp:Label ID="lblNombres" CssClass="form-label" runat="server" Text="Nombres:"></asp:Label>
                <asp:TextBox ID="txtNombres" CssClass="form-control" runat="server"></asp:TextBox>
            </div>
            <div class="col-md-6">
                <asp:Label ID="lblLengua" CssClass="form-label" runat="server" Text="Lengua:"></asp:Label>
                <asp:TextBox ID="txtLengua" CssClass="form-control" runat="server"></asp:TextBox>
            </div>
         </div>
            <!-- Segunda fila: Apellido Paterno y Dirección -->
        <div class="row mb-3">
            <div class="col-md-6">
                <asp:Label ID="lblApellidoPaterno" CssClass="form-label" runat="server" Text="Apellido Paterno:"></asp:Label>
                <asp:TextBox ID="txtApellidoPaterno" CssClass="form-control" runat="server"></asp:TextBox>
            </div>
            <div class="col-md-6">
                <asp:Label ID="lblDireccion" CssClass="form-label" runat="server" Text="Dirección:"></asp:Label>
                <asp:TextBox ID="txtDireccion" CssClass="form-control" runat="server"></asp:TextBox>
            </div>
         </div>
            <!-- Tercera fila: Apellido Materno y Sexo -->
        <div class="row mb-3">
            <div class="col-md-6">
                <asp:Label ID="lblApellidoMaterno" CssClass="form-label" runat="server" Text="Apellido Materno:"></asp:Label>
                <asp:TextBox ID="txtApellidoMaterno" CssClass="form-control" runat="server"></asp:TextBox>
            </div>
            <div class="col-md-6">
                <asp:Label ID="lblSexo" CssClass="form-label" runat="server" Text="Sexo:"></asp:Label>
                <asp:DropDownList ID="ddlSexo" CssClass="form-select" runat="server">
                    <asp:ListItem Text="Masculino" Value="M"></asp:ListItem>
                    <asp:ListItem Text="Femenino" Value="F"></asp:ListItem>
                </asp:DropDownList>
            </div>
        </div>
            <!-- Cuarta fila: Fecha de Nacimiento y Religión -->
        <div class="row mb-3">
            <div class="col-md-6">
                <asp:Label ID="lblFechaNacimiento" CssClass="form-label" runat="server" Text="Fecha de Nacimiento:"></asp:Label>
                <asp:TextBox ID="dtpFechaNacimiento" CssClass="form-control" runat="server" TextMode="Date"></asp:TextBox>
            </div>
            <div class="col-md-6">
                <asp:Label ID="lblReligion" CssClass="form-label" runat="server" Text="Religión:"></asp:Label>
                <asp:TextBox ID="txtReligion" CssClass="form-control" runat="server"></asp:TextBox>
            </div>
        </div>

        <!-- División 3: Condición Médica y Discapacidades -->
        <div class="row mb-3">
            <div class="col-md-6">
                <asp:Label ID="lblCondicionMedica" CssClass="form-label" runat="server" Text="Condición Médica:"></asp:Label>
                <asp:TextBox ID="txtCondicionMedica" CssClass="form-control" runat="server"></asp:TextBox>
            </div>
            <div class="col-md-6">
                <asp:Label ID="lblDiscapacidades" CssClass="form-label" runat="server" Text="Discapacidades:"></asp:Label>
                <asp:TextBox ID="txtDiscapacidades" CssClass="form-control" runat="server"></asp:TextBox>
            </div>
        </div>

        <!-- Botones Guardar y Cancelar -->
        <div class="row">
            <div class="col text-end">
                <asp:Button ID="btnGuardar" CssClass="btn btn-success me-2" runat="server" Text="Guardar" OnClick="btnGuardar_Click" />
                <asp:Button ID="btnCancelar" CssClass="btn btn-secondary" runat="server" Text="Cancelar" OnClick="btnCancelar_Click" />
            </div>
        </div>
    </div>
</asp:Content>
