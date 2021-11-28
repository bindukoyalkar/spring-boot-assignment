package com.bindu.restaurant.foodorder.service;

import com.bindu.restaurant.foodorder.dao.CategoryRepository;
import com.bindu.restaurant.foodorder.entity.Category;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CategoryServiceImplTest {
    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    void getAllCategories() {
        when(categoryRepository.findAll()).thenReturn(Stream.of(new Category(1,"Tiffin"),
                new Category(2,"Dessert")).collect(Collectors.toList()));
        assertEquals(2,categoryService.getAllCategories().size());
    }

    @Test
    void getCategoryById() {
        Category category= new Category(1,"Tiffin");
        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        assertEquals(category,categoryService.getCategoryById(1));
    }

    @Test
    void saveCategory() {
        Category category = new Category(1,"Coffee");
        categoryService.saveCategory(category);
        verify(categoryRepository,times(1)).save(category);
    }

    @Test
    void deleteCategoryById() {
        Category category = new Category(1,"Coffee");
        categoryService.deleteCategoryById(1);
        verify(categoryRepository,times(1)).deleteById(1);
    }
}