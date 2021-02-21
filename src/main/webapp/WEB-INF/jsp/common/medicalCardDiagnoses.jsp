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
                        <button type="submit" class="btn btn-dark mt-4">
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
                        <fmt:message key="medical_card_diagnoses.th.created_time" bundle="${lang}"/>
                    </th>
                    <th>
                        <fmt:message key="medical_card_diagnoses.th.name" bundle="${lang}"/>
                    </th>
                    <th>
                        <fmt:message key="medical_card_diagnoses.th.created_by" bundle="${lang}"/>
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="diagnosis" items="${diagnoses}">
                    <c:if test="${locale.name == 'en_US'}">
                        <c:set var="description" value="${diagnosis.descriptionEN}"/>
                    </c:if>
                    <c:if test="${locale.name == 'uk_UA'}">
                        <c:set var="description" value="${diagnosis.descriptionUA}"/>
                    </c:if>
                    <tr data-toggle="popover" data-content="${description}" data-trigger="click"
                        title='<fmt:message key="medical_card.description" bundle="${lang}"/>' data-placement="top">
                        <td><custom:formatDate value="${diagnosis.createTime}" pattern="dd-MM-yyy HH:mm:ss"/></td>
                        <c:if test="${locale.name == 'en_US'}">
                            <td>${diagnosis.nameEN}</td>
                            <td>
                                    ${diagnosis.doctorSurnameEN} ${diagnosis.doctorNameEN}
                                (${diagnosis.specializationNameEN})
                            </td>
                            <div data-toggle="popover" data-content="${diagnosis.descriptionEN}"></div>
                        </c:if>
                        <c:if test="${locale.name == 'uk_UA'}">
                            <td>${diagnosis.nameUA}</td>
                            <td>
                                    ${diagnosis.doctorSurnameUA} ${diagnosis.doctorNameUA}
                                (${diagnosis.specializationNameUA})
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
</div>


<c:set var="js" value="true"/>
<%@ include file="/WEB-INF/jspf/footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/popover.js"></script>
</body>
</html>