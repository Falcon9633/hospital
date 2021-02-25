<%@ include file="/WEB-INF/jspf/directive/page.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/i18n.jsp" %>
<fmt:message key="settings.title" var="title" bundle="${lang}"/>
<%@ include file="/WEB-INF/jspf/head.jsp" %>
<%@ include file="/WEB-INF/jspf/header.jsp" %>

<div class="container">
    <%@ include file="/WEB-INF/jspf/errorMessage.jsp" %>

    <form class="form-inline" action="controller" method="post">
        <input type="hidden" name="command" value="changeEmail"/>
        <label for="email" class="mr-sm-2">
            <fmt:message key="settings.label.new_email" bundle="${lang}"/>
        </label>
        <input type="email" class="mr-sm-2 form-control mb-2 mr-sm-2" name="email" id="email"
               placeholder='<fmt:message key="settings.placeholder.new_email" bundle="${lang}"/>'
               required minlength="6" maxlength="45">
        <button type="submit" class="btn btn-dark mb-2">
            <fmt:message key="button.change" bundle="${lang}"/>
        </button>
    </form>

    <form class="form-inline" action="controller" method="post">
        <input type="hidden" name="command" value="changePassword">
        <label for="new_password" class="mr-sm-2">
            <fmt:message key="settings.label.new_password" bundle="${lang}"/>
        </label>
        <input type="password" class="form-control mb-2 mr-sm-2" name="new_password" id="new_password"
               placeholder='<fmt:message key="settings.placeholder.new_password" bundle="${lang}"/>'
               minlength="6" maxlength="16" pattern="^(?:(?=.*?\p{N})(?=.*?\p{Lu})(?=.*?\p{Ll}))[^\p{C}]+$"
               required title='<fmt:message key="validation.error.account_password_pattern" bundle="${lang}"/>'>
        <label for="confirmed_password" class="mr-sm-2">
            <fmt:message key="settings.label.confirm_password" bundle="${lang}"/>
        </label>
        <input type="password" class="form-control mb-2 mr-sm-2" name="confirmed_password" id="confirmed_password"
               placeholder='<fmt:message key="settings.placeholder.confirm_password" bundle="${lang}"/>'
               minlength="6" maxlength="16" pattern="^(?:(?=.*?\p{N})(?=.*?\p{Lu})(?=.*?\p{Ll}))[^\p{C}]+$"
               required title='<fmt:message key="validation.error.account_password_pattern" bundle="${lang}"/>'>
        <label for="old_password" class="mr-sm-2">
            <fmt:message key="settings.label.old_password" bundle="${lang}"/>
        </label>
        <input type="password" class="form-control mb-2 mr-sm-2" name="old_password" id="old_password"
               placeholder='<fmt:message key="settings.placeholder.old_password" bundle="${lang}"/>'
               minlength="6" maxlength="16" pattern="^(?:(?=.*?\p{N})(?=.*?\p{Lu})(?=.*?\p{Ll}))[^\p{C}]+$"
               required title='<fmt:message key="validation.error.account_password_pattern" bundle="${lang}"/>'>
        <button type="submit" class="btn btn-dark mb-2">
            <fmt:message key="button.change" bundle="${lang}"/>
        </button>
    </form>
</div>

<c:set var="js" value="true"/>
<%@ include file="/WEB-INF/jspf/footer.jsp" %>
</body>
</html>