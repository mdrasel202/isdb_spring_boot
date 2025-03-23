package com.rasel.student_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rasel.student_management.model.Clazz;

public interface StudentClassRepository extends JpaRepository<Clazz, Integer>{

}
