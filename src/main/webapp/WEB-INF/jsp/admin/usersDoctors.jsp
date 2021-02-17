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
            <th>Surname Name</th>
            <th>Specialization</th>
            <th>Patients count</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>

        </tbody>
    </table>

</div>


<c:set var="js" value="true"/>
<%@ include file="/WEB-INF/jspf/footer.jsp" %>
</body>
</html>