/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmv.controllers;

import com.tmv.pojos.Cart;
import com.tmv.utils.Utils;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
 
/**
 *
 * @author PC
 */
@RestController
public class APICartController {
    @PostMapping("/api/cart")
    public int addToCart(@RequestBody Cart params, HttpSession session){
        
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        if(cart == null)
            cart = new HashMap<>();
        int productId = params.getProductId();
        if(cart.containsKey(productId ) == true){//san pham da co trong gio
            Cart c = cart.get(productId);
            c.setQuantity(c.getProductId() + 1);
        }else{// san pham chua co trong gio
            cart.put(productId, params);
        }
        
        session.setAttribute("cart", cart);
        
        return Utils.countCart(cart);
    }
}
