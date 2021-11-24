package com.bindu.restaurant.foodorder.dao;

import com.bindu.restaurant.foodorder.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(String name);
}
