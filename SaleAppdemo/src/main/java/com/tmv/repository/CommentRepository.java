/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmv.repository;

import com.tmv.pojos.Comment;
import java.util.List;

/**
 *
 * @author PC
 */
public interface CommentRepository {
    Comment addComment(Comment c);
    Long countCommentByProductId(int productId);
    List<Comment> getCommentByProductId(int productId, int page);
}
