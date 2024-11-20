<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="AdministrarDirectores.aspx.cs" Inherits="AmorYPazBackend.AdministrarDirectores" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphHead" runat="server">
    <link href="Content/estilosMasterPage.css" rel="stylesheet" />
    <link href="Content/bootstrap.css" rel="stylesheet" />
    <script src="Scripts/scriptsListarDirectores.js"></script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphTitulo" runat="server">
    Administrar Directores
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container">
        <div class="card">
            <div class="card-header">
                <h2>Listado de Directores</h2>
            </div>
            <div class="card-body">
                <div class="row align-items-center mb-3">
                    <div class="col-12 col-md-auto me-2">
                        <asp:Label ID="lblNombre" runat="server" Text="Ingrese el nombre del director: " CssClass="form-label"></asp:Label>
                    </div>
                    <div class="col-12 col-md-6 me-2 mt-3 mt-md-0">
                        <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control"></asp:TextBox>
                    </div>
                    <div class="col-12 col-md-auto me-2 mt-3 mt-md-0">
                        <asp:LinkButton ID="lbBuscar" runat="server" CssClass="btn btn-info" Text="<i class='fa-solid fa-magnifying-glass'></i> Buscar" OnClick="lbBuscar_Click" />
                    </div>
                    <div class="col-12 col-md text-md-end mt-3 mt-md-0">
                        <asp:LinkButton ID="lbRegistrar" runat="server" CssClass="btn btn-success" Text="<i class='fa-solid fa-plus'></i> Registrar Director" OnClick="lbRegistrar_Click" />
                    </div>
                </div>
                <div class="row">
                    <asp:GridView ID="dgvDirectores" runat="server" AutoGenerateColumns="false"
                        OnRowDataBound="dgvDirectores_RowDataBound" AllowPaging="true"
                        OnPageIndexChanging="dgvDirectores_PageIndexChanging" PageSize="5"
                        CssClass="table table-hover table-responsive table-striped">
                        <Columns>
                            <asp:BoundField HeaderText="Nombre" ItemStyle-CssClass="align-middle" />
                            <asp:BoundField HeaderText="Sexo" ItemStyle-CssClass="align-middle" />
                            <asp:BoundField HeaderText="FechaInicio" ItemStyle-CssClass="align-middle" />
                            <asp:BoundField HeaderText="Correo" ItemStyle-CssClass="align-middle" />
                            <asp:TemplateField>
                                <ItemTemplate>
                                    <asp:LinkButton runat="server" Text="<i class='fa-solid fa-edit'></i> Modificar" CssClass="btn btn-warning" OnClick="lbModificar_Click" CommandArgument='<%# Eval("IdPersona") %>' />
                                    <asp:LinkButton runat="server" Text="<i class='fa-solid fa-trash'></i> Eliminar" CssClass="btn btn-danger" OnClick="lbEliminar_Click" CommandArgument='<%# Eval("IdPersona") %>' />
                                </ItemTemplate>
                            </asp:TemplateField>
                        </Columns>
                    </asp:GridView>
                </div>
            </div>
        </div>
    </div>

    <div id="modalMensaje" class="modal fade" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalLabel">Mensaje</h5>
                </div>
                <div class="modal-body">
                    <p id="mensajeTexto">El registro del plan de estudios se ha realizado con éxito.</p>
                </div>
            </div>
        </div>
    </div>

</asp:Content>
