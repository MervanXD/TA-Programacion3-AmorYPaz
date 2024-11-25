function showModal(idMatricula) {
    document.getElementById("hiddenIdMatricula").value = idMatricula;
    let modal = new bootstrap.Modal(document.getElementById("modalAcciones"));
    modal.show();
}

function realizarAccion(accion) {
    let idMatricula = document.getElementById("hiddenIdMatricula").value;

    alert(`Acción '${accion}' aplicada al estudiante con ID ${idMatricula}`);
    let modal = bootstrap.Modal.getInstance(document.getElementById("modalAcciones"));
    modal.hide();
}
