package com.bindu.restaurant.foodorder.service;

import com.bindu.restaurant.foodorder.dao.CategoryRepository;
import com.bindu.restaurant.foodorder.dao.MenuRepository;
import com.bindu.restaurant.foodorder.entity.Category;
import com.bindu.restaurant.foodorder.entity.Menu;
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
class MenuServiceImplTest {

    @Autowired
    private MenuService menuService;

    @MockBean
    private MenuRepository menuRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    void findAll() {
        when(menuRepository.findAll()).thenReturn(Stream.of(new Menu(1,"Latte"
                        ,149.0,1,"Delicious Latte"),
                new Menu(2,"Choco Chip Cake",199.0,2,
                        "Choco Chip cake")).collect(Collectors.toList()));
        assertEquals(2,menuService.findAll().size());
    }

    @Test
    void findById() {
        Menu menu= new Menu(1,"Latte",149.0,1,"Delicious Latte");
        when(menuRepository.findById(1)).thenReturn(Optional.of(menu));
        assertEquals(menu,menuService.findById(1));
    }

    @Test
    void save() {
        Menu menu= new Menu(1,"Latte",149.0,1,"Delicious Latte");
        menuService.save(menu);
        verify(menuRepository,times(1)).save(menu);
    }

    @Test
    void deleteById() {
        Menu menu= new Menu(1,"Latte",149.0,1,"Delicious Latte");
        menuService.deleteById(1);
        verify(menuRepository,times(1)).deleteById(1);
    }

    @Test
    void getAllDishesOfCategory() {
        Category category= new Category(1,"Tiffin");
        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        when(menuRepository.findAllByCategoryId(1))
                .thenReturn(Stream.of(new Menu(1,"Dosa",75.0,1,
                                "Delicious Dosa",category),
                new Menu(2,"Idly",50.0,1,
                        "Podi Idly",category))
                        .collect(Collectors.toList()));
        assertEquals(2,menuService.getAllDishesOfCategory(1).size());
    }
}