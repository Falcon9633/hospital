<%@ include file="/WEB-INF/jspf/directive/page.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/i18n.jsp" %>
<fmt:message key="medical_card.title" var="title" bundle="${lang}"/>
<%@ include file="/WEB-INF/jspf/head.jsp" %>
<%@ include file="/WEB-INF/jspf/header.jsp" %>

<div class="container-fluid">
    <div class="row">
        <c:set var="surg" value="true"/>
        <%@ include file="/WEB-INF/jspf/medicalCardSidebar.jsp" %>
        <div class="col-8 ml-5">
            <%@ include file="/WEB-INF/jspf/errorMessage.jsp" %>

            <form action="controller" method="post">
                <input type="hidden" name="command" value="createSurgery"/>
                <input type="hidden" name="medical_card_id" value="${medicalCardId}">
                <input type="hidden" name="patient_id" value="${patientId}">
                <div class="row">
                    <div class="col">
                        <label for="name_EN" class="mr-sm-2 mt-n2">
                            <fmt:message key="medical_card_surgeries.label.name_en" bundle="${lang}"/>
                        </label>
                        <input type="text" name="name_EN" class="form-control mb-2 mr-sm-2" id="name_EN"
                               required minlength="3" maxlength="45"
                               placeholder='<fmt:message key="placeholder.name_en" bundle="${lang}"/>'/>
                    </div>
                    <div class="col">
                        <label for="name_UA" class="mr-sm-2 mt-n2">
                            <fmt:message key="medical_card_surgeries.label.name_ua" bundle="${lang}"/>
                        </label>
                        <input type="text" name="name_UA" class="form-control mb-2 mr-sm-2" id="name_UA"
                               required minlength="3" maxlength="45"
                               placeholder='<fmt:message key="placeholder.name_ua" bundle="${lang}"/>'/>
                    </div>

                </div>

                <div class="row">
                    <div class="col">
                        <label for="description_en" class="mr-sm-2">
                            <fmt:message key="placeholder.description_en" bundle="${lang}"/>
                        </label>
                        <textarea name="description_EN" class="form-control" id="description_en" rows="2"
                                  maxlength="1024"
                                  placeholder='<fmt:message key="placeholder.description_en" bundle="${lang}"/>'></textarea>
                    </div>
                    <div class="col">
                        <label for="description_ua" class="mr-sm-2">
                            <fmt:message key="placeholder.description_ua" bundle="${lang}"/>
                        </label>
                        <textarea name="description_UA" class="form-control" id="description_ua" rows="2"
                                  maxlength="1024"
                                  placeholder='<fmt:message key="placeholder.description_ua" bundle="${lang}"/>'></textarea>
                    </div>
                </div>

                <div class="row">
                    <div class="col-11">
                        <label for="emp_id" class="mt-2">
                            <fmt:message key="medical_card.label.doctor" bundle="${lang}"/>
                        </label>
                        <select class="form-control" name="emp_id" required id="emp_id">
                            <option value=""><fmt:message key="medical_card.select.doctor" bundle="${lang}"/></option>
                            <c:forEach var="specDoctors" items="${specDoctorsMap}">
                                <c:if test="${locale.name == 'en_US'}">
                                    <optgroup label="${specDoctors.key.nameEN}">
                                        <c:forEach var="doctor" items="${specDoctors.value}">
                                            <c:if test="${!empty doctor}">
                                                <option value="${doctor.id}">
                                                        ${doctor.surnameEN} ${doctor.nameEN}
                                                </option>
                                            </c:if>
                                        </c:forEach>
                                    </optgroup>
                                </c:if>
                                <c:if test="${locale.name == 'uk_UA'}">
                                    <optgroup label="${specDoctors.key.nameUA}">
                                        <c:forEach var="doctor" items="${specDoctors.value}">
                                            <option value="${doctor.id}">
                                                    ${doctor.surnameUA} ${doctor.nameUA}
                                            </option>
                                        </c:forEach>
                                    </optgroup>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-1 mt-3">
                        <button type="submit" class="btn btn-dark mt-4" data-toggle="confirmation" data-popout="true"
                                data-placement="bottom" data-title=""
                                data-btn-ok-label='<fmt:message key="button.popout.confirm" bundle="${lang}"/>'
                                data-btn-cancel-label='<fmt:message key="button.popout.cancel" bundle="${lang}"/>'>
                            <fmt:message key="button.create" bundle="${lang}"/>
                        </button>
                    </div>
                </div>
            </form>

            <table class="table table-dark table-bordered table-hover">
                <thead>
                <tr>
                    <th>
                        <fmt:message key="medical_card.th.created_time" bundle="${lang}"/>
                    </th>
                    <th>
                        <fmt:message key="medical_card_surgeries.th.surgeries" bundle="${lang}"/>
                    </th>
                    <th>
                        <fmt:message key="medical_card.th.created_by" bundle="${lang}"/>
                    </th>
                    <th>
                        <fmt:message key="medical_card.th.served_by" bundle="${lang}"/>
                    </th>
                    <th>
                        <fmt:message key="medical_card.th.end" bundle="${lang}"/>
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="surgery" items="${surgeries}">
                    <tr>
                        <input type="hidden" data-type="assigmentId" value="${surgery.id}">
                        <td><custom:formatDate value="${surgery.createTime}" pattern="dd-MM-yyy HH:mm:ss"/></td>
                        <c:if test="${locale.name == 'en_US'}">
                            <td>${surgery.nameEN}</td>
                            <td>
                                    ${surgery.doctorSurnameEN} ${surgery.doctorNameEN}
                                (${surgery.specializationNameEN})
                            </td>
                            <td>
                                    ${surgery.servedBySurnameEN} ${surgery.servedByNameEN}
                            </td>
                            <td data-end="${surgery.end}">
                                    ${surgery.end ? 'finised' : 'actual'}
                            </td>
                            <input type="hidden" data-type="description" value="${surgery.descriptionEN}">
                        </c:if>
                        <c:if test="${locale.name == 'uk_UA'}">
                            <td>${surgery.nameUA}</td>
                            <td>
                                    ${surgery.doctorSurnameUA} ${surgery.doctorNameUA}
                                (${surgery.specializationNameUA})
                            </td>
                            <td>
                                    ${surgery.servedBySurnameUA} ${surgery.servedByNameUA}
                            </td>
                            <td>
                                    ${surgery.end ? 'завершено' : 'актуально'}
                            </td>
                            <input type="hidden" data-type="description" value="${surgery.descriptionUA}">
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div class="modal fade" id="medicalCardModal" tabindex="-1" role="dialog">
                <div class="modal-dialog modal-dialog-centered modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title">
                                <fmt:message key="medical_card.modal.title" bundle="${lang}"/>
                            </h3>
                        </div>
                        <div class="modal-body">
                            <textarea class="form-control" data-type="descriptionModal" rows="6" readonly></textarea>
                            <c:if test="${role.name == 'doctor'}">
                                <form action="controller" method="post" id="assigmentForm">
                                    <input type="hidden" name="command" value="editSurgery">
                                    <input type="hidden" name="patient_id" value="${patientId}">
                                    <input type="hidden" name="medical_card_id" value="${medicalCardId}">
                                    <input type="hidden" name="assigment_id" value="">
                                    <input type="hidden" name="end" value="true">
                                </form>
                            </c:if>
                        </div>
                        <div class="modal-footer">
                            <c:if test="${role.name == 'doctor'}">
                                <button type="submit" class="btn btn-primary mr-3" form="assigmentForm">
                                    <fmt:message key="modal.end.button" bundle="${lang}"/>
                                </button>
                            </c:if>
                            <button type="button" class="btn btn-dark" data-dismiss="modal">
                                <fmt:message key="modal.close.button" bundle="${lang}"/>
                            </button>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>


<c:set var="js" value="true"/>
<%@ include file="/WEB-INF/jspf/footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap-confirmation.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/confirmationButton.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/medicalCardModal.js"></script>
</body>
</html>

