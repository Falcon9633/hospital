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
<%--create urls for i18n--%>
<c:url var="localEN" value="controller">
    <c:param name="command" value="changeLocale"/>
    <c:param name="localeId" value="0"/>
    <c:param name="redirect" value="${requestScope['javax.servlet.forward.query_string']}"/>
</c:url>
<c:url var="localUA" value="controller">
    <c:param name="command" value="changeLocale"/>
    <c:param name="localeId" value="1"/>
    <c:param name="redirect" value="${requestScope['javax.servlet.forward.query_string']}"/>
</c:url>