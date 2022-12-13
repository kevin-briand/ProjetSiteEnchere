<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
	<%@ include file="bootstrapHeader.html" %>
    <title>Voir profil</title>
</head>
<body class="d-flex flex-column min-vh-100">
	<%@ include file="header.jspf" %>
	<div class="container">
    <h1 class="text-center"style="margin-bottom: 75px; margin-top: 50px;">Profil de ${utilisateur.pseudo}</h1>
    <div class="row justify-content-md-around mb-3">
        <div class="col md-4">
        	<div class="row md-around mb-1">
	            <div class="col mb-1 text-end">
	                Pseudo :
	            </div>
	           <div class="col mb-1">
	                <p>${utilisateur.pseudo}</p>
	            </div>
            </div>
            <div class="row md-around mb-1">
	            <div class="col mb-1 text-end">
	                Nom :
	            </div>
	           <div class="col mb-1">
	                <p>${utilisateur.nom}</p>
	            </div>
            </div>
            <div class="row md-around mb-1">
	            <div class="col mb-1 text-end">
	                Prénom :
	            </div>
	           <div class="col mb-1">
	                <p>${utilisateur.prenom}</p>
	            </div>
            </div>
            <div class="row md-around mb-1">
	            <div class="col mb-1 text-end">
	                Email :
	            </div>
	           <div class="col mb-1">
	                <p>${utilisateur.email}</p>
	            </div>
            </div>
            <div class="row md-around mb-1">
	            <div class="col mb-1 text-end">
	                Telephone :
	            </div>
	           <div class="col mb-1">
	                <p>${utilisateur.telephone}</p>
	            </div>
            </div>
          <div class="row md-around mb-1">
	            <div class="col mb-1 text-end">
	                Rue :
	            </div>
	           <div class="col mb-1">
	                <p>${utilisateur.rue}</p>
	            </div>
            </div>
            <div class="row md-around mb-1">
	            <div class="col mb-1 text-end">
	                Code postal :
	            </div>
	           <div class="col mb-1">
	                <p>${utilisateur.codePostal}</p>
	            </div>
            </div>
            <div class="row md-around mb-1">
	            <div class="col mb-1 text-end">
	                Ville :
	            </div>
	           <div class="col mb-1">
	                <p>${utilisateur.ville}</p>
	            </div>
            </div>
        </div>
    <c:if test="${utilisateur.pseudo == sessionScope.utilisateurConnecte.pseudo}" >
	    <div class="text-center" style="margin-bottom: 75px; margin-top: 50px">
			<a type="button" class="btn btn-primary btn-lg"
			 href="<%=request.getContextPath()%>/user/modifierProfil">Modifier mon profil</a>
		</div>
	</c:if>
    </div>
	</div>
	<%@ include file="footer.jspf" %>
	<%@ include file="bootstrapScripts.html" %>
</body>
</html>