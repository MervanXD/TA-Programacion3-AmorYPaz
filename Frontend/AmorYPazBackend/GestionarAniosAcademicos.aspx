<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="GestionarAniosAcademicos.aspx.cs" Inherits="AmorYPazBackend.GestionarAniosAcademicos" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphHead" runat="server">
    <link href="Content/EstilosGestionarAniosAcademicos.css" rel="stylesheet" />
    <script src="Scripts/scriptsGestionarPlanes.js"></script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphTitulo" runat="server">
    Gestionar Años Académicos
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container">
        <div class="header">
            <!--<h1>Nombre de la Institución Educativa</h1>-->
            <asp:Label ID="lblInfoIE" runat="server" CssClass="label-title"></asp:Label>
            <div class="btn-group">
                <asp:Button ID="btnActual" runat="server" Text="Actual" OnClick="btnActual_Click" CommandArgument="actual" CssClass="btn btn-primary button-bordered" />
                <asp:Button ID="btnFinalizados" runat="server" Text="Finalizados" OnClick="btnActual_Click" CommandArgument="finalizados" CssClass="btn btn-primary button-bordered" />
            </div>
        </div>
        <div class="table-container">
            <asp:GridView ID="gvAnios" runat="server" CssClass="table table-bordered gridview"  AllowPaging="true" PageSize="3" OnPageIndexChanging="gvAnios_PageIndexChanging"
                AutoGenerateColumns="False" OnRowDataBound="gvAnios_RowDataBound" 
                HeaderStyle-CssClass="grid-header">
                <Columns>
                    <asp:BoundField HeaderText="Año" HeaderStyle-CssClass="grid-header" ItemStyle-CssClass="align-middle" ItemStyle-HorizontalAlign="Center" />
                    <asp:BoundField HeaderText="Fecha Inicio" HeaderStyle-CssClass="grid-header" ItemStyle-CssClass="align-middle" ItemStyle-HorizontalAlign="Center" />
                    <asp:BoundField HeaderText="Fecha Fin" HeaderStyle-CssClass="grid-header" ItemStyle-CssClass="align-middle" ItemStyle-HorizontalAlign="Center" />
                    <asp:BoundField HeaderText="Estado" HeaderStyle-CssClass="grid-header" ItemStyle-CssClass="align-middle" ItemStyle-HorizontalAlign="Center" />
                    <asp:TemplateField>
                        <ItemStyle HorizontalAlign="Center" />
                        <ItemTemplate>
                            <asp:LinkButton ID="lbModificar" runat="server" Text="<i class='fa-solid fa-pencil'></i>" CssClass="btn btn-warning" OnClick="lbModificar_Click" CommandArgument='<%# Eval("idAnio") %>' />
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
            <asp:Button ID="AddButton" runat="server" Text="Añadir" CssClass="btn btn-primary btn-add" OnClick="AddButton_Click" />
        </div>
    </div>

</asp:Content>
