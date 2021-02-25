package ua.com.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.constant.Path;
import ua.com.dao.AccountDao;
import ua.com.dao.impl.DaoFactory;
import ua.com.entity.Account;
import ua.com.entity.Locale;
import ua.com.util.PasswordUtil;
import ua.com.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

public class ChangePasswordCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ChangePasswordCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");
        HttpSession session = req.getSession();
        Locale locale = (Locale) session.getAttribute("locale");

        String forward = Path.SETTINGS_PAGE;

        String newPassword = req.getParameter("new_password");
        String confirmedPassword = req.getParameter("confirmed_password");
        String oldPassword = req.getParameter("old_password");
        if (Validator.isPasswordNotValid(newPassword, session, locale, LOGGER, forward) ||
            Validator.isPasswordNotValid(confirmedPassword, session, locale, LOGGER, forward) ||
            Validator.isPasswordNotValid(oldPassword, session, locale, LOGGER, forward)) {
            return forward;
        }

        if (!newPassword.equals(confirmedPassword)){
            Validator.setErrorMessage(session, locale.getMessage("change_password_command.error.passwords_not_match"),
                    LOGGER, forward);
            return forward;
        }

        Account account = (Account) session.getAttribute("account");
        try {
            if (!PasswordUtil.comparePasswords(oldPassword, account.getPassword())){
                Validator.setErrorMessage(session, locale.getMessage("change_password_command.error.incorrect_old_password"),
                        LOGGER, forward);
                return forward;
            }
            account.setPassword(PasswordUtil.encryptPassword(newPassword));
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage(), e.getCause());
            return forward;
        }

        AccountDao accountDao = DaoFactory.getAccountDao();
        accountDao.update(account);

        forward = Path.REDIRECT + Path.SETTINGS_COMMAND;
        session.removeAttribute("errorMessage");

        LOGGER.debug("execute finishes");
        return forward;
    }
}
