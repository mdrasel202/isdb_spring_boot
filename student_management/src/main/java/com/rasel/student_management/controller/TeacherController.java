package com.rasel.student_management.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rasel.student_management.model.Teacher;
import com.rasel.student_management.service.TeacherService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/teacher")

public class TeacherController {

    
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    //Post
    @PostMapping
    public Teacher saveTeacher(@RequestBody Teacher teacher){
        return teacherService.saveTeacher(teacher);
    }

    //get
    @GetMapping("/{id}")
    public Teacher getTeacher(@PathVariable Integer id){
        return teacherService.getTeacher(id);
    }

    //delete
    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable Integer id){
        teacherService.deleteTeacher(id);
    }

    //getAllmapping
    @GetMapping
    public List<Teacher> getAllTeacher(){
        return teacherService.getAllTeacher();
    }

    //put
    @PutMapping("/{id}")
    public Teacher updateTeacher(@PathVariable Integer id, @RequestBody Teacher teacher){
        return teacherService.updateTeacher(id, teacher);
    }
}
