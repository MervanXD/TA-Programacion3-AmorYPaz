<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="GestionarMatricula.aspx.cs" Inherits="AmorYPazBackend.GestionarMatricula" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphHead" runat="server">
    <script src="Scripts/scriptsMasterPage.js"></script>
    <link href="Content/EstilosGestionarPlanesEstudio.css" rel="stylesheet" />
    <script src="Scripts/scriptsGestionarPlanes.js"></script>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="cphTitulo" runat="server">
    Gestionar Matrículas
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container">
        <div class="header">
            <asp:Label ID="lblInfoMatricula" runat="server" CssClass="label-title"></asp:Label>
            <!--<div class="btn-group">
                <asp:Button ID="btnActual" runat="server" Text="Actual" OnClick="btnActual_Click" CommandArgument="actual" CssClass="btn btn-primary button-bordered" />
                <asp:Button ID="btnHistorial" runat="server" Text="Historial" OnClick="btnHistorial_Click" CommandArgument="historial" CssClass="btn btn-primary button-bordered" />
            </div>-->
        </div>
        <div>
            <asp:DropDownList ID="ddlGrados" runat="server" AutoPostBack="true" OnSelectedIndexChanged="ddlGrados_SelectedIndexChanged">
            </asp:DropDownList>
        </div>
        <div class="table-container">
            <asp:GridView ID="gvMatriculas" runat="server" CssClass="table table-bordered gridview"
                AutoGenerateColumns="False" OnRowDataBound="gvMatriculas_RowDataBound"
                OnRowCommand="gvMatriculas_RowCommand" HeaderStyle-CssClass="grid-header" ShowHeaderWhenEmpty="True" EmptyDataText="No Records Found">
                <Columns>
                    <asp:BoundField HeaderText="Año" HeaderStyle-CssClass="grid-header" ItemStyle-CssClass="align-middle" ItemStyle-HorizontalAlign="Center"/>
                    <asp:BoundField HeaderText="Alumno" HeaderStyle-CssClass="grid-header" ItemStyle-CssClass="align-middle" ItemStyle-HorizontalAlign="Center"/>
                    <asp:BoundField HeaderText="Grado" HeaderStyle-CssClass="grid-header" ItemStyle-CssClass="align-middle" ItemStyle-HorizontalAlign="Center"/>
                    <asp:BoundField HeaderText="Fecha" HeaderStyle-CssClass="grid-header" ItemStyle-CssClass="align-middle" ItemStyle-HorizontalAlign="Center"/>
                    <asp:TemplateField HeaderText="Resultados" HeaderStyle-CssClass="grid-header">
                        <ItemStyle HorizontalAlign="Center" />
                        <ItemTemplate>
                            <asp:LinkButton ID="lbResultados" runat="server" Text="<i class='fa-solid fa-notebook'></i>" CssClass="btn btn-info" OnClick="lbResultados_Click" CommandArgument='<%# Eval("idMatricula") %>' />
                        </ItemTemplate>
                    </asp:TemplateField>
                    <asp:TemplateField HeaderText="Acciones" HeaderStyle-CssClass="grid-header">
                        <ItemStyle HorizontalAlign="Center" />
                        <ItemTemplate>
                            <asp:LinkButton ID="lbModificar" runat="server" Text="<i class='fa-solid fa-pencil'></i>" CssClass="btn btn-warning" OnClick="lbModificar_Click" CommandArgument='<%# Eval("idMatricula") %>' />
                            <asp:LinkButton ID="lbEliminar" runat="server" Text="<i class='fa-solid fa-trash'></i>" CssClass="btn btn-danger" OnClick="lbEliminar_Click" CommandArgument='<%# Eval("idMatricula") %>' />
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
            <asp:Button ID="btnAgregar" runat="server" Text="Añadir" CssClass="btn btn-primary btn-add" OnClick="btnAgregar_Click" />
        </div>
    </div>

    <!-- Modal de Confirmación de Eliminación -->
    <div id="deleteConfirmModal" class="modal fade" tabindex="-1" aria-labelledby="deleteConfirmModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteConfirmModalLabel">Confirmar Eliminación</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p>¿Está seguro que desea eliminar esta matrícula?</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <asp:Button ID="btnConfirmarEliminar" runat="server" Text="Eliminar" CssClass="btn btn-danger" OnClick="btnConfirmarEliminar_Click" />
                </div>
            </div>
        </div>
    </div>
</asp:Content>
