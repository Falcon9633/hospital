package ua.com.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Main interface of the Command pattern.
 *
 * @author Orest Dmyterko
 */
public interface Command {
    /**
     * Executes command functionality.
     *
     * @param req  an HttpServletRequest object that contains the request the client has made of the servlet
     * @param resp an HttpServletResponse object that contains the response the servlet sends to the client
     * @return The path to which request dispatcher must be forwarded
     */
    String execute(HttpServletRequest req, HttpServletResponse resp);
}
