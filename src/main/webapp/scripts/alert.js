let alertErreur = document.getElementById('alertErreur');
let alertInfo = document.getElementById('alertInfo');

function alert(message, type) {
    return [
        '<div class="m-1 w-50 mx-auto alert alert-' + type + ' alert-dismissible" role="alert">',
        '   <div>' + message + '</div>',
        '   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>',
        '</div>'
    ].join('');
}


if(alertErreur.outerText !== "") {
    alertErreur.innerHTML = alert(alertErreur.outerText,"danger");
}
if(alertInfo.outerText !== "") {
    alertInfo.innerHTML = alert(alertInfo.outerText,"success");
}