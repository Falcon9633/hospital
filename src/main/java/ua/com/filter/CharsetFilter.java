package ua.com.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;


/**
 * Handles all requests and responses as UTF-8 encoded.
 *
 * @author Orest Dmyterko
 */
public class CharsetFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(CharsetFilter.class);
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
        if (encoding == null) {
            encoding = "UTF-8";
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.debug("doFilter starts");
        if (request.getCharacterEncoding() == null){
            LOGGER.trace("Request encoding = null, set encoding -> " + encoding);
            request.setCharacterEncoding(encoding);
        }

        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // NO-OP
    }
}
