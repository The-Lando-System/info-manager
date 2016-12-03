package com.mattvoget.infomanager.config;

import com.mattvoget.sarlacc.client.SarlaccClient;
import com.mattvoget.sarlacc.client.models.Token;
import com.mattvoget.sarlacc.client.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private Logger log = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if (SecurityContextHolder.getContext().getAuthentication() != null){
            return SecurityContextHolder.getContext().getAuthentication();
        }

        log.info("In the custom auth provider");
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();



        Token token = SarlaccClient.getUserToken(name,password);
        User user = SarlaccClient.getUserDetails(token);

        log.info("Got user: " + user.getUsername());

        return new UsernamePasswordAuthenticationToken(user, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
