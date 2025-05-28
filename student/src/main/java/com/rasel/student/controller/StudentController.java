package com.rasel.student.controller;

import com.rasel.student.model.Student;
import com.rasel.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student save = service.createStudent(student);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll(){
        List<Student> get = service.getAll();
        return new ResponseEntity<>(get, HttpStatus.OK);
    }
}
