/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmv.repository.impl;


import com.tmv.pojos.Comment;
import com.tmv.pojos.OrderDetail;
import com.tmv.pojos.Product;
import com.tmv.repository.ProductRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
public class ProductRepositoryImpl implements ProductRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public List<Product> getProducts(String kw, int page) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root root = query.from(Product.class);
        query = query.select(root);
        
        if( kw != null){
            Predicate p = builder.like(root.get("name").as(String.class), 
                    String.format("%%%s%%", kw));
            query = query.where(p);
        }
        
        query = query.orderBy(builder.desc(root.get("id")));
        
        Query q = session.createQuery(query);
        
        int max = 9;
        int first = (page -1)*max;
        q.setFirstResult(first);       
        q.setMaxResults(max);

        
        return q.getResultList();   
    }

    @Override
    public boolean addorUpdate(Product prdct) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try{
            session.save(prdct);
            return true;
        }catch (Exception ex){
            System.err.println("== ADD PRODUCT ERRPR ==="+ ex.getMessage());
            ex.printStackTrace();
        }
        
        return false;        
    }

    @Override
    public long countProudct() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("Select Count(*) From Product");
        
        //Where product.id=:id
        
        return Long.parseLong(q.getSingleResult().toString());
    }

    @Override
    public Product getProductById(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        return session.get(Product.class, id);
    }


    @Override
    public List<Object> getTrendProduct(int num) {
        Session session = this.sessionFactory.getObject().getCurrentSession();

        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        //Join 2 root
        Root<Product> pRoot = q.from(Product.class);
        Root<OrderDetail> oRoot = q.from(OrderDetail.class);
        
        //ket bang
        q.where(b.equal(oRoot.get("product"), pRoot.get("id")));


       //truy van
       
       q.multiselect(pRoot.get("id"), pRoot.get("name"), pRoot.get("price"),  pRoot.get("image"),
                    b.count(oRoot.get("product")));

       q.groupBy(pRoot.get("name"));
       
       Query query = session.createQuery(q);
       query.setMaxResults(num);

       return query.getResultList();
    }

    @Override
    public List<Object> getMostDiscuss(int num) {
        Session session = this.sessionFactory.getObject().getCurrentSession();

        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        //Join 2 root
        Root<Product> pRoot = q.from(Product.class);
        Root<Comment> cRoot = q.from(Comment.class);
        
        //ket bang
        q.where(b.equal(cRoot.get("product"), pRoot.get("id")));


       //truy van
       
       q.multiselect(pRoot.get("id"), pRoot.get("name"), pRoot.get("price"),  pRoot.get("image"),
                    b.count(cRoot.get("product")));

       q.groupBy(pRoot.get("name"));
       q.orderBy(b.desc(b.count(cRoot.get("product"))), b.desc(pRoot.get("id")));
       
       Query query = session.createQuery(q);
       query.setMaxResults(num);

       return query.getResultList();
    }
    



  

    
}
