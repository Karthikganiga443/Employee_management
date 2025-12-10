package com.employee.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.employee.management.model.Employee;
import com.employee.management.service.EmployeeService;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService empServ;

    @GetMapping("/allEmpDetails")
    public String viewHomePage(Model model) {
        return viewPage(1,"id","asc", model);
    }

    @GetMapping("/page/{pageNo}")
    public String viewPage(@PathVariable int pageNo, 
    		@RequestParam("sortField") String sortField,
    		@RequestParam("sortDir") String sortDir,
    		Model model) {
        int pageSize = 9;
        Page<Employee> page = empServ.findPaginated(pageNo, pageSize, sortField, sortDir);

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listEmployees", page.getContent());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")? "desc":"asc");
        
        return "all_emp_details";
    }

    @GetMapping("/showNewEmpForm")
    public String showNewEmpForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "new_employees";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee emp
                               ) {

        empServ.addEmployee(emp);
        return "redirect:all_emp_details";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable String id, Model model) {
        Employee emp = empServ.getEmployeeById(id);
        model.addAttribute("employee", emp);
        return "update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmpById(@PathVariable String id) {
        empServ.deleteEmployeeById(id);
        return "redirect:all_emp_details";
    }

    @GetMapping("/employeeDetails/{id}")
    public String showEmployeeDetails(@PathVariable String id, Model model) {
        Employee employee = empServ.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employee_details";
    }
}
