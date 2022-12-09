<%@ page import="javax.swing.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
	<%@ include file="bootstrapHeader.html" %>
    <title>Voir profil</title>
</head>
<body>
	<%@ include file="header.jsp" %>
	<div class="container">
    <h1 class="text-center"style="margin-bottom: 100px; margin-top: 50px;">Profil de ${utilisateur.pseudo}</h1>
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
    </div>
	</div>
	<%@ include file="footer.html" %>
	<%@ include file="bootstrapScripts.html" %>
</body>
</html>