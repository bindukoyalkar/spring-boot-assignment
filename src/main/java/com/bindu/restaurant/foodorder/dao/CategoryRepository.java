package com.bindu.restaurant.foodorder.dao;

import com.bindu.restaurant.foodorder.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
