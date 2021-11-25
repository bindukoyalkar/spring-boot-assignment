package com.bindu.restaurant.foodorder.dao;

import com.bindu.restaurant.foodorder.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu,Integer> {
    List<Menu> findAllByCategoryId(int id);
}
