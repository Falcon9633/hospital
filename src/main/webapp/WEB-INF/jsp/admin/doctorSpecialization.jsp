<%@ include file="/WEB-INF/jspf/directive/page.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/i18n.jsp" %>
<fmt:message key="doctor_specialization.title" var="title" bundle="${lang}"/>
<%@ include file="/WEB-INF/jspf/head.jsp" %>
<%@ include file="/WEB-INF/jspf/header.jsp" %>

<div class="container">
    <%@ include file="/WEB-INF/jspf/errorMessage.jsp" %>
    <form class="form-inline" action="controller" method="post">
        <input type="hidden" name="command" value="createSpecialization"/>
        <label for="name_EN" class="mr-sm-2">
            <fmt:message key="doctor_specialization.name_en.label" bundle="${lang}"/>
        </label>
        <input type="text" name="name_EN" class="form-control mb-2 mr-sm-2" id="name_EN"
               required minlength="3" maxlength="45"
               placeholder='<fmt:message key="placeholder.name_en" bundle="${lang}"/>'
        />

        <label for="name_UA" class="mr-sm-2">
            <fmt:message key="doctor_specialization.name_ua.label" bundle="${lang}"/>
        </label>
        <input type="text" name="name_UA" class="form-control mb-2 mr-sm-2" id="name_UA"
               required minlength="3" maxlength="45"
               placeholder='<fmt:message key="placeholder.name_ua" bundle="${lang}"/>'
        />

        <button type="submit" class="btn btn-dark mb-2">
            <fmt:message key="button.create" bundle="${lang}"/>
        </button>
    </form>

    <table class="table table-dark table-bordered table-hover">
        <thead>
        <tr>
            <th><a href="controller?command=doctorSpecialization&sortBy=name&sortDirName=${sortDirName}">
                <fmt:message key="doctor_specialization.th_name" bundle="${lang}"/>
            </a></th>
            <th><fmt:message key="doctor_specialization.th_update_by" bundle="${lang}"/></th>
            <th><fmt:message key="doctor_specialization.th_last_time_updated" bundle="${lang}"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="spec" items="${specAccDetailList}">
            <tr>
                <c:if test="${locale.name == 'en_US'}">
                    <td>${spec.nameEN}</td>
                    <td>${spec.accountNameEN} ${spec.accountSurnameEN}</td>
                </c:if>
                <c:if test="${locale.name == 'uk_UA'}">
                    <td>${spec.nameUA}</td>
                    <td>${spec.accountNameUA} ${spec.accountSurnameUA}</td>
                </c:if>
                <td>
                    <custom:formatDate value="${spec.updateTime}" pattern="dd-MM-yyyy HH:mm:ss"/>
                </td>
                <input type="hidden" value="${spec.id}" name="current_id">
                <input type="hidden" value="${spec.nameEN}" name="current_name_EN">
                <input type="hidden" value="${spec.nameUA}" name="current_name_UA">
            </tr>
        </c:forEach>
        </tbody>
    </table>


    <div class="modal fade" id="specializationEditingModal" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title">
                        <fmt:message key="doctor_specialization.modal.title" bundle="${lang}"/>
                    </h1>
                </div>
                <div class="modal-body">
                    <form id="modal_edit_specialization" class="form-inline" action="controller" method="post">
                        <input type="hidden" name="command" value="editSpecialization"/>
                        <label for="edited_name_EN" class="mr-sm-2">
                            <fmt:message key="doctor_specialization.name_en.label" bundle="${lang}"/>
                        </label>
                        <input type="text" name="edited_name_EN" class="form-control mb-2 mr-sm-2" id="edited_name_EN"
                               required minlength="3" maxlength="45"
                               placeholder='<fmt:message key="placeholder.name_en" bundle="${lang}"/>'/>

                        <label for="edited_name_UA" class="mr-sm-2">
                            <fmt:message key="doctor_specialization.name_ua.label" bundle="${lang}"/>
                        </label>
                        <input type="text" name="edited_name_UA" class="form-control mb-2 mr-sm-2" id="edited_name_UA"
                               required minlength="3" maxlength="45"
                               placeholder='<fmt:message key="placeholder.name_ua" bundle="${lang}"/>'/>

                        <input type="hidden" id="specialization_id" name="id"/>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-dark" data-dismiss="modal">
                        <fmt:message key="modal.close.button" bundle="${lang}"/>
                    </button>
                    <button type="submit" class="btn btn-dark" form="modal_edit_specialization">
                        <fmt:message key="modal.save.button" bundle="${lang}"/>
                    </button>
                </div>
            </div>
        </div>
    </div>

</div>

<c:set var="js" value="true"/>
<%@ include file="/WEB-INF/jspf/footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/specializationEditingModal.js"></script>
</body>
</html>