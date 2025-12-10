package com.employee.management.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.employee.management.model.Employee;

public interface EmployeeService {

    List<Employee> getAllEmployees();
    
    void addEmployee(Employee emp);
    
    public Employee getEmployeeById(String id);
    
    public void deleteEmployeeById(String id);
    
    Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
