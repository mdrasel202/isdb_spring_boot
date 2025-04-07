package com.rasel.student_management.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rasel.student_management.dto.StudentDTO;
import com.rasel.student_management.model.Student;
import com.rasel.student_management.service.StudentService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/student")
@Tag(name = "Student Controller", description = "API for management")
public class StudentController {

	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	// post
	@PostMapping
	public ResponseEntity<Student> saveStudent(@RequestBody StudentDTO studentDTO) {
		Student saved = studentService.saveStudent(studentDTO);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	// get
	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable Integer id) {
		Student student = studentService.getStudent(id);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	// delete
	@DeleteMapping("/{id}")
	public ResponseEntity<Student> deleteStudent(@PathVariable Integer id) {
		studentService.deleteStudent(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// get All Student
	@GetMapping
	public ResponseEntity<List<Student>> getAllStudent() {
		List<Student> students = studentService.getAllStudent();
		return new ResponseEntity<>(students, HttpStatus.OK);
	}

	// put
	@PutMapping("/{id}")
	public ResponseEntity<Student> updaStudent(@PathVariable Integer id, @RequestBody StudentDTO studentDTO) {
		Student updated = studentService.updaStudent(id, studentDTO);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}
}
