package ua.com.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.constant.Path;
import ua.com.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class SecurityFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(SessionFilter.class);

    private static Map<Role, List<String>> accessMap = new HashMap<>();
    private static List<String> commons = new ArrayList<>();
    private static List<String> outOfControl = new ArrayList<>();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.debug("doFilter starts");

        if (accessAllowed(request)) {
            chain.doFilter(request, response);
        } else {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.sendRedirect(Path.LOGIN_PAGE);
        }
    }

    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String commandName = request.getParameter("command");
        if (commandName == null || commandName.isEmpty()){
            return false;
        }

        if (outOfControl.contains(commandName)){
            return true;
        }

        HttpSession session = httpRequest.getSession(false);
        if (session == null){
            return false;
        }

        Role role = (Role) session.getAttribute("role");
        if (role == null){
            return false;
        }

        return accessMap.get(role).contains(commandName)
                || commons.contains(commandName);
    }

    public void init(FilterConfig fConfig) throws ServletException {
        LOGGER.debug("Filter initialization starts");

        // roles
        accessMap.put(Role.ADMIN, asList(fConfig.getInitParameter("admin")));
        accessMap.put(Role.DOCTOR, asList(fConfig.getInitParameter("doctor")));
        accessMap.put(Role.NURSE, asList(fConfig.getInitParameter("nurse")));
        accessMap.put(Role.PATIENT, asList(fConfig.getInitParameter("patient")));
        LOGGER.trace("Access map --> " + accessMap);

        // commons
        commons = asList(fConfig.getInitParameter("common"));
        LOGGER.trace("Common commands --> " + commons);

        // out of control
        outOfControl = asList(fConfig.getInitParameter("out-of-control"));
        LOGGER.trace("Out of control commands --> " + outOfControl);

        LOGGER.debug("Filter initialization finished");
    }

    /**
     * Extracts parameter values from string.
     *
     * @param str parameter values string.
     * @return list of parameter values.
     */
    private List<String> asList(String str) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) list.add(st.nextToken());
        return list;
    }
}
