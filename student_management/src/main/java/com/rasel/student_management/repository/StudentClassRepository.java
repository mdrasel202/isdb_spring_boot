package com.rasel.student_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rasel.student_management.dao.ClassTeacherProjection;
import com.rasel.student_management.model.StudentClass;

public interface StudentClassRepository extends JpaRepository<StudentClass, Integer> {
	// JPQL projection
//	@Query("SELECT sc.name AS className, sc.roomNumber AS roomNumber, sc.classTeacher.email AS email, sc.classTeacher.name AS teacherName FROM StudentClass sc")
//	List<ClassTeacherProjection> getAllClassTeacher();

	// JPQL class
//	@Query("SELECT new org.isdb.StudentCRUD.dao.ClassTeacherDTO(sc.name, sc.classTeacher.name) FROM StudentClass sc")
//	List<ClassTeacherDTO> fetchAllClassTeacherDTOs();

	// JPQL record
//	@Query("SELECT new org.isdb.StudentCRUD.dao.ClassTeacherRecord(sc.name, sc.classTeacher.name) FROM StudentClass sc")
//	List<ClassTeacherRecord> fetchAllClassTeacherRecords();

	// NATIVE / ROW Query
	@Query(value = """
			SELECT c.name AS className, c.room_Number AS roomNumber,
			t.email AS email, t.name AS teacherName
			FROM T_CLASS c
			         INNER JOIN T_TEACHER t ON t.id = c.class_teacher
			         """, nativeQuery = true)
	List<ClassTeacherProjection> getAllClassTeacher();

}
