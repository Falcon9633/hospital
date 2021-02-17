const PATIENT_EDITING_MODAL = $('#patientEditingModal');
const SPECIALIZATION_ID = $('#patient_id');
const EDITED_NAME_EN = $('#edited_name_EN');
const EDITED_SURNAME_EN = $('#edited_surname_EN');
const EDITED_NAME_UA = $('#edited_name_UA');
const EDITED_SURNAME_UA = $('#edited_surname_UA');
const EDITED_BIRTHDAY = $('#edited_birthday');
const EDITED_LOCKED = $('#edited_locked');
const MODAL_TITLE = $('h4.modal-title');
const MODAL_TITLE_TEXT = MODAL_TITLE.text();

function init() {
    PATIENT_EDITING_MODAL.modal({'show': false});

    $('tbody').on('click', 'tr', showModal);
}

function showModal() {
    PATIENT_EDITING_MODAL.modal('show');

    let currentRow = $(this).closest('tr');
    let id = currentRow.find('input[name=current_id').val();
    let nameEN = currentRow.find('input[name=current_name_EN').val();
    let surnameEN = currentRow.find('input[name=current_surname_EN').val();
    let nameUA = currentRow.find('input[name=current_name_UA').val();
    let surnameUA = currentRow.find('input[name=current_surname_UA').val();
    let birthday = currentRow.find('input[name=current_birthday').val();
    let locked = currentRow.find('input[name=current_locked').val();
    let updateTime = currentRow.find('input[name=current_update_time]').val();

    initModalContent(id, nameEN, surnameEN, nameUA, surnameUA, birthday, locked, updateTime);
}

function initModalContent(id, nameEN, surnameEN, nameUA, surnameUA, birthday, locked, updateTime) {
    SPECIALIZATION_ID.val(id);
    EDITED_NAME_EN.val(nameEN);
    EDITED_SURNAME_EN.val(surnameEN);
    EDITED_NAME_UA.val(nameUA);
    EDITED_SURNAME_UA.val(surnameUA);
    EDITED_BIRTHDAY.val(birthday);
    if (locked === 'true') {
        EDITED_LOCKED.attr('checked', 'checked');
    } else {
        EDITED_LOCKED.removeAttr('checked');
    }
    MODAL_TITLE.text(MODAL_TITLE_TEXT + updateTime);
}

$(document).ready(init);