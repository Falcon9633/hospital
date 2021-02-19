package ua.com.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.constant.Path;
import ua.com.dao.AccountDao;
import ua.com.dao.impl.AccountDaoImpl;
import ua.com.dao.impl.DaoFactory;
import ua.com.entity.Account;
import ua.com.entity.Locale;
import ua.com.entity.Role;
import ua.com.service.AccountService;
import ua.com.service.impl.AccountServiceImpl;
import ua.com.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

/**
 * Registers a new account depends on the role.
 *
 * @author Orest Dmyterko
 */
public class RegisterAccountCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(RegisterAccountCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");
        HttpSession session = req.getSession();
        session.removeAttribute("errorMessage");

        String forward = "";

        int roleId;

        try {
            roleId = Integer.parseInt(req.getParameter("role_id"));
            if (roleId < 0 || roleId > 3) {
                throw new NumberFormatException("roleId = " + roleId + " must be >= 0 and <= 3");
            }
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage());
            return forward;
        }

        Role registrationRole = Role.getRole(roleId);
        LOGGER.trace("requested param registration role -> {}", registrationRole);

        switch (registrationRole) {
            case ADMIN:
                forward = Path.REDIRECT + Path.ACCOUNT_REGISTRATION_ADMIN_COMMAND;
                break;
            case DOCTOR:
                forward = Path.REDIRECT + Path.ACCOUNT_REGISTRATION_DOCTOR_COMMAND;
                break;
            case NURSE:
                forward = Path.REDIRECT + Path.ACCOUNT_REGISTRATION_NURSE_COMMAND;
                break;
            case PATIENT:
                forward = Path.REDIRECT + Path.ACCOUNT_REGISTRATION_PATIENT_COMMAND;
                break;
            default:
                forward = Path.REDIRECT + Path.LOGIN_PAGE;
                break;
        }

        Locale locale = (Locale) session.getAttribute("locale");
        LOGGER.trace("session locale -> {}", locale);

        String login = req.getParameter("login");
        LOGGER.trace("requested param login -> {}", login);
        if (Validator.isLoginNotValid(login, session, locale, LOGGER, forward)) {
            return forward;
        }

        String nameEN = req.getParameter("name_EN");
        LOGGER.trace("requested param name_EN -> {}", nameEN);
        if (Validator.isAccDetailsNameSurnameENNotValid(nameEN, session, locale, LOGGER, forward)) {
            return forward;
        }

        String surnameEN = req.getParameter("surname_EN");
        LOGGER.trace("requested param surname_EN -> {}", surnameEN);
        if (Validator.isAccDetailsNameSurnameENNotValid(surnameEN, session, locale, LOGGER, forward)) {
            return forward;
        }

        String nameUA = req.getParameter("name_UA");
        LOGGER.trace("requested param name_UA -> {}", nameUA);
        if (Validator.isAccDetailsNameSurnameUANotValid(nameUA, session, locale, LOGGER, forward)) {
            return forward;
        }

        String surnameUA = req.getParameter("surname_UA");
        LOGGER.trace("requested param surname_UA -> {}", surnameUA);
        if (Validator.isAccDetailsNameSurnameUANotValid(surnameUA, session, locale, LOGGER, forward)) {
            return forward;
        }

        String email = req.getParameter("email");
        LOGGER.trace("requested param email -> {}", email);
        if (Validator.isEmailNotValid(email, session, locale, LOGGER, forward)) {
            return forward;
        }

        int specializationId = 0;
        if (registrationRole == Role.DOCTOR) {
            // remove cached result for admin/users_doctors page
            session.removeAttribute("doctorAccountBeans");
            try {
                specializationId = Integer.parseInt(req.getParameter("specialization_id"));
                LOGGER.trace("requested param specialization_id -> {}", specializationId);
                if (specializationId < 1) {
                    throw new NumberFormatException("specialization_id = " + specializationId + " must be > 0");
                }
            } catch (NumberFormatException e) {
                LOGGER.error(e.getMessage(), e.getCause());
                Validator.setErrorMessage(session,
                        locale.getMessage("register_account_command.error.incorrect_specialization"), LOGGER, forward);
                return forward;
            }
        }

        LocalDate birthday = null;
        if (registrationRole == Role.PATIENT) {
            try {
                birthday = LocalDate.parse(req.getParameter("birthday"));
                LOGGER.trace("requested param birthday -> {}", birthday);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e.getCause());
                Validator.setErrorMessage(session,
                        locale.getMessage("validation.error.invalid_birthday"), LOGGER, forward);
                return forward;
            }
        }

        AccountDao accountDao = DaoFactory.getAccountDao();
        Account loginExists = accountDao.findByLogin(login);
        if (loginExists != null){
            Validator.setErrorMessage(session,
                    locale.getMessage("register_account_command.error.account_login_already_exist"), LOGGER, forward);
            return forward;
        }

        Account account = (Account) session.getAttribute("account");
        AccountService accountService = new AccountServiceImpl();
        boolean successful = accountService.registerAccount(login, account.getId(), registrationRole, nameEN, surnameEN,
                nameUA, surnameUA, email, specializationId, birthday);

        if (!successful) {
            Validator.setErrorMessage(session,
                    locale.getMessage("register_account_command.error.failed_to_register"), LOGGER);
        }

        LOGGER.debug("execute finishes");
        return forward;
    }
}
