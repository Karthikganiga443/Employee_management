package com.employee.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.employee.management.DTO.UserRegistrationDto;
import com.employee.management.service.UserService;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	
	@Autowired
	private UserService userService;

	@GetMapping
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new UserRegistrationDto());
		return "registration";
	}

	@PostMapping
	public String registrationUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		if (userService.emailExists(registrationDto.getEmail())) {
			return "redirect:/registration?error=email_exists";
		}
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}
}