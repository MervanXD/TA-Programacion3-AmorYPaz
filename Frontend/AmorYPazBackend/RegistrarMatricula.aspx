<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="RegistrarMatricula.aspx.cs" Inherits="AmorYPazBackend.RegistrarMatricula" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphHead" runat="server">
    <script src="Scripts/scriptsMasterPage.js"></script>
    <script src="Scripts/scriptsRegMatricula.js"></script>
    <link href="Content/estilosMasterPage.css" rel="stylesheet"/>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphTitulo" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <asp:ScriptManager ID="smMatricula" runat="server" />
    <asp:UpdatePanel ID="upMatricula" runat="server">
        <ContentTemplate>
            <div class="container">
                <div class="card">
                    <div class="card-header">
                        <h2>
                            <asp:Label ID="lblTitulo" runat="server" Text="Registro de Matrícula"></asp:Label>
                        </h2>
                    </div>
                    <div class="card-body">
                        <div class="mb-3 row">
                            <asp:Label ID="lblAnio" runat="server" Text="Periodo Académico: " CssClass="col-sm-2 col-form-label"></asp:Label>
                            <div class="col-sm-8">
                                <asp:DropDownList ID="ddlAnio" runat="server" CssClass="form-control">
                                </asp:DropDownList>
                                <asp:RequiredFieldValidator ID="rfvAnio" runat="server" 
                                    ControlToValidate="ddlAnio" ValidationGroup="GrupoGuardarM"
                                    ErrorMessage="El periodo académico es obligatorio." ForeColor="Red">
                                </asp:RequiredFieldValidator>
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <asp:Label ID="lblFecha" runat="server" Text="Fecha: " CssClass="col-sm-2 col-form-label"></asp:Label>
                            <div class="col-sm-8">
                                <input id="dtpFecha" class="form-control" type="date" runat="server" />
                                <asp:RequiredFieldValidator ID="rfvFecha" runat="server" 
                                    ControlToValidate="dtpFecha" ValidationGroup="GrupoGuardarM"
                                    ErrorMessage="La fecha es obligatoria." ForeColor="Red">
                                </asp:RequiredFieldValidator>
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <asp:Label ID="lblAlumno" runat="server" Text="Alumno: " CssClass="col-sm-2 col-form-label"></asp:Label>
                            <div class="col-sm-8">
                                <asp:TextBox ID="txtAlumno" runat="server" CssClass="form-control"></asp:TextBox>
                                <asp:Button ID="btnBuscar_Estudiante" runat="server" CssClass="btn btn-primary" Text="Buscar" OnClick="btnBuscar_Estudiante_Click" />
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <asp:Label ID="lblGrado" runat="server" Text="Grado: " CssClass="col-sm-2 col-form-label"></asp:Label>
                            <div class="col-sm-8">
                                <asp:DropDownList ID="ddlGrado" runat="server" CssClass="form-control">
                                </asp:DropDownList>
                                <asp:RequiredFieldValidator ID="rfvGrado" runat="server" 
                                    ControlToValidate="ddlGrado" ValidationGroup="GrupoGuardarM"
                                    ErrorMessage="El grado es obligatorio." ForeColor="Red">
                                </asp:RequiredFieldValidator>
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <asp:Label ID="lblVacantes" runat="server" Text="Vacantes Libres: " CssClass="col-sm-2 col-form-label"></asp:Label>
                            <div class="col-sm-8">
                                <asp:TextBox ID="txtVacantes" runat="server" CssClass="form-control" ReadOnly="true"></asp:TextBox>
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <asp:Label ID="lblObservacion" runat="server" Text="Observación: " CssClass="col-sm-2 col-form-label"></asp:Label>
                            <div class="col-sm-8">
                                <asp:TextBox ID="txtObservacion" runat="server" CssClass="form-control" TextMode="MultiLine" Rows="3"></asp:TextBox>
                            </div>
                        </div>
                    </div>

                    <div class="card-footer">
                        <asp:Button ID="btnCancelar" runat="server" CssClass="float-start btn btn-primary" Text="Cancelar" OnClick="btnCancelar_Click"/>
                        <asp:Button ID="btnGuardar" runat="server" CssClass="float-end btn btn-primary" Text="Guardar" ValidationGroup="GrupoGuardarM" OnClick="btnGuardar_Click"/>
                    </div>
                </div>
            </div>

            <!-- Modal de Error -->
            <div class="modal fade" id="errorModal" tabindex="-1" aria-labelledby="errorModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header bg-danger text-white">
                            <h5 class="modal-title" id="errorModalLabel">
                                <i class="bi bi-exclamation-triangle-fill me-2"></i>Mensaje de Error
                            </h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <asp:Label ID="lblMensajeError" runat="server" Text="Mensaje de ejemplo..." CssClass="form-text text-danger"></asp:Label>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal de Confirmación -->
            <div id="modalMensaje" class="modal fade" tabindex="-1" aria-labelledby="confirmLogoutModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="modalLabel">Mensaje</h5>
                        </div>
                        <div class="modal-body">
                            <p id="mensajeTexto">Se ha realizado el registro con éxito</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal" id="form-modal">
                <div class="modal-dialog modal-xl">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Selección de Estudiante a matricular</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <asp:UpdatePanel ID="upBusqEstudiante" runat="server" UpdateMode="Conditional">
                                <ContentTemplate>
                                    <div class="container pb-3">
                                        <div class="row align-items-center">
                                            <div class="col-auto">
                                                <asp:Label CssClass="col-form-label" runat="server" Text="Ingresar nombre o DNI del estudiante:"></asp:Label>
                                            </div>
                                            <div class="col-sm-4">
                                                <asp:TextBox CssClass="form-control" ID="txtNombreEstudianteModal" runat="server"></asp:TextBox>
                                            </div>
                                            <div class="col-sm-2">
                                                <asp:LinkButton ID="lbBuscarEstudianteModal" runat="server" CssClass="btn btn-info" Text="<i class='fa-solid fa-magnifying-glass pe-2'></i> Buscar" OnClick="lbBuscarEstudianteModal_Click" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="container">
                                        <asp:GridView ID="gvEstudiantes" runat="server" AllowPaging="true" PageSize="10" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped" OnPageIndexChanging="gvEstudiantes_PageIndexChanging" HeaderStyle-CssClass="grid-header" OnRowDataBound="gvEstudiantes_RowDataBound">
                                            <Columns>
                                                <asp:BoundField HeaderText="DNI"/>
                                                <asp:BoundField HeaderText="Nombres"/>
                                                <asp:TemplateField>
                                                    <ItemTemplate>
                                                        <asp:LinkButton CssClass="btn btn-success" runat="server" Text="<i class='fa-solid fa-check'></i> Seleccionar"
                                                            OnClick="SeleccionarEstudiante_Click" CommandArgument='<%# Eval("idPersona") %>' />
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


        </ContentTemplate>
    </asp:UpdatePanel>
</asp:Content>