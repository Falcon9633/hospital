package ua.com.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.command.Command;
import ua.com.constant.Path;
import ua.com.dao.SpecializationDao;
import ua.com.dao.impl.SpecializationDaoImpl;
import ua.com.entity.Account;
import ua.com.entity.Locale;
import ua.com.entity.Specialization;
import ua.com.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CreateSpecializationCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CreateSpecializationCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");
        HttpSession session = req.getSession();
        session.removeAttribute("errorMessage");
        Locale locale = (Locale) session.getAttribute("locale");

        String forward = Path.DOCTOR_SPECIALIZATION_PAGE;

        String nameEN = req.getParameter("name_EN");
        LOGGER.trace("requested param name_EN -> {}", nameEN);
        if (Validator.isSpecializationNameNotValid(nameEN, session, locale, LOGGER, forward)) {
            return forward;
        }

        String nameUA = req.getParameter("name_UA");
        LOGGER.trace("requested param name_UA -> {}", nameUA);
        if (Validator.isSpecializationNameNotValid(nameUA, session, locale, LOGGER, forward)) {
            return forward;
        }

        SpecializationDao specializationDao = new SpecializationDaoImpl();
        List<Specialization> foundByName = specializationDao.findByName(nameEN, nameUA);
        LOGGER.trace("foundByName -> {}", foundByName);
        if (foundByName.size() > 0) {
            Validator.setErrorMessage(session,
                    locale.getMessage("create_specialization_command.error.specialization_already_exist"), LOGGER, forward);
            return forward;
        }

        Account account = (Account) session.getAttribute("account");
        Specialization insertedSpec = specializationDao.insert(new Specialization(nameEN, nameUA, account.getId()));
        if (insertedSpec.getId() == null){
            Validator.setErrorMessage(session,
                    locale.getMessage("create_specialization_command.error.failed_to_create"), LOGGER, forward);
            return forward;
        }

        forward = Path.REDIRECT + Path.DOCTOR_SPECIALIZATION_COMMAND;
        session.removeAttribute("specAccDetailList");

        LOGGER.debug("execute finishes");
        return forward;
    }
}
