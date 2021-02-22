<%@ include file="/WEB-INF/jspf/directive/page.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/i18n.jsp" %>
<fmt:message key="doctor_specialization.title" var="title" bundle="${lang}"/>
<%@ include file="/WEB-INF/jspf/head.jsp" %>
<%@ include file="/WEB-INF/jspf/header.jsp" %>

<div class="container-fluid">
    <div class="row">
        <div class="bg-dark border-right mt-n4 opacity-3" id="sidebar-wrapper">
            <div class="list-group list-group-flush">
                <a href="#" class="list-group-item list-group-item-action bg-dark text-light sidebar-active">Dashboard</a>
                <a href="#" class="list-group-item list-group-item-action bg-dark text-light">Shortcuts</a>
                <a href="#" class="list-group-item list-group-item-action bg-dark text-light">Overview</a>
                <a href="#" class="list-group-item list-group-item-action bg-dark text-light">Events</a>
                <a href="#" class="list-group-item list-group-item-action bg-dark text-light">Profile</a>
                <a href="#" class="list-group-item list-group-item-action bg-dark text-light">Status</a>
            </div>
        </div>
        <div class="col-8 ml-5">
            <h2>qwe[qw,e[,qw[,eq[,e[lq,w[le,qwe,[q,[we,[</h2>
        </div>
    </div>
</div>

<c:set var="js" value="true"/>
<%@ include file="/WEB-INF/jspf/footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/specializationEditingModal.js"></script>
</body>
</html>