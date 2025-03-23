package com.rasel.student_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rasel.student_management.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
