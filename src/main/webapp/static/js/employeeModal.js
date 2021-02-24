const EMPLOYEE_ASSIGNMENT_MODAL = $('#employeeAssignmentModal');
const DESCRIPTION = $('[data-type=descriptionModal]');

function init() {
    EMPLOYEE_ASSIGNMENT_MODAL.modal({'show': false});

    $('tbody').on('click', 'tr', showModal);
}

function showModal() {
    EMPLOYEE_ASSIGNMENT_MODAL.modal('show');

    let currentRow = $(this).closest('tr');
    let description = currentRow.find('input[data-type=description]').val();

    initModalContent(description);
}

function initModalContent(description){
    DESCRIPTION.text(description);
}

$(document).ready(init);