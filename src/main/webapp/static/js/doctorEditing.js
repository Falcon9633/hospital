const DOCTOR_EDITING_MODAL = $('#doctorEditingModal');
const EDITED_DOCTOR_ID = $('#doctor_id');
const EDITED_NAME_EN = $('#edited_name_EN');
const EDITED_SURNAME_EN = $('#edited_surname_EN');
const EDITED_NAME_UA = $('#edited_name_UA');
const EDITED_SURNAME_UA = $('#edited_surname_UA');
const EDITED_LOCKED = $('#edited_locked');
const MODAL_TITLE = $('h4.modal-title');
const MODAL_TITLE_TEXT = MODAL_TITLE.text();
const MODAL_SPECIALIZATION_OPTIONS = $('select[name=specialization_id] option');

function init() {
    DOCTOR_EDITING_MODAL.modal({'show': false});

    $('tbody').on('click', 'tr', showModal);
}

function showModal() {
    DOCTOR_EDITING_MODAL.modal('show');

    let currentRow = $(this).closest('tr');
    let id = currentRow.find('input[name=current_id]').val();
    let nameEN = currentRow.find('input[name=current_name_EN]').val();
    let surnameEN = currentRow.find('input[name=current_surname_EN]').val();
    let nameUA = currentRow.find('input[name=current_name_UA]').val();
    let surnameUA = currentRow.find('input[name=current_surname_UA]').val();
    let locked = currentRow.find('input[type=checkbox]').is(":checked");
    let updateTime = currentRow.find('input[name=current_update_time]').val();
    let specializationId = currentRow.find('input[name=current_specialization_id]').val();

    initModalContent(id, nameEN, surnameEN, nameUA, surnameUA, locked, updateTime, specializationId);
}

function selectCurrentOption(id, $options) {
    $options.each(function () {
        if ($(this).val() === id) {
            $(this).attr('selected', 'selected');
        }
    });
}

function initModalContent(id, nameEN, surnameEN, nameUA, surnameUA, locked, updateTime, specializationId) {
    selectCurrentOption(specializationId, MODAL_SPECIALIZATION_OPTIONS)
    EDITED_DOCTOR_ID.val(id);
    EDITED_NAME_EN.val(nameEN);
    EDITED_SURNAME_EN.val(surnameEN);
    EDITED_NAME_UA.val(nameUA);
    EDITED_SURNAME_UA.val(surnameUA);
    if (locked === true) {
        EDITED_LOCKED.attr('checked', 'checked');
    } else {
        EDITED_LOCKED.removeAttr('checked');
    }
    MODAL_TITLE.text(MODAL_TITLE_TEXT + updateTime);
}

$(document).ready(init);