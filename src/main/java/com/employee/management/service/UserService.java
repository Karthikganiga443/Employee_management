package com.employee.management.service;

import com.employee.management.DTO.UserRegistrationDto;
import com.employee.management.model.User;

public interface UserService {

	User save(UserRegistrationDto registrationDto);
}
