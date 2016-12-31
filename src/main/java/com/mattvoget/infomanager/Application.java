package com.mattvoget.infomanager;

import com.mattvoget.cryptutils.CryptUtils;
import com.mattvoget.infomanager.config.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@Import({WebSecurityConfig.class})
public class Application {

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Value("${encryption.password}")
    private String encryptionPassword;

    @Bean
    public CryptUtils getCryptUtils(){
        return new CryptUtils(encryptionPassword);
    }

}
