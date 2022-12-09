<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <%@ include file="bootstrapHeader.html" %>
    <title>Mon profil</title>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="container text-center">
    <h1 style="margin-bottom: 100px; margin-top: 50px;">Mon profil</h1>
    <p>${requestScope["erreur"]}</p>
    <form method="post" action="<%=request.getContextPath()%>/profil/modification">
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
                            value="${sessionScope.utilisateurConnecte.pseudo}"
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
                            value="${sessionScope.utilisateurConnecte.prenom}"
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
                            value="${sessionScope.utilisateurConnecte.telephone}"
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
                            value="${sessionScope.utilisateurConnecte.codePostal}"
                    />
                </div>
                <div class="mb-3">
                    <input
                            type="password"
                            name="oldPassword"
                            class="form-control"
                            id="inputPassword"
                            placeholder="Mot de passe actuel"
                            aria-label="Mot de passe actuel"
                    />
                </div>
                <div class="mb-3">
                    <input
                            type="password"
                            name="password"
                            class="form-control"
                            id="removePassword"
                            placeholder="Nouveau mot de passe"
                            aria-label="Nouveau mot de passe"
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
                            value="${sessionScope.utilisateurConnecte.nom}"
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
                            value="${sessionScope.utilisateurConnecte.email}"
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
                            value="${sessionScope.utilisateurConnecte.rue}"
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
                            value="${sessionScope.utilisateurConnecte.ville}"
                    />
                </div>
                <div class="mb-3">
                    <input
                            type="password"
                            name="passwordConfirmation"
                            class="form-control"
                            id="inputConfirmation"
                            placeholder="Confirmation mot de passe"
                            aria-label="Confirmation mot de passe"
                    />
                </div>
                <div class="mb-3" style="margin-right: 300px">
                    ${sessionScope.utilisateurConnecte.credit}
                </div>
            </div>
        </div>
        <div class="buttons">
            <button type="submit" class="btn btn-primary btn-lg">Enregistrer</button>
            <a type="button" class="btn btn-secondary btn-lg"
               href="<%=request.getContextPath()%>/SuppressionUtilisateur">Supprimer mon compte</a>
        </div>
    </form>
</div>
<%@ include file="footer.html" %>
<%@ include file="bootstrapScripts.html" %>
</body>
</html>

