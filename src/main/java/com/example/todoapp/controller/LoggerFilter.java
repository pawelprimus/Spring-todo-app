package com.example.todoapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
class LoggerFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(LoggerFilter.class);

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            var httpRequest = (HttpServletRequest) request;
            logger.info("[doFilter]" + httpRequest.getMethod() + " " + httpRequest.getRequestURI()); // jak ktos do nas uderza to dostaje dany filtr

        }
        chain.doFilter(request, response);
        logger.info("[doFilter] 2");
    }

    //interceptor to springowy filter do zaczepiania webowych servletow


    /*@Override
    public int getOrder() {
        return Ordered.;
    }*/
}
