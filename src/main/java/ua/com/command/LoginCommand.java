package ua.com.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.constant.Path;
import ua.com.dao.AccountDao;
import ua.com.dao.impl.AccountDaoImpl;
import ua.com.entity.Account;
import ua.com.entity.Role;
import ua.com.exception.DBException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Validates requested parameters and checks credentials in the DB, then forwards to an appropriate page.
 *
 * @author Orest Dmyterko
 */
public class LoginCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("LoginCommand.execute starts");
        HttpSession session = req.getSession();

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        LOGGER.trace("requested param login -> {}", login);

        String errorMessage = null;
        String forward = Path.LOGIN_PAGE;

//        validators for login and password

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            errorMessage = "empty login or password";
            req.setAttribute("error", true);
            req.setAttribute("errorMessage", errorMessage);
            LOGGER.info("error message -> {}", errorMessage);
            LOGGER.debug("forward to -> {}", forward);
            return forward;
        }

//        -----------------------------------

        AccountDao accountDao = new AccountDaoImpl();
        Account account = null;
        try {
            account = accountDao.findByLogin(login);
        } catch (DBException e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.trace("Found in db account -> {}", account);

        if (account == null || !account.getPassword().equals(password)) {
            errorMessage = "не вірний логін або пароль";
            LOGGER.info("error message -> {}", errorMessage);
        } else {
            Role role = Role.getRole(account.getRoleId());
            LOGGER.trace("account role -> {}", role);

            if (role == Role.ADMIN) {
                forward = Path.ADMIN_PAGE;
            }

            if (role == Role.DOCTOR) {
                forward = Path.DOCTOR_PAGE;
            }

            if (role == Role.NURSE) {
                forward = Path.NURSE_PAGE;
            }

            if (role == Role.PATIENT) {
                forward = Path.PATIENT_PAGE;
            }

            session.setAttribute("account", account);
            session.setAttribute("role", role);
        }

        LOGGER.debug("LoginCommand.execute finishes, forward -> {}", forward);
        return forward;
    }
}
