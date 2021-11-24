package com.bindu.restaurant.foodorder.service;

import com.bindu.restaurant.foodorder.entity.Menu;

import java.util.List;

public interface MenuService {
     List<Menu> findAll();

     Menu findById(int dishId);

     void save(Menu dish);

     void deleteById(int dishId);

     List<Menu> getAllDishesOfCategory(int id);
}
