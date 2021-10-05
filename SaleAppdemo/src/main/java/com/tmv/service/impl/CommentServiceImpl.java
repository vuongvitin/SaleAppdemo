/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmv.service.impl;

import com.tmv.pojos.Comment;
import com.tmv.pojos.Product;
import com.tmv.pojos.User;
import com.tmv.repository.CommentRepository;
import com.tmv.repository.ProductRepository;
import com.tmv.repository.UserRepository;
import com.tmv.service.CommentService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public Comment addComment(String content, int productId, User creator) {
        Product product = this.productRepository.getProductById(productId);
        User user = this.userRepository.getUserById(creator.getId());
        Comment c = new Comment();
        c.setContent(content);
        c.setUser(user);
        c.setProduct(product);
        c.setCreatedDate(new Date());
        
        return this.commentRepository.addComment(c);
    }

    @Override
    public Long countCommentByProductId(int productId) {
        return this.commentRepository.countCommentByProductId(productId);
    }

    @Override
    public List<Comment> getCommentByProductId(int productId, int page) {
        return this.commentRepository.getCommentByProductId(productId, page);
    }
    
}
