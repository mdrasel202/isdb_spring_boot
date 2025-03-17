package com.rasel.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rasel.model.Employee;
import com.rasel.service.EmployeeService;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
	private final EmployeeService service;

	public EmployeeController(EmployeeService service) {
		this.service = service;
	}

	@PostMapping
	public Employee saveEmp(@RequestBody Employee employee) {
		Employee savedEmp = service.saveEmployee(employee);
		return savedEmp;
	}

//	@GetMapping("/{id}")
//	public Employee getEmpById(@PathVariable int id) {
//		Employee empById = service.getEmpById(id);
//		return empById;
//	}
//	
	@GetMapping("/{id}")
	public Employee getEmpById(@PathVariable("id") int id) {
		Employee empById = service.getEmpById(id);
		return empById;
	}

	// userName get
	@GetMapping("find/{name}")
	public List<Employee> getEmpUser(@PathVariable String name) {
//		Employee getUSer = service.getEmpUser(name);
		return service.getEmpUser(name);
	}

	@GetMapping
	public List<Employee> getAllEmp() {
		List<Employee> allEmp = service.getAllEmp();
		return allEmp;
	}

//	@DeleteMapping("/{id}")
//	public void deleteById(@PathVariable int id) {
//		service.deleteById(id);
//	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") int id) {
		service.deleteById(id);
	}

//	@PutMapping("/{id}")
//	public Employee updateEmp(@PathVariable int id, @RequestBody Employee employee) {
//		Employee update = service.updateEmp(id, employee);
//		return update;
//	}

	@PutMapping("/{id}")
	public Employee updateEmp(@PathVariable("id") int id, @RequestBody Employee employee) {
		Employee update = service.updateEmp(id, employee);
		return update;
	}

}
