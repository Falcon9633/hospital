<%--Sets the locale--%>
<c:choose>
    <c:when test="${!empty locale}">
        <fmt:setLocale value="${locale.name}" scope="session"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="en_US" scope="session"/>
    </c:otherwise>
</c:choose>
<%--Sets the path to bundle--%>
<fmt:setBundle basename="resources" var="lang" scope="session"/>
