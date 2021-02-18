package ua.com.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.constant.Path;
import ua.com.entity.Account;
import ua.com.entity.Locale;
import ua.com.service.AccountService;
import ua.com.service.impl.AccountServiceImpl;
import ua.com.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EditPatientCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(EditPatientCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");
        HttpSession session = req.getSession();
        session.removeAttribute("errorMessage");
        Locale locale = (Locale) session.getAttribute("locale");

        String forward = Path.USERS_PATIENTS_PAGE;

        Long patientId;
        try {
            patientId = Long.valueOf(req.getParameter("id"));
            if (patientId < 1) {
                throw new NumberFormatException("patient_id = " + patientId + " must be > 0");
            }
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(), e.getCause());
            return forward;
        }
        LOGGER.trace("requested param id -> {}", patientId);

        String nameEN = req.getParameter("edited_name_EN");
        LOGGER.trace("requested param edited_name_EN -> {}", nameEN);
        if (Validator.isAccDetailsNameSurnameENNotValid(nameEN, session, locale, LOGGER, forward)) {
            return forward;
        }

        String surnameEN = req.getParameter("edited_surname_EN");
        LOGGER.trace("requested param edited_surname_EN -> {}", surnameEN);
        if (Validator.isAccDetailsNameSurnameENNotValid(surnameEN, session, locale, LOGGER, forward)) {
            return forward;
        }

        String nameUA = req.getParameter("edited_name_UA");
        LOGGER.trace("requested param edited_name_UA -> {}", nameUA);
        if (Validator.isAccDetailsNameSurnameUANotValid(nameUA, session, locale, LOGGER, forward)) {
            return forward;
        }

        String surnameUA = req.getParameter("edited_surname_UA");
        LOGGER.trace("requested param edited_surname_UA -> {}", surnameUA);
        if (Validator.isAccDetailsNameSurnameUANotValid(surnameUA, session, locale, LOGGER, forward)) {
            return forward;
        }

        LocalDate birthday = null;
        try {
            birthday = LocalDate.parse(req.getParameter("edited_birthday"));
            LOGGER.trace("requested param edited_birthday -> {}", birthday);
        } catch (DateTimeParseException e) {
            LOGGER.error(e.getMessage(), e.getCause());
            Validator.setErrorMessage(session,
                    locale.getMessage("validation.error.invalid_birthday"), LOGGER, forward);
            return forward;
        }

        boolean locked = Validator.checkInputCheckbox(req.getParameter("edited_locked"));
        LOGGER.trace("requested param edited_locked -> {}", locked);

        Account updatedBy = (Account) session.getAttribute("account");

        AccountService accountService = new AccountServiceImpl();
        boolean successful = accountService.editPatient(patientId, nameEN, surnameEN, nameUA, surnameUA, birthday, locked, updatedBy.getId());
        if (!successful){
            Validator.setErrorMessage(session, locale.getMessage("edit_patient_command.error.failed_to_edit"), LOGGER, forward);
            return forward;
        }

        forward = Path.REDIRECT + Path.USERS_PATIENTS_COMMAND;
        LOGGER.debug("execute finishes");
        return forward;
    }
}
