package com.rasel.student_management.controller;

import com.rasel.student_management.dto.StudentClassDTO;
import com.rasel.student_management.model.StudentClass;
import com.rasel.student_management.service.StudentClassService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/class")
public class StudentClassController {
    private final StudentClassService studentClassService;

    public StudentClassController(StudentClassService studentClassService){
        this.studentClassService = studentClassService;
    }

    //post
    @PostMapping
    public ResponseEntity<?> saveStudentClass(@Valid @RequestBody StudentClassDTO classDTO){
        StudentClass saves = studentClassService.saveStudentClass(classDTO);
        return new ResponseEntity<>(saves, HttpStatus.CREATED);
    }

    //get
    @GetMapping("/{id}")
    public StudentClass getStudentClass(@PathVariable Integer id){
        StudentClass byId = studentClassService.getStudentClass(id);
        return byId;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentClass(@PathVariable Integer id){
        studentClassService.deleteStudentClass(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public List<StudentClass> getAllStudentClass(){
        return studentClassService.getAllStudentClass();
    }

    @PutMapping("/{id}")
    public StudentClass updateStudentClass(@PathVariable Integer id, @RequestBody StudentClassDTO classDTO){
        StudentClass updated = studentClassService.updateStudentClass(id, classDTO);
        return updated;
    }
}
