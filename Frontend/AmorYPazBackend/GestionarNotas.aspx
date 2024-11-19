<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="GestionarNotas.aspx.cs" Inherits="AmorYPazBackend.GestionarNotas" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphHead" runat="server">
    <link href="Content/EstilosGestionarAniosAcademicos.css" rel="stylesheet" />
    <link href="Content/estilosNotas.css" rel="stylesheet" />
    <script src="Scripts/scriptsNotas.js"></script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphTitulo" runat="server">
    Gestionar Notas del Alumno
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container">
        <div class="header" style="display: flex; flex-wrap: wrap; align-items: center; gap: 20px; padding: 10px 0;">
            <div>
                <h1 style="flex: 1; margin: 0;">
                    <asp:Label ID="lblTitulo" runat="server" Text="Registro de Notas" CssClass="label-title"></asp:Label>
                </h1>
            </div>
            
            <div style="display: flex; align-items: center; gap: 20px; margin-top: 10px;">
                <!-- Año Académico -->
                <div style="display: flex; align-items: center; gap: 10px; margin-right: 20px;">
                    <label for="ddlAnio" style="font-weight: bold;">Año Académico:</label>
                    <asp:DropDownList ID="ddlAnio" runat="server" CssClass="form-control" Style="width: 150px;" AutoPostBack="true" OnSelectedIndexChanged="ddl_SelectedIndexChanged"></asp:DropDownList>
                </div>

                <!-- Grado y Nivel -->
                <div style="display: flex; align-items: center; gap: 10px; margin-right: 20px;">
                    <label for="ddlGrado" style="font-weight: bold;">Grado y Nivel:</label>
                    <asp:DropDownList ID="ddlGrado" runat="server" CssClass="form-control" Style="width: 150px;" AutoPostBack="true" OnSelectedIndexChanged="ddl_SelectedIndexChanged"></asp:DropDownList>
                </div>

                <!-- Nombre del Estudiante -->
                <div style="display: flex; align-items: center; gap: 10px;">
                    <label for="ddlEstudiante" style="font-weight: bold;">Nombre del Estudiante:</label>
                    <asp:DropDownList ID="ddlEstudiante" runat="server" CssClass="form-control" Style="width: 200px;" AutoPostBack="true" OnSelectedIndexChanged="ddl_SelectedIndexChanged"></asp:DropDownList>
                </div>
            </div>
        </div>

        <div class="table-container">
            <asp:Label ID="lblMensaje" runat="server" Text="" CssClass="mensaje" Visible="false"></asp:Label>
            <asp:GridView ID="gvNotas" runat="server" CssClass="table table-bordered gridview" AllowPaging="true" PageSize="8" OnPageIndexChanging="gvNotas_PageIndexChanging"
                AutoGenerateColumns="False" OnRowDataBound="gvNotas_RowDataBound"
                HeaderStyle-CssClass="grid-header" ShowHeaderWhenEmpty="true">
                <Columns>
                    <asp:BoundField HeaderText="Nombre del Curso" HeaderStyle-CssClass="grid-header" ItemStyle-CssClass="align-middle" ItemStyle-HorizontalAlign="Center" />
                    <asp:TemplateField HeaderText="Calificación">
                        <HeaderStyle Width="400px" />
                        <ItemStyle HorizontalAlign="Center" />
                        <ItemTemplate>
                            <div style="display: flex; align-items: center; justify-content: center;">
                                <asp:TextBox ID="txtNotaCurso" runat="server" CssClass="form-control textbox-small" Style="text-align: center; width: 80px;" Enabled="false"></asp:TextBox>
                                <asp:LinkButton ID="lbModificar" runat="server" Text="<i class='fa-solid fa-pencil'></i>" CssClass="btn btn-warning" Style="margin-left: 10px;" OnClick="lbModificar_Click"></asp:LinkButton>
                                <asp:LinkButton ID="lbGuardar" runat="server" Text="<i class='fa-solid fa-lock'></i>" CssClass="btn btn-info" Style="margin-left: 10px;" CommandArgument='<%# Eval("curso.idCurso") %>' OnClick="lbGuardar_Click"></asp:LinkButton>
                            </div>
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
        </div>
        <div class="card-footer text-center mt-3">
            <asp:LinkButton ID="lbCancelar" runat="server" Text="Cancelar" CssClass="btn btn-secondary" OnClick="lbCancelar_Click" />
            <asp:LinkButton ID="lbAceptar" runat="server" Text="Guardar todos" CssClass="btn btn-primary" OnClick="btnAceptar_Click" />
        </div>
    </div>

    <div id="modalMensaje" class="modal fade" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalLabel">Mensaje</h5>
                </div>
                <div class="modal-body">
                    <p id="mensajeTexto">El registro de notas del estudiante se ha realizado con éxito.</p>
                </div>
            </div>
        </div>
    </div>

</asp:Content>
