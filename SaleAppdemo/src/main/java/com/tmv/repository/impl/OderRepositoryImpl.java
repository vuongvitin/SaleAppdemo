/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmv.repository.impl;

import com.tmv.pojos.Cart;
import com.tmv.pojos.OrderDetail;
import com.tmv.pojos.SaleOrder;
import com.tmv.repository.OrderRepository;
import com.tmv.repository.ProductRepository;
import com.tmv.repository.UserRepository;
import com.tmv.utils.Utils;
import java.util.Date;
import java.util.Map;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author PC
 */
@Repository
public class OderRepositoryImpl implements OrderRepository{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addReceipt(Map<Integer, Cart> cart) {
        try{
            Session session = this.sessionFactory.getObject().getCurrentSession();
        SaleOrder order = new SaleOrder();
        order.setUser(this.userRepository.getUserById(6));
        order.setCreatedDate(new Date());
        
        Map<String, String> stats = Utils.cartStats(cart);
        
        order.setAmount(Long.parseLong(stats.get("amount")));
        
        session.save(order);
        
        for(Cart c: cart.values()){
            OrderDetail d = new OrderDetail();
            d.setSaleOrder(order);
            d.setProduct(this.productRepository.getProductById(c.getProductId()));
            d.setUnitPrice(c.getPrice());
            d.setNum(c.getQuantity());
            
            session.save(d);
        }
        
            return true;
        }catch(HibernateException ex){
            ex.printStackTrace();
        }
        
        
        
        return false;
    }
    
}
