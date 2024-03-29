 package com.thymeleafspringbootapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.thymeleafspringbootapplication.model.Employee;
import com.thymeleafspringbootapplication.service.EmployeeService;

@Controller
public class EmployeeController {
	
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/")
	public String showlogin(Model model) {
		
		return "login";
	}
	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	  public String handleLogin(@RequestParam("username") String username, 
	                            @RequestParam("password") String password) {
		  if (isValidUser(username, password)) {
		      // User is authenticated, redirect to the home page
		      return "redirect:/showindex";
		    } 
		  else {
		      // Authentication failed, show an error message or redirect back to the login page
		      return "login";
		    }
	  }
	private boolean isValidUser(String username, String password) {
	    if(username=="admin"&&password=="admin") {
	    	return false;
	    }
	    else {
	    	return true;
	  }}
	
	@GetMapping("/showindex")
	public String viewHomePage(Model model) {
		// Create model attribute to bind form data
		model.addAttribute("listEmployees", employeeService.getAllEmployees());
		return "index";
	}
	
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		// Create model attribute to bind form data
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_employee";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		employeeService.saveEmployee(employee);
		return "redirect:/showindex";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value="id") long id, Model model) {
		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("employee", employee);
		return "update_employee";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable (value = "id") long id) {
		this.employeeService.deleteEmployeeById(id);
		return "redirect:/showindex";
	}

}
