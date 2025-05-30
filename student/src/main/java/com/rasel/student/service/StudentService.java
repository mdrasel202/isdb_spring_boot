package com.rasel.student.service;

import com.rasel.student.model.Student;
import com.rasel.student.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public void deleteStudent(Long id) {
         studentRepository.deleteById(id);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student with ID " + id + " not found"));

        existing.setName(studentDetails.getName());
        existing.setClazz(studentDetails.getClazz());
        existing.setAddress(studentDetails.getAddress());

        return studentRepository.save(existing);
    }
}
