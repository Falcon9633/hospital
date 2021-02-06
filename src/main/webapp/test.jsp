<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <title>test</title>
</head>
<body>
<h1>test page</h1>
<c:if test="${requestScope.get('error') == true}">
    <c:out value="${requestScope.get('errorMessage')}"/>
</c:if>
</body>
</html>
