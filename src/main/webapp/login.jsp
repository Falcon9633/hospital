<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Login page</title>
</head>
<body>
<form action="controller" method="post">
    <input type="hidden" name="command" value="login"/>
    <input type="text" name="login" placeholder="login"/>
    <input type="password" name="password" placeholder="password"/>
    <input type="submit" value="log in"/>
    <c:if test="${errorMessage != null}">
        <h2>${errorMessage}</h2><br>
    </c:if>
</form>
</body>
</html>
