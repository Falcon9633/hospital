<%@ taglib tagdir="/WEB-INF/tags" prefix="tl" %>
<div class="bg-dark border-right mt-n4 opacity-3" id="sidebar-wrapper">
    <div class="list-group list-group-flush">
        <tl:sidebarUrl doctor="${doctorSideBar}" patient="${patientSideBar}" medicalCardId="${medicalCardId}" patientId="${patientId}"
                       diag="${diag}" medic="${medic}" proc="${proc}" surg="${surg}"/>
    </div>
    <c:if test="${role.name == 'doctor' && !medicalCard.discharged && medicalCard.doctorId == account.id}">
    <div class="list-group list-group-flush">
        <a href="controller?command=dischargePatient&medical_card_id=${medicalCardId}&patient_id=${patientId}"
           class="list-group-item list-group-item-action bg-primary text-light"
           data-toggle="confirmation"
           data-popout="true"
           data-placement="bottom" data-title=""
           data-btn-ok-label='<fmt:message key="button.popout.confirm" bundle="${lang}"/>'
           data-btn-cancel-label='<fmt:message key="button.popout.cancel" bundle="${lang}"/>'>
            <fmt:message key="medical_card.sidebar.anchor.discharge_patient" bundle="${lang}"/>
        </a>
    </div>
    </c:if>
</div>