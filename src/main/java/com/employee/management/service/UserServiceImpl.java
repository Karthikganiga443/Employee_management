package com.employee.management.service;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.management.DTO.UserRegistrationDto;
import com.employee.management.model.Role;
import com.employee.management.model.User;
import com.employee.management.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User save(UserRegistrationDto registrationDto) {
		// TODO Auto-generated method stub
		Role role=new Role("ROLE_USER");
		
		User user=new User(registrationDto.getFirstName(),
				registrationDto.getLastName(),
				registrationDto.getEmail(),
				registrationDto.getPassword(), Arrays.asList(role));
		
		return userRepo.save(user);
	
	}

}
