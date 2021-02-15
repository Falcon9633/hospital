<%@ include file="/WEB-INF/jspf/directive/page.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/i18n.jsp" %>
<fmt:message key="account_registration_patient.title" var="title" bundle="${lang}"/>
<%@ include file="/WEB-INF/jspf/head.jsp" %>
<%@ include file="/WEB-INF/jspf/header.jsp" %>

<div class="container">
    <form action="controller" method="post">
        <input type="hidden" name="role_id" value="3"/>
        <%@ include file="/WEB-INF/jspf/accountRegistrationForm.jsp" %>
        <div class="form-group">
            <label for="birthday">
                <fmt:message key="account_registration_patient.birthday.placeholder" var="birthdayPh" bundle="${lang}"/>
                ${birthdayPh}:
            </label>
            <input type="date" class="form-control" name="birthday" id="birthday" min="1910-01-01" required />
        </div>
        <button type="submit" class="btn btn-dark">
            <fmt:message key="account_registration.button.register" bundle="${lang}"/>
        </button>
        <%@ include file="/WEB-INF/jspf/errorMessage.jsp" %>
    </form>
</div>

<c:set var="js" value="true"/>
<%@ include file="/WEB-INF/jspf/footer.jsp" %>

</body>
</html>