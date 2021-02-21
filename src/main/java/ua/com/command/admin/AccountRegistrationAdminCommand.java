package ua.com.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.command.Command;
import ua.com.constant.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountRegistrationAdminCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AccountRegistrationAdminCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("execute starts");

        return Path.ACCOUNT_REGISTRATION_ADMIN_PAGE;
    }
}
