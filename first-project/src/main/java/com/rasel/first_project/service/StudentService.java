package com.rasel.first_project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rasel.first_project.model.Student;
import com.rasel.first_project.repository.StudentRepository;

@Service
public class StudentService {

	private final StudentRepository repository;

	public StudentService(StudentRepository repository) {
		this.repository = repository;
	}

	public Student saveStudent(Student student) {
		Student saved = repository.save(student);
		return saved;
	}

	public List<Student> getStudents() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	public void deleteById(int id) {
		repository.deleteById(id);
	}

	public Optional<Student> findStudentById(int id) {
		return repository.findById(id);
	}
}
