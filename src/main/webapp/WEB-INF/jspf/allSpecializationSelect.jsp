<select class="form-control mr-sm-2" name="specialization_id" required id="${selectId}">
    <option value=""><fmt:message key="select.specialization" bundle="${lang}"/></option>
    <c:forEach var="specialization" items="${specializations}">
        <c:if test="${locale.name == 'en_US'}">
            <option value="${specialization.id}">
                    ${specialization.nameEN}
            </option>
        </c:if>
        <c:if test="${locale.name == 'uk_UA'}">
            <option value="${specialization.id}">
                    ${specialization.nameUA}
            </option>
        </c:if>
    </c:forEach>
</select>
