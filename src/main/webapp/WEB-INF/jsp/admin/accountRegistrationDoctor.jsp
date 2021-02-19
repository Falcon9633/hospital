<%@ include file="/WEB-INF/jspf/directive/page.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/i18n.jsp" %>
<fmt:message key="account_registration_doctor.title" var="title" bundle="${lang}"/>
<%@ include file="/WEB-INF/jspf/head.jsp" %>
<%@ include file="/WEB-INF/jspf/header.jsp" %>

<div class="container">
    <h2>${title}</h2>
    <form action="controller" method="post">
        <input type="hidden" name="role_id" value="1"/>
        <%@ include file="/WEB-INF/jspf/accountRegistrationForm.jsp" %>
        <div class="form-group">
            <label for="specialization_id">
                <fmt:message key="account_registration_doctor.specialization.placeholder" var="specializationPh" bundle="${lang}"/>
                ${specializationPh}:
            </label>
            <c:set var="selectId" value="specialization_id"/>
            <%@ include file="/WEB-INF/jspf/allSpecializationSelect.jsp" %>
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