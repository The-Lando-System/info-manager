package com.mattvoget.infomanager.config;

import com.mattvoget.cryptutils.CryptUtils;
import com.mattvoget.infomanager.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.SimpleAutowireCandidateResolver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@ComponentScan({"com.mattvoget.sarlacc.client"})
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {//extends WebSecurityConfigurerAdapter {


//
//    @Autowired
//    private CustomUserDetailsService customUserDetailsService;
//
//    @Bean
//    public BCryptPasswordEncoder encoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserDetailsService);
//    }
//
//    @Bean
//    public FilterRegistrationBean authFilterRegistrationBean() {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setName("adminFilter");
//        AdminFilter adminFilter = new AdminFilter();
//        registrationBean.setFilter(adminFilter);
//        registrationBean.addUrlPatterns("/note/*");
//        registrationBean.setOrder(2);
//        return registrationBean;
//    }
//

//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .csrf().disable()
//            .authorizeRequests().anyRequest().authenticated()
//            .and()
//            .httpBasic();
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
//    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowedOrigins("http://localhost:3000");
//            }
//        };
//    }

}
