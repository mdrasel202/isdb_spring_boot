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

import com.rasel.student_management.model.Teacher;
import com.rasel.student_management.service.TeacherService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/teacher")
@Tag(name = "Teacher Controller", description = "API for management")
public class TeacherController {

	private final TeacherService teacherService;

	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	// Post
	@PostMapping
	public ResponseEntity<?> saveTeacher(@RequestBody Teacher teacher) {
		Teacher save = teacherService.saveTeacher(teacher);
		return new ResponseEntity<>(save, HttpStatus.CREATED);
	}

	// get
	@GetMapping("/{id}")
	public Teacher getTeacher(@PathVariable Integer id) {
		return teacherService.getTeacher(id);
	}

	// delete
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTeacher(@PathVariable Integer id) {
		teacherService.deleteTeacher(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// getAllmapping
	@GetMapping
	public List<Teacher> getAllTeacher() {
		return teacherService.getAllTeacher();
	}

	// put
	@PutMapping("/{id}")
	public Teacher updateTeacher(@PathVariable Integer id, @RequestBody Teacher teacher) {
		return teacherService.updateTeacher(id, teacher);
	}
}
