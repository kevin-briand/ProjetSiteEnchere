<%@ page import="javax.swing.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <title>Voir profil</title>
</head>
<body>
<%@ include file="/WEB-INF/header.jsp" %>
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
	                <p>${utilisateur.pseudo}</p>
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
<%@include file="/WEB-INF/footer.html" %>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
		integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
		crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
		integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
		crossorigin="anonymous"></script>
</body>
</html>