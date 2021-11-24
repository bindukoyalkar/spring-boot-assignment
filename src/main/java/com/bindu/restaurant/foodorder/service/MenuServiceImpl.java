package com.bindu.restaurant.foodorder.service;

import com.bindu.restaurant.foodorder.dao.CategoryRepository;
import com.bindu.restaurant.foodorder.dao.MenuRepository;
import com.bindu.restaurant.foodorder.entity.Category;
import com.bindu.restaurant.foodorder.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService{
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    @Override
    public Menu findById(int id) {
        Optional<Menu> result= menuRepository.findById(id);
        Menu dish =null;
        if(result.isPresent()){
            dish = result.get();
        }
        else{
            throw new RuntimeException("Did not find dish id - "+id);
        }
        return dish;
    }

    @Override
    public void save(Menu dish) {
        menuRepository.save(dish);
    }

    @Override
    public void deleteById(int id) {
        menuRepository.deleteById(id);
    }

    @Override
    public List<Menu> getAllDishesOfCategory(int id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        Category category= null;
        if(optionalCategory.isPresent())
            return menuRepository.findAllByCategory_Id(id);
        else
            throw new  RuntimeException("Did not find Category with id - "+id);
    }
}
