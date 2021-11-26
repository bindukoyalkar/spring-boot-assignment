package com.bindu.restaurant.foodorder.controller;

import com.bindu.restaurant.foodorder.dto.DishDTO;
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

    private static final String SAVEDISH ="addOrUpdateDish";
    private static final String SAVECATEGORY="addOrUpdateCategory";
    private static final String CATEGORIES="categories";
    private static final String CATEGORY="category";
    private static final String DISHDTO="dishDTO";

    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }

    @GetMapping("/admin/categories")
    public String getCategories(Model theModel){
        List<Category> categoryList= categoryService.getAllCategories();
        theModel.addAttribute(CATEGORIES,categoryList);
        return CATEGORIES;
    }

    @GetMapping("/admin/categories/add")
    public String addCategory(Model theModel){
        theModel.addAttribute(CATEGORY,new CategoryDTO());
        return SAVECATEGORY;
    }

    @PostMapping("/admin/categories/save")
    public String saveCategory(@Valid @ModelAttribute("category") CategoryDTO categoryDTO,
                               BindingResult theBindingResult,Model theModel){
        if(theBindingResult.hasErrors()){
            theModel.addAttribute(CATEGORY,categoryDTO);
            return SAVECATEGORY;
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
        return SAVECATEGORY;
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
        theModel.addAttribute(DISHDTO,new DishDTO());
        theModel.addAttribute(CATEGORIES,categoryService.getAllCategories());
        return SAVEDISH;
    }

    @PostMapping("/admin/menus/save")
    public String saveMenu(@Valid @ModelAttribute("dishDTO") DishDTO dishDTO,
                           BindingResult theBindingResult,Model theModel){
        if(theBindingResult.hasErrors()){
            theModel.addAttribute(DISHDTO,dishDTO);
            theModel.addAttribute(CATEGORIES,categoryService.getAllCategories());
            return SAVEDISH;
        }
        else {
            Menu menu = new Menu();
            menu.setDishId(dishDTO.getDishId());
            menu.setDishName(dishDTO.getDishName());
            menu.setCategory(categoryService.getCategoryById(dishDTO.getCategoryId()));
            menu.setPrice(dishDTO.getPrice());
            menu.setQuantity(1);
            menu.setDescription(dishDTO.getDescription());
            menuService.save(menu);
            return "redirect:/admin/menus";
        }
    }

    @GetMapping("/admin/menus/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("dishId") int theId, Model theModel){
        Menu theDish=menuService.findById(theId);
        DishDTO dishDTO= new DishDTO();
        dishDTO.setDishId(theDish.getDishId());
        dishDTO.setDishName(theDish.getDishName());
        dishDTO.setCategoryId(theDish.getCategory().getId());
        dishDTO.setPrice(theDish.getPrice());
        dishDTO.setQuantity(1);
        dishDTO.setDescription(theDish.getDescription());
        theModel.addAttribute(DISHDTO,dishDTO);
        theModel.addAttribute(CATEGORIES,categoryService.getAllCategories());
        return SAVEDISH;
    }

    @GetMapping("/admin/menus/delete")
    public String delete(@RequestParam("dishId") int theId){
        menuService.deleteById(theId);
        return "redirect:/admin/menus";
    }
}
