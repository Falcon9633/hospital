<%@ include file="/WEB-INF/jspf/directive/taglib.jsp" %>
<%@ include file="/WEB-INF/jspf/directive/i18n.jsp" %>
<%@ attribute name="doctor" required="true" type="java.lang.Boolean" %>
<%@ attribute name="patient" required="true" type="java.lang.Boolean" %>
<%@ attribute name="medicalCardId" required="true" %>
<%@ attribute name="patientId" required="true" %>
<%@ attribute name="diag" required="false" %>
<%@ attribute name="medic" required="false" %>
<%@ attribute name="proc" required="false" %>
<%@ attribute name="surg" required="false" %>

<c:if test="${doctor == true}">
    <a href="controller?command=doctorDiagnosesMedicalCard&medical_card_id=${medicalCardId}&patient_id=${patientId}"
       class="list-group-item list-group-item-action bg-dark text-light ${diag ? 'sidebar-active':''}">
        <fmt:message key="medical_card.sidebar.anchor.diagnoses" bundle="${lang}"/>
    </a>
    <a href="controller?command=doctorMedicamentsMedicalCard&medical_card_id=${medicalCardId}&patient_id=${patientId}"
       class="list-group-item list-group-item-action bg-dark text-light ${medic ? 'sidebar-active':''}">
        <fmt:message key="medical_card.sidebar.anchor.medicaments" bundle="${lang}"/>
    </a>
    <a href="controller?command=doctorProceduresMedicalCard&medical_card_id=${medicalCardId}&patient_id=${patientId}"
       class="list-group-item list-group-item-action bg-dark text-light ${proc ? 'sidebar-active':''}">
        <fmt:message key="medical_card.sidebar.anchor.procedures" bundle="${lang}"/>
    </a>
    <a href="controller?command=doctorSurgeriesMedicalCard&medical_card_id=${medicalCardId}&patient_id=${patientId}"
       class="list-group-item list-group-item-action bg-dark text-light ${surg ? 'sidebar-active':''}">
        <fmt:message key="medical_card.sidebar.anchor.surgeries" bundle="${lang}"/>
    </a>
</c:if>

<c:if test="${patient == true}">
    <a href="controller?command=patientDiagnosesMedicalCard&medical_card_id=${medicalCardId}"
       class="list-group-item list-group-item-action bg-dark text-light ${diag ? 'sidebar-active':''}">
        <fmt:message key="medical_card.sidebar.anchor.diagnoses" bundle="${lang}"/>
    </a>
    <a href="controller?command=patientMedicamentsMedicalCard&medical_card_id=${medicalCardId}"
       class="list-group-item list-group-item-action bg-dark text-light ${medic ? 'sidebar-active':''}">
        <fmt:message key="medical_card.sidebar.anchor.medicaments" bundle="${lang}"/>
    </a>
    <a href="controller?command=patientProceduresMedicalCard&medical_card_id=${medicalCardId}"
       class="list-group-item list-group-item-action bg-dark text-light ${proc ? 'sidebar-active':''}">
        <fmt:message key="medical_card.sidebar.anchor.procedures" bundle="${lang}"/>
    </a>
    <a href="controller?command=patientSurgeriesMedicalCard&medical_card_id=${medicalCardId}"
       class="list-group-item list-group-item-action bg-dark text-light ${surg ? 'sidebar-active':''}">
        <fmt:message key="medical_card.sidebar.anchor.surgeries" bundle="${lang}"/>
    </a>
</c:if>

