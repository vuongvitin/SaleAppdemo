/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmv.controllers;

import com.tmv.pojos.Product;
import com.tmv.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author PC
 */
@Controller
public class ProductController {
    @Autowired
    private ProductService ProductService;
    
    @GetMapping("products/{productId}")
    public String detail(Model model,@PathVariable(value = "productId") int productId){
        model.addAttribute("productId", this.ProductService.getProductById(productId));
        
        
        return "product-detail";
    }
}
