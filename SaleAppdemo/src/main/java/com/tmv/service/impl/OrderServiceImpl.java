/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmv.service.impl;

import com.tmv.pojos.Cart;
import com.tmv.repository.OrderRepository;
import com.tmv.service.OrderService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    
    
    @Override
    public boolean addReceipt(Map<Integer, Cart> cart) {
        if(cart != null)
            return this.orderRepository.addReceipt(cart);
        else
            return false;
    }
    
}
