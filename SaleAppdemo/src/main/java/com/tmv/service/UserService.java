/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmv.service;

import com.tmv.pojos.User;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;
/**
 *
 * @author PC
 */
public interface UserService extends UserDetailsService{
    User getUserByID(int userId);
    boolean addUser(User user);
    List<User> getUser(String username); 
}
