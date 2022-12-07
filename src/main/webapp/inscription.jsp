<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <title>Inscription</title>
</head>
<body>
<div class="container text-center">
    <h1 style="margin-bottom: 100px; margin-top: 50px;">Mon profil</h1>
    <form method="post" action="<%=request.getContextPath()%>/Inscription">
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
                    />
                </div>
            </div>
        </div>
        <div class="buttons">
            <button type="submit" class="btn btn-primary btn-lg">Créer</button>
            <a type="button" class="btn btn-secondary btn-lg" href="#">Annuler</a>
        </div>
    </form>
</div>
</body>
</html>
