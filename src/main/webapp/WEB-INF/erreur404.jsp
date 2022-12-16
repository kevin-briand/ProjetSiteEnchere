<%@ page import="javax.swing.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <%@ include file="bootstrapHeader.html" %>
    <title>ERROR</title>
</head>
<body class="d-flex flex-column min-vh-100">
<%@ include file="header.jspf" %>
<div class="container text-center">
    <img src="<%=request.getContextPath()%>/img/404.jpg" alt="Erreur 404" style="width: 50%"/>
    <p>${requestScope["erreur"]}</p>
</div>
<%@ include file="footer.jspf" %>
<%@ include file="bootstrapScripts.html" %>
</body>
</html>
