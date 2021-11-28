package com.bindu.restaurant.foodorder.controller;

import com.bindu.restaurant.foodorder.global.GlobalData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login() {
		GlobalData.cart.clear();
		return "login";
	}
}
