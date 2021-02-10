<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin account registration</title>
</head>
<body>
<form action="controller" method="post">
    <input type="hidden" name="command" value="registerAccount"/><br>
    <input type="hidden" name="role_id" value="0"/><br>
    <input type="text" name="login" placeholder="login"/><br>
    <input type="text" name="name_EN" placeholder="name in English"/><br>
    <input type="text" name="surname_EN" placeholder="surname in English"/><br>
    <input type="text" name="name_UA" placeholder="name in Ukrainian"/><br>
    <input type="text" name="surname_UA" placeholder="surname in Ukrainian"/><br>
    <input type="text" name="email" placeholder="email"/><br>
    <input type="submit" value="register"/><br>
    <c:if test="${errorMessage != null}">
        <h2>${errorMessage}</h2><br>
    </c:if>
</form>
</body>
</html>