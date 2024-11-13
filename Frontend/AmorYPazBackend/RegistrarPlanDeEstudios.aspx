<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="RegistrarPlanDeEstudios.aspx.cs" Inherits="AmorYPazBackend.RegistrarPlanDeEstudios" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphHead" runat="server">
    <link href="Content/EstilosRegistrarPlanDeEstudios.css" rel="stylesheet" />
    <link href="Content/estilosMasterPage.css" rel="stylesheet" />
    <link href="Content/bootstrap.css" rel="stylesheet" />
    <script src="Scripts/scriptsRegistrarPlanes.js"></script>

</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphTitulo" runat="server">
    Registrar Plan de Estudios
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <asp:ScriptManager ID="ScriptManager1" runat="server" />
    <asp:UpdatePanel ID="upContenedor" runat="server" UpdateMode="Conditional">
        <ContentTemplate>
            <div class="container">
                <div class="header">
                    <h1>
                        <asp:Label ID="lblNombreInstitucion" runat="server" Text="Registro de Plan de Estudios" CssClass="label-title"></asp:Label>
                    </h1>
                </div>
                <div class="card-body">
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <asp:Label ID="lblAnio" runat="server" Text="Año:" CssClass="col-form-label fw-bold"></asp:Label>
                            <asp:DropDownList ID="ddlAnio" runat="server" CssClass="form-select">
                                <asp:ListItem Text="Seleccione el año" Value="0"></asp:ListItem>
                            </asp:DropDownList>
                        </div>
                        <div class="col-md-6">
                            <asp:Label ID="lblGrado" runat="server" Text="Grado:" CssClass="col-form-label fw-bold"></asp:Label>
                            <asp:DropDownList ID="ddlGrado" runat="server" CssClass="form-select">
                                <asp:ListItem Text="Seleccione el grado y nivel" Value="0"></asp:ListItem>
                            </asp:DropDownList>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <asp:Label ID="lblFechaInicio" runat="server" Text="Fecha de Inicio:" CssClass="col-form-label fw-bold"></asp:Label>
                            <asp:TextBox ID="txtFechaInicio" runat="server" CssClass="form-control" TextMode="Date"></asp:TextBox>
                        </div>
                        <div class="col-md-6">
                            <asp:Label ID="lblFechaFin" runat="server" Text="Fecha de Fin:" CssClass="col-form-label fw-bold"></asp:Label>
                            <asp:TextBox ID="txtFechaFin" runat="server" CssClass="form-control" TextMode="Date"></asp:TextBox>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <asp:Label ID="lblDescripcion" runat="server" Text="Descripción:" CssClass="col-form-label fw-bold"></asp:Label>
                            <asp:TextBox ID="txtDescripcion" runat="server" CssClass="form-control" TextMode="MultiLine" Rows="4"></asp:TextBox>
                        </div>
                    </div>
                </div>

                <div class="card">
                    <div class="card-header" style="background-color: #78BFBF;">
                        <h5 class="card-title mb-0" style="background-color: #78BFBF;">Lista de Cursos</h5>
                    </div>
                    <div class="card-body">
                        <div class="row align-items-center pb-3">
                            <div class="col-auto">
                                <asp:Label ID="lblCurso" CssClass="form-label" runat="server" Text="Curso:"></asp:Label>
                            </div>
                            <div class="col-sm-4">
                                <asp:TextBox ID="txtNombreCurso" CssClass="form-control" runat="server" Enabled="false"></asp:TextBox>
                            </div>
                            <div class="col-sm-2">
                                <asp:LinkButton ID="lbBuscarCurso" runat="server"
                                    CssClass="btn btn-info" Text="<i class='fa-solid fa-magnifying-glass pe-2'></i> Buscar" OnClick="lbBuscarCursoModal_Click"/>
                            </div>
                            <div class="col text-end">
                                <asp:LinkButton ID="lbAgregarCurso" runat="server" CssClass="btn btn-success" Text="<i class='fa-solid fa-plus pe-2'></i> Agregar" OnClick="lbAgregarCurso_Click1" />
                            </div>
                        </div>
                        <asp:UpdatePanel runat="server">
                            <ContentTemplate>
                                <div class="row">
                                    <asp:GridView ID="gvCursos" runat="server" AllowPaging="true" PageSize="5" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped"
                                        OnPageIndexChanging="gvCursos_PageIndexChanging" ShowHeaderWhenEmpty="true" HeaderStyle-CssClass="grid-header">
                                        <Columns>
                                            <asp:BoundField HeaderText="Nombre del Curso" />
                                            <asp:TemplateField>
                                                <ItemTemplate>
                                                    <asp:LinkButton runat="server" Text="<i class='fa-solid fa-trash'></i>"
                                                        OnClick="EliminarCurso_Click" CommandArgument='<%# Eval("nombre") %>' />
                                                </ItemTemplate>
                                            </asp:TemplateField>
                                        </Columns>
                                    </asp:GridView>
                                </div>
                            </ContentTemplate>
                        </asp:UpdatePanel>
                    </div>
                </div>


                <div class="card-footer text-center">
                    <asp:LinkButton ID="lbGuardar" runat="server" Text="Guardar" CssClass="btn btn-primary" OnClick="lbGuardar_Click" />
                    <asp:LinkButton ID="lbCancelar" runat="server" Text="Cancelar" CssClass="btn btn-secondary" OnClick="lbCancelar_Click" />
                </div>
            </div>

            <!-- Modal para crear un nuevo curso -->
            <div class="modal" id="crearCursoModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Crear Curso</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="mb-3">
                                <asp:Label runat="server" Text="Nombre del Curso:" CssClass="form-label"></asp:Label>
                                <asp:TextBox runat="server" ID="TextBox1" CssClass="form-control"></asp:TextBox>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <asp:LinkButton ID="btnGuardarCurso" runat="server" CssClass="btn btn-primary" OnClick="btnGuardarCurso_Click">Guardar</asp:LinkButton>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal" id="form-modal">
                <div class="modal-dialog modal-xl">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Selección de Cursos a registrar</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <asp:UpdatePanel runat="server">
                                <ContentTemplate>
                                    <div class="container pb-3">
                                        <div class="row align-items-center">
                                            <div class="col-auto">
                                                <asp:Label CssClass="col-form-label" runat="server" Text="Ingresar nombre del curso:"></asp:Label>
                                            </div>
                                            <div class="col-sm-4">
                                                <asp:TextBox CssClass="form-control" ID="txtNombreCursoModal" runat="server"></asp:TextBox>
                                            </div>
                                            <div class="col-sm-2">
                                                <asp:LinkButton ID="lbBuscarCursoModal" runat="server" CssClass="btn btn-info" Text="<i class='fa-solid fa-magnifying-glass pe-2'></i> Buscar" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="container">
                                        <asp:GridView ID="gvCursosModal" runat="server" AllowPaging="true" PageSize="5" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped" OnPageIndexChanging="gvCursosModal_PageIndexChanging" HeaderStyle-CssClass="grid-header">
                                            <Columns>
                                                <asp:BoundField HeaderText="Nombre del Curso" DataField="nombre" />
                                                <asp:TemplateField>
                                                    <ItemTemplate>
                                                        <asp:LinkButton CssClass="btn btn-success" runat="server" Text="<i class='fa-solid fa-check'></i> Seleccionar"
                                                            OnClick="SeleccionarCurso_Click" CommandArgument='<%# Eval("nombre") %>' />
                                                    </ItemTemplate>
                                                </asp:TemplateField>
                                            </Columns>
                                        </asp:GridView>
                                    </div>
                                </ContentTemplate>
                            </asp:UpdatePanel>
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
            </ContentTemplate>
    </asp:UpdatePanel>
</asp:Content>  