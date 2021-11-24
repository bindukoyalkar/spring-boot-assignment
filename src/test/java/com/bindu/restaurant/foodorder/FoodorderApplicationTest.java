package com.bindu.restaurant.foodorder;

import com.bindu.restaurant.foodorder.dao.CategoryRepository;
import com.bindu.restaurant.foodorder.dao.MenuRepository;
import com.bindu.restaurant.foodorder.entity.Category;
import com.bindu.restaurant.foodorder.entity.Menu;
import com.bindu.restaurant.foodorder.service.CategoryService;
import com.bindu.restaurant.foodorder.service.MenuService;
import org.junit.Test;
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
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FoodorderApplicationTest {
    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private MenuService menuService;

    @MockBean
    private MenuRepository menuRepository;

    @Test
    public void getAllCategoriesTest(){
        when(categoryRepository.findAll()).thenReturn(Stream.of(new Category(1,"Tiffin"),
                new Category(2,"Dessert")).collect(Collectors.toList()));
        assertEquals(2,categoryService.getAllCategories().size());
    }

    @Test
    public void getCategoryByIdTest(){
        int id=1;
        Category category= new Category(1,"Tiffin");
        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
        assertEquals(category,categoryService.getCategoryById(id));
    }

    @Test
    public void saveCategoryTest(){
        Category category = new Category(1,"Coffee");
        categoryService.saveCategory(category);
        verify(categoryRepository,times(1)).save(category);
    }

    @Test
    public void deleteCategoryByIdTest(){
        Category category = new Category(1,"Coffee");
        int id=1;
        categoryService.deleteCategoryById(id);
        verify(categoryRepository,times(1)).deleteById(1);
    }

    //Test for Menu service
    @Test
    public void getAllMenuTest(){
        when(menuRepository.findAll()).thenReturn(Stream.of(new Menu(1,"Latte"
                        ,149.0,1,"Delicious Latte"),
                new Menu(2,"Choco Chip Cake",199.0,2,
                        "Choco Chip cake")).collect(Collectors.toList()));
        assertEquals(2,menuService.findAll().size());
    }

    @Test
    public void getMenuById(){
        int id=1;
        Menu menu= new Menu(1,"Latte",149.0,1,"Delicious Latte");
        when(menuRepository.findById(id)).thenReturn(Optional.of(menu));
        assertEquals(menu,menuService.findById(id));
    }

    @Test
    public void saveMenu(){
        Menu menu= new Menu(1,"Latte",149.0,1,"Delicious Latte");
        menuService.save(menu);
        verify(menuRepository,times(1)).save(menu);
    }

    @Test
    public void deleteMenu(){
        Menu menu= new Menu(1,"Latte",149.0,1,"Delicious Latte");
        int id=1;
        menuService.deleteById(1);
        verify(menuRepository,times(1)).deleteById(id);
    }


   /* public void groupDishesByCategory(){

        when(menuRepository.findAllByCategory_Id())
    }*/

}