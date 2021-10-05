/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmv.controllers;

import com.tmv.pojos.User;
import com.tmv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author PC
 */
@Controller
public class UserController {
    @Autowired
    private UserService userDetailsService;
    
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register";
    }
    @PostMapping("/register")
    public String registers(Model model, @ModelAttribute(value = "user") User user){
        if(user.getPassword().isEmpty()
                || !user.getPassword().equals(user.getConfirmPassword()))
        model.addAttribute("errMsg", "Mat khau khong khop!!!asasd");        
        else{
            if(this.userDetailsService.addUser(user) == true)
                return "redirect:/login";
            model.addAttribute("errMsg", "Da co loi xay ra vui long quay lai sau!!!");  
        }
        return "register";
    }
}
