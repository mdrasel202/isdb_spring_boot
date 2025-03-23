package com.rasel.student_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rasel.student_management.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer>{

}
