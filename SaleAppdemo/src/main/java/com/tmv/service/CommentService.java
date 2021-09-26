/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmv.service;

import com.tmv.pojos.Comment;

/**
 *
 * @author PC
 */
public interface CommentService {
    Comment addComment(String content, int productId);
}
