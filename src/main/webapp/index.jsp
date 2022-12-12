<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/bootstrapHeader.html" %>
    <title>Accueil</title>
</head>
<body>
<%@ include file="/WEB-INF/header.jspf" %>

<div class="container ">
    <h1 class="text-center">Liste des enchères</h1>

    <!-- Filtres -->
    <form method="post" action="<%=request.getContextPath()%>/encheres/accueil">
        <div class="row flex-row text-left">
            <p class="text-left">Filtres : </p>
            <div class="col-md-6">
                <div class="form-group row mb-3">
                    <div class="col-md-12">
                        <input
                                type="text"
                                name="nom"
                                class="form-control"
                                id="nom"
                                placeholder="Le nom de l'article contient"
                                aria-label="nom"
                                value="${nom}"
                        />
                    </div>
                </div>
                <div class="form-group row mb-3">
                    <label for="categorie" class="col-sm-2 col-form-label">Catégories : </label>
                    <div class="col-md-10 ">
                        <select name="categorie" id="categorie" class="px-5">
                            <option value="-1">Toutes</option>
                            <c:forEach var="categorie" items="${categories}">
                                <option value="${categorie.idCategorie}">categorie.libelle</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <c:if test="${utilisateurConnecte != null}">
                    <div class="row">
                        <div class="col-lg-6 py-1">
                            <div class="form-group row">
                                <div class="col-1 my-auto">
                                    <input type="radio" id="rAV" name="achat-vente" onclick="disableCheckBox()" value="0">
                                </div>
                                <label for="rAV" class="py-1 col col-form-label">Achats</label>
                            </div>
                            <div class="form-group row mx-3">
                                <div class="col-1 my-auto">
                                    <input type="checkbox" name="aOuvertes" id="ouvertes" value="0">
                                </div>
                                <label for="ouvertes" class="py-1 col col-form-label">enchères ouvertes</label>
                            </div>
                            <div class="form-group row mx-3">
                                <div class="col-1 my-auto">
                                    <input type="checkbox" name="aEnCours" id="enCours" value="1">
                                </div>
                                <label for="enCours" class="py-1 col col-form-label">mes enchères en cours</label>
                            </div>
                            <div class="form-group row mb-3 mx-3">
                                <div class="col-1 my-auto">
                                    <input type="checkbox" name="aRemportees" id="remportees" value="2">
                                </div>
                                <label for="remportees" class="py-1 col col-form-label">mes enchères remportées</label>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="form-group row mb-1">
                                <div class="col-1 my-auto">
                                    <input type="radio" id="rA" name="achat-vente" onclick="disableCheckBox()" value="1">
                                </div>
                                <label for="rA" class="py-1 col col-form-label">Mes ventes</label>
                            </div>
                            <div class="form-group row mx-3">
                                <div class="col-1 my-auto">
                                    <input type="checkbox" name="vEnCours" id="vEnCours" value="0">
                                </div>
                                <label for="vEnCours" class="py-1 col col-form-label">mes ventes en cours</label>
                            </div>
                            <div class="form-group row mx-3">
                                <div class="col-1 my-auto">
                                    <input type="checkbox" name="vNonDeb" id="vNonDeb" value="1">
                                </div>
                                <label for="vNonDeb" class="py-1 col col-form-label">ventes non débutées</label>
                            </div>
                            <div class="form-group row mb-3 mx-3">
                                <div class="col-1 my-auto">
                                    <input type="checkbox" name="vTerminees" id="vTerminees" value="2">
                                </div>
                                <label for="vTerminees" class="py-1 col col-form-label">ventes terminées</label>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
            <div class="col-md-4 m-auto text-center">
                <a type="submit" class="btn btn-primary py-5 px-5" href="#">Rechercher</a>
            </div>
        </div>
    </form>

    <!-- affichage enchères -->


</div>

<%@include file="/WEB-INF/footer.jspf" %>

<%@ include file="/WEB-INF/bootstrapScripts.html" %>

<script>
    function toggleCheckBox(checkbox, enable) {
        checkbox.disabled = enable;
        if(enable) {
            checkbox.checked = false;
        }
    }

    function disableCheckBox() {
        let enable = document.getElementById("rAV").checked;

        toggleCheckBox(document.getElementById("ouvertes"), !enable);
        toggleCheckBox(document.getElementById("enCours"), !enable);
        toggleCheckBox(document.getElementById("remportees"), !enable);

        toggleCheckBox(document.getElementById("vEnCours"), enable);
        toggleCheckBox(document.getElementById("vNonDeb"), enable);
        toggleCheckBox(document.getElementById("vTerminees"), enable);
    }

    //Désactivation à la fin du chargement de la page
    disableCheckBox();
</script>
</body>
</html>