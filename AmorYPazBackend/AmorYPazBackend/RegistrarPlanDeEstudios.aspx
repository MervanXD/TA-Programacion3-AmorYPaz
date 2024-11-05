<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="RegistrarPlanDeEstudios.aspx.cs" Inherits="AmorYPazBackend.RegistrarPlanDeEstudios" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphHead" runat="server">
    <link href="Content/EstilosRegistrarPlanDeEstudios.css" rel="stylesheet" />
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphTitulo" runat="server">
    Registrar Plan de Estudios
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="content">
   <div class="header">
    <h1>
        <asp:Label ID="lblNombreInstitucion" runat="server" Text=""></asp:Label>
    </h1>
    <img alt="Logo de la Institución Educativa" height="50" src="https://storage.googleapis.com/a1aa/image/rKEbxFzSEeUiIqGTB0DFrWqe0yI4e3gpY6IJzfj25XyfUSudC.jpg" width="50"/>
   </div>

    <div class="row">
     <div class="col-md-6">
      <div class="form-group">
       <label for="anio">
        Año
       </label>
       <select class="form-control" id="anio">
        <option>
         Año
        </option>
       </select>
      </div>
      <div class="form-group">
       <label for="fechaInicio">
        Fecha Inicio
       </label>
       <input class="form-control" id="fechaInicio" type="date"/>
      </div>
      <div class="form-group">
       <label for="fechaFin">
        Fecha Fin
       </label>
       <input class="form-control" id="fechaFin" type="date"/>
      </div>
      <div class="form-group">
       <label for="descripcion">
        Descripción
       </label>
       <textarea class="form-control" id="descripcion" rows="4"></textarea>
      </div>
     </div>
     <div class="col-md-6">
      <div class="form-group">
       <label for="nivel">
        Nivel
       </label>
       <input class="form-control" id="nivel" type="text" value="Primaria"/>
      </div>
      <div class="form-group">
       <label for="grado">
        Grado
       </label>
       <input class="form-control" id="grado" type="text" value="4to"/>
      </div>
      <div class="form-group">
       <label for="cursos">
        Cursos
       </label>
       <textarea class="form-control" id="cursos" rows="4">Ingrese el archivo con los cursos</textarea>
      </div>
     </div>
    </div>
    <button class="btn btn-primary" type="submit">
     Siguiente
    </button>

  </div>
</asp:Content>
