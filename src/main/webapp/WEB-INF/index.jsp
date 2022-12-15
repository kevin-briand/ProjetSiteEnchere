<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="bootstrapHeader.html" %>
    <title>Accueil</title>
</head>
<body class="d-flex flex-column min-vh-100">
<%@ include file="header.jspf" %>

<div class="container ">
    <h1 class="text-center">Liste des enchères</h1>

    <!-- Filtres -->
    <form method="post" action="<%=request.getContextPath()%>/encheres/">
        <div class="row flex-row text-left mb-5">
            <p class="text-left">Filtres : </p>
            <div class="col-md-6 col-lg-7">
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
                    <label for="categorie" class="col-lg-2 col-md-4 col-form-label">Catégories : </label>
                    <div class="col-md-10 ">
                        <select name="categorie" id="categorie" class="px-5">
                            <option value="-1">Toutes</option>
                            <c:forEach var="categorie" items="${categories}">
                                <option value="${categorie.idCategorie}
                                        <c:if test="${categorie.libelle == catStr}">selected</c:if>">
                                        categorie.libelle</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <c:if test="${utilisateurConnecte != null}">
                    <div class="row">
                        <div class="col-lg-6 py-1">
                            <div class="form-group row">
                                <div class="col-1 my-auto">
                                    <input type="radio" id="rAV" name="achat-vente" onclick="disableCheckBox()" value="0" <c:if test="${radio == 0}">checked</c:if>>
                                </div>
                                <label for="rAV" class="py-1 col col-form-label">Achats</label>
                            </div>
                            <div class="form-group row mx-3">
                                <div class="col-1 my-auto">
                                    <input type="checkbox" name="aOuvertes" id="ouvertes" <c:if test="${aOuvertes == true}">checked</c:if>>
                                </div>
                                <label for="ouvertes" class="py-1 col col-form-label">enchères ouvertes</label>
                            </div>
                            <div class="form-group row mx-3">
                                <div class="col-1 my-auto">
                                    <input type="checkbox" name="aEnCours" id="enCours" <c:if test="${aEnCours == true}">checked</c:if>>
                                </div>
                                <label for="enCours" class="py-1 col col-form-label">mes enchères en cours</label>
                            </div>
                            <div class="form-group row mb-3 mx-3">
                                <div class="col-1 my-auto">
                                    <input type="checkbox" name="aRemportees" id="remportees" <c:if test="${aRemportees == true}">checked</c:if>>
                                </div>
                                <label for="remportees" class="py-1 col col-form-label">mes enchères remportées</label>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="form-group row mb-1">
                                <div class="col-1 my-auto">
                                    <input type="radio" id="rA" name="achat-vente" onclick="disableCheckBox()" value="1" <c:if test="${radio == 1}">checked</c:if>>
                                </div>
                                <label for="rA" class="py-1 col col-form-label">Mes ventes</label>
                            </div>
                            <div class="form-group row mx-3">
                                <div class="col-1 my-auto">
                                    <input type="checkbox" name="vEnCours" id="vEnCours" <c:if test="${vEnCours == true}">checked</c:if>>
                                </div>
                                <label for="vEnCours" class="py-1 col col-form-label">mes ventes en cours</label>
                            </div>
                            <div class="form-group row mx-3">
                                <div class="col-1 my-auto">
                                    <input type="checkbox" name="vNonDeb" id="vNonDeb" <c:if test="${vNonDeb == true}">checked</c:if>>
                                </div>
                                <label for="vNonDeb" class="py-1 col col-form-label">ventes non débutées</label>
                            </div>
                            <div class="form-group row mb-3 mx-3">
                                <div class="col-1 my-auto">
                                    <input type="checkbox" name="vTerminees" id="vTerminees" <c:if test="${vTerminees == true}">checked</c:if>>
                                </div>
                                <label for="vTerminees" class="py-1 col col-form-label">ventes terminées</label>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
            <div class="col-md-4 m-auto text-center">
                <button type="submit" class="btn btn-primary py-5 px-5">Rechercher</button>
            </div>
        </div>
    </form>

    <!-- CARDS -->
    <div class="row mb-2">
        <c:choose>
            <c:when test="${articles != null}">
            <c:forEach var="article" items="${articles}">
                <!-- CARD -->
                <div class="col-md-5">
                    <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                        <div class="col-auto d-none d-lg-block">
                            <img src="<%=request.getContextPath()%>/img/no-img.svg" alt="pas d'image disponible"/>
                        </div>
                        <div class="col p-4 d-flex flex-column">
                            <h3><strong class="d-inline-block mb-2 text-success">
                                <a href="<%=request.getContextPath()%>/enchere/info?id=${article.idArticle}">${article.nomArticle}</a>
                            </strong></h3>
                            <p>Prix : ${article.prixVente} points <br>
                                Fin de l'enchère : <span class="date">${article.dateFinEnchere}</span><br>
                                Vendeur : <a href="<%=request.getContextPath()%>/user/AfficherProfil?user=${article.utilisateur.idUtilisateur}">
                                        ${article.utilisateur.pseudo}
                                </a>
                            </p>
                        </div>
                    </div>
                </div>
            </c:forEach>
            </c:when>
            <c:otherwise>
                <p class="text-center">Aucune enchères</p>
            </c:otherwise>
        </c:choose>
    </div>


</div>

<%@include file="footer.jspf" %>

<%@ include file="bootstrapScripts.html" %>

<script>
    function toggleCheckBox(checkbox, enable) {
        checkbox.disabled = enable;
        if(enable) {
            checkbox.checked = false;
        }
    }

    function disableCheckBox() {
        let enable = document.getElementById("rAV").checked;
        if(!document.getElementById("rA").checked && !enable) {
            document.getElementById("rAV").checked = true;
        }

        toggleCheckBox(document.getElementById("ouvertes"), !enable);
        toggleCheckBox(document.getElementById("enCours"), !enable);
        toggleCheckBox(document.getElementById("remportees"), !enable);

        toggleCheckBox(document.getElementById("vEnCours"), enable);
        toggleCheckBox(document.getElementById("vNonDeb"), enable);
        toggleCheckBox(document.getElementById("vTerminees"), enable);
    }



    //execution des scripts à la fin du chargement de la page
    document.addEventListener('DOMContentLoaded', function() {
        if(document.getElementById("rAV") != null)
            disableCheckBox();
        formatDate();
    }, false);

</script>
</body>
</html>