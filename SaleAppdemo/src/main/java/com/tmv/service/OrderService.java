/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmv.service;

import com.tmv.pojos.Cart;
import java.util.Map;

/**
 *
 * @author PC
 */
public interface OrderService {
    boolean addReceipt(Map<Integer, Cart> cart);
}
