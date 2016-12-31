package com.mattvoget.infomanager.services;

import com.mattvoget.sarlacc.models.Token;
import com.mattvoget.sarlacc.models.User;
import org.eclipse.jetty.io.RuntimeIOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

//@Service
public class CustomUserDetailsService {//implements UserDetailsService {

//    private Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);
//
//    @Value("${auth.url.token}")
//    private String authUrlToken;
//
//    @Value("${auth.url.user}")
//    private String authUrlUser;
//
//    @Value("${auth.client.id}")
//    private String authClientId;
//
//    @Value("${auth.client.password}")
//    private String authClientPassword;
//
//    @Autowired
//    private SecurityHelper securityHelper;
//
//    @Override
//    public UserDetails loadUserByUsername(String accessToken) throws UsernameNotFoundException {
//
//        User user = null;
//
//        try {
//            log.info("Session ID on the request is: " + RequestContextHolder.currentRequestAttributes().getSessionId());
//            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            log.info("User already found in security context with username: " + user.getUsername());
//            return user;
//        } catch (Exception e){
//            log.info("No user found in the security context...");
//            log.info("Calling Sarlacc to load user with access token: " + accessToken);
//        }
//
//        Token token = new Token();
//        token.setAccessToken(accessToken);
//
//        SarlaccClient client = new SarlaccClient(authClientId,authClientPassword,authUrlToken,authUrlUser);
//
//        try {
//            user = client.getUserDetails(token);
//        } catch (Exception e){
//            log.error("Error calling the Sarlacc to find user!");
//            throw new RuntimeException(e);
//        }
//        if (user == null){
//            log.error("Failed to find user!");
//        }
//
//        user.setPassword("");
//        user.setToken(token);
//
//        return user;
//    }

}
