package com.employee.management.service;

import com.employee.management.DTO.UserRegistrationDto;
import com.employee.management.model.User;

public interface UserService {

	User save(UserRegistrationDto registrationDto);
	
	boolean validateUser(String email, String password);
	
	boolean emailExists(String email);
}