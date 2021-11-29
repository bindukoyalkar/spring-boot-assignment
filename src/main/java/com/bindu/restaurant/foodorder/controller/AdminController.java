package com.bindu.restaurant.foodorder.controller;

import com.bindu.restaurant.foodorder.dto.MenuDTO;
import com.bindu.restaurant.foodorder.entity.Category;
import com.bindu.restaurant.foodorder.dto.CategoryDTO;
import com.bindu.restaurant.foodorder.entity.Menu;
import com.bindu.restaurant.foodorder.service.CategoryService;
import com.bindu.restaurant.foodorder.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MenuService menuService;

    private static final String SAVE_CATEGORY ="addOrUpdateCategory";
    private static final String CATEGORIES="categories";
    private static final String CATEGORY="category";
    private static final String MENU_DTO ="menuDTO";
    private static final String SAVE_MENU ="addOrUpdateMenu";

    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }

    @GetMapping("/admin/categories")
    public String getCategories(Model theModel){
        List<Category> allCategories= categoryService.getAllCategories();
        theModel.addAttribute(CATEGORIES,allCategories);
        return CATEGORIES;
    }

    @GetMapping("/admin/categories/add")
    public String addCategory(Model theModel){
        theModel.addAttribute(CATEGORY,new CategoryDTO());
        return SAVE_CATEGORY;
    }

    @PostMapping("/admin/categories/save")
    public String saveCategory(@Valid @ModelAttribute("category") CategoryDTO categoryDTO,
                               BindingResult theBindingResult,Model theModel){
        if(theBindingResult.hasErrors()){
            theModel.addAttribute(CATEGORY,categoryDTO);
            return SAVE_CATEGORY;
        }
        else {
            Category category = new Category();
            category.setId(categoryDTO.getId());
            category.setName(categoryDTO.getName());
            categoryService.saveCategory(category);
            return "redirect:/admin/categories";
        }
    }

    @GetMapping("/admin/categories/update")
    public String updateCategory(@RequestParam("id") int id, Model theModel){
        Category theCategory= categoryService.getCategoryById(id);
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(theCategory.getId());
        categoryDTO.setName(theCategory.getName());
        theModel.addAttribute(CATEGORY,categoryDTO);
        return SAVE_CATEGORY;
    }

    @GetMapping("/admin/categories/delete")
    public String deleteCategory(@RequestParam("id") int id){
        categoryService.deleteCategoryById(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/menus")
    public String menuList(Model theModel){
        List<Menu> theMenu= menuService.findAll();
        theModel.addAttribute("menu",theMenu);
        return "list-dishes";
    }

    @GetMapping("/admin/menus/add")
    public String addDish(Model theModel){
        theModel.addAttribute(MENU_DTO,new MenuDTO());
        theModel.addAttribute(CATEGORIES,categoryService.getAllCategories());
        return SAVE_MENU;
    }

    @PostMapping("/admin/menus/save")
    public String saveMenu(@Valid @ModelAttribute("menuDTO") MenuDTO menuDTO,
                           BindingResult theBindingResult,Model theModel){
        if(theBindingResult.hasErrors()){
            theModel.addAttribute(MENU_DTO,menuDTO);
            theModel.addAttribute(CATEGORIES,categoryService.getAllCategories());
            return SAVE_MENU;
        }
        else {
            Menu menu = new Menu();
            menu.setDishId(menuDTO.getDishId());
            menu.setDishName(menuDTO.getDishName());
            menu.setCategory(categoryService.getCategoryById(menuDTO.getCategoryId()));
            menu.setPrice(menuDTO.getPrice());
            menu.setQuantity(1);
            menu.setDescription(menuDTO.getDescription());
            menuService.save(menu);
            return "redirect:/admin/menus";
        }
    }

    @GetMapping("/admin/menus/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("dishId") int theId, Model theModel){
        Menu theMenu=menuService.findById(theId);
        MenuDTO menuDTO= new MenuDTO();
        menuDTO.setDishId(theMenu.getDishId());
        menuDTO.setDishName(theMenu.getDishName());
        menuDTO.setCategoryId(theMenu.getCategory().getId());
        menuDTO.setPrice(theMenu.getPrice());
        menuDTO.setQuantity(1);
        menuDTO.setDescription(theMenu.getDescription());
        theModel.addAttribute(MENU_DTO,menuDTO);
        theModel.addAttribute(CATEGORIES,categoryService.getAllCategories());
        return SAVE_MENU;
    }

    @GetMapping("/admin/menus/delete")
    public String delete(@RequestParam("dishId") int theId){
        menuService.deleteById(theId);
        return "redirect:/admin/menus";
    }
}
