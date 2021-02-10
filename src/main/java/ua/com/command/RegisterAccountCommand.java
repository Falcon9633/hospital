package ua.com.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.constant.Path;
import ua.com.entity.Account;
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

        String forward = "";

        int roleId;

        try {
            roleId = Integer.parseInt(req.getParameter("role_id"));
        } catch (NumberFormatException e) {
            LOGGER.info(e.getMessage());
            return "";
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

        String login = req.getParameter("login");
        if (Validator.isLoginNotValid(login)) {
            Validator.setErrorMessage(session, "login is not valid", LOGGER, forward);
            return forward;
        }
        LOGGER.trace("requested param login -> {}", login);

        String nameEN = req.getParameter("name_EN");
        if (Validator.isAccDetailsNameENNotValid(nameEN)) {
            Validator.setErrorMessage(session, "name in English is not valid", LOGGER, forward);
            return forward;
        }
        LOGGER.trace("requested param name_EN -> {}", nameEN);

        String surnameEN = req.getParameter("surname_EN");
        if (Validator.isSurnameENNotValid(surnameEN)) {
            Validator.setErrorMessage(session, "surname in English is not valid", LOGGER, forward);
            return forward;
        }
        LOGGER.trace("requested param surname_EN -> {}", surnameEN);

        String nameUA = req.getParameter("name_UA");
        if (Validator.isAccDetailsNameUANotValid(nameUA)) {
            Validator.setErrorMessage(session, "name in Ukrainian is not valid", LOGGER, forward);
            return forward;
        }
        LOGGER.trace("requested param name_UA -> {}", nameUA);

        String surnameUA = req.getParameter("surname_UA");
        if (Validator.isSurnameUANotValid(surnameUA)) {
            Validator.setErrorMessage(session, "surname in Ukrainian is not valid", LOGGER, forward);
            return forward;
        }
        LOGGER.trace("requested param surname_UA -> {}", surnameUA);

        String email = req.getParameter("email");
        if (Validator.isEmailNotValid(email)) {
            Validator.setErrorMessage(session, "email is not valid", LOGGER, forward);
            return forward;
        }
        LOGGER.trace("requested param email -> {}", email);

        int specializationId = -1;
        if (registrationRole == Role.DOCTOR) {
            try {
                specializationId = Integer.parseInt(req.getParameter("specialization_id"));
                LOGGER.trace("requested param specialization -> {}", specializationId);
            } catch (NumberFormatException e) {
                LOGGER.info(e.getMessage());
                Validator.setErrorMessage(session, "specialization should not be empty", LOGGER, forward);
                return forward;
            }
        }

        LocalDate birthday = null;
        if (registrationRole == Role.PATIENT) {
            try {
                birthday = LocalDate.parse(req.getParameter("birthday"));
                LOGGER.trace("requested param birthday -> {}", birthday);
            } catch (Exception e) {
                LOGGER.info(e.getMessage());
                Validator.setErrorMessage(session, "birthday is not valid", LOGGER, forward);
                return forward;
            }
        }

        Account account = (Account) session.getAttribute("account");
        AccountService accountService = new AccountServiceImpl();
        boolean successful = accountService.registerAccount(login, account.getId(), registrationRole, nameEN, surnameEN,
                nameUA, surnameUA, email, specializationId, birthday);

        if (!successful){
            Validator.setErrorMessage(session, "failed to register account", LOGGER);
        }

        LOGGER.debug("execute finishes, forward -> {}", forward);
        return forward;
    }
}
