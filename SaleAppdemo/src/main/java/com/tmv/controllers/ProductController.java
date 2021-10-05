/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmv.controllers;

import com.tmv.pojos.Product;
import com.tmv.repository.CommentRepository;
import com.tmv.service.ProductService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author PC
 */
@Controller
public class ProductController {
    @Autowired
    private ProductService ProductService;
    @Autowired
    private CommentRepository commentRepository;
    
    @GetMapping("products/{productId}")
    public String detail(Model model,@PathVariable(value = "productId") int productId,
            @RequestParam(required = false) Map<String, String> params){
        model.addAttribute("productId", this.ProductService.getProductById(productId));
        
        model.addAttribute("countComment", this.commentRepository.countCommentByProductId(productId));
        
        
        int page = Integer.parseInt(params.getOrDefault("page", "1"));
        
        model.addAttribute("comment", this.commentRepository.getCommentByProductId(productId, page));
        
        return "product-detail";
    }
}
