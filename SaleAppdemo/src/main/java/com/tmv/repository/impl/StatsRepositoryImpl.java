/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmv.repository.impl;

import com.tmv.pojos.Category;
import com.tmv.pojos.OrderDetail;
import com.tmv.pojos.Product;
import com.tmv.pojos.SaleOrder;
import com.tmv.repository.StatsRepository;
import java.util.ArrayList;
import java.util.Date;
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
public class StatsRepositoryImpl implements StatsRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public List<Object> cateStats() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = builder.createQuery(Object[].class);
        
        Root rootP = q.from(Product.class);
        Root rootC = q.from(Category.class);
        
        q.where(builder.equal(rootP.get("category"), rootC.get("id")));
        
        q.multiselect(rootC.get("id"), rootC.get("name"),
                builder.count(rootP.get("id")));
        
        q.groupBy(rootC.get("id"));
        
        Query query = session.createQuery(q);
        
        return query.getResultList();
    }

    @Override
    public List<Object> productStats(String kw, Date fromDate, Date toDate) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = builder.createQuery(Object[].class);
        
        Root rootP = q.from(Product.class);
        Root rootO = q.from(SaleOrder.class);
        Root rootD = q.from(OrderDetail.class);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(rootD.get("product"), rootP.get("id")));
        predicates.add(builder.equal(rootD.get("saleOrder"), rootO.get("id")));
        
        q.multiselect(rootP.get("id"), rootP.get("name"),
                builder.sum(builder.prod(rootD.get("unitPrice"), rootD.get("num"))));
        
        
        
        if(kw != null && !kw.isEmpty()){
            predicates.add(builder.like(rootP.get("name").as(String.class), 
                    String.format("%%%s%%", kw)));
        }
        
        if(fromDate != null)
            predicates.add(builder.greaterThanOrEqualTo(rootO.get("giaTour"), fromDate));
        if(toDate != null)
           predicates.add(builder.lessThanOrEqualTo(rootO.get("giaTour"), toDate));
        
        
        
        q.where(predicates.toArray(new Predicate[] {}));
        
        q.groupBy(rootP.get("id"));
        q.orderBy(builder.asc(rootP.get("id")));
        
        Query query = session.createQuery(q);
        
        return query.getResultList();
    }

    @Override
    public List<Object> productMonthStats(String kw, Date fromDate, Date toDate) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = builder.createQuery(Object[].class);
        
        Root rootP = q.from(Product.class);
        Root rootO = q.from(SaleOrder.class);
        Root rootD = q.from(OrderDetail.class);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(rootD.get("product"), rootP.get("id")));
        predicates.add(builder.equal(rootD.get("saleOrder"), rootO.get("id")));
        
        q.multiselect(builder.function("MONTH", Integer.class, rootO.get("createdDate")),
                builder.function("YEAR", Integer.class, rootO.get("createdDate")),
                builder.sum(builder.prod(rootD.get("unitPrice"), rootD.get("num"))));
        
        
        
        if(kw != null && !kw.isEmpty()){
            predicates.add(builder.like(rootP.get("name").as(String.class), 
                    String.format("%%%s%%", kw)));
        }
        
        if(fromDate != null)
            predicates.add(builder.greaterThanOrEqualTo(rootO.get("createdDate"), fromDate));
        if(toDate != null)
           predicates.add(builder.lessThanOrEqualTo(rootO.get("createdDate"), toDate));
        
        q.where(predicates.toArray(new Predicate[] {}));
        
        q.groupBy(builder.function("MONTH", Integer.class, rootO.get("createdDate")),
                builder.function("YEAR", Integer.class, rootO.get("createdDate")));
        q.orderBy(builder.asc(rootP.get("id")));
        
        Query query = session.createQuery(q);
        
        return query.getResultList();
    }
    
}
