<%@ include file="/WEB-INF/jspf/directive/page.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/i18n.jsp" %>
<fmt:message key="users_doctors.title" var="title" bundle="${lang}"/>
<%@ include file="/WEB-INF/jspf/head.jsp" %>
<%@ include file="/WEB-INF/jspf/header.jsp" %>

<div class="container">
    <%@ include file="/WEB-INF/jspf/errorMessage.jsp" %>

    <table class="table table-dark table-bordered table-hover">
        <thead>
        <tr>
            <th><a href="controller?command=usersDoctors&sortBy=surname&sortDir=${sortDir}">
                <fmt:message key="users.th_surname_name" bundle="${lang}"/></a></th>
            <th><a href="controller?command=usersDoctors&sortBy=specialization&sortDir=${sortDir}">
                <fmt:message key="users_doctors.th_specialization" bundle="${lang}"/></a></th>
            <th><a href="controller?command=usersDoctors&sortBy=patientCount&sortDir=${sortDir}">
                <fmt:message key="users_doctors.th_patients_count" bundle="${lang}"/></a></th>
            <th><fmt:message key="users.th_email" bundle="${lang}"/></th>
            <th><fmt:message key="users.th_created_time" bundle="${lang}"/></th>
            <th><fmt:message key="users.th_updated_by" bundle="${lang}"/></th>
            <th><fmt:message key="users.th_locked" bundle="${lang}"/></th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="doctor" items="${doctorAccountBeans}">
                <tr>
                    <c:if test="${locale.name == 'en_US'}">
                        <td>${doctor.surnameEN} ${doctor.nameEN}</td>
                        <td>${doctor.specializationNameEN}</td>
                    </c:if>
                    <c:if test="${locale.name == 'uk_UA'}">
                        <td>${doctor.surnameUA} ${doctor.nameUA}</td>
                        <td>${doctor.specializationNameUA}</td>
                    </c:if>
                    <td>${doctor.patientCount}</td>
                    <td>${doctor.email}</td>
                    <td><custom:formatDate value="${doctor.createTime}" pattern="dd-MM-yyyy HH:mm:ss"/></td>
                    <c:if test="${locale.name == 'en_US'}">
                        <td>${doctor.updatedBySurnameEN} ${doctor.updatedByNameEN}</td>
                    </c:if>
                    <c:if test="${locale.name == 'uk_UA'}">
                        <td>${doctor.updatedBySurnameUA} ${doctor.updatedByNameUA}</td>
                    </c:if>
                    <td><input type="checkbox" disabled ${doctor.locked ? 'checked' : ''}></td>
                    <input type="hidden" value="${doctor.id}" name="current_id"/>
                    <input type="hidden" value="${doctor.nameEN}" name="current_name_EN"/>
                    <input type="hidden" value="${doctor.surnameEN}" name="current_surname_EN"/>
                    <input type="hidden" value="${doctor.nameUA}" name="current_name_UA"/>
                    <input type="hidden" value="${doctor.surnameUA}" name="current_surname_UA"/>
                    <input type="hidden" value="${doctor.specializationId}" name="current_specialization_id"/>
                    <input type="hidden" value='<custom:formatDate value="${doctor.updateTime}" pattern="dd-MM-yyyy HH:mm:ss"/>'
                           name="current_update_time"/>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div class="modal fade" id="doctorEditingModal" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-dialog-centered modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">
                        <fmt:message key="users_doctors.modal.title" bundle="${lang}"/>
                    </h4>
                </div>
                <div class="modal-body">
                    <form id="modal_edit_patient" class="form-inline" action="controller" method="post">
                        <input type="hidden" name="command" value="editDoctor"/>
                        <c:set var="editedRole" value="doctor"/>
                        <%@ include file="/WEB-INF/jspf/userEditingModal.jsp" %>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-dark" data-dismiss="modal">
                        <fmt:message key="modal.close.button" bundle="${lang}"/>
                    </button>
                    <button type="submit" class="btn btn-dark" form="modal_edit_patient">
                        <fmt:message key="modal.save.button" bundle="${lang}"/>
                    </button>
                </div>
            </div>
        </div>
    </div>

</div>


<c:set var="js" value="true"/>
<%@ include file="/WEB-INF/jspf/footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/doctorEditing.js"></script>
</body>
</html>