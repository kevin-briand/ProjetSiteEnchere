<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <%@ include file="bootstrapHeader.html" %>
    <title>Détail vente</title>
</head>
<body class="d-flex flex-column min-vh-100">
<%@ include file="header.jspf" %>

<div class="container text-center" style="margin-bottom: 25px;">
    <div class="row">

        <div class="col">

            <h1 style="margin-bottom: 50px; margin-top: 50px;">Détail de la vente : ${article.nomArticle}</h1>

            <div class="row justify-content-md-around mb-3">
                <div class="col md-4">

                    <div class="row md-around mb-1">
                        <div class="col mb-1 text-end">
                            <p>Description :</p>
                        </div>
                        <div class="col mb-1 text-start">
                            <p>${article.description}</p>
                        </div>
                    </div>

                    <div class="row md-around mb-1">
                        <div class="col mb-1 text-end">
                            <p>Catégorie :</p>
                        </div>
                        <div class="col mb-1 text-start">
                            <p>${article.categorie.libelle}</p>
                        </div>
                    </div>

                    <c:if test="${!empty article.enchere}">
                        <div class="row md-around mb-1">
                            <div class="col mb-1 text-end">
                                <p>Meilleure offre :</p>
                            </div>
                            <div class="col mb-1 text-start">
                                <p>${article.enchere.montantEnchere} pts par ${article.enchere.utilisateur.pseudo}</p>
                            </div>
                        </div>
                    </c:if>

                    <div class="row md-around mb-1">
                        <div class="col mb-1 text-end">
                            <p>Mise à prix :</p>
                        </div>
                        <div class="col mb-1 text-start">
                            <p>${article.prixInitial} points</p>
                        </div>
                    </div>

                    <div class="row md-around mb-1">
                        <div class="col mb-1 text-end">
                            <p>Fin de l'enchère :</p>
                        </div>
                        <div class="col mb-1 text-start">
                            <p class="date">${article.dateFinEnchere}</p>
                        </div>
                    </div>

                    <div class="row md-around mb-1">
                        <div class="col mb-1 text-end">
                            <p>Retrait :</p>
                        </div>
                        <div class="col mb-1 text-start">
                            <p>${article.lieuRetrait.rue}</p>
                            <p>${article.lieuRetrait.codePostal} ${article.lieuRetrait.ville}</p>
                        </div>
                    </div>

                    <div class="row md-around mb-1">
                        <div class="col mb-1 text-end">
                            <p>Vendeur :</p>
                        </div>
                        <div class="col mb-1 text-start">
                            <p>${article.utilisateur.pseudo}</p>
                        </div>
                    </div>

                    <c:if test="${sessionScope.utilisateurConnecte != null}">
                        <form method="post" action="<%=request.getContextPath()%>/enchere/info">
                            <input type="hidden" value="${article.idArticle}" name="idArticle" id="idArticle">
                            <div class="row md-around mb-1">
                                <div class="col mb-1 text-end">
                                    <label class="align-middle" for="choixMontantEnchere">Ma proposition :</label>
                                </div>

                                <div class="col mb-1 text-start">
                                    <div class="row mb-1">
                                        <!-- test si l'utilisateur a assez de points pour enchérir -->
                                        <c:choose>
                                            <c:when test="${sessionScope.utilisateurConnecte.pseudo == article.utilisateur.pseudo}">
                                                Vous ne pouvez pas enchérir sur votre propre vente.
                                            </c:when>
                                            <c:when test="${article.etatVenteArticle == 'NON_DEBUTEE'}">
                                                Vous ne pouvez pas enchérir : la vente n'a pas commencée. Elle débutera le 
                                                <p class="date">${article.dateDebutEnchere}</p>.
                                            </c:when>
                                             <c:when test="${article.etatVenteArticle == 'TERMINEE'}">
                                                Vous ne pouvez plus enchérir : la vente est terminée depuis le <p class="date">${article.dateFinEnchere}.</p>
                                            </c:when>
                                            <c:when test="${sessionScope.utilisateurConnecte.credit > article.enchere.montantEnchere ||
										sessionScope.utilisateurConnecte.credit > article.prixInitial}">
                                                <input style="width: 150px;"
                                                       required="required"
                                                       type="number"
                                                       name="choixMontantEnchere"
                                                       class="form-control"
                                                       id="choixMontantEnchere"
                                                       placeholder="Montant"
                                                       aria-label="Montant de l'enchere"
                                                <c:choose>
                                                <c:when test="${article.enchere != null }">
                                                	   value="${article.enchere.montantEnchere+1}"
                                                       min="${article.enchere.montantEnchere+1}"
                                                </c:when>
                                                <c:otherwise>
                                             		   value="${article.prixInitial+1}"
                                                       min="${article.prixInitial+1}"
                                                </c:otherwise>
                                                </c:choose>
                                                       max="${sessionScope.utilisateurConnecte.credit}"
                                                >
                                                <div class="col mb-1">
                                                    <button type="submit" class="btn btn-primary btn-lg">Enchérir
                                                    </button>
                                                </div>
                                            </c:when>
                                            <c:otherwise>Vous n'avez pas assez de points.</c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jspf" %>
<%@ include file="bootstrapScripts.html" %>
</body>
</html>