package ua.com.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.constant.Path;
import ua.com.dao.AccountDao;
import ua.com.dao.impl.DaoFactory;
import ua.com.entity.Account;
import ua.com.entity.Locale;
import ua.com.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditDoctorCommand implements Command {
    public static final Logger LOGGER = LogManager.getLogger(EditDoctorCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");
        HttpSession session = req.getSession();
        session.removeAttribute("errorMessage");
        Locale locale = (Locale) session.getAttribute("locale");

        String forward = Path.REDIRECT + Path.USERS_DOCTORS_COMMAND;

        Long doctorId = null;
        try {
            doctorId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e){
            LOGGER.error(e.getMessage(), e.getCause());
        }
        if (!Validator.isRequestedIdValid(doctorId)) {
            return forward;
        }
        LOGGER.trace("requested param id -> {}", doctorId);

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

        boolean locked = Validator.checkInputCheckbox(req.getParameter("edited_locked"));
        LOGGER.trace("requested param edited_locked -> {}", locked);

        Integer specializationId = null;
        try {
            specializationId = Integer.valueOf(req.getParameter("specialization_id"));
        } catch (NumberFormatException e){
            LOGGER.error(e.getMessage(), e.getCause());
        }
        if (!Validator.isRequestedIdValid(specializationId)){
            return forward;
        }
        LOGGER.trace("requested param specialization_id -> {}", specializationId);

        Account updatedBy = (Account) session.getAttribute("account");

        AccountDao accountDao = DaoFactory.getAccountDao();
        boolean successful = accountDao.editDoctor(doctorId, specializationId, nameEN, surnameEN, nameUA, surnameUA, locked, updatedBy.getId());
        if (!successful){
            Validator.setErrorMessage(session, locale.getMessage("edit_doctor_command.error.failed_to_edit"), LOGGER);
        }

        session.removeAttribute("doctorAccountBeans");

        LOGGER.debug("execute finishes");
        return forward;
    }
}
