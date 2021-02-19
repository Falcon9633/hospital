package ua.com.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.constant.Path;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(SessionFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.debug("doFilter starts");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        if (session == null && !"/login.jsp".equals(req.getServletPath())) {
            System.out.println("session is null");
            resp.sendRedirect(Path.LOGIN_PAGE);
        } else {
            chain.doFilter(request, response);
        }
    }
}
