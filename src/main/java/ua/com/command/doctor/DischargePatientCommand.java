package ua.com.command.doctor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.constant.Path;
import ua.com.dao.MedicalCardDao;
import ua.com.dao.impl.DaoFactory;
import ua.com.entity.Account;
import ua.com.entity.Locale;
import ua.com.entity.MedicalCard;
import ua.com.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DischargePatientCommand implements ua.com.command.Command {
    private static final Logger LOGGER = LogManager.getLogger(DischargePatientCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");
        HttpSession session = req.getSession();
        session.removeAttribute("errorMessage");
        Locale locale = (Locale) session.getAttribute("locale");

        String forward = Path.REDIRECT + Path.DOCTOR_CURRENT_MEDICAL_CARDS_COMMAND;

        Long medicalCardId = null;
        Long patientId = null;
        try {
            medicalCardId = Long.parseLong(req.getParameter("medical_card_id"));
            patientId = Long.parseLong(req.getParameter("patient_id"));
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }
        LOGGER.trace("requested param medical_card_id -> {}", medicalCardId);
        LOGGER.trace("requested param patient_id -> {}", patientId);

        if (!Validator.isRequestedIdValid(medicalCardId) ||
                !Validator.isRequestedIdValid(patientId)) {
            return forward;
        }

        forward = Path.REDIRECT + String.format(Path.DOCTOR_DIAGNOSES_MEDICAL_CARD_COMMAND, medicalCardId, patientId);

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
        }

        medicalCard.setDischarged(true);
        medicalCard.setUpdatedBy(account.getId());
        boolean successful = medicalCardDao.dischargePatient(medicalCard);
        if (!successful){
            Validator.setErrorMessage(session, locale.getMessage("discharge_patient_command.error.failed_to_discharge_patient"), LOGGER);
        }

        LOGGER.debug("execute finishes");
        return forward;
    }
}
