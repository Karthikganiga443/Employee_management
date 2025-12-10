package com.employee.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.employee.management.DTO.UserRegistrationDto;
import com.employee.management.service.UserService;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	
	@Autowired
	private UserService userSer;

	public String registrationUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		userSer.save(registrationDto);
		return "redirect:/registration?success";
	}
}
