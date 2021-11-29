package com.bindu.restaurant.foodorder.controller;

import com.bindu.restaurant.foodorder.dto.UserRegistrationDto;
import com.bindu.restaurant.foodorder.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	private UserService userService;

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		String password=registrationDto.getPassword();
		String confirmPassword=registrationDto.getConfirmPassword();
		if(!password.equals(confirmPassword)){
			return "redirect:/registration?failure";
		}
		else {
			userService.save(registrationDto);
			return "redirect:/registration?success";
		}
	}
}
