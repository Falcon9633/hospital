package ua.com.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.constant.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsersDoctorsCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(UsersDoctorsCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");

        return Path.USERS_DOCTORS_PAGE;
    }
}
