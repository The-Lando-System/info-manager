package com.mattvoget.infomanager.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mattvoget.sarlacc.models.Token;
import com.mattvoget.sarlacc.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class SarlaccUserService {

    private Logger log = LoggerFactory.getLogger(SarlaccUserService.class);

    public static final String TOKEN_NAME = "x-access-token";

    @Value("${auth.url.token}")
    private String authUrlToken;

    @Value("${auth.url.user}")
    private String authUrlUser;

    @Value("${auth.client.id}")
    private String authClientId;

    @Value("${auth.client.password}")
    private String authClientPassword;


    private LoadingCache<String,User> userCache = CacheBuilder.newBuilder()
            .maximumSize(50)
            .expireAfterAccess(30, TimeUnit.MINUTES)
            .build(
                new CacheLoader<String, User>() {
                    public User load(String accessToken) throws Exception {

                        log.info("Calling the Sarlacc to find user with access token: " + accessToken);

                        Token token = new Token();
                        token.setAccessToken(accessToken);

                        SarlaccClient client = new SarlaccClient(authClientId,authClientPassword,authUrlToken,authUrlUser);
                        User user = null;
                        try {
                            user = client.getUserDetails(token);
                        } catch (Exception e){
                            log.error("Failed to get a user from the Sarlacc!");
                            throw new RuntimeException(e);
                        }

                        log.info(String.format("Found user %s from Sarlacc! Adding to cache.",user.getUsername()));

                        return user;
                    }
                }
            );

    public User getUser(String accessToken){

        log.info("Attempting to find user with access token: " + accessToken);

        User user = null;

        try {
            user = userCache.get(accessToken);
        } catch (Exception e){
            log.info("Failed to get a user!");
            throw new SarlaccUserException("A bad access token was provided");
        }

        log.info("Returning user with username: " + user.getUsername());

        return user;
    }

    public long getUserCacheSize(){
        return userCache.size();
    }

}
