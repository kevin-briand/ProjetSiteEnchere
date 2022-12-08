<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <title>Inscription</title>
</head>
<body>
<div class="container text-center">
    <h1 style="margin-bottom: 100px; margin-top: 50px;">Mon profil</h1>

	<%
			Object erreurInscription = request.getAttribute("erreurInscription");
			if(erreurInscription!=null){
		%>
				<p style="margin-bottom: 50px; color:red;"><%=erreurInscription %></p>
		<%		
			}
		%>
	
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
                            value="<%=erreurInscription!=null?request.getParameter("pseudo"):""%>"
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
							value="<%=erreurInscription!=null?request.getParameter("prenom"):""%>"
                    />
                </div>
                <div class="mb-3">
                    <input
                            type="text"
                            name="telephone"
                            class="form-control"
                            id="telephone"
                            placeholder="Téléphone"
                            aria-label="Téléphone"
							value="<%=erreurInscription!=null?request.getParameter("telephone"):""%>"
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
                            value="<%=erreurInscription!=null?request.getParameter("cp"):""%>"
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
                            value="<%=erreurInscription!=null?request.getParameter("nom"):""%>"
                    />
                </div>
                <div class="mb-3">
                    <input
                    		required="required"
                            type="text"
                            name="email"
                            class="form-control"
                            id="email"
                            placeholder="Email"
                            aria-label="Email"
                            value="<%=erreurInscription!=null?request.getParameter("email"):""%>"
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
                            value="<%=erreurInscription!=null?request.getParameter("rue"):""%>"
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
                            value="<%=erreurInscription!=null?request.getParameter("ville"):""%>"
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
