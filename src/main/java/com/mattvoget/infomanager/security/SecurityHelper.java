package com.mattvoget.infomanager.security;

import com.mattvoget.sarlacc.client.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("securityHelper")
public class SecurityHelper {

    private Logger log = LoggerFactory.getLogger(SecurityHelper.class);

    public boolean hasAccess(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("Allowing access for user: " + user.getUsername());
        return true;
    }

    private User getUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
