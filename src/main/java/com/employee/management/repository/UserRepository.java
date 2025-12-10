package com.employee.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.management.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
