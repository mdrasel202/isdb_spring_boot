package com.rasel.student_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rasel.student_management.model.Teacher;
import com.rasel.student_management.repository.TeacherRepository;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }

    //post
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    //getbyId
    public Teacher getTeacher(Integer id) {
        return teacherRepository.findById(id).orElse(null);
    }

    //delete
    public void deleteTeacher(Integer id) {
        teacherRepository.deleteById(id);
    }

    //getAll
    public List<Teacher> getAllTeacher() {
        return teacherRepository.findAll();
    }

    public Teacher updateTeacher(Integer id, Teacher teacher) {
        Teacher teacherById = teacherRepository.findById(id).orElse(null);

        if(teacherById != null){
            if(teacher.getName() != null){
                teacherById.setName(teacher.getName());
            }

            if(teacher.getEmail() != null){
                teacherById.setEmail(teacher.getEmail());
            }

            if(teacher.getGender() != null){
                teacherById.setGender(teacher.getGender());
            }

            if(teacher.getAddress() != null){
                teacherById.setAddress(teacher.getAddress());
            }

            if(teacher.getPhone() != null){
                teacherById.setAddress(teacher.getPhone());
            }

            if(teacher.getJoiningDate() != null){
                teacherById.setJoiningDate(teacher.getJoiningDate());
            }

            if(teacher.getSalary() != null){
                teacherById.setSalary(teacher.getSalary());
            }

            if(teacher.getMaritalStatus() != null){
                teacherById.setMaritalStatus(teacher.getMaritalStatus());
            }

            return teacherRepository.save(teacherById);
        }else{
            return null;
        }
        
    }



}
