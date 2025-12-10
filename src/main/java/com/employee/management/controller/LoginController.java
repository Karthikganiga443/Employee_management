package com.employee.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.employee.management.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}

	@PostMapping("/login")
	public String loginUser(@RequestParam String email, 
	                         @RequestParam String password, 
	                         Model model,
	                         HttpSession session) {
		
		if (userService.validateUser(email, password)) {
			session.setAttribute("user_email", email);
			return "redirect:/allEmpDetails";
		} else {
			model.addAttribute("error", "Invalid email or password");
			return "login";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
}