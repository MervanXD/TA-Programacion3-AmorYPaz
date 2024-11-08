<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="ListaConsolidadoAlumnos.aspx.cs" Inherits="AmorYPazBackend.ListaConsolidadoAlumnos" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphHead" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphTitulo" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="content">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="#">Reportes</a></li>
                <li class="breadcrumb-item active" aria-current="page">Mejores alumnos</li>
            </ol>
        </nav>
        <div class="row">
            <div class="col-md-4">
                <label for="anioAcademico" class="form-label">Año Academico</label>
                <select id="anioAcademico" class="form-select">
                    <option>2024</option>
                </select>
            </div>
            <div class="col-md-4">
                <label for="grado" class="form-label">Grado</label>
                <select id="grado" class="form-select">
                    <option>4to Secundaria</option>
                </select>
            </div>
            <div class="col-md-4">
                <label for="institucionEducativa" class="form-label">Institución Educativa</label>
                <select id="institucionEducativa" class="form-select">
                    <option>Saco Oliveros</option>
                </select>
            </div>
        </div>
        <div class="table-container">
            <!--Los datos de la tabla tienen que ser del back pero se hará luego-->
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Grado</th>
                        <th>Nivel</th>
                        <th>Colegio</th>
                        <th>Promedio</th>
                        <th>Consolidado Curricular</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Martin</td>
                        <td>4to</td>
                        <td>Secundaria</td>
                        <td>Saco Oliveros</td>
                        <td>19.20</td>
                        <td><a href="#">Ver Consolidado</a></td>
                    </tr>
                    <tr>
                        <td>Manuel</td>
                        <td>4to</td>
                        <td>Secundaria</td>
                        <td>Saco Oliveros</td>
                        <td>19.00</td>
                        <td><a href="#">Ver Consolidado</a></td>
                    </tr>
                    <tr>
                        <td>Jim</td>
                        <td>4to</td>
                        <td>Secundaria</td>
                        <td>Saco Oliveros</td>
                        <td>18.70</td>
                        <td><a href="#">Ver Consolidado</a></td>
                    </tr>
                    <tr>
                        <td>Sebastián</td>
                        <td>4to</td>
                        <td>Secundaria</td>
                        <td>Saco Oliveros</td>
                        <td>18.50</td>
                        <td><a href="#">Ver Consolidado</a></td>
                    </tr>
                    <tr>
                        <td>Juan</td>
                        <td>4to</td>
                        <td>Secundaria</td>
                        <td>Saco Oliveros</td>
                        <td>18.30</td>
                        <td><a href="#">Ver Consolidado</a></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</asp:Content>
