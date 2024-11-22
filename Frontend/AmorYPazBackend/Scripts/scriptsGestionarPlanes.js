function showDescriptionModal(description) {
    console.log("Description: " + description);
    document.getElementById("modalDescriptionText").innerText = description;
    var myModal = new bootstrap.Modal(document.getElementById('descriptionModal'));
    myModal.show();
}

function closeDescriptionModal() {
    var myModal = bootstrap.Modal.getInstance(document.getElementById('descriptionModal'));
    modal.style.display = "none";
}

window.onclick = function (event) {
    var modal = document.getElementById("descriptionModal");
    if (event.target === modal) {
        modal.style.display = "none";
    }
}

function filtrarPlanes(tipoFiltro) {
    console.log("filtrando planes");
    document.getElementById("btnActual").classList.remove("active");
    document.getElementById("btnFinalizados").classList.remove("active");
    if (tipoFiltro === 'actual') {
        document.getElementById("btnActual").classList.add("active");
    } else {
        document.getElementById("btnFinalizados").classList.add("active");
    }
    filtrarPlanesEstudio(tipoFiltro);
}
