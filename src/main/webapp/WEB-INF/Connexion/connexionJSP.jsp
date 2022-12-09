<%--
  Created by IntelliJ IDEA.
  User: romaingaillard
  Date: 06/12/2022
  Time: 09:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@ include file="../bootstrapHeader.html" %>
    <title>Connexion JSP</title>
</head>
<body>
<%@ include file="../header.jsp" %>
<p>${requestScope["erreurConnexion"]}</p>
<form method="post" action="<%=request.getContextPath()%>/Connexion">
      <label for="email">email : </label> <input required id="email" type="text" name="email" value="${requestScope["email"]}"><br>
<label for="mdp">mot de passe : </label>  <input required id="mdp" type="password" name="password"><br>
<input type="submit" name="valider" value="valider">
</form>
<%@ include file="../footer.html" %>
<%@ include file="../bootstrapScripts.html" %>
</body>
</html>
