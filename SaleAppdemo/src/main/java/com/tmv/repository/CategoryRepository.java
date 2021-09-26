/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmv.repository;

import com.tmv.pojos.Category;
import java.util.List;

/**
 *
 * @author PC
 */
public interface CategoryRepository {
    List<Category> getCategories();
    Category getCategoryById(int catId);
}
