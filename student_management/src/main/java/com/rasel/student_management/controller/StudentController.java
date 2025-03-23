package com.rasel.student_management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rasel.student_management.model.Student;
import com.rasel.student_management.service.StudentService;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    
    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    //post
    @PostMapping
    public Student saveStudent(@RequestBody Student student){
        return studentService.saveStudent(student);
    }

    //get
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Integer id){
        return studentService.getStudent(id);
    }

    //delete
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Integer id){
        studentService.deleteStudent(id);
    }

    //get All Student
    @GetMapping
    public List<Student> getAllStudent(){
        return studentService.getAllStudent();
    }

    //put
    @PutMapping("/{id}")
    public Student updaStudent(@PathVariable Integer id, @RequestBody Student student){
        return studentService.updaStudent(id, student);
    }
}
