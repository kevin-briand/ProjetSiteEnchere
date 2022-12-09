<%@ page import="javax.swing.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <title>Connexion</title>
</head>
<body>
<%@ include file="/WEB-INF/header.jsp" %>
<div class="container text-center">
    <h1 style="margin-bottom: 100px; margin-top: 50px;">Connexion</h1>

    <p style="margin-bottom: 50px; color:red;">${requestScope["erreurConnexion"]}</p>

    <form method="post" action="<%=request.getContextPath()%>/Connexion">
        <div class="row justify-content-md-around mb-3">
            <div class="col-md-4">
                <div class="mb-3">
                    <input
                            required="required"
                            type="text"
                            name="email"
                            class="form-control"
                            id="email"
                            placeholder="Pseudo ou e-mail"
                            aria-label="Pseudo"/>
                            <!--value="${requestScope["email"]}"-->
                            <!--value="${requestScope["pseudo"]}"-->
                </div>
            </div>
            <div class="col-md-4">
                <div class="mb-3">
                    <input
                            required="required"
                            type="password"
                            name="password"
                            class="form-control"
                            id="mdp"
                            placeholder="Mot de passe"
                            aria-label="mdp"
                            oninput="testPassword()"
                    />
                </div>
            </div>
        </div>
        <div class="buttons" style="margin-top: 60px">
            <button type="submit" name="valider" value="valider" class="btn btn-primary btn-lg">Connexion</button>
        </div>
        <div class="form-check-inline" style="margin-top: 60px">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" name="checkbox">
            <label class="form-check-label" for="flexCheckDefault">
                Se souvenir de moi
            </label>
            <a href="#" style="margin-left: 30px">Mot de passe oublié</a>
        </div>
    </form>

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
