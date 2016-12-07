package com.mattvoget.infomanager.security;

import com.mattvoget.sarlacc.models.Role;
import com.mattvoget.sarlacc.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("securityHelper")
public class SecurityHelper {

    private Logger log = LoggerFactory.getLogger(SecurityHelper.class);

    public boolean hasAccess(){
        return getUser() == null ? false : true;
    }

    public boolean isAdmin(){
        return hasAccess() ? (getUser().getRole() == Role.ADMIN) : false;
    }

    private User getUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
