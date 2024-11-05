<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="GestionarPlanesEstudio.aspx.cs" Inherits="AmorYPazBackend.GestionarPlanesEstudio" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphHead" runat="server">
    <link href="Content/EstilosGestionarPlanesEstudio.css" rel="stylesheet" />
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphTitulo" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="content">
        <div class="header">
            <h1>Nombre de la Institución Educativa</h1>
            <!--<asp:Label ID="lblInfoIE" runat="server" CssClass="label-title"></asp:Label>-->
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
            <asp:GridView ID="GridView1" runat="server" CssClass="table table-bordered" AutoGenerateColumns="False">
                <Columns>
                    <asp:BoundField DataField="Anho" HeaderText="Año" />
                    <asp:BoundField DataField="Nivel" HeaderText="Nivel" />
                    <asp:BoundField DataField="Grado" HeaderText="Grado" />
                    <asp:BoundField DataField="Matriculados" HeaderText="# matrículas" />
                    <asp:BoundField DataField="Vacantes" HeaderText="Vacantes" />
                    <asp:TemplateField HeaderText="Activo">
                        <ItemTemplate>
                            <asp:CheckBox ID="CBActivo" runat="server" Checked='<%# Eval("IsActive") %>' />
                        </ItemTemplate>
                    </asp:TemplateField>
                    <asp:TemplateField HeaderText="">
                        <ItemTemplate>
                            <asp:Button ID="EditButton" runat="server" Text="Editar" CssClass="btn btn-secondary" CommandName="Edit" CommandArgument='<%# Eval("ID") %>' />
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
            <asp:Button ID="AddButton" runat="server" Text="Añadir" CssClass="btn btn-secondary btn-add" OnClick="AddButton_Click" />
        </div>
    </div>
</asp:Content>
