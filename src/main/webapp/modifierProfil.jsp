<%@ page import="fr.eni.team42.enchere.bo.Utilisateur" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <title>Mon profil</title>
</head>
<body>
<div class="container text-center">
    <h1 style="margin-bottom: 100px; margin-top: 50px;">Mon profil</h1>
    <% Utilisateur utilisateur = (Utilisateur) request.getSession(false).getAttribute("utilisateurConnecte"); %>
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
                            value="<%=utilisateur.getPseudo()%>"
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
                            value="<%=utilisateur.getPrenom()%>"
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
                            value="<%=utilisateur.getTelephone()%>"
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
                            value="<%=utilisateur.getCodePostal()%>"
                    />
                </div>
                <div class="mb-3">
                    <input
                            type="password"
                            name="password"
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
                            value="<%=utilisateur.getNom()%>"
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
                            value="<%=utilisateur.getEmail()%>"
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
                            value="<%=utilisateur.getRue()%>"
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
                            value="<%=utilisateur.getVille()%>"
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
                    <%="Crédit"%>
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
</body>
</html>

