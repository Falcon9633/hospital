package ua.com.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.constant.Path;
import ua.com.dao.AccountDao;
import ua.com.dao.impl.AccountDaoImpl;
import ua.com.entity.Account;
import ua.com.entity.Role;
import ua.com.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        LOGGER.trace("requested param login -> {}", login);

        String forward = Path.REDIRECT + Path.LOGIN_PAGE;

//        if (Validator.isLoginNotValid(login)) {
//            setError(session, "login is not valid");
//            LOGGER.debug("forward to -> {}", forward);
//            return forward;
//        }
//
//        if (Validator.isPasswordNotValid(password)){
//            setError(session, "password is not valid");
//            LOGGER.debug("forward to -> {}", forward);
//            return forward;
//        }

        AccountDao accountDao = new AccountDaoImpl();
        Account account = accountDao.findByLogin(login);
        LOGGER.trace("Found in db account -> {}", account);

        if (account == null || !account.getPassword().equals(password)) {
            setError(session, "incorrect login/password");
            LOGGER.debug("forward to -> {}", forward);
            return forward;
        }

        if (account.isLocked()) {
            setError(session, "account is locked");
        } else {
            Role role = Role.getRole(account.getRoleId());
            LOGGER.trace("account role -> {}", role);

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
            session.setAttribute("role", role);
        }

        LOGGER.debug("execute finishes, forward -> {}", forward);
        return forward;
    }

    private void setError(HttpSession session, String errorMessage) {
        session.setAttribute("errorMessage", errorMessage);
        LOGGER.info("error message -> {}", errorMessage);
    }

}