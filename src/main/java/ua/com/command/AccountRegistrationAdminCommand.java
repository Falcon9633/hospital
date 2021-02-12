package ua.com.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.constant.Path;
import ua.com.entity.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AccountRegistrationAdminCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AccountRegistrationAdminCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");

        return Path.ACCOUNT_REGISTRATION_ADMIN_PAGE;
    }
}