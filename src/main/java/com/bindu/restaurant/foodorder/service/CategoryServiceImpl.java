package com.bindu.restaurant.foodorder.service;

import com.bindu.restaurant.foodorder.dao.CategoryRepository;
import com.bindu.restaurant.foodorder.entity.Category;
import com.bindu.restaurant.foodorder.exceptions.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(int id) {
        Optional<Category> result = categoryRepository.findById(id);
        Category category=null;
        if(result.isPresent()){
            category= result.get();
        }
        else{
            throw new CategoryNotFoundException("No such category is present");
        }
        return category;
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(int id) {
        categoryRepository.deleteById(id);
    }
}
