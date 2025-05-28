package com.rasel.student.service;

import com.rasel.student.model.Student;
import com.rasel.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student) {
       return studentRepository.save(student);
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }
}
