/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmv.utils;

import com.tmv.pojos.Cart;
import java.util.Map;

/**
 *
 * @author PC
 */
public class Utils {
    public static int countCart(Map<Integer, Cart> cart){
        int q = 0;
        if(cart != null)
            for(Cart c: cart.values())
                q += c.getQuantity();
        
        return q;
    }
}
