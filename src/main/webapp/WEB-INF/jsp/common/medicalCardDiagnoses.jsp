<%@ include file="/WEB-INF/jspf/directive/page.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/i18n.jsp" %>
<fmt:message key="medical_card.title" var="title" bundle="${lang}"/>
<%@ include file="/WEB-INF/jspf/head.jsp" %>
<%@ include file="/WEB-INF/jspf/header.jsp" %>

<div class="container-fluid">
    <div class="row">
        <c:set var="diag" value="true"/>
        <%@ include file="/WEB-INF/jspf/medicalCardSidebar.jsp" %>
        <div class="col-8 ml-5">
            <%@ include file="/WEB-INF/jspf/errorMessage.jsp" %>

            <form action="controller" method="post">
                <input type="hidden" name="command" value="createDiagnosis"/>
                <input type="hidden" name="medical_card_id" value="${medicalCardId}">
                <input type="hidden" name="patient_id" value="${patientId}">
                <div class="row">
                    <div class="col-5">
                        <label for="name_EN" class="mr-sm-2 mt-n2">
                            <fmt:message key="medical_card_diagnoses.label.name_en" bundle="${lang}"/>
                        </label>
                        <input type="text" name="name_EN" class="form-control mb-2 mr-sm-2" id="name_EN"
                               required minlength="3" maxlength="45"
                               placeholder='<fmt:message key="placeholder.name_en" bundle="${lang}"/>'/>
                    </div>
                    <div class="col-5">
                        <label for="name_UA" class="mr-sm-2 mt-n2">
                            <fmt:message key="medical_card_diagnoses.label.name_ua" bundle="${lang}"/>
                        </label>
                        <input type="text" name="name_UA" class="form-control mb-2 mr-sm-2" id="name_UA"
                               required minlength="3" maxlength="45"
                               placeholder='<fmt:message key="placeholder.name_ua" bundle="${lang}"/>'/>
                    </div>

                    <div class="col-1">
                        <button type="submit" class="btn btn-dark mt-4" data-toggle="confirmation" data-popout="true"
                                data-placement="bottom" data-title=""
                                data-btn-ok-label='<fmt:message key="button.popout.confirm" bundle="${lang}"/>'
                                data-btn-cancel-label='<fmt:message key="button.popout.cancel" bundle="${lang}"/>'>
                            <fmt:message key="button.create" bundle="${lang}"/>
                        </button>
                    </div>
                </div>

                <div class="row">
                    <div class="col-5">
                        <label for="description_en" class="mr-sm-2">
                            <fmt:message key="placeholder.description_en" bundle="${lang}"/>
                        </label>
                        <textarea name="description_EN" class="form-control" id="description_en" rows="2"
                                  maxlength="1024"
                                  placeholder='<fmt:message key="placeholder.description_en" bundle="${lang}"/>'></textarea>
                    </div>
                    <div class="col-5">
                        <label for="description_ua" class="mr-sm-2">
                            <fmt:message key="placeholder.description_ua" bundle="${lang}"/>
                        </label>
                        <textarea name="description_UA" class="form-control" id="description_ua" rows="2"
                                  maxlength="1024"
                                  placeholder='<fmt:message key="placeholder.description_ua" bundle="${lang}"/>'></textarea>
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
                        <fmt:message key="medical_card_diagnoses.th.name" bundle="${lang}"/>
                    </th>
                    <th>
                        <fmt:message key="medical_card.th.created_by" bundle="${lang}"/>
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="diagnosis" items="${diagnoses}">
                    <tr>
                        <td><custom:formatDate value="${diagnosis.createTime}" pattern="dd-MM-yyy HH:mm:ss"/></td>
                        <c:if test="${locale.name == 'en_US'}">
                            <td>${diagnosis.nameEN}</td>
                            <td>
                                    ${diagnosis.doctorSurnameEN} ${diagnosis.doctorNameEN}
                                (${diagnosis.specializationNameEN})
                            </td>
                            <input type="hidden" data-type="description" value="${diagnosis.descriptionEN}">
                        </c:if>
                        <c:if test="${locale.name == 'uk_UA'}">
                            <td>${diagnosis.nameUA}</td>
                            <td>
                                    ${diagnosis.doctorSurnameUA} ${diagnosis.doctorNameUA}
                                (${diagnosis.specializationNameUA})
                            </td>
                            <input type="hidden" data-type="description" value="${diagnosis.descriptionUA}">
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
    </div>
</div>


<c:set var="js" value="true"/>
<%@ include file="/WEB-INF/jspf/footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap-confirmation.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/confirmationButton.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/medicalCardModal.js"></script>
</body>
</html>