<%@ include file="/WEB-INF/jspf/directive/page.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/i18n.jsp" %>
<fmt:message key="administrate_patient_medical_cards.title" var="title" bundle="${lang}"/>
<%@ include file="/WEB-INF/jspf/head.jsp" %>
<%@ include file="/WEB-INF/jspf/header.jsp" %>

<div class="container">
    <%@ include file="/WEB-INF/jspf/errorMessage.jsp" %>
    <form class="form-inline" action="controller" method="post">
        <input type="hidden" name="command" value="createMedicalCard">
        <input type="hidden" name="patient_id" value="${patientId}"/>
        <label class="mr-sm-2 mt-sm-2">
            <h4>
                <fmt:message key="administrate_patient_medical_cards.create_new.label" bundle="${lang}"/>
            </h4>
        </label>
        <button type="submit" class="btn btn-dark">
            <fmt:message key="administrate_patient_medical_cards.create_new.button" bundle="${lang}"/>
        </button>
    </form>

    <table class="table table-dark table-bordered table-hover">
        <thead>
        <tr>
            <th>Create time</th>
            <th>Doctor details</th>
            <th>Last time updated</th>
            <th>Updated by</th>
            <th>Discharged</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="medCard" items="${medCardDoctorBeans}">
            <tr>
                <td><custom:formatDate value="${medCard.createTime}" pattern="dd-MM-yyyy HH:mm:ss"/></td>
                <td>
                    <c:if test="${locale.name == 'en_US'}">
                        ${medCard.doctorSurnameEN} ${medCard.doctorNameEN}
                        ${empty medCard.specializationNameEN ? '' : '('.concat(medCard.specializationNameEN).concat(')')}
                    </c:if>
                    <c:if test="${locale.name == 'uk_UA'}">
                        ${medCard.doctorSurnameUA} ${medCard.doctorNameUA}
                        ${empty medCard.specializationNameUA ? '' : '('.concat(medCard.specializationNameUA).concat(')')}
                    </c:if>
                </td>
                <td><custom:formatDate value="${medCard.updateTime}" pattern="dd-MM-yyy HH:mm:ss"/></td>
                <td>
                    <c:if test="${locale.name == 'en_US'}">
                        ${medCard.updatedBySurnameEN} ${medCard.updatedByNameEN}
                    </c:if>
                    <c:if test="${locale.name == 'uk_UA'}">
                        ${medCard.updatedBySurnameUA} ${medCard.updatedByNameUA}
                    </c:if>
                </td>
                <td><input type="checkbox" disabled ${medCard.discharged ? 'checked' : ''}/></td>
                <input type="hidden" name="current_medical_card_id" value="${medCard.id}">
                <input type="hidden" name="current_doctor_id" value="${medCard.doctorId}">
            </tr>
        </c:forEach>
        </tbody>
    </table>


    <div class="modal fade" id="medicalCardSetDoctorModal" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h2 class="modal-title">
                        Prescribe/change doctor
                    </h2>
                </div>
                <div class="modal-body">
                    <form id="modal_medical_card_set_doctor" action="controller" method="post">
                        <input type="hidden" name="command" value="medicalCardSetDoctor">
                        <input type="hidden" name="patient_id" value="${patientId}"/>
                        <input type="hidden" name="medical_card_id" id="medical_card_id">
                        <select class="form-control" name="doctor_id" required>
                            <option value="">Choose doctor</option>
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
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-dark" data-dismiss="modal">
                        <fmt:message key="modal.close.button" bundle="${lang}"/>
                    </button>
                    <button type="submit" class="btn btn-dark" form="modal_medical_card_set_doctor">
                        <fmt:message key="modal.save.button" bundle="${lang}"/>
                    </button>
                </div>
            </div>
        </div>
    </div>

</div>

<c:set var="js" value="true"/>
<%@ include file="/WEB-INF/jspf/footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/administratePatientMedicalCards.js"></script>
</body>
</html>