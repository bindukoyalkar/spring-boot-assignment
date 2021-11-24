package com.bindu.restaurant.foodorder.service;

import com.bindu.restaurant.foodorder.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    Category getCategoryById(int id);

    void saveCategory(Category category);

    void deleteCategoryById(int id);
}
