package com.rasel.student_management.service;

import java.util.List;
import java.util.Optional;

import com.rasel.student_management.dto.StudentClassDTO;
import com.rasel.student_management.dto.StudentDTO;
import com.rasel.student_management.model.Book;
import com.rasel.student_management.model.StudentClass;
import com.rasel.student_management.repository.BookRepository;
import org.springframework.stereotype.Service;

import com.rasel.student_management.model.Student;
import com.rasel.student_management.repository.StudentRepository;

import javax.lang.model.util.Elements;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentClassService studentClassService;

    public StudentService(StudentRepository studentRepository, StudentClassService studentClassService){
        this.studentRepository = studentRepository;
        this.studentClassService = studentClassService;
    }

    //post
    public Student saveStudent(StudentDTO studentDTO) {
        Integer classId = studentDTO.getClassID();
        StudentClass clazz = studentClassService.getStudentClass(classId);

        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        if(clazz != null)
            student.setStudentClass(clazz);
        student.setRoll(studentDTO.getRoll());
        student.setPhone(studentDTO.getPhone());
        student.setAddress(studentDTO.getAddress());
        student.setGender(studentDTO.getGender());
        student.setDob(studentDTO.getDob());

        return studentRepository.save(student);
    }

    //get
    public Student getStudent(Integer id) {
       return studentRepository.findById(id).orElse(null);
    }

    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }

    //get All Student
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public Student updaStudent(Integer id, StudentDTO studentDTO) {
        Optional<Student> studentById = studentRepository.findById(id);
        if(studentById.isPresent()){
            Student astudent = new Student();
            if(studentDTO.getName() != null){
                astudent.setName(studentDTO.getName());
            }

            if(studentDTO.getEmail() != null){
                astudent.setEmail(studentDTO.getEmail());
            }

            if(studentDTO.getClassID() != null){
                Integer classId = studentDTO.getClassID();
                StudentClass clazz = studentClassService.getStudentClass(classId);
                if(clazz == null){
                    throw new IllegalArgumentException("Class not found");
                }
                astudent.setStudentClass(clazz);
            }

            if(studentDTO.getRoll() !=  null){
                astudent.setRoll(studentDTO.getRoll());
            }

//            if(studentDTO.getBookIds() != null){
//                astudent.s
//            }

            if(studentDTO.getPhone() != null){
                astudent.setPhone(studentDTO.getPhone());
            }

            if(studentDTO.getAddress() != null){
                astudent.setAddress(studentDTO.getAddress());
            }

            if(studentDTO.getGender() != null){
                astudent.setGender(studentDTO.getGender());
            }

            if(studentDTO.getDob() != null){
                astudent.setDob(studentDTO.getDob());
            }
            return studentRepository.save(astudent);
        }else{
            throw new IllegalArgumentException("Student not found");
        }
    }
}
