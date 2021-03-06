package ua.com.command.doctor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.constant.Path;
import ua.com.dao.AccountDao;
import ua.com.dao.MedicalCardDao;
import ua.com.dao.SurgeryDao;
import ua.com.dao.impl.DaoFactory;
import ua.com.entity.*;
import ua.com.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateSurgeryCommand implements ua.com.command.Command {
    private static final Logger LOGGER = LogManager.getLogger(CreateSurgeryCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");
        HttpSession session = req.getSession();
        session.removeAttribute("errorMessage");
        Locale locale = (Locale) session.getAttribute("locale");

        String forward = Path.REDIRECT + Path.DOCTOR_CURRENT_MEDICAL_CARDS_COMMAND;

        Long medicalCardId = null;
        Long patientId = null;
        Long empId = null;
        try {
            medicalCardId = Long.parseLong(req.getParameter("medical_card_id"));
            patientId = Long.parseLong(req.getParameter("patient_id"));
            empId = Long.parseLong(req.getParameter("emp_id"));
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }
        LOGGER.trace("requested param medical_card_id -> {}", medicalCardId);
        LOGGER.trace("requested param patient_id -> {}", patientId);
        LOGGER.trace("requested param emp_id -> {}", empId);

        if (!Validator.isRequestedIdValid(medicalCardId) ||
                !Validator.isRequestedIdValid(patientId) ||
                !Validator.isRequestedIdValid(empId)) {
            return forward;
        }

        forward = Path.REDIRECT + String.format(Path.DOCTOR_SURGERIES_MEDICAL_CARD_COMMAND, medicalCardId, patientId);

        String nameEN = req.getParameter("name_EN");
        LOGGER.trace("requested param name_EN -> {}", nameEN);
        if (Validator.isAssigmentNameNotValid(nameEN, session, locale, LOGGER, forward)) {
            return forward;
        }

        String nameUA = req.getParameter("name_UA");
        LOGGER.trace("requested param name_UA -> {}", nameUA);
        if (Validator.isAssigmentNameNotValid(nameUA, session, locale, LOGGER, forward)) {
            return forward;
        }

        String descriptionEN = req.getParameter("description_EN");
        LOGGER.trace("requested param description_EN -> {}", descriptionEN);
        if (Validator.isDescriptionNotValid(descriptionEN, session, locale, LOGGER, forward)) {
            return forward;
        }

        String descriptionUA = req.getParameter("description_UA");
        LOGGER.trace("requested param description_UA -> {}", descriptionUA);
        if (Validator.isDescriptionNotValid(descriptionUA, session, locale, LOGGER, forward)) {
            return forward;
        }

        AccountDao accountDao = DaoFactory.getAccountDao();
        boolean doctorExists = accountDao.isDoctorExists(empId);
        if (!doctorExists) {
            Validator.setErrorMessage(session, locale.getMessage("validation.error.doctor_not_exist"), LOGGER, forward);
            return forward;
        }

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
        if (!medicalCard.getDoctorId().equals(account.getId())) {
            Validator.setErrorMessage(session, locale.getMessage("validation.error.no_access"), LOGGER, forward);
        }

        Surgery surgery = new Surgery(nameEN, nameUA, descriptionEN, descriptionUA, account.getId(), empId, medicalCardId);
        SurgeryDao surgeryDao = DaoFactory.getSurgeryDao();
        Surgery inserted = surgeryDao.insert(surgery);
        if (inserted.getId() == null) {
            Validator.setErrorMessage(session, locale.getMessage("create_surgery_command.error.failed_to_create"), LOGGER, forward);
        }

        LOGGER.debug("execute finishes");
        return forward;
    }
}
