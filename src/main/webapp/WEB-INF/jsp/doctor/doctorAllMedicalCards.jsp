<%@ include file="/WEB-INF/jspf/directive/page.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/i18n.jsp" %>
<fmt:message key="doctor_all_medical_cards.title" var="title" bundle="${lang}"/>
<%@ include file="/WEB-INF/jspf/head.jsp" %>
<%@ include file="/WEB-INF/jspf/header.jsp" %>

<div class="container">
    <%@ include file="/WEB-INF/jspf/errorMessage.jsp" %>

    <table class="table table-dark table-bordered table-hover">
        <thead>
        <tr>
            <th><fmt:message key="doctor_medical_cards.th.patient_details" bundle="${lang}"/></th>
            <th><fmt:message key="doctor_medical_cards.th.patient_birthday" bundle="${lang}"/></th>
            <th><fmt:message key="th.status" bundle="${lang}"/></th>
            <th><fmt:message key="th.create_time" bundle="${lang}"/></th>
            <th><fmt:message key="th.medical_card" bundle="${lang}"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="medCard" items="${medCards}">
            <tr>
                <td>
                    <c:if test="${locale.name == 'en_US'}">
                        ${medCard.patientSurnameEN} ${medCard.patientNameEN}
                    </c:if>
                    <c:if test="${locale.name == 'uk_UA'}">
                        ${medCard.patientSurnameUA} ${medCard.patientNameUA}
                    </c:if>
                </td>
                <td><custom:formatDate value="${medCard.patientBirthday}" pattern="dd-MM-yyy"/></td>
                <c:choose>
                    <c:when test="${medCard.discharged}">
                        <td><fmt:message key="td.discharged" bundle="${lang}"/></td>
                    </c:when>
                    <c:otherwise>
                        <td><fmt:message key="td.staying" bundle="${lang}"/></td>
                    </c:otherwise>
                </c:choose>
                <td><custom:formatDate value="${medCard.createTime}" pattern="dd-MM-yyyy HH:mm:ss"/></td>
                <td>
                    <a href="controller?command=doctorDiagnosesMedicalCard&medical_card_id=${medCard.id}&patient_id=${medCard.patientId}"
                       class="btn btn-light" role="button" aria-pressed="true">
                        <fmt:message key="button.choose" bundle="${lang}"/>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<c:set var="js" value="true"/>
<%@ include file="/WEB-INF/jspf/footer.jsp" %>
</body>
</html>