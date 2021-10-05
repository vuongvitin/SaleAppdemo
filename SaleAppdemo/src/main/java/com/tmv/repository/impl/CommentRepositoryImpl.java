/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmv.repository.impl;

import com.tmv.pojos.Comment;
import com.tmv.pojos.Product;
import com.tmv.repository.CommentRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
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
public class CommentRepositoryImpl implements CommentRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Override
    public Comment addComment(Comment c) {
        Session session = sessionFactory.getObject().getCurrentSession();
        try{
            session.save(c);
            return c;
        }catch(HibernateException ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Long countCommentByProductId(int productId) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("Select Count(*) From Comment Where product.id=:id");
        
        q.setParameter("id", productId);
        
        return (Long) q.getSingleResult();
    }

    @Override
    public List<Comment> getCommentByProductId(int productId, int page) {
             Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Comment> query = builder.createQuery(Comment.class);
        Root root = query.from(Comment.class);

        
        query = query.where(builder.equal(root.get("product"), productId));
        query = query.orderBy(builder.desc(root.get("id")));
        
        Query q = session.createQuery(query);
        
        int max = 3;
        int first = (page -1)*max;
        q.setFirstResult(first);       
        q.setMaxResults(max);

        
        return q.getResultList();  
    }

 


    
}
