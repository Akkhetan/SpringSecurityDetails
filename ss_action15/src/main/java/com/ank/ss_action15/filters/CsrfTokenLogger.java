package com.ank.ss_action15.filters;

import jakarta.servlet.*;
import org.springframework.security.web.csrf.CsrfToken;

import java.io.IOException;
import java.util.logging.Logger;

public class CsrfTokenLogger implements Filter {

    private Logger logger =
            Logger.getLogger(CsrfTokenLogger.class.getName());


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        CsrfToken csrfToken = (CsrfToken) servletRequest.getAttribute("_csrf");
        logger.info("CSRF token " + csrfToken.getToken());

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
