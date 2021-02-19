package ua.com.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.constant.Path;
import ua.com.dao.AccountDao;
import ua.com.dao.impl.DaoFactory;
import ua.com.entity.Account;
import ua.com.entity.Locale;
import ua.com.util.Validator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ChangeLocaleCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute  starts");
        HttpSession session = req.getSession();
        String redirect = req.getParameter("redirect");
        String forward = Path.REDIRECT + "controller?" + redirect;

        int localeId = 0;
        try {
            localeId = Integer.parseInt(req.getParameter("localeId"));
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }
        localeId = Validator.checkLocaleId(localeId);
        LOGGER.trace("requested param localeId -> {}", localeId);
        Locale locale = Locale.getLocale(localeId);
        session.setAttribute("locale", locale);

        Account account = (Account) session.getAttribute("account");
        if (account != null) {
            account.setLocaleId(localeId);
            AccountDao accountDao = DaoFactory.getAccountDao();
            accountDao.update(account);
        }

        Cookie cookie = new Cookie("locale", String.valueOf(localeId));
        cookie.setMaxAge(60 * 60 * 24 * 365);
        resp.addCookie(cookie);

        LOGGER.debug("execute  finishes");
        return forward;
    }
}
