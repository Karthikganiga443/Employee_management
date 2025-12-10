package com.employee.management.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.employee.management.model.Employee;
import com.employee.management.repository.EmployeeRepository;
@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository empRepo;
	
	@Override
	public List<Employee> getAllEmployees() {
		
		return empRepo.findAll();
		
	}

	@Override
	public void addEmployee(Employee emp) {
		// TODO Auto-generated method stub
		empRepo.save(emp);
	}

	@Override
	public Employee getEmployeeById(String id) {
		// TODO Auto-generated method stub
		return empRepo.findById(id).orElseThrow(()-> new RuntimeException("employee not found"));
	}

	@Override
	public void deleteEmployeeById(String id) {
		// TODO Auto-generated method stub
		empRepo.deleteById(id);
		
	}

	@Override
	public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		// TODO Auto-generated method stub
		Sort sort=sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable=PageRequest.of(pageNo-1, pageSize, sort);
		return empRepo.findAll(pageable);
	}

   
}
