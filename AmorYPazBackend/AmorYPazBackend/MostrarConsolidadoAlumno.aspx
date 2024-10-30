<%@ Page Title="" Language="C#" MasterPageFile="~/SuperIntendente.Master" AutoEventWireup="true" CodeBehind="MostrarConsolidadoAlumno.aspx.cs" Inherits="AmorYPazBackend.MostrarConsolidadoAlumno" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphHead" runat="server">
    <link href="Content/EstilosMostrarConsoAlum.css" rel="stylesheet" />
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphTitulo" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="#">Reportes</a></li>
            <li class="breadcrumb-item active" aria-current="page">Mejores alumnos</li>
        </ol>
    </nav>
    <div class="mb-3">
        <label class="form-label">Alumno</label>
        <input type="text" readonly class="form-control-plaintext" value="Jim Navarrete Cáceres">
        <label class="form-label">Grado</label>
        <input type="text" readonly class="form-control-plaintext" value="4to Secundaria">
    </div>
    <div class="mb-3">
        <label class="form-label">Institución Educativa</label>
        <input type="text" readonly class="form-control-plaintext" value="Juan Pablo II">
    </div>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Curso</th>
                <th>Nota Final</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>Geometría</td>
                <td>18</td>
            </tr>
            <tr>
                <td>Álgebra</td>
                <td>20</td>
            </tr>
            <tr>
                <td>Aritmética</td>
                <td>19</td>
            </tr>
            <tr>
                <td>Lenguaje</td>
                <td>20</td>
            </tr>
            <tr>
                <td>Biología</td>
                <td>17</td>
            </tr>
        </tbody>
    </table>
</asp:Content>
