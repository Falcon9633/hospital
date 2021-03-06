<%@ include file="/WEB-INF/jspf/directive/page.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/i18n.jsp" %>
<fmt:message key="users_patients.title" var="title" bundle="${lang}"/>
<%@ include file="/WEB-INF/jspf/head.jsp" %>
<%@ include file="/WEB-INF/jspf/header.jsp" %>

<div class="container">
    <%@ include file="/WEB-INF/jspf/errorMessage.jsp" %>

    <table class="table table-dark table-bordered table-hover">
        <thead>
        <tr>
            <th>
                <a href="controller?command=usersPatients&sortBy=surname&sortDir=${sortDir}&currentPage=${empty currentPage ? '1' : currentPage}">
                    <fmt:message key="users.th_surname_name" bundle="${lang}"/>
                </a></th>
            <th>
                <a href="controller?command=usersPatients&sortBy=birthday&sortDir=${sortDir}&currentPage=${empty currentPage ? '1' : currentPage}">
                    <fmt:message key="users_patients.th_birthday" bundle="${lang}"/>
                </a></th>
            <th><fmt:message key="users.th_email" bundle="${lang}"/></th>
            <th><fmt:message key="th.created_time" bundle="${lang}"/></th>
            <th><fmt:message key="users.th_updated_by" bundle="${lang}"/></th>
            <th><fmt:message key="users.th_locked" bundle="${lang}"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="patient" items="${patientAccountBeans}">
            <tr>
                <c:if test="${locale.name == 'en_US'}">
                    <td>${patient.surnameEN} ${patient.nameEN}</td>
                </c:if>
                <c:if test="${locale.name == 'uk_UA'}">
                    <td>${patient.surnameUA} ${patient.nameUA}</td>
                </c:if>
                <td><custom:formatDate value="${patient.birthday}" pattern="dd-MM-yyyy"/></td>
                <td>${patient.email}</td>
                <td><custom:formatDate value="${patient.createTime}" pattern="dd-MM-yyyy HH:mm:ss"/></td>
                <c:if test="${locale.name == 'en_US'}">
                    <td>${patient.updatedBySurnameEN} ${patient.updatedByNameEN}</td>
                </c:if>
                <c:if test="${locale.name == 'uk_UA'}">
                    <td>${patient.updatedBySurnameUA} ${patient.updatedByNameUA}</td>
                </c:if>
                <td><input type="checkbox" disabled ${patient.locked ? 'checked' : ''}></td>

                <input type="hidden" value="${patient.id}" name="current_id"/>
                <input type="hidden" value="${patient.nameEN}" name="current_name_EN"/>
                <input type="hidden" value="${patient.surnameEN}" name="current_surname_EN"/>
                <input type="hidden" value="${patient.nameUA}" name="current_name_UA"/>
                <input type="hidden" value="${patient.surnameUA}" name="current_surname_UA"/>
                <input type="hidden" value="${patient.birthday}" name="current_birthday"/>
                <input type="hidden" value="${patient.locked}" name="current_locked"/>
                <input type="hidden" value='<custom:formatDate value="${patient.updateTime}" pattern="dd-MM-yyyy HH:mm:ss"/>'
                       name="current_update_time"/>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="modal fade" id="patientEditingModal" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-dialog-centered modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">
                        <fmt:message key="users_patients.modal.title" bundle="${lang}"/>
                    </h4>
                </div>
                <div class="modal-body">
                    <form id="modal_edit_patient" class="form-inline" action="controller" method="post">
                        <input type="hidden" name="command" value="editPatient"/>
                        <c:set var="editedRole" value="patient"/>
                        <%@ include file="/WEB-INF/jspf/userEditingModal.jsp" %>
                    </form>
                </div>
                <div class="modal-footer">
                    <a href="controller?command=administratePatientMedicalCards&patient_id="
                       class="btn btn-dark" role="button" aria-pressed="true" id="modal_medical_card_button">
                        <fmt:message key="users_patients.modal.medical_card.button" bundle="${lang}"/>
                    </a>
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

    <c:if test="${numOfPages > 1}">
        <nav aria-label="Patient navigation">
            <ul class="pagination justify-content-center">
                <c:if test="${currentPage != 1}">
                    <li class="page-item">
                        <a class="page-link"
                           href="controller?command=usersPatients&currentPage=${currentPage-1}&sortBy=${empty sortBy ? 'surname' : sortBy}&sortDir=${empty sortDirPagination ? 'asc' : sortDirPagination}">
                            <fmt:message key="pagination.previous.button" bundle="${lang}"/></a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${numOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage == i}">
                            <li class="page-item active"><a class="page-link">
                                    ${i} <span class="sr-only">(<fmt:message key="pagination.current.span" bundle="${lang}"/>)</span></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link"
                                   href="controller?command=usersPatients&currentPage=${i}&sortBy=${empty sortBy ? 'surname' : sortBy}&sortDir=${empty sortDirPagination ? 'asc' : sortDirPagination}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${currentPage lt noOfPages}">
                    <li class="page-item">
                        <a class="page-link"
                           href="controller?command=usersPatients&currentPage=${currentPage+1}&sortBy=${empty sortBy ? 'surname' : sortBy}&sortDir=${empty sortDirPagination ? 'asc' : sortDirPagination}">
                            <fmt:message key="pagination.next.button" bundle="${lang}"/></a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </c:if>
</div>

<c:set var="js" value="true"/>
<%@ include file="/WEB-INF/jspf/footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/patientEditingModal.js"></script>
</body>
</html>