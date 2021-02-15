const SPECIALIZATION_EDITING_MODAL = $('#specializationEditingModal');
const SPECIALIZATION_ID = $('#specialization_id');
const EDITED_NAME_EN = $('#edited_name_EN');
const EDITED_NAME_UA = $('#edited_name_UA');

function init() {
    SPECIALIZATION_EDITING_MODAL.modal({'show' : false});

    $('tbody').on('click', 'tr', showModal);
}

function showModal() {
    SPECIALIZATION_EDITING_MODAL.modal('show');

    let currentRow = $(this).closest('tr');
    let id = currentRow.find('input[name=current_id').val();
    let nameEN = currentRow.find('input[name=current_name_EN').val();
    let nameUA = currentRow.find('input[name=current_name_UA').val();

    initModalContent(id, nameEN, nameUA);
}

function initModalContent(id, nameEN, nameUA) {
    SPECIALIZATION_ID.val(id);
    EDITED_NAME_EN.val(nameEN);
    EDITED_NAME_UA.val(nameUA);
}

$(document).ready(init);