function mostrarModalCursos() {
    console.log("mensaje");
    var modalForm = new bootstrap.Modal(document.getElementById('form-modal'));
    modalForm.toggle();
}

function showModalFormRegistrarCurso() {
    console.log("mensaje");
    var modalForm = new bootstrap.Modal(document.getElementById('crearCursoModal'));
    modalForm.show(); // Cambia toggle() por show() para abrir el modal
}
function validarNombreCurso(sender, args) {
    var nombre = args.Value;
    var regex = /^[A-Za-z\s]+$/; // Solo letras y espacios
    args.IsValid = regex.test(nombre);

}

function mostrarModal(mensaje, redirectUrl) {
    console.log("Modal llamado con mensaje:", mensaje); // Para depuración
    document.getElementById("mensajeTexto").innerText = mensaje;
    var modal = new bootstrap.Modal(document.getElementById('modalMensaje'));
    modal.show();
    setTimeout(function () {
        modal.hide();
        window.location.href = redirectUrl;
    }, 4000);
}

