const MEDICAL_CARD_MODAL = $('#medicalCardModal');
const DESCRIPTION = $('[data-type=descriptionModal]');
const EDITED_ASSIGMENT_ID = $('input[name=assigment_id]');
const FINISH_BUTTON = $('button[form=assigmentForm]');

function init() {
    MEDICAL_CARD_MODAL.modal({'show': false});

    $('tbody').on('click', 'tr', showModal);
}

function showModal() {
    MEDICAL_CARD_MODAL.modal('show');

    let currentRow = $(this).closest('tr');
    let description = currentRow.find('input[data-type=description]').val();
    let assignmentId = currentRow.find('input[data-type=assigmentId]').val();
    let end = currentRow.find('td[data-end]').attr('data-end');

    initModalContent(description, end, assignmentId);
}

function initModalContent(description, end, assigmentId){
    DESCRIPTION.text(description);
    hideFinishButton(end);
    if(EDITED_ASSIGMENT_ID){
        EDITED_ASSIGMENT_ID.val(assigmentId);
    }
}

function hideFinishButton(end){
    if (end === 'true'){
        FINISH_BUTTON.hide();
    } else {
        FINISH_BUTTON.show();
    }
}

$(document).ready(init);