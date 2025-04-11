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

import com.rasel.student_management.dao.ClassTeacherProjection;
import com.rasel.student_management.dto.StudentClassDTO;
import com.rasel.student_management.model.StudentClass;
import com.rasel.student_management.service.StudentClassService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/class")
@Tag(name = "Class Controller", description = "API for management")
public class StudentClassController {
	private final StudentClassService studentClassService;

	public StudentClassController(StudentClassService studentClassService) {
		this.studentClassService = studentClassService;
	}

	// post
	@PostMapping
	public ResponseEntity<?> saveStudentClass(@Valid @RequestBody StudentClassDTO classDTO) {
		StudentClass saves = studentClassService.saveStudentClass(classDTO);
		return new ResponseEntity<>(saves, HttpStatus.CREATED);
	}

	// get
	@GetMapping("/{id}")
	public StudentClass getStudentClass(@PathVariable Integer id) {
		StudentClass byId = studentClassService.getStudentClass(id);
		return byId;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteStudentClass(@PathVariable Integer id) {
		studentClassService.deleteStudentClass(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping
	public List<StudentClass> getAllStudentClass() {
		return studentClassService.getAllStudentClass();
	}

	@PutMapping("/{id}")
	public StudentClass updateStudentClass(@PathVariable Integer id, @RequestBody StudentClassDTO classDTO) {
		StudentClass updated = studentClassService.updateStudentClass(id, classDTO);
		return updated;
	}

	@GetMapping("/getAllClassTeacher")
	public List<ClassTeacherProjection> getAllClassTeacher() {
		return studentClassService.getAllClassTeacher();
	}
}
