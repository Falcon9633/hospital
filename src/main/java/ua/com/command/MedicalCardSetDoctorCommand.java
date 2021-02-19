package ua.com.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.constant.Path;
import ua.com.dao.AccountDao;
import ua.com.dao.MedicalCardDao;
import ua.com.dao.impl.DaoFactory;
import ua.com.entity.Account;
import ua.com.entity.Locale;
import ua.com.entity.MedicalCard;
import ua.com.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MedicalCardSetDoctorCommand implements Command {
    public static final Logger LOGGER = LogManager.getLogger(MedicalCardSetDoctorCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");
        HttpSession session = req.getSession();
        session.removeAttribute("errorMessage");
        Locale locale = (Locale) session.getAttribute("locale");

        String forward = Path.ADMINISTRATE_PATIENT_MEDICAL_CARDS_PAGE;

        long patientId = 0;
        try {
            patientId = Long.parseLong(req.getParameter("patient_id"));
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }
        if (!Validator.isRequestedIdValid(patientId)){
            return forward;
        }
        LOGGER.trace("requested param patient_id -> {}", patientId);

        forward = Path.REDIRECT + Path.ADMINISTRATE_PATIENT_MEDICAL_CARDS_COMMAND + patientId;


        long doctorId = 0;
        try {
            doctorId = Long.parseLong(req.getParameter("doctor_id"));
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }
        if (!Validator.isRequestedIdValid(doctorId)){
            return forward;
        }
        LOGGER.trace("requested param doctor_id -> {}", doctorId);

        AccountDao accountDao = DaoFactory.getAccountDao();
        boolean doctorExists = accountDao.isDoctorExists(doctorId);
        if (!doctorExists){
            Validator.setErrorMessage(session, locale.getMessage("validation.error.doctor_not_exist"), LOGGER, forward);
            return forward;
        }

        long medicalCardId = 0;
        try {
            medicalCardId = Long.parseLong(req.getParameter("medical_card_id"));
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }
        if (!Validator.isRequestedIdValid(medicalCardId)){
            return forward;
        }
        LOGGER.trace("requested param medical_card_id -> {}", medicalCardId);

        MedicalCardDao medicalCardDao = DaoFactory.getMedicalCardDao();
        MedicalCard medicalCard = medicalCardDao.findById(medicalCardId);
        LOGGER.trace("found in db medicalCard -> {}", medicalCard);

        if (medicalCard == null){
            Validator.setErrorMessage(session, locale.getMessage("validation.error.medical_card_not_exist"),
                    LOGGER, forward);
            return forward;
        }

        if (medicalCard.isDischarged()){
            Validator.setErrorMessage(session,
                    locale.getMessage("medical_card_set_doctor_command.error.cannot_change_discharged_medical_card"),
                    LOGGER, forward);
        }

        Account account = (Account) session.getAttribute("account");
        medicalCard.setDoctorId(doctorId);
        medicalCard.setUpdatedBy(account.getId());
        boolean successful = medicalCardDao.update(medicalCard);
        if (!successful){
            Validator.setErrorMessage(session,
                    locale.getMessage("medical_card_set_doctor_command.error.failed_to_set_doctor"), LOGGER);
        }

        LOGGER.debug("execute finishes");
        return forward;
    }
}