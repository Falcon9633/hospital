<%@ include file="/WEB-INF/jspf/directive/page.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/i18n.jsp" %>
<fmt:message key="assignment.title" var="title" bundle="${lang}"/>
<%@ include file="/WEB-INF/jspf/head.jsp" %>
<%@ include file="/WEB-INF/jspf/header.jsp" %>

<div class="container">
    <%@ include file="/WEB-INF/jspf/errorMessage.jsp" %>

    <table class="table table-dark table-bordered table-hover">
        <thead>
        <tr>
            <th><fmt:message key="assignment.th.assignment" bundle="${lang}"/></th>
            <th><fmt:message key="assignment.th.type" bundle="${lang}"/></th>
            <th><fmt:message key="th.served_by" bundle="${lang}"/></th>
            <th><fmt:message key="th.created_by" bundle="${lang}"/></th>
            <th><fmt:message key="th.created_time" bundle="${lang}"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="medicament" items="${medicaments}">
            <tr>
                <c:if test="${locale.name == 'en_US'}">
                    <td>${medicament.nameEN}</td>
                    <td><fmt:message key="td.medicament" bundle="${lang}"/></td>
                    <td>
                        ${medicament.empSurnameEN} ${medicament.empNameEN}
                        <c:choose>
                            <c:when test="${!empty medicament.doctorSpecializationNameEN}">
                                (${medicament.doctorSpecializationNameEN})
                            </c:when>
                            <c:otherwise>(<fmt:message key="td.nurse" bundle="${lang}"/>)</c:otherwise>
                        </c:choose>
                    </td>
                    <td>${medicament.createdBySurnameEN} ${medicament.createdByNameEN}</td>
                    <input type="hidden" data-type="description" value="${medicament.descriptionEN}">
                </c:if>
                <c:if test="${locale.name == 'uk_UA'}">
                    <td>${medicament.nameUA}</td>
                    <td><fmt:message key="td.medicament" bundle="${lang}"/></td>
                    <td>
                        ${medicament.empSurnameUA} ${medicament.empNameUA}
                        <c:choose>
                            <c:when test="${!empty medicament.doctorSpecializationNameUA}">
                                (${empty medicament.doctorSpecializationNameUA})
                            </c:when>
                            <c:otherwise>(<fmt:message key="td.nurse" bundle="${lang}"/>)</c:otherwise>
                        </c:choose>
                    </td>
                    <td>${medicament.createdBySurnameUA} ${medicament.createdByNameUA}</td>
                    <input type="hidden" data-type="description" value="${medicament.descriptionEN}">
                </c:if>
                <td><custom:formatDate value="${medicament.createTime}" pattern="dd-MM-yyy HH:mm:ss"/></td>
            </tr>
        </c:forEach>
        <c:forEach var="procedure" items="${procedures}">
            <tr>
                <c:if test="${locale.name == 'en_US'}">
                    <td>${procedure.nameEN}</td>
                    <td><fmt:message key="td.procedure" bundle="${lang}"/></td>
                    <td>
                            ${procedure.empSurnameEN} ${procedure.empNameEN}
                        <c:choose>
                            <c:when test="${!empty procedure.doctorSpecializationNameEN}">
                                (${procedure.doctorSpecializationNameEN})
                            </c:when>
                            <c:otherwise>(<fmt:message key="td.nurse" bundle="${lang}"/>)</c:otherwise>
                        </c:choose>
                    </td>
                    <td>${procedure.createdBySurnameEN} ${procedure.createdByNameEN}</td>
                    <input type="hidden" data-type="description" value="${procedure.descriptionEN}">
                </c:if>
                <c:if test="${locale.name == 'uk_UA'}">
                    <td>${procedure.nameUA}</td>
                    <td><fmt:message key="td.procedure" bundle="${lang}"/></td>
                    <td>
                            ${procedure.empSurnameUA} ${procedure.empNameUA}
                        <c:choose>
                            <c:when test="${!empty procedure.doctorSpecializationNameUA}">
                                (${empty procedure.doctorSpecializationNameUA})
                            </c:when>
                            <c:otherwise>(<fmt:message key="td.nurse" bundle="${lang}"/>)</c:otherwise>
                        </c:choose>
                    </td>
                    <td>${procedure.createdBySurnameUA} ${procedure.createdByNameUA}</td>
                    <input type="hidden" data-type="description" value="${procedure.descriptionEN}">
                </c:if>
                <td><custom:formatDate value="${procedure.createTime}" pattern="dd-MM-yyy HH:mm:ss"/></td>
            </tr>
        </c:forEach>
        <c:forEach var="surgery" items="${surgeries}">
            <tr>
                <c:if test="${locale.name == 'en_US'}">
                    <td>${surgery.nameEN}</td>
                    <td><fmt:message key="td.surgery" bundle="${lang}"/></td>
                    <td>
                            ${surgery.empSurnameEN} ${surgery.empNameEN}
                        <c:choose>
                            <c:when test="${!empty surgery.doctorSpecializationNameEN}">
                                (${surgery.doctorSpecializationNameEN})
                            </c:when>
                            <c:otherwise>(<fmt:message key="td.nurse" bundle="${lang}"/>)</c:otherwise>
                        </c:choose>
                    </td>
                    <td>${surgery.createdBySurnameEN} ${surgery.createdByNameEN}</td>
                    <input type="hidden" data-type="description" value="${surgery.descriptionEN}">
                </c:if>
                <c:if test="${locale.name == 'uk_UA'}">
                    <td>${surgery.nameUA}</td>
                    <td><fmt:message key="td.surgery" bundle="${lang}"/></td>
                    <td>
                            ${surgery.empSurnameUA} ${surgery.empNameUA}
                        <c:choose>
                            <c:when test="${!empty surgery.doctorSpecializationNameUA}">
                                (${empty surgery.doctorSpecializationNameUA})
                            </c:when>
                            <c:otherwise>(<fmt:message key="td.nurse" bundle="${lang}"/>)</c:otherwise>
                        </c:choose>
                    </td>
                    <td>${surgery.createdBySurnameUA} ${surgery.createdByNameUA}</td>
                    <input type="hidden" data-type="description" value="${surgery.descriptionEN}">
                </c:if>
                <td><custom:formatDate value="${surgery.createTime}" pattern="dd-MM-yyy HH:mm:ss"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


    <div class="modal fade" id="assignmentModal" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-dialog-centered modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title">
                        <fmt:message key="assignment.modal.title" bundle="${lang}"/>
                    </h3>
                </div>
                <div class="modal-body">
                    <textarea class="form-control" data-type="descriptionModal" rows="6" readonly></textarea>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-dark" data-dismiss="modal">
                        <fmt:message key="modal.close.button" bundle="${lang}"/>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<c:set var="js" value="true"/>
<%@ include file="/WEB-INF/jspf/footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/assignmentModal.js"></script>
</body>
</html>
