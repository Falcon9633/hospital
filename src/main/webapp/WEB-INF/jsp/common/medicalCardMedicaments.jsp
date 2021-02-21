<%@ include file="/WEB-INF/jspf/directive/page.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/i18n.jsp" %>
<fmt:message key="medical_card.title" var="title" bundle="${lang}"/>
<%@ include file="/WEB-INF/jspf/head.jsp" %>
<%@ include file="/WEB-INF/jspf/header.jsp" %>

<div class="container-fluid">
    <div class="row">
        <c:set var="medic" value="true"/>
        <%@ include file="/WEB-INF/jspf/medicalCardSidebar.jsp" %>
        <div class="col-8 ml-5">
            <h2>qwe[qw,e[,qw[,eq[,e[lq,w[le,qwe,[q,[we,[</h2>
        </div>
    </div>
</div>


<c:set var="js" value="true"/>
<%@ include file="/WEB-INF/jspf/footer.jsp" %>
</body>
</html>
