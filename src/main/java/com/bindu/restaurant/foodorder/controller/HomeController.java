/*
package com.bindu.restaurant.foodorder.controller;

import com.bindu.restaurant.foodorder.service.CategoryService;
import com.bindu.restaurant.foodorder.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class HomeController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MenuService menuService;

    //@GetMapping({"/","/home"})
    @GetMapping("/home")
    public String homePage(Model theModel){
        theModel.addAttribute("dishes",menuService.findAll());
theModel.addAttribute("categories",categoryService.findAll());

        return "home";
    }

}
*/
