<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="GestionarPlanesEstudio.aspx.cs" Inherits="AmorYPazBackend.GestionarPlanesEstudio" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphHead" runat="server">
    <link href="Content/EstilosGestionarPlanesEstudio.css" rel="stylesheet" />
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphTitulo" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="content">
        <div class="header">
            <!--<h1>Nombre de la Institución Educativa</h1>-->
            <asp:Label ID="lblInfoIE" runat="server" CssClass="label-title"></asp:Label>
            <div class="logo">
                <!-- para poner la imagen
                <img src="" alt="Alternate Text" />
                -->
            </div>
            <div class="btn-group">
                <button type="button" class="btn btn-secondary btn-custom">Actual</button>
                <button type="button" class="btn btn-secondary btn-custom">Finalizados</button>
            </div>
        </div>
        <div class="table-container">
            <asp:GridView ID="gvPlanes" runat="server" CssClass="table table-bordered" AutoGenerateColumns="False" OnRowDataBound="gvPlanes_RowDataBound">
                <Columns>
                    <asp:BoundField ItemStyle-CssClass="align-middle" HeaderText="Año" />
                    <asp:BoundField ItemStyle-CssClass="align-middle" HeaderText="Fecha Inicio" />
                    <asp:BoundField ItemStyle-CssClass="align-middle" HeaderText="Fecha Fin" />
                    <asp:BoundField ItemStyle-CssClass="align-middle" HeaderText="numCursos" />
                    
                    
                </Columns>
            </asp:GridView>
            <asp:Button ID="AddButton" runat="server" Text="Añadir" CssClass="btn btn-secondary btn-add" OnClick="AddButton_Click" />
        </div>
    </div>
</asp:Content>
