package com.bindu.restaurant.foodorder.controller;

import com.bindu.restaurant.foodorder.dto.ProductDTO;
import com.bindu.restaurant.foodorder.entity.Category;
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

    private static final String addOrUpdateDish ="addOrUpdateDish";
    private static final String addOrUpdateCategory="addOrUpdateCategory";

    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }

    @GetMapping("/admin/categories")
    public String getCategories(Model theModel){
        List<Category> categoryList= categoryService.getAllCategories();
        theModel.addAttribute("categories",categoryList);
        return "categories";
    }

    @GetMapping("/admin/categories/add")
    public String addCategory(Model theModel){
        Category category = new Category();
        theModel.addAttribute("category",category);
        return addOrUpdateCategory;
    }

    @PostMapping("/admin/categories/save")
    public String saveCategory(@Valid @ModelAttribute("category") Category theCategory,
                               BindingResult theBindingResult){
        if(theBindingResult.hasErrors()){
            return addOrUpdateCategory;
        }
        else {
            categoryService.saveCategory(theCategory);
            return "redirect:/admin/categories";
        }
    }

    @GetMapping("/admin/categories/update")
    public String updateCategory(@RequestParam("id") int id, Model theModel){
        Category theCategory= categoryService.getCategoryById(id);
        theModel.addAttribute("category",theCategory);
        return addOrUpdateCategory;
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
        theModel.addAttribute("productDTO",new ProductDTO());
        theModel.addAttribute("categories",categoryService.getAllCategories());
        return addOrUpdateDish;
    }

    @PostMapping("/admin/menus/save")
    public String saveMenu(@Valid @ModelAttribute("productDTO") ProductDTO productDTO,
                           BindingResult theBindingResult,Model theModel){
        if(theBindingResult.hasErrors()){
            theModel.addAttribute("productDTO",productDTO);
            theModel.addAttribute("categories",categoryService.getAllCategories());
            return addOrUpdateDish;
        }
        else {
            Menu menu = new Menu();
            menu.setDishId(productDTO.getDishId());
            menu.setDishName(productDTO.getDishName());
            menu.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()));
            menu.setPrice(productDTO.getPrice());
            menu.setQuantity(1);
            menu.setDescription(productDTO.getDescription());
            menuService.save(menu);
            return "redirect:/admin/menus";
        }
    }

    @GetMapping("/admin/menus/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("dishId") int theId, Model theModel){
        Menu theDish=menuService.findById(theId);
        ProductDTO productDTO= new ProductDTO();
        productDTO.setDishId(theDish.getDishId());
        productDTO.setDishName(theDish.getDishName());
        productDTO.setCategoryId(theDish.getCategory().getId());
        productDTO.setPrice(theDish.getPrice());
        productDTO.setQuantity(1);
        productDTO.setDescription(theDish.getDescription());
        theModel.addAttribute("productDTO",productDTO);
        theModel.addAttribute("categories",categoryService.getAllCategories());
        return addOrUpdateDish;
    }

    @GetMapping("/admin/menus/delete")
    public String delete(@RequestParam("dishId") int theId){
        menuService.deleteById(theId);
        return "redirect:/admin/menus";
    }
}
