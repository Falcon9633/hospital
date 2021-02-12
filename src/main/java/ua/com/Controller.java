package ua.com;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.command.Invoker;
import ua.com.constant.Path;
import ua.com.entity.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Front controller servlet.
 *
 * @author Orest Dmyterko
 */
public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    /**
     * Handles commands from view.
     *
     * @param req  an HttpServletRequest object that contains the request the client has made of the servlet
     * @param resp an HttpServletResponse object that contains the response the servlet sends to the client
     */
    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("process starts");

        String commandName = req.getParameter("command");
        LOGGER.trace("command name -> {}", commandName);

        Invoker invoker = new Invoker();
        String path = invoker.invoke(commandName, req, resp);

        LOGGER.debug("process finishes, path -> {}", path);

        dispatch(req, resp, path);
    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
        if (path.startsWith(Path.REDIRECT)){
            String redirect = path.substring(Path.REDIRECT.length());
            resp.sendRedirect(redirect);
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
            requestDispatcher.forward(req, resp);
        }
    }
}
