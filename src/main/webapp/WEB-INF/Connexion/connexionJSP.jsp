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
    <title>Connexion JSP</title>
</head>
<body>
<form method="post" action="<%=request.getContextPath()%>/ServletConnexion">
      <label for="email">email : </label> <input id="email" type="email" name="email"><br>
<label for="mdp">mot de passe : </label>  <input id="mdp" type="password" name="password"><br>
<input type="submit" name="valider" value="valider">
</form>

</body>
</html>
