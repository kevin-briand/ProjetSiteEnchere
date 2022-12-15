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

function formatDate() {
    const dates = document.getElementsByClassName("date");
    for (let i = 0; i < dates.length; i++) {
        let date = dates[i].innerText.split("T")[0];
        date = date.split("-")[2] + "-" + date.split("-")[1] + "-" + date.split("-")[0];
        const heure = dates[i].innerText.split("T")[1];
        dates[i].innerText = date + " à " + heure;
    }
}