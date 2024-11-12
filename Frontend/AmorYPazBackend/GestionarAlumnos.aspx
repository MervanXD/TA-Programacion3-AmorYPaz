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

    <div class="card-body">
    <div class="row align-items-center mb-3">
        <div class="col-12 col-md-auto me-2">
            <asp:Label ID="lblDNI" runat="server" Text="DNI del alumno: " CssClass="col-form-label"></asp:Label>
        </div>
        <div class="col-12 col-md-6 me-2 mt-3 mt-md-0">
            <asp:TextBox ID="txtDNI" runat="server" CssClass="form-control"></asp:TextBox>
        </div>
        <div class="col-12 col-md-auto me-2 mt-3 mt-md-0">
            <asp:LinkButton ID="lbBuscar" runat="server" CssClass="btn btn-info" Text="<i class='fa-solid fa-magnifying-glass'></i> Buscar" OnClick="lbBuscar_Click"/>
        </div>
        <div class="col-12 col-md text-md-end mt-3 mt-md-0">
            <asp:LinkButton ID="lbRegistrar" runat="server" CssClass="btn btn-success" Text="<i class='fa-solid fa-plus'></i> Registrar alumno" OnClick="lbRegistrar_Click"/>
        </div>
    </div>

    <div>
        <asp:DropDownList ID="ddlGrados" runat="server" AutoPostBack="true" OnSelectedIndexChanged="ddlGrados_SelectedIndexChanged">
        </asp:DropDownList>
    </div>

    <div class="table-container">
        <asp:GridView ID="gvAlumnos" runat="server" CssClass="table table-bordered gridview"
            AutoGenerateColumns="False" OnRowDataBound="gvAlumnos_RowDataBound"
             HeaderStyle-CssClass="grid-header" ShowHeaderWhenEmpty="True" EmptyDataText="No Records Found">
            <Columns>
                <asp:BoundField HeaderText="DNI" DataField="dni" HeaderStyle-CssClass="grid-header" ItemStyle-CssClass="align-middle" ItemStyle-HorizontalAlign="Center" />
                <asp:BoundField HeaderText="Nombres" DataField="nombres" HeaderStyle-CssClass="grid-header" ItemStyle-CssClass="align-middle" ItemStyle-HorizontalAlign="Center" />
                <asp:BoundField HeaderText="Apellido Paterno" DataField="apellidoPaterno" HeaderStyle-CssClass="grid-header" ItemStyle-CssClass="align-middle" ItemStyle-HorizontalAlign="Center" />
                <asp:BoundField HeaderText="Apellido Materno" DataField="apellidoMaterno" HeaderStyle-CssClass="grid-header" ItemStyle-CssClass="align-middle" ItemStyle-HorizontalAlign="Center" />
                <asp:TemplateField HeaderText="Acciones" HeaderStyle-CssClass="grid-header">
                    <ItemStyle HorizontalAlign="Center" />
                    <ItemTemplate>
                        <asp:LinkButton ID="lbModificar" runat="server" Text="<i class='fa-solid fa-pencil'></i>" CssClass="btn btn-warning" CommandName="Modificar" OnClick="lbModificar_Click" CommandArgument='<%# Eval("idPersona") %>' />
                        <asp:LinkButton runat="server" Text="<i class='fa-solid fa-trash'></i>" CssClass="btn btn-danger" OnClick="lbEliminar_Click" CommandArgument='<%# Eval("idPersona") %>'/>
                    </ItemTemplate>
                </asp:TemplateField>
            </Columns>
        </asp:GridView>
    </div>
</div>




</asp:Content>

