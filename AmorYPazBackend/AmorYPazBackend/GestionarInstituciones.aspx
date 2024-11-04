<%@ Page Title="" Language="C#" MasterPageFile="~/SuperIntendente.Master" AutoEventWireup="true" CodeBehind="GestionarInstituciones.aspx.cs" Inherits="AmorYPazBackend.GestionarInstituciones" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Listado de Insituciones
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container">
        <div class="card">
            <div class="card-header">
                <h2>Listado de Instituciones</h2>
            </div>
            <div class="card-body">
                <div class="row align-items-center mb-3">
                    <div class="col-12 col-md-auto me-2">
                        <asp:Label ID="lblNombre" runat="server" Text="Nombre: " CssClass="col-form-label"></asp:Label>
                    </div>
                    <div class="col-12 col-md-6 me-2 mt-3 mt-md-0">
                        <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control"></asp:TextBox>
                    </div>
                    <div class="col-12 col-md-auto me-2 mt-3 mt-md-0">
                        <asp:LinkButton ID="lbBuscar" runat="server" CssClass="btn btn-info" Text="<i class='fa-solid fa-magnifying-glass'></i> Buscar" OnClick="lbBuscar_Click"/>
                    </div>
                    <div class="col-12 col-md text-md-end mt-3 mt-md-0">
                        <asp:LinkButton ID="lbRegistrar" runat="server" CssClass="btn btn-success" Text="<i class='fa-solid fa-plus'></i> Registrar Institucion" OnClick="lbRegistrar_Click"/>
                    </div>
                </div>
                <div class="row">
                    <asp:GridView ID="gvInstituciones" runat="server" AllowPaging="true" PageSize="5" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped" ShowHeaderWhenEmpty="true" OnPageIndexChanging="gvInstituciones_PageIndexChanging">
                        <columns>
                            <asp:BoundField HeaderText="Nombre" DataField="nombre"/>
                            <asp:BoundField HeaderText="Direccion" DataField="direccion"/>
                            <asp:TemplateField HeaderText="Director">
                                <ItemTemplate>
                                    <%# Eval("director.nombres") + " " + Eval("director.apellidoPaterno") + " " + Eval("director.apellidoMaterno")%>
                                </ItemTemplate>
                            </asp:TemplateField>
                            <asp:TemplateField>
                                <itemtemplate>
                                    <asp:LinkButton runat="server" Text="<i class='fa-solid fa-edit'></i>" CssClass="btn btn-warning" OnClick="lbModificar_click" CommandArgument='<%# Eval("idInstitucion") %>'/>
                                    <asp:LinkButton runat="server" Text="<i class='fa-solid fa-eye'></i>" OnClick="lbVisualizar_click"  CommandArgument='<%# Eval("idInstitucion") %>' />
                                </itemtemplate>
                            </asp:TemplateField>
                        </columns>
                    </asp:GridView>
                </div>
            </div>
        </div>
    </div>

</asp:Content>

