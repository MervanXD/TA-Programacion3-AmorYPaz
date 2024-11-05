<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="MostrarPlanDeEstudio.aspx.cs" Inherits="AmorYPazBackend.MostrarPlanDeEstudio" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphHead" runat="server">
    <link href="Content/EstilosMostrarPlanDeEstudio.css" rel="stylesheet" />
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphTitulo" runat="server">
    Plan de Estudio
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="header">
        <h1>Nombre de la Institución Educativa</h1>
        <div class="logo">Logo</div>
    </div>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="year">Año</label>
                    <input type="text" class="form-control" id="year" value="2023">
                </div>
                <div class="form-group">
                    <label for="start-date">Fecha Inicio</label>
                    <div class="input-group">
                        <input type="text" class="form-control" id="start-date" value="01 / 03 / 2023">
                        <i class="fas fa-calendar-alt calendar-icon"></i>
                    </div>
                </div>
                <div class="form-group">
                    <label for="end-date">Fecha Fin</label>
                    <div class="input-group">
                        <input type="text" class="form-control" id="end-date" value="14 / 12 / 2023">
                        <i class="fas fa-calendar-alt calendar-icon"></i>
                    </div>
                </div>
                <div class="form-group">
                    <label for="description">Descripción</label>
                    <textarea class="form-control" id="description" rows="3">Año finalizados con exito y con una media de notas de 12 y con blablablabla</textarea>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label for="level">Nivel</label>
                    <input type="text" class="form-control" id="level" value="Primaria">
                </div>
                <div class="form-group">
                    <label for="grade">Grado</label>
                    <input type="text" class="form-control" id="grade" value="4to">
                </div>
                <div class="form-group">
                    <label for="courses">Cursos</label>
                    <textarea class="form-control" id="courses" rows="3">Descargar archivo con los cursos</textarea>
                </div>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Siguiente</button>
</asp:Content>
