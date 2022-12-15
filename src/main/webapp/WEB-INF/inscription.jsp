<%@ page import="javax.swing.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="bootstrapHeader.html" %>
    <title>Inscription</title>
</head>
<body class="d-flex flex-column min-vh-100">
<%@ include file="/WEB-INF/header.jspf" %>
<div class="container text-center">
    <h1 style="margin-bottom: 75px; margin-top: 50px;">Inscription</h1>

	<p style="margin-bottom: 50px; color:red;">${requestScope["erreurInscription"]}</p>
		
	
    <form method="post" onsubmit="return testForm(this);" action="<%=request.getContextPath()%>/Inscription">
        <div class="row justify-content-md-around mb-3">
            <div class="col-md-4">
                <div class="mb-3">
                    <input
                            required="required"
                            type="text"
                            name="pseudo"
                            class="form-control"
                            id="pseudo"
                            placeholder="Pseudo"
                            aria-label="Pseudo"
                            maxlength="30"
                            value="${user.pseudo}"
                    />
                </div>
                <div class="mb-3">
                    <input
                            required="required"
                            type="text"
                            name="prenom"
                            class="form-control"
                            id="prenom"
                            placeholder="Prénom"
                            aria-label="Prénom"
                            maxlength="30"
							value="${user.prenom}"
                    />
                </div>
                <div class="mb-3">
                    <input
                            type="tel"
                            name="telephone"
                            class="form-control"
                            id="telephone"
                            placeholder="Téléphone"
                            aria-label="Téléphone"
                            pattern="[0-9]{10-11}"
                            minlength="10"
                            maxlength="11"
                            value="${user.telephone}"
                    />
                </div>
                <div class="mb-3">
                    <input
                            required="required"
                            type="text"
                            name="cp"
                            class="form-control"
                            id="cp"
                            placeholder="Code Postal"
                            aria-label="Code Postal"
                            maxlength="5"
                            pattern="[0-9]{5}"
                            value="${user.codePostal}"
                    />
                </div>
                <div class="mb-3">
                    <input
                            required="required"
                            type="password"
                            name="password"
                            class="form-control"
                            id="inputPassword"
                            placeholder="Mot de passe"
                            aria-label="Mot de passe"
                            minlength="7"
                            maxlength="30"
                    />
                </div>
            </div>
            <div class="col-md-4">
                <div class="mb-3">
                    <input
                            required="required"
                            type="text"
                            name="nom"
                            class="form-control"
                            id="nom"
                            placeholder="Nom"
                            aria-label="Nom"
                            maxlength="30"
					        value="${user.nom}"
                    />
                </div>
                <div class="mb-3">
                    <input
                            required="required"
                            type="email"
                            name="email"
                            class="form-control"
                            id="email"
                            placeholder="Email"
                            aria-label="Email"
                            maxlength="50"
                            value="${user.email}"
                    />
                </div>
                <div class="mb-3">
                    <input
                            required="required"
                            type="text"
                            name="rue"
                            class="form-control"
                            id="rue"
                            placeholder="Rue"
                            aria-label="Rue"
                            maxlength="50"
                            value="${user.rue}"
                    />
                </div>
                <div class="mb-3">
                    <input
                            required="required"
                            type="text"
                            name="ville"
                            class="form-control"
                            id="ville"
                            placeholder="Ville"
                            aria-label="Ville"
                            maxlength="30"
                            value="${user.ville}"
                    />
                </div>
                <div class="mb-3">
                    <input
                            required="required"
                            type="password"
                            name="passwordConfirmation"
                            class="form-control"
                            id="inputConfirmation"
                            placeholder="Confirmation mot de passe"
                            aria-label="Confirmation mot de passe"
                            minlength="7"
                            maxlength="30"
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
<%@include file="/WEB-INF/footer.jspf" %>
<script>
    function testPassword() {
        const pwd = document.getElementById("inputPassword");
        const confirmPwd = document.getElementById("inputConfirmation");

        if (pwd.value !== confirmPwd.value) {
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
        if (setErrorInput(form.pseudo, form.pseudo.value.length > 30)) isOk = false;
        if (setErrorInput(form.nom, form.nom.value.length > 30)) isOk = false;
        if (setErrorInput(form.prenom, form.prenom.value.length > 30)) isOk = false;
        if (setErrorInput(form.email, form.email.value.length > 50)) isOk = false;
        if (setErrorInput(form.telephone, form.telephone.value.length > 15)) isOk = false;
        if (setErrorInput(form.cp, form.cp.value.length > 5)) isOk = false;
        if (setErrorInput(form.rue, form.rue.value.length > 50)) isOk = false;
        if (setErrorInput(form.ville, form.ville.value.length > 30)) isOk = false;
        if (!testPassword()) isOk = false;

        return isOk;
    }

    function setErrorInput(input, error) {
        if (error) {
            input.style.borderColor = "red";
        } else {
            input.style.borderColor = "";
        }
        return error;
    }
</script>
<%@ include file="bootstrapScripts.html" %>
</body>
</html>
