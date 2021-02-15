package ua.com.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.constant.Path;
import ua.com.dao.AccountDao;
import ua.com.dao.AccountDetailsDao;
import ua.com.dao.impl.AccountDaoImpl;
import ua.com.dao.impl.AccountDetailsDaoImpl;
import ua.com.entity.Account;
import ua.com.entity.AccountDetails;
import ua.com.entity.Locale;
import ua.com.entity.Role;
import ua.com.util.PasswordUtil;
import ua.com.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

/**
 * Checks credentials in the DB, then forwards to an appropriate page.
 *
 * @author Orest Dmyterko
 */
public class LoginCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");
        HttpSession session = req.getSession();
        session.removeAttribute("errorMessage");

        Locale locale = (Locale) session.getAttribute("locale");
        if (locale == null) {
            session.setAttribute("locale", Locale.EN);
            locale = Locale.EN;
        }


        String forward = Path.REDIRECT + Path.LOGIN_PAGE;

        String login = req.getParameter("login");
        LOGGER.trace("requested param login -> {}", login);
//        if (Validator.isLoginNotValid(login, session, locale, LOGGER, forward)) {
//            return forward;
//        }

        String password = req.getParameter("password");
//        if (Validator.isPasswordNotValid(password, session, locale, LOGGER, forward)){
//            return forward;
//        }

        AccountDao accountDao = new AccountDaoImpl();
        Account account = accountDao.findByLogin(login);
        LOGGER.trace("Found in db account -> {}", account);

        try {
            if (account == null || !PasswordUtil.comparePasswords(password, account.getPassword())) {
                Validator.setErrorMessage(session,
                        locale.getMessage("validation.error.incorrect_login_password"), LOGGER, forward);
                return forward;
            }
        } catch (NoSuchAlgorithmException e) {
            Validator.setErrorMessage(session,
                    locale.getMessage("validation.error.unable_to_login"), LOGGER, forward);
            return forward;
        }

        if (account.isLocked()) {
            Validator.setErrorMessage(session, locale.getMessage("validation.error.account_is_locked"), LOGGER);
        } else {
            Role role = Role.getRole(account.getRoleId());
            LOGGER.trace("account role -> {}", role);

            AccountDetailsDao accountDetailsDao = new AccountDetailsDaoImpl();
            AccountDetails accountDetails = accountDetailsDao.findById(account.getId());
            LOGGER.trace("found in db accountDetails -> {}", accountDetails);

            if (role == Role.ADMIN) {
                forward = Path.REDIRECT + Path.ACCOUNT_REGISTRATION_PATIENT_COMMAND;
            }

            if (role == Role.DOCTOR) {
                forward = Path.REDIRECT;
            }

            if (role == Role.NURSE) {
                forward = Path.REDIRECT;
            }

            if (role == Role.PATIENT) {
                forward = Path.REDIRECT;
            }

            session.setAttribute("account", account);
            session.setAttribute("accountDetails", accountDetails);
            session.setAttribute("role", role);
            session.setAttribute("locale", Locale.getLocale(account.getLocaleId()));
        }

        LOGGER.debug("execute finishes, forward -> {}", forward);
        return forward;
    }

}