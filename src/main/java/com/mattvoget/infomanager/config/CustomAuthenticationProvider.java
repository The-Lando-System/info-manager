package com.mattvoget.infomanager.config;

import com.mattvoget.sarlacc.client.SarlaccClient;
import com.mattvoget.sarlacc.models.Token;
import com.mattvoget.sarlacc.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private Logger log = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null){
            User user = (User) auth.getPrincipal();
            log.debug("User still loaded in security context: " + user.getUsername());
            return auth;
        }

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        log.info(String.format("Calling Sarlacc to authenticate user with creds: username=%s, password=%s",name,password));

        SarlaccClient client = new SarlaccClient("acme","acmesecret","http://localhost:8080/oauth/token","http://localhost:8080/user-details");

        Token token = client.getUserToken(name,password,"password");
        User user = client.getUserDetails(token);

        log.info("Successfully retrieved user from the Sarlacc: " + user.getUsername());

        user.setToken(token);

        return new UsernamePasswordAuthenticationToken(user, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
