/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmv.handlers;

import com.tmv.pojos.User;
import com.tmv.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author PC
 */
public class LoginSuccessHandlers implements AuthenticationSuccessHandler{
    @Autowired
    private UserService userDetailsService;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse respone, Authentication a) throws IOException, ServletException {
        User user = this.userDetailsService.getUser(a.getName()).get(0);
        request.getSession().setAttribute("currentUser", user);
        
        respone.sendRedirect("/TourApp");
    }

    
    
}
