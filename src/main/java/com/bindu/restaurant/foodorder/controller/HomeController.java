package com.bindu.restaurant.foodorder.controller;

import com.bindu.restaurant.foodorder.service.CategoryService;
import com.bindu.restaurant.foodorder.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MenuService menuService;

    @GetMapping("/home")
    public String homePage(Model theModel){
        theModel.addAttribute("categories",categoryService.getAllCategories());
        theModel.addAttribute("dishes",menuService.findAll());
        return "home";
    }

    @GetMapping("/home/category/{id}")
    public String itemsByCategory(@PathVariable int id, Model theModel){
        categoryService.getCategoryById(id);
        theModel.addAttribute("categories",categoryService.getAllCategories());
        theModel.addAttribute("dishes",menuService.getAllDishesOfCategory(id));
        return "home";
    }

}
