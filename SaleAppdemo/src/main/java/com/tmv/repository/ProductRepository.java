/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmv.repository;


import com.tmv.pojos.Product;
import java.util.List;

/**
 *
 * @author PC
 */
public interface ProductRepository {
    List<Product> getProducts(String kw, int page);
    long countProudct ();
    boolean addorUpdate(Product product);
    Product getProductById(int productId);
//    List<Product> getProductBest();
    List<Object> getTrendProduct(int num);
    List<Object> getMostDiscuss(int num);
}
