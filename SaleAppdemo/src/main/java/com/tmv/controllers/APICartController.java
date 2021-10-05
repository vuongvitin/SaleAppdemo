/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmv.controllers;

import com.tmv.pojos.Cart;
import com.tmv.service.OrderService;
import com.tmv.utils.Utils;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
 
/**
 *
 * @author PC
 */
@RestController
@ControllerAdvice
public class APICartController {
    @Autowired
    private OrderService orderService;
    
    @PostMapping("/api/cart")
    public int addToCart(@RequestBody Cart params, HttpSession session){
        
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        if(cart == null)
            cart = new HashMap<>();
        
        int pdId = params.getProductId();
        if(cart.containsKey(pdId) == true){//san pham da co trong gio
            Cart c = cart.get(pdId);
            c.setQuantity(c.getQuantity() + 1);
        }else{// san pham chua co trong gio
            cart.put(pdId, params);
        }
        
        session.setAttribute("cart", cart);
        
        return Utils.countCart(cart);
    }
    
    @PutMapping("/api/cart")
//    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String, String>> updateCartItem(@RequestBody Cart params, HttpSession session){
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        if(cart == null)
            cart = new HashMap<>();
        
        int pdId = params.getProductId();
        if(cart.containsKey(pdId) == true){//san pham da co trong gio
            Cart c = cart.get(pdId);
            c.setQuantity(params.getQuantity());
        }
        
        
        session.setAttribute("cart", cart);
        
        return new ResponseEntity<>(Utils.cartStats(cart), HttpStatus.OK );
    }
    
    @DeleteMapping("/api/cart/{productId}")
    public ResponseEntity<Map<String, String>> deleteCartItem(@PathVariable(value = "productId") int productId, HttpSession session){
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        if(cart != null && cart.containsKey(productId)){
            cart.remove(productId);
            
            session.setAttribute("Cart", cart);
        }
                
    
        
        return new ResponseEntity<>(Utils.cartStats(cart), HttpStatus.OK );
    }
    
    @PostMapping("/api/pay")
    public HttpStatus pay(HttpSession session){
        if(this.orderService.addReceipt((Map<Integer, Cart>) session.getAttribute("cart")) == true){
            session.removeAttribute("cart");
            return HttpStatus.OK;
        }
            
        
        return HttpStatus.BAD_REQUEST;
    }
    
}
