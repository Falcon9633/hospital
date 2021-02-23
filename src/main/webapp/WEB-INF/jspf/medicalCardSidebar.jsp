<div class="bg-dark border-right mt-n4 opacity-3" id="sidebar-wrapper">
    <div class="list-group list-group-flush">
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