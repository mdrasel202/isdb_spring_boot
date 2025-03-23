package com.rasel.student_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rasel.student_management.model.Student;
import com.rasel.student_management.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    //post
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    //get
    public Student getStudent(Integer id) {
       return studentRepository.findById(id).orElse(null);
    }

    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }

    //get All Student
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public Student updaStudent(Integer id, Student student) {
        Student studentBy = studentRepository.findById(id).orElse(null);
        return null;
    }
}
