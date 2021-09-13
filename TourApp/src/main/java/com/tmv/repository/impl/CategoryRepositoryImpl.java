/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmv.repository.impl;

import com.tmv.pojos.Category;
import com.tmv.repository.CategoryRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author PC
 */
@Repository
@Transactional
public class CategoryRepositoryImpl implements CategoryRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override    
    public List<Category> getCategories() {
        Session s = sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("From Category");
        return q.getResultList();
    }

    @Override
    public Category getCategoryById(int catId) {
        Session s = sessionFactory.getObject().getCurrentSession();
        return s.get(Category.class, catId);
    }
    
}
