package com.mattvoget.infomanager;

import com.mattvoget.cryptutils.CryptUtils;
import com.mattvoget.sarlacc.client.SarlaccAdminFilter;
import com.mattvoget.sarlacc.client.SarlaccUserFilter;
import com.mattvoget.sarlacc.client.SarlaccUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

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
    @Value("${auth.url.token}")
    private String authUrlToken;

    @Value("${auth.url.user}")
    private String authUrlUser;

    @Value("${auth.client.id}")
    private String authClientId;

    @Value("${auth.client.password}")
    private String authClientPassword;

    @Bean
    public SarlaccUserService sarlaccUserService(){
        return new SarlaccUserService(authUrlToken,authUrlUser,authClientId,authClientPassword);
    }

    @Bean
    @Autowired
    public SarlaccUserFilter sarlaccUserFilter(SarlaccUserService sarlaccUserService){
        return new SarlaccUserFilter(sarlaccUserService);
    }

    @Bean
    @Autowired
    public FilterRegistrationBean adminFilterBean(SarlaccUserService sarlaccUserService) {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("adminFilter");
        SarlaccAdminFilter adminFilter = new SarlaccAdminFilter(sarlaccUserService);
        registrationBean.setFilter(adminFilter);
        registrationBean.addUrlPatterns("/note/*");
        registrationBean.setOrder(2);
        return registrationBean;
    }

}
