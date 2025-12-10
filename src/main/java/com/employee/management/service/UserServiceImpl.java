package com.employee.management.service;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee.management.DTO.UserRegistrationDto;
import com.employee.management.model.Role;
import com.employee.management.model.User;
import com.employee.management.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public User save(UserRegistrationDto registrationDto) {
		Role role = new Role("ROLE_USER");
		
		User user = new User(
				registrationDto.getFirstName(),
				registrationDto.getLastName(),
				registrationDto.getEmail(),
				passwordEncoder.encode(registrationDto.getPassword()),
				Arrays.asList(role)
		);
		
		return userRepo.save(user);
	}

	@Override
	public boolean validateUser(String email, String password) {
		Optional<User> user = userRepo.findByEmail(email);
		
		if (user.isPresent()) {
			return passwordEncoder.matches(password, user.get().getPassword());
		}
		return false;
	}

	@Override
	public boolean emailExists(String email) {
		return userRepo.findByEmail(email).isPresent();
	}
}