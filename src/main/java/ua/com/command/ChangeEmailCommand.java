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

public class ChangeEmailCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ChangeEmailCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");
        HttpSession session = req.getSession();
        Locale locale = (Locale) session.getAttribute("locale");
        Account account = (Account) session.getAttribute("account");

        String forward = Path.SETTINGS_PAGE;

        String email = req.getParameter("email");
        LOGGER.trace("requested param email -> {}", email);
        if (Validator.isEmailNotValid(email, session, locale, LOGGER, forward)){
            return forward;
        }

        account.setEmail(email);
        AccountDao accountDao = DaoFactory.getAccountDao();
        accountDao.update(account);

        forward = Path.REDIRECT + Path.SETTINGS_COMMAND;
        session.removeAttribute("errorMessage");

        LOGGER.debug("execute finishes");
        return forward;
    }
}
