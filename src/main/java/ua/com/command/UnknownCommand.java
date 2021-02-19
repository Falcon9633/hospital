package ua.com.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.constant.Path;
import ua.com.entity.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class UnknownCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(UnknownCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");
        return Path.LOGIN_PAGE;
    }
}
