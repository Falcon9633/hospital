package ua.com.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.command.Command;
import ua.com.constant.Path;
import ua.com.dao.AccountDao;
import ua.com.dao.MedicalCardDao;
import ua.com.dao.impl.DaoFactory;
import ua.com.dao.impl.MedicalCardDaoImpl;
import ua.com.entity.Account;
import ua.com.entity.Locale;
import ua.com.entity.MedicalCard;
import ua.com.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateMedicalCardCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CreateMedicalCardCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");
        HttpSession session = req.getSession();
        session.removeAttribute("errorMessage");
        Locale locale = (Locale) session.getAttribute("locale");

        String forward = Path.ADMINISTRATE_PATIENT_MEDICAL_CARDS_PAGE;

        Long patientId = null;
        try {
            patientId = Long.valueOf(req.getParameter("patient_id"));
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }
        if (!Validator.isRequestedIdValid(patientId)) {
            return forward;
        }
        LOGGER.trace("requested param patient_id -> {}", patientId);

        forward = Path.REDIRECT + Path.ADMINISTRATE_PATIENT_MEDICAL_CARDS_COMMAND + patientId;

        AccountDao accountDao = DaoFactory.getAccountDao();
        boolean patientExists = accountDao.isPatientExists(patientId);
        if (!patientExists){
            Validator.setErrorMessage(session,
                    locale.getMessage("create_medical_card_command.error.failed_to_create"), LOGGER, forward);
            return forward;
        }

        Account account = (Account) session.getAttribute("account");

        MedicalCard medicalCard = new MedicalCard(account.getId(), patientId);

        MedicalCardDao medicalCardDao = new MedicalCardDaoImpl();
        MedicalCard insertedMedCard = medicalCardDao.insert(medicalCard);

        if (insertedMedCard.getId() == null){
            Validator.setErrorMessage(session,
                    locale.getMessage("create_medical_card_command.error.failed_to_create"), LOGGER, forward);
            return forward;
        }

        LOGGER.debug("execute finishes");
        return forward;
    }
}
