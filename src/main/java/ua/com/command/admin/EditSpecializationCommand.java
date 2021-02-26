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

public class EditSpecializationCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(EditSpecializationCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");
        HttpSession session = req.getSession();
        session.removeAttribute("errorMessage");
        Locale locale = (Locale) session.getAttribute("locale");

        String forward = Path.DOCTOR_SPECIALIZATION_PAGE;

        Integer specializationId;
        try {
            specializationId = Integer.valueOf(req.getParameter("id"));
            if (specializationId < 1) {
                throw new NumberFormatException("specialization_id = " + specializationId + " must be > 0");
            }
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(), e.getCause());
            return forward;
        }
        LOGGER.trace("requested param id -> {}", specializationId);

        String nameEN = req.getParameter("edited_name_EN");
        LOGGER.trace("requested param edited_name_EN -> {}", nameEN);
        if (Validator.isSpecializationNameNotValid(nameEN, session, locale, LOGGER, forward)) {
            return forward;
        }

        String nameUA = req.getParameter("edited_name_UA");
        LOGGER.trace("requested param edited_name_UA -> {}", nameUA);
        if (Validator.isSpecializationNameNotValid(nameUA, session, locale, LOGGER, forward)) {
            return forward;
        }

        SpecializationDao specializationDao = new SpecializationDaoImpl();
        Specialization foundById = specializationDao.findById(specializationId);
        LOGGER.trace("foundById -> {}", foundById);
        if (foundById == null) {
            Validator.setErrorMessage(session,
                    locale.getMessage("edit_specialization_command.error.specialization_not_exits"),
                    LOGGER, forward);
            return forward;
        }

        List<Specialization> foundByName = specializationDao.findByName(nameEN, nameUA);
        LOGGER.trace("foundByName -> {}", foundByName);
        for (Specialization s : foundByName) {
            if (!foundById.getId().equals(s.getId())) {
                Validator.setErrorMessage(session,
                        locale.getMessage("validation.error.specialization_already_exist"), LOGGER, forward);
                return forward;
            }
        }

        Account account = (Account) session.getAttribute("account");
        foundById.setNameEN(nameEN);
        foundById.setNameUA(nameUA);
        foundById.setUpdatedBy(account.getId());
        boolean successful = specializationDao.update(foundById);
        if (!successful) {
            Validator.setErrorMessage(session,
                    locale.getMessage("edit_specialization_command.error.failed_to_edit"),
                    LOGGER, forward
            );
        }

        forward = Path.REDIRECT + Path.DOCTOR_SPECIALIZATION_COMMAND;
        session.removeAttribute("specAccDetailList");

        LOGGER.debug("execute finishes");
        return forward;
    }
}
