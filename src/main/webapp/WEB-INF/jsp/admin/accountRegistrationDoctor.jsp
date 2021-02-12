<%@ include file="/WEB-INF/jspf/directive/page.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jsp" %>
<fmt:message key="account_registration_doctor.title" var="title" bundle="${lang}"/>
<%@ include file="/WEB-INF/jspf/head.jsp" %>
<%@ include file="/WEB-INF/jspf/header.jsp" %>

<div class="container my-sm-4">
    <form action="controller" method="post">
        <input type="hidden" name="role_id" value="1"/>
        <%@ include file="/WEB-INF/jspf/accountRegistrationForm.jsp" %>
        <div class="form-group">
            <label for="specialization_id">
                <fmt:message key="account_registration_doctor.specialization.placeholder" var="specializationPh" bundle="${lang}"/>
                ${specializationPh}:
            </label>
            <select class="form-control"  name="specialization_id" id="specialization_id">
                <option value="">${specializationPh}</option>
                <c:if test="${specializations != null}">
                    <c:forEach var="specialization" items="${specializations}">
                        <option value="${specialization.id}">
                            <c:choose>
                                <c:when test="${locale.name == 'EN'}">${specialization.nameEN}</c:when>
                                <c:otherwise>${specialization.nameUA}</c:otherwise>
                            </c:choose>
                        </option>
                    </c:forEach>
                </c:if>
            </select>
        </div>
        <button type="submit" class="btn btn-dark">
            <fmt:message key="account_registration.button.register" bundle="${lang}"/>
        </button>
        <%@ include file="/WEB-INF/jspf/errorMessage.jsp" %>
    </form>
</div>

<c:set var="js" value="true"/>
<%@ include file="/WEB-INF/jspf/footer.jsp" %>