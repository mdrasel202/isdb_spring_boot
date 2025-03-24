package com.rasel.student_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rasel.student_management.model.StudentClass;

public interface StudentClassRepository extends JpaRepository<StudentClass, Integer>{

}
