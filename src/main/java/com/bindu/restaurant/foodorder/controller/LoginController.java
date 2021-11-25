package com.bindu.restaurant.foodorder.controller;

import com.bindu.restaurant.foodorder.global.GlobalData;
import com.bindu.restaurant.foodorder.service.CategoryService;
import com.bindu.restaurant.foodorder.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private MenuService menuService;

	@GetMapping("/login")
	public String login() {
		GlobalData.cart.clear();
		return "login";
	}
}
