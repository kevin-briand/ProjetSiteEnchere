<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <title>Inscription</title>
</head>
<body>
<div class="container text-center">
    <h1 style="margin-bottom: 100px; margin-top: 50px;">Mon profil</h1>
    <form method="post" onsubmit="return testForm(this);" action="<%=request.getContextPath()%>/Inscription">
        <div class="row mb-3">
            <div class="col-5">
                <div class="mb-3 row">
                    <input
                    		required="required"
                            type="text"
                            name="pseudo"
                            class="form-control"
                            id="pseudo"
                            placeholder="Pseudo"
                            aria-label="Pseudo"
                    />
                </div>
                <div class="mb-3 row">
                    <input
                    		required="required"
                            type="text"
                            name="prenom"
                            class="form-control"
                            id="prenom"
                            placeholder="Prénom"
                            aria-label="Prénom"
                    />
                </div>
                <div class="mb-3 row">
                    <input
                            type="text"
                            name="telephone"
                            class="form-control"
                            id="telephone"
                            placeholder="Téléphone"
                            aria-label="Téléphone"
                    />
                </div>
                <div class="mb-3 row">
                    <input
                    		required="required"
                            type="text"
                            name="cp"
                            class="form-control"
                            id="cp"
                            placeholder="Code Postal"
                            aria-label="Code Postal"
                    />
                </div>
                <div class="mb-3 row">
                    <input
                    		required="required"
                            type="password"
                            name="password"
                            class="form-control"
                            id="inputPassword"
                            placeholder="Mot de passe"
                            aria-label="Mot de passe"
                    />
                </div>
            </div>
            <div class="col-2"></div>
            <div class="col-5">
                <div class="mb-3 row">
                    <input
                    		required="required"
                            type="text"
                            name="nom"
                            class="form-control"
                            id="nom"
                            placeholder="Nom"
                            aria-label="Nom"
                    />
                </div>
                <div class="mb-3 row">
                    <input
                    		required="required"
                            type="text"
                            name="email"
                            class="form-control"
                            id="email"
                            placeholder="Email"
                            aria-label="Email"
                    />
                </div>
                <div class="mb-3 row">
                    <input
                            required="required"
                            type="text"
                            name="rue"
                            class="form-control"
                            id="rue"
                            placeholder="Rue"
                            aria-label="Rue"
                    />
                </div>
                <div class="mb-3 row">
                    <input
                            required="required"
                            type="text"
                            name="ville"
                            class="form-control"
                            id="ville"
                            placeholder="Ville"
                            aria-label="Ville"
                    />
                </div>
                <div class="mb-3 row">
                    <input
                            required="required"
                            type="password"
                            name="passwordConfirmation"
                            class="form-control"
                            id="inputConfirmation"
                            placeholder="Confirmation mot de passe"
                            aria-label="Confirmation mot de passe"
                            oninput="testPassword()"
                    />
                </div>
            </div>
        </div>
        <div class="buttons">
            <button type="submit" class="btn btn-primary btn-lg">Créer</button>
            <button type="reset" class="btn btn-secondary btn-lg">Annuler</button>
        </div>
    </form>
</div>
<script>
    function testPassword() {
        const pwd = document.getElementById("inputPassword");
        const confirmPwd = document.getElementById("inputConfirmation");

        if(pwd.value !== confirmPwd.value) {
            pwd.style.borderColor = "red";
            confirmPwd.style.borderColor = "red";
            return false;
        } else {
            pwd.style.borderColor = "green";
            confirmPwd.style.borderColor = "green";
            return true;
        }
    }

    function testForm(form) {
        var isOk = true;
        if(setErrorInput(form.pseudo, form.pseudo.value.length > 30)) isOk = false;
        if(setErrorInput(form.nom, form.nom.value.length > 30)) isOk = false;
        if(setErrorInput(form.prenom, form.prenom.value.length > 30)) isOk = false;
        if(setErrorInput(form.email, form.email.value.length > 50)) isOk = false;
        if(setErrorInput(form.telephone, form.telephone.value.length > 15)) isOk = false;
        if(setErrorInput(form.cp, form.cp.value.length > 5)) isOk = false;
        if(setErrorInput(form.rue, form.rue.value.length > 50)) isOk = false;
        if(setErrorInput(form.ville, form.ville.value.length > 30)) isOk = false;
        if(!testPassword()) isOk = false;

        return isOk;
    }

    function setErrorInput(input, error) {
        if(error) {
            input.style.borderColor = "red";
        } else {
            input.style.borderColor = "";
        }
        return error;
    }
</script>
</body>
</html>
