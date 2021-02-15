<%@ include file="/WEB-INF/jspf/directive/page.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/i18n.jsp" %>
<fmt:message key="login.title" var="title" bundle="${lang}"/>
<%@ include file="/WEB-INF/jspf/head.jsp" %>

<body class="main-bg">
<div class="login-container text-c animated flipInX">
    <div>
        <h1 class="logo-badge text-whitesmoke"><span class="fa fa-user-circle"></span></h1>
    </div>
    <h3 class="text-whitesmoke"><fmt:message key="login.sub_title" bundle="${lang}"/></h3>
    <div class="container-content">
        <form class="margin-t" action="controller" method="post">
            <input type="hidden" name="command" value="login"/>
            <div class="form-group">
                <fmt:message key="login.login.placeholder" var="loginPh"/>
                <input type="text" name="login" class="form-control" required="required"
                       placeholder='<fmt:message key="login.login.placeholder" bundle="${lang}"/>'>
            </div>
            <div class="form-group">
                <input type="password" name="password" class="form-control" required="required"
                       placeholder='<fmt:message key="login.password.placeholder" bundle="${lang}"/>'>
            </div>
            <button type="submit" class="form-button button-l margin-b"><fmt:message key="login.button.submit" bundle="${lang}"/></button>
            <%@ include file="/WEB-INF/jspf/errorMessage.jsp" %>
        </form>
        <a class="flag-icon flag-icon-ua" href="controller?command=unknownCommand"></a><%--TODO--%>
        <a class="flag-icon flag-icon-us" href="controller?command=unknownCommand"></a><%--TODO--%>
        <p class="margin-t text-whitesmoke"><small><fmt:message key="login.copyrights" bundle="${lang}"/></small></p>
    </div>
</div>

<c:set var="js" value="false"/>
<%@ include file="/WEB-INF/jspf/footer.jsp" %>

</body>
</html>