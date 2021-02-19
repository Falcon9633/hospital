package ua.com.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.constant.Path;
import ua.com.entity.Locale;
import ua.com.util.Validator;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LocaleFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(LocaleFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //NO-OP
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        LOGGER.debug("doFilter starts");
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpSession session = httpReq.getSession(false);
        if (session == null) {
            Cookie[] cookies = httpReq.getCookies();
            Cookie cookie = null;
            if (cookies != null){
                for (Cookie c : cookies) {
                    if (Path.LOCALE_COOKIE.equals(c.getName())){
                        cookie = c;
                    }
                }
            }

            if (cookie != null){
                int localeId = 0;
                try {
                    localeId = Integer.parseInt(cookie.getValue());
                } catch (NumberFormatException e){
                    LOGGER.error(e.getMessage(), e.getCause());
                }
                localeId = Validator.checkLocaleId(localeId);

                Locale locale = Locale.getLocale(localeId);
                session = httpReq.getSession();
                session.setAttribute("locale", locale);
            }
        }

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        //NO-OP
    }
}
