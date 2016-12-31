package com.mattvoget.infomanager.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SarlaccUserFilter implements Filter{

    private Logger log = LoggerFactory.getLogger(SarlaccUserFilter.class);

    @Autowired
    private SarlaccUserService sarlaccUserService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Entering the Sarlacc User Filter!");
        log.info("Size of the user cache is " + sarlaccUserService.getUserCacheSize());

        filterChain.doFilter(servletRequest,servletResponse);
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }



    @Override
    public void destroy() {

    }
}
