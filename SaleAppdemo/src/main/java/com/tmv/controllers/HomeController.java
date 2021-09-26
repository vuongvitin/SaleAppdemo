/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmv.controllers;



import com.tmv.pojos.Category;
import com.tmv.service.CategoryService;
import com.tmv.service.ProductService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author PC
 */
@Controller
@ControllerAdvice
public class HomeController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    
    
    @ModelAttribute
    public void commmonAttrs(Model model){
        model.addAttribute("categories", this.categoryService.getCategories());
    }
    
    @RequestMapping("/")
    public String index(Model model, @RequestParam(required = false) Map<String, String> params){
        
        String kw = params.getOrDefault("kw", null);
        int page = Integer.parseInt(params.getOrDefault("page", "1"));
        
        String cateId = params.get("CateId");
        if(cateId == null){
            model.addAttribute("products", this.productService.getProducts(kw, page));        
        }else{
            Category c = this.categoryService.getCategoryById(Integer.parseInt(cateId));
            model.addAttribute("products", c.getProductCollection());
        }
        
        model.addAttribute("productCounter", this.productService.countProduct());
        
        model.addAttribute("trendProducts", this.productService.getTrendProduct(6));
        model.addAttribute("mostDisProducts", this.productService.getMostDiscuss(6));
       
        
        return "index";
    }
}
