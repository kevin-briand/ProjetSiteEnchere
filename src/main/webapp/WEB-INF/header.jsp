<%@ page import="javax.swing.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">ENI ENCHERES</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDarkDropdown"
                aria-controls="navbarNavDarkDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Menu
                    </a>
                    <ul class="dropdown-menu dropdown-menu-dark">
                        <li><a class="dropdown-item" href="#">Encheres</a></li>
                        <li><a class="dropdown-item" href="/ProjetSiteEnchere/AfficherProfil">Profil</a></li>
                        <li><a class="dropdown-item" href="#">Parametres</a></li>
                    </ul>
                </li>
            </ul>
        </div>

        <c:if test="${utilisateurConnecte == null}">
            <div class="text-end">
                <a href="/ProjetSiteEnchere/Connexion" type="button" class="btn btn-light text-dark me-2">Se
                    connecter</a>
                <a href="/ProjetSiteEnchere/Inscription" type="button" class="btn btn-primary">S'inscrire</a>
            </div>
        </c:if>
        <c:if test="${utilisateurConnecte != null}">
            <div class="text-end">
                ${utilisateurConnecte.pseudo}
                <a href="index.jsp" type="button" class="btn btn-primary">Se déconnecter</a>
            </div>
        </c:if>
    </div>
</nav>