package com.bindu.restaurant.foodorder.service;

import com.bindu.restaurant.foodorder.dto.UserRegistrationDto;
import com.bindu.restaurant.foodorder.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}
