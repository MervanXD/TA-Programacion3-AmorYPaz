<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="GestionarPlanesEstudio.aspx.cs" Inherits="AmorYPazBackend.GestionarPlanesEstudio" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphHead" runat="server">
    <link href="Content/EstilosGestionarPlanesEstudio.css" rel="stylesheet" />
    <script src="Scripts/scriptsGestionarPlanes.js"></script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphTitulo" runat="server">
    Gestionar Plan de Estudios
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container">
        <div class="header">
            <!--<h1>Nombre de la Institución Educativa</h1>-->
            <asp:Label ID="lblInfoIE" runat="server" CssClass="label-title"></asp:Label>
            <div class="btn-group">
                <asp:Button ID="btnActual" runat="server" Text="Actual" OnClick="btnFiltrar_Click" CommandArgument="actual" CssClass="btn btn-primary button-bordered" />
                <asp:Button ID="btnFinalizados" runat="server" Text="Finalizados" OnClick="btnFiltrar_Click" CommandArgument="finalizados" CssClass="btn btn-primary button-bordered" />
            </div>
        </div>
        <div class="table-container">
            <asp:GridView ID="gvPlanes" runat="server" CssClass="table table-bordered gridview"
                AutoGenerateColumns="False" OnRowDataBound="gvPlanes_RowDataBound"
                OnRowCommand="gvPlanes_RowCommand" HeaderStyle-CssClass="grid-header">
                <Columns>
                    <asp:BoundField HeaderText="Año" HeaderStyle-CssClass="grid-header" ItemStyle-CssClass="align-middle" ItemStyle-HorizontalAlign="Center"/>
                    <asp:BoundField HeaderText="Grado y Seccion" HeaderStyle-CssClass="grid-header" ItemStyle-CssClass="align-middle" ItemStyle-HorizontalAlign="Center"/>
                    <asp:BoundField HeaderText="Fecha Inicio" HeaderStyle-CssClass="grid-header" ItemStyle-CssClass="align-middle" ItemStyle-HorizontalAlign="Center"/>
                    <asp:BoundField HeaderText="Fecha Fin" HeaderStyle-CssClass="grid-header" ItemStyle-CssClass="align-middle" ItemStyle-HorizontalAlign="Center"/>
                    <asp:BoundField HeaderText="Número de Cursos" HeaderStyle-CssClass="grid-header" ItemStyle-CssClass="align-middle" ItemStyle-HorizontalAlign="Center"/>
                    <asp:TemplateField HeaderText="Descripción" HeaderStyle-CssClass="grid-header">
                        <ItemStyle HorizontalAlign="Center" />
                        <itemtemplate>
                            <asp:LinkButton id="lbDetalle" runat="server" Text="<i class='fa-solid fa-note'></i>" CssClass="btn btn-primary" CommandName="MostrarDescripcion" CommandArgument='<%# Eval("descripcion") %>' />    
                        </itemtemplate>
                    </asp:TemplateField>
                    <asp:TemplateField>
                        <ItemStyle HorizontalAlign="Center" />
                        <ItemTemplate>
                            <asp:LinkButton ID="lbModificar" runat="server" Text="<i class='fa-solid fa-pencil'></i>" CssClass="btn btn-warning" OnClick="lbModificar_Click" CommandArgument='<%# Eval("idPlan") %>' />
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
            <asp:Button ID="AddButton" runat="server" Text="Añadir" CssClass="btn btn-primary btn-add" OnClick="AddButton_Click" />
        </div>
    </div>
    <!--Modal de detalle-->
    <div id="descriptionModal" class="modal fade" tabindex="-1" aria-labelledby="confirmLogoutModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalLabel">Descripción del Plan de Estudios</h5>
                </div>
                <div class="modal-body">
                    <p id="modalDescriptionText"></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                </div>
            </div>
        </div>
    </div>


</asp:Content>
