<%@ Page Title="" Language="C#" MasterPageFile="~/SuperIntendente.Master" AutoEventWireup="true" CodeBehind="MostrarInstitucion.aspx.cs" Inherits="AmorYPazBackend.MostrarInstitucion" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphHead" runat="server">
    <link rel="stylesheet" href="Content/estilosMostrarInstitucion.css" />
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphTitulo" runat="server">
    Datos de la Institucion
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container">
        <h5>Institución</h5>
        <div class="row">
            <div class="col-md-8">
                <form>
                    <div class="form-group row">
                        <label for="nombreInstitucion" class="col-sm-4 col-form-label">Nombre de la Institución</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="nombreInstitucion" value="Colegio PUCP">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="direccionInstitucion" class="col-sm-4 col-form-label">Dirección de la Institución</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="direccionInstitucion" value="Av. Universitaria 1801">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="director" class="col-sm-4 col-form-label">Director</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="director" value="Manuel Alejandro Sánchez la Rosa">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="cantidadMatriculados" class="col-sm-4 col-form-label">Cantidad de Matriculados</label>
                        <div class="col-sm-4">
                            <input type="number" class="form-control" id="cantidadMatriculados" value="250">
                        </div>
                        <label for="fechaCreacion" class="col-sm-4 col-form-label">Fecha de Creación</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="fechaCreacion" value="20/01/1990">
                        </div>
                        <div class="col-sm-1">
                            <i class="fas fa-calendar-alt"></i>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-4">Nivel</div>
                        <div class="col-sm-8">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" id="secundaria">
                                <label class="form-check-label" for="secundaria">Secundaria</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" id="primaria">
                                <label class="form-check-label" for="primaria">Primaria</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" id="inicial">
                                <label class="form-check-label" for="inicial">Inicial</label>
                            </div>
                        </div>
                    </div>
                    <div class="btn-group">
                        <button type="submit" class="btn btn-primary">Guardar</button>
                        <button type="button" class="btn btn-secondary">Cancelar</button>
                    </div>
                </form>
            </div>
            <div class="col-md-4 d-flex align-items-center">
                <div class="image-placeholder">
                    <span>Image</span>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
