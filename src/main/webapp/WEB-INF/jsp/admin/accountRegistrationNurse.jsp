<%@ include file="/WEB-INF/jspf/directive/page.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/i18n.jsp" %>
<fmt:message key="account_registration_nurse.title" var="title" bundle="${lang}"/>
<%@ include file="/WEB-INF/jspf/head.jsp" %>
<%@ include file="/WEB-INF/jspf/header.jsp" %>

<div class="container">
    <h2>${title}</h2>
    <form action="controller" method="post">
        <input type="hidden" name="role_id" value="2"/>
        <%@ include file="/WEB-INF/jspf/accountRegistrationForm.jsp" %>
        <button type="submit" class="btn btn-dark">
            <fmt:message key="button.register" bundle="${lang}"/>
        </button>
        <%@ include file="/WEB-INF/jspf/errorMessage.jsp" %>
    </form>
</div>


<c:set var="js" value="true"/>
<%@ include file="/WEB-INF/jspf/footer.jsp" %>

</body>
</html>