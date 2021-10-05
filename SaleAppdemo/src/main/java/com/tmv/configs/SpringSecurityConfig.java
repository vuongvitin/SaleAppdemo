/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmv.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tmv.handlers.LoginSuccessHandlers;
import com.tmv.handlers.LogoutHandlers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author PC
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.tmv.repository",
    "com.tmv.service"   
})
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationSuccessHandler loginSuccessHandler;
    @Autowired
    private LogoutSuccessHandler logoutHandler;
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
    }
    
    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary
            = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dwsyse8jk",
            "api_key", "253437757473245",
            "api_secret", "P5nKeGfIL5dlvOqaaNxOzNTzijY",
            "secure", true));
        
        return cloudinary;
    }
    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler(){
        return new LoginSuccessHandlers();
    }
    
    @Bean
    public LogoutSuccessHandler logoutHandler(){
        return new LogoutHandlers();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
    
    @Override
    protected void configure(HttpSecurity http)
        throws Exception {
        http.formLogin().loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password");
        
        
        http.formLogin().defaultSuccessUrl("/")
                .failureUrl("/login?error");
        
        http.formLogin().successHandler(this.loginSuccessHandler);
        
        http.logout().logoutSuccessHandler(this.logoutHandler);
        
//        http.logout().logoutSuccessUrl("/login");
        
        http.exceptionHandling().accessDeniedPage("/login?accessDenied");
        
         http.authorizeRequests().antMatchers("/").permitAll()
                    .antMatchers("/admin/**")
                    .access("hasRole('ROLE_ADMIN')");
//        .antMatchers("/**/pay")
//        .access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")

        
        http.csrf().disable();
        

       
    }
    
    
    
    
}
