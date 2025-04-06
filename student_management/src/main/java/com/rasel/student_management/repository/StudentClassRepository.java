package com.rasel.student_management.repository;

import com.rasel.student_management.dao.ClassTeacher;
import com.rasel.student_management.dao.ClassTeacherProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rasel.student_management.model.StudentClass;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentClassRepository extends JpaRepository<StudentClass, Integer>{
    @Query("SELECT sc.name AS className, sc.roomNumber AS roomNumber, sc.classTeacher.email AS email, sc.classTeacher.name AS teacherName FROM StudentClass sc")
    List<ClassTeacherProjection> getAllClassTeacher();

//    @Query("SELECT new org.isdb")
}
