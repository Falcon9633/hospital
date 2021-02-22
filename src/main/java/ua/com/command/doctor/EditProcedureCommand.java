package ua.com.command.doctor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.constant.Path;
import ua.com.dao.MedicalCardDao;
import ua.com.dao.ProcedureDao;
import ua.com.dao.impl.DaoFactory;
import ua.com.entity.*;
import ua.com.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditProcedureCommand implements ua.com.command.Command {
    private static final Logger LOGGER = LogManager.getLogger(EditProcedureCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");
        HttpSession session = req.getSession();
        session.removeAttribute("errorMessage");
        Locale locale = (Locale) session.getAttribute("locale");

        String forward = Path.REDIRECT + Path.DOCTOR_CURRENT_MEDICAL_CARDS_COMMAND;

        Long medicalCardId = null;
        Long patientId = null;
        Long assigmentId = null;
        try {
            medicalCardId = Long.parseLong(req.getParameter("medical_card_id"));
            patientId = Long.parseLong(req.getParameter("patient_id"));
            assigmentId = Long.parseLong(req.getParameter("assigment_id"));
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }
        LOGGER.trace("requested param medical_card_id -> {}", medicalCardId);
        LOGGER.trace("requested param patient_id -> {}", patientId);
        LOGGER.trace("requested param assigment_id -> {}", assigmentId);

        if (!Validator.isRequestedIdValid(medicalCardId) ||
                !Validator.isRequestedIdValid(patientId) ||
                !Validator.isRequestedIdValid(assigmentId)) {
            return forward;
        }

        forward = Path.REDIRECT + String.format(Path.DOCTOR_PROCEDURES_MEDICAL_CARD_COMMAND, medicalCardId, patientId);

        boolean end = Boolean.parseBoolean(req.getParameter("end"));
        LOGGER.trace("requested param end -> {}", end);

        MedicalCardDao medicalCardDao = DaoFactory.getMedicalCardDao();
        MedicalCard medicalCard = medicalCardDao.findById(medicalCardId);
        if (medicalCard.getId() == null){
            Validator.setErrorMessage(session, locale.getMessage("validation.error.medical_card_not_exist"), LOGGER, forward);
            return forward;
        }

        if (medicalCard.isDischarged()) {
            Validator.setErrorMessage(session, locale.getMessage("validation.error.patient_is_discharged"), LOGGER, forward);
            return forward;
        }

        Account account = (Account) session.getAttribute("account");
        if (!medicalCard.getDoctorId().equals(account.getId())){
            Validator.setErrorMessage(session, locale.getMessage("validation.error.no_access"), LOGGER, forward);
            return forward;
        }

        ProcedureDao procedureDao = DaoFactory.getProcedureDao();
        Procedure procedure = procedureDao.findById(assigmentId);
        LOGGER.trace("found in db procedure -> {}", procedure);

        if (procedure.getId() == null){
            Validator.setErrorMessage(session, locale.getMessage("validation.error.procedure_not_exists"), LOGGER, forward);
            return forward;
        }

        if (procedure.isEnd()){
            Validator.setErrorMessage(session, locale.getMessage("validation.error.procedure_is_end"), LOGGER, forward);
            return forward;
        }

        procedure.setEnd(end);
        boolean successful = procedureDao.update(procedure);
        if (!successful){
            Validator.setErrorMessage(session, locale.getMessage("edit_procedure_command.error.failed_to_edit"), LOGGER);
        }

        LOGGER.debug("execute finishes");
        return forward;
    }
}
