package ua.com.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.constant.Path;
import ua.com.entity.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    public static final Logger LOGGER = LogManager.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");
        HttpSession session = req.getSession(false);
        Locale locale = null;
        if (session != null){
            locale = (Locale) session.getAttribute("locale");
            session.invalidate();
        }

        session = req.getSession();
        session.setAttribute("locale", locale);

        return Path.REDIRECT + Path.LOGIN_PAGE;
    }
}