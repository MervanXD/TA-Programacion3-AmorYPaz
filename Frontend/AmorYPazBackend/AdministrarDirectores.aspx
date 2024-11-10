﻿<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="AdministrarDirectores.aspx.cs" Inherits="AmorYPazBackend.AdministrarDirectores" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphHead" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphTitulo" runat="server">
    Administrar Directores
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container">
        <div class="container row">
            <div class="row align-items-center">
                <div class="col-auto">
                    <asp:Label ID="lblNombreDNI" CssClass="form-label" runat="server" Text="Ingrese id del director:"></asp:Label>
                </div>
                <div class="col-sm-3">
                    <asp:TextBox ID="txtNombreDNI" CssClass="form-control" runat="server"></asp:TextBox>
                </div>
                <div class="col-sm-2">
                    <asp:LinkButton ID="lbBuscar" CssClass="btn btn-info" runat="server" OnClick="lbBuscar_Click" Text="<i class='fa-solid fa-magnifying-glass pe-2'></i> Buscar" />
                </div>
                <div class="col text-end p-3">
                    <asp:LinkButton ID="lbRegistrar" CssClass="btn btn-success" runat="server" OnClick="lbRegistrar_Click" Text="<i class='fa-solid fa-plus pe-2'></i> Registrar Director" />
                </div>
            </div>
        </div>
        <div class="container row">
            <asp:GridView ID="dgvDirectores" runat="server" AutoGenerateColumns="false"
                OnRowDataBound="dgvDirectores_RowDataBound" AllowPaging="true"
                OnPageIndexChanging="dgvDirectores_PageIndexChanging" PageSize="5"
                CssClass="table table-hover table-responsive table-striped">
                <Columns>
                    <asp:BoundField HeaderText="Nombre" ItemStyle-CssClass="align-middle"/>
                    <asp:BoundField HeaderText="Sexo" ItemStyle-CssClass="align-middle"/>
                    <asp:BoundField HeaderText="FechaInicio" ItemStyle-CssClass="align-middle"/>
                    <asp:BoundField HeaderText="Correo" ItemStyle-CssClass="align-middle"/>
                    <asp:TemplateField>
                        <ItemTemplate>
                            <asp:LinkButton runat="server" Text="<i class='fa-solid fa-edit'></i> Modificar" CssClass="btn btn-warning" OnClick="lbModificar_Click" CommandArgument='<%# Eval("IdPersona") %>'/>
                            <asp:LinkButton runat="server" Text="<i class='fa-solid fa-trash'></i> Eliminar" CssClass="btn btn-danger" OnClick="lbEliminar_Click" CommandArgument='<%# Eval("IdPersona") %>'/>
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
        </div>
    </div>
</asp:Content>
