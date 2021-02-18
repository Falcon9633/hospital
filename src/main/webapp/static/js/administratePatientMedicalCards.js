const MEDICAL_CARD_ADD_DOCTOR_MODAL = $('#medicalCardSetDoctorModal');
const MODAL_DOCTORS_OPTIONS = $('select[name=doctor_id] option');
const EDITED_MEDICAL_CARD_ID = $('#medical_card_id')

function init() {
    MEDICAL_CARD_ADD_DOCTOR_MODAL.modal({'show': false});

    $('tbody').on('click', 'tr', showModal);
}

function showModal() {
    let currentRow = $(this).closest('tr');
    let isDischarged = currentRow.find('input[type=checkbox]').is(":checked");

    if (isDischarged === false){
        MEDICAL_CARD_ADD_DOCTOR_MODAL.modal('show');
    }

    let medicalCardId = currentRow.find('input[name=current_medical_card_id').val();
    let doctorId = currentRow.find('input[name=current_doctor_id').val();

    initModalContent(medicalCardId, doctorId);
}

function selectCurrentDoctor(doctorId) {
    MODAL_DOCTORS_OPTIONS.each(function (){
        if ($(this).val() === doctorId){
            $(this).attr('selected', 'selected');
        }
    });
}

function initModalContent(medicalCardId, doctorId){
    selectCurrentDoctor(doctorId);
    EDITED_MEDICAL_CARD_ID.val(medicalCardId);
}

$(document).ready(init);