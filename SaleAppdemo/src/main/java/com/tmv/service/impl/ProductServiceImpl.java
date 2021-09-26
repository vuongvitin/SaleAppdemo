/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmv.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tmv.pojos.Product;
import com.tmv.repository.ProductRepository;
import com.tmv.service.ProductService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
//    @Autowired
//    private Cloudinary cloudinary;
    
    

    @Override
    public List<Product> getProducts(String kw, int page) {
        return this.productRepository.getProducts(kw, page);
    }

    @Override
    public long countProduct() {
        return this.productRepository.countProudct();
    }

    @Override
    public boolean addOrUpdate(Product product) {
//       try {
//                Map r = cloudinary.uploader().upload(product.getFile().getBytes(),
//                ObjectUtils.asMap("resource_type", "auto"));
//                
//                String img = (String) r.get("secure_url");
//                product.setImage(img);
//                
//                return this.productRepository.addorUpdate(product);
//            } catch (IOException ex) {
//                    System.out.println("==ADD PRODUCT==" + ex.getMessage());
//            }
       
       return false;
    }

    @Override
    public Product getProductById(int id) {
        return this.productRepository.getProductById(id);
    }

    @Override
    public Object getTrendProduct(int num) {
        return this.productRepository.getTrendProduct(num);
    }

    @Override
    public Object getMostDiscuss(int num) {
        return this.productRepository.getMostDiscuss(num);
    }
    
}