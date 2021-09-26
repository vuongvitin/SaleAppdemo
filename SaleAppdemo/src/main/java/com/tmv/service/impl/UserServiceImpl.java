/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmv.service.impl;

import com.tmv.pojos.User;
import com.tmv.repository.UserRepository;
import com.tmv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository UserRepository;

    @Override
    public User getUserByID(int userId) {
        return this.UserRepository.getUserById(userId);
    }
    
}
