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
	<div class="col-1">
		<img alt="" src="#">
	</div>
	
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
		                <p>${dateFinEnchere}</p>
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
		         
				<form method="post" action="<%=request.getContextPath()%>/encheres/NouvelleEnchere">
		        	<div class="row md-around mb-1">
		            	<div class="col mb-1 text-end">
		            		<label class="align-middle" for="choixMontantEnchere">Mise à prix :</label>
		            	</div>
		           <div class="col mb-1 text-start">
		           		<div class="row mb-1">
	                	<input style="width: 150px;"
	                	required="required"
	                          type="number"
	                          name="choixMontantEnchere"
	                          class="form-control"
	                          id="choixMontantEnchere"
	                          placeholder="Montant"
	                          aria-label="Montant de l'enchere"
	                          min="${article.enchere.montantEnchere}"
	                	>
						<div class="col mb-1">
			                <button type="submit" class="btn btn-primary btn-lg">Enchérir</button>
			           	</div>
		           		</div>
		            </div>
		            </div>
	                </form>
				</div>
			</div>
		</div>
	</div>	
</div>
</div>

<%@ include file="footer.jspf" %>
<%@ include file="bootstrapScripts.html" %>
</body>
</html>