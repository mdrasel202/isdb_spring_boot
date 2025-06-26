package com.rasel.controller;

import java.util.List;
import java.util.Map;

import com.rasel.service.FileStorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rasel.model.Employee;
import com.rasel.service.EmployeeService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
	private final EmployeeService service;
	private final FileStorageService storageService;

	public EmployeeController(EmployeeService service, FileStorageService storageService) {
		this.service = service;
		this.storageService = storageService;
	}

	@PostMapping
	public Employee saveEmp(@RequestBody Employee employee) {
		Employee savedEmp = service.saveEmployee(employee);
		return savedEmp;
	}

	@PostMapping("/{employeeId}/upload")
	public ResponseEntity<?> uploadFile(@PathVariable Integer employeeId,
										@RequestParam("file")MultipartFile file){
		try{
			String savedFileName = storageService.storeFile(file);

			String accessUrl = ServletUriComponentsBuilder
					.fromCurrentContextPath()
					.path("/imageurl/")
					.path(savedFileName)
					.toUriString();

			Employee empById = service.getEmpById(employeeId);
			empById.setImage(accessUrl);

			service.updateEmp(employeeId, empById);

			return ResponseEntity.ok(Map.of("message", "File uploaded", "url", accessUrl));
		}catch (Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("error", e.getMessage()));
		}
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
