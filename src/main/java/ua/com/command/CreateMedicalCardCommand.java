package ua.com.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.constant.Path;
import ua.com.dao.MedicalCardDao;
import ua.com.dao.impl.MedicalCardDaoImpl;
import ua.com.entity.Account;
import ua.com.entity.Locale;
import ua.com.entity.MedicalCard;
import ua.com.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateMedicalCardCommand implements Command {
    public static final Logger LOGGER = LogManager.getLogger(CreateMedicalCardCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");
        HttpSession session = req.getSession();
        session.removeAttribute("errorMessage");
        Locale locale = (Locale) session.getAttribute("locale");

        String forward = Path.ADMINISTRATE_PATIENT_MEDICAL_CARDS_PAGE;

        Long patientId;
        try {
            patientId = Long.valueOf(req.getParameter("patient_id"));
            if (patientId < 1) {
                throw new NumberFormatException("patient_id = " + patientId + " must be > 0");
            }
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(), e.getCause());
            return forward;
        }
        LOGGER.trace("requested param patient_id -> {}", patientId);

        Account account = (Account) session.getAttribute("account");

        MedicalCard medicalCard = new MedicalCard(account.getId(), patientId);

        MedicalCardDao medicalCardDao = new MedicalCardDaoImpl();
        MedicalCard insertedMedCard = medicalCardDao.insert(medicalCard);

        if (insertedMedCard.getId() == null){
            Validator.setErrorMessage(session,
                    locale.getMessage("create_medical_card_command.error.failed_to_create"), LOGGER, forward);
            return forward;
        }

        forward = Path.REDIRECT + Path.ADMINISTRATE_PATIENT_MEDICAL_CARDS_COMMAND + patientId;

        LOGGER.debug("execute finishes");
        return forward;
    }
}
