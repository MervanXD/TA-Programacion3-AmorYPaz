<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="GestionarAlumnos.aspx.cs" Inherits="AmorYPazBackend.GestionarAlumnos" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphHead" runat="server">
    <link href="Content/EstilosGestionarPlanesEstudio.css" rel="stylesheet" />
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphTitulo" runat="server">
    Gestionar Alumnos
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container">
    <div class="header">
        <!--<h1>Nombre de la Institución Educativa</h1>-->
        <asp:Label ID="lblInfoIE" runat="server" CssClass="label-title" Text="Gestión de Alumnos"></asp:Label>
    </div>
    <div class="table-container">
        <asp:GridView ID="gvAlumnos" runat="server" CssClass="table table-bordered gridview"
            AutoGenerateColumns="False" OnRowDataBound="gvAlumnos_RowDataBound"
            OnRowCommand="gvAlumnos_RowCommand" HeaderStyle-CssClass="grid-header" ShowHeaderWhenEmpty="True" EmptyDataText="No Records Found">
            <Columns>
                <asp:BoundField HeaderText="DNI" DataField="dni" HeaderStyle-CssClass="grid-header" ItemStyle-CssClass="align-middle" ItemStyle-HorizontalAlign="Center" />
                <asp:BoundField HeaderText="Nombres" DataField="nombres" HeaderStyle-CssClass="grid-header" ItemStyle-CssClass="align-middle" ItemStyle-HorizontalAlign="Center" />
                <asp:BoundField HeaderText="Apellido Paterno" DataField="apellido_paterno" HeaderStyle-CssClass="grid-header" ItemStyle-CssClass="align-middle" ItemStyle-HorizontalAlign="Center" />
                <asp:BoundField HeaderText="Apellido Materno" DataField="apellido_materno" HeaderStyle-CssClass="grid-header" ItemStyle-CssClass="align-middle" ItemStyle-HorizontalAlign="Center" />
                <asp:TemplateField HeaderText="Acciones" HeaderStyle-CssClass="grid-header">
                    <ItemStyle HorizontalAlign="Center" />
                    <ItemTemplate>
                        <!--<asp:LinkButton ID="lbModificar" runat="server" Text="<i class='fa-solid fa-pencil'></i>" CssClass="btn btn-warning" CommandName="Modificar" CommandArgument='<%# Eval("dni") %>' />-->
                    </ItemTemplate>
                </asp:TemplateField>
            </Columns>
        </asp:GridView>
        <asp:Button ID="AddButton" runat="server" Text="Añadir Alumno" CssClass="btn btn-primary btn-add" OnClick="AddButton_Click" />
    </div>
</div>




</asp:Content>

