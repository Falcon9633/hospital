package ua.com.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Initializes and holds all commands. Invokes concrete command.
 *
 * @author Orest Dmyterko
 */
public class Invoker {
    private static final Logger LOGGER = LogManager.getLogger(Invoker.class);
    private static final Map<String, Command> commandContainer = new HashMap<>();

    static {
        commandContainer.put("noCommand", new NoCommand());
        commandContainer.put("login", new LoginCommand());

    }

    /**
     * Invokes concrete command, if command does not exist invokes 'noCommand' command.
     *
     * @param commandName command which must be invoked
     * @param req         an HttpServletRequest object that contains the request the client has made of the servlet
     * @param resp        an HttpServletResponse object that contains the response the servlet sends to the client
     * @return The path to which request dispatcher must be forwarded
     */
    public String invoke(String commandName, HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("Invoker.invoke starts");
        if (commandName == null || !commandContainer.containsKey(commandName)) {
            LOGGER.trace("{} not found", commandName);
            return commandContainer.get("noCommand").execute(req, resp);
        }

        return commandContainer.get(commandName).execute(req, resp);
    }
}
