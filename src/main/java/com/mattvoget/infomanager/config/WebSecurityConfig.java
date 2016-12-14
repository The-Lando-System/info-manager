package com.mattvoget.infomanager.config;

import com.mattvoget.sarlacc.client.authentication.AdminFilter;
import com.mattvoget.sarlacc.client.authentication.SarlaccAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;

@ComponentScan({"com.mattvoget.sarlacc.client"})
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SarlaccAuthProvider authProvider;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Bean
    public FilterRegistrationBean authFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("adminFilter");
        AdminFilter adminFilter = new AdminFilter();
        registrationBean.setFilter(adminFilter);
        registrationBean.addUrlPatterns("/note/*");
        registrationBean.setOrder(2);
        return registrationBean;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests().anyRequest().authenticated()
            .and()
            .httpBasic();
    }

}
