package com.mattvoget.infomanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mattvoget.cryptutils.CryptUtils;
import com.mattvoget.sarlacc.client.SarlaccUserFilter;
import com.mattvoget.sarlacc.client.SarlaccUserService;
import com.mattvoget.sarlacc.client.SarlaccUserServiceImpl;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // For encrypting notes and other sensitive data ==============
    @Value("${encryption.password}")
    private String encryptionPassword;

    @Bean
    public CryptUtils getCryptUtils(){
        return new CryptUtils(encryptionPassword);
    }

    // Use the Sarlacc for user information ==============
    @Value("${sarlacc.url}")
    private String sarlaccUrl;

    @Value("${sarlacc.client.id}")
    private String sarlaccClientId;

    @Value("${sarlacc.client.password}")
    private String sarlaccClientPassword;

    @Bean
    public SarlaccUserService sarlaccUserService(){
        return new SarlaccUserServiceImpl(sarlaccUrl,sarlaccClientId,sarlaccClientPassword);
    }

    @Bean
    @Autowired
    public SarlaccUserFilter sarlaccUserFilter(SarlaccUserServiceImpl sarlaccUserService){
        return new SarlaccUserFilter(sarlaccUserService);
    }

}
