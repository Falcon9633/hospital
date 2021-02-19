<label for="edited_name_EN" class="mr-sm-2">
    <fmt:message key="users.modal.edited_name_en.label" bundle="${lang}"/>
</label>
<input type="text" name="edited_name_EN" class="form-control mb-2 mr-sm-2" id="edited_name_EN"
       required minlength="2" maxlength="45" pattern="^[\w\d-_ ]+$"
       placeholder='<fmt:message key="users.modal.edited_name_en.placeholder" bundle="${lang}"/>'/>

<label for="edited_surname_EN" class="mr-sm-2">
    <fmt:message key="users.modal.edited_surname_en.label" bundle="${lang}"/>
</label>
<input type="text" name="edited_surname_EN" class="form-control mb-2 mr-sm-2"
       id="edited_surname_EN"
       required minlength="2" maxlength="45" pattern="^[\w\d-_ ]+$"
       placeholder='<fmt:message key="users.modal.edited_surname_en.placeholder" bundle="${lang}"/>'/>

<label for="edited_name_UA" class="mr-sm-2">
    <fmt:message key="users.modal.edited_name_ua.label" bundle="${lang}"/>
</label>
<input type="text" name="edited_name_UA" class="form-control mb-2 mr-sm-2" id="edited_name_UA"
       required minlength="2" maxlength="45" pattern="^[а-їѓА-ЯІЇЄЁ\d-_ ]+$"
       placeholder='<fmt:message key="users.modal.edited_name_ua.placeholder" bundle="${lang}"/>'/>

<label for="edited_surname_UA" class="mr-sm-2">
    <fmt:message key="users.modal.edited_surname_ua.label" bundle="${lang}"/>
</label>
<input type="text" name="edited_surname_UA" class="form-control mb-2 mr-sm-2" id="edited_surname_UA"
       required minlength="2" maxlength="45" pattern="^[а-їѓА-ЯІЇЄЁ\d-_ ]+$"
       placeholder='<fmt:message key="users.modal.edited_surname_ua.placeholder" bundle="${lang}"/>'/>

<c:if test="${editedRole == 'patient'}">
    <label for="edited_birthday" class="mr-sm-2">
        <fmt:message key="users_patients.modal.edited_birthday.label" bundle="${lang}"/>
    </label>
    <input type="date" name="edited_birthday" class="form-control mb-2 mr-sm-2" id="edited_birthday"
           required min="1910-01-01"/>
</c:if>

<c:if test="${editedRole == 'doctor'}">
    <label for="edited_specialization" class="mr-sm-2">
        <fmt:message key="users_doctors.modal.edited_specialization" bundle="${lang}"/>
    </label>
    <c:set var="selectId" value="edited_specialization"/>
    <%@ include file="/WEB-INF/jspf/allSpecializationSelect.jsp" %>
</c:if>


<div class="form-check">
    <label for="edited_locked" class="form-check-label mr-sm-2">
        <fmt:message key="users.modal.edited_locked.label" bundle="${lang}"/>
    </label>
    <input type="checkbox" name="edited_locked" class="form-check-input" id="edited_locked"/>
</div>


<input type="hidden" id="${editedRole}_id" name="id"/>
