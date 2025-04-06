package com.rasel.student_management.service;

import com.rasel.student_management.dao.ClassTeacher;
import com.rasel.student_management.dao.ClassTeacherProjection;
import com.rasel.student_management.dto.StudentClassDTO;
import com.rasel.student_management.model.StudentClass;
import com.rasel.student_management.model.Teacher;
import com.rasel.student_management.repository.StudentClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentClassService {

    private final StudentClassRepository studentClassRepository;
    private final TeacherService teacherService;

    private StudentClassService(StudentClassRepository studentClassRepository,
                                TeacherService teacherService){
        this.studentClassRepository = studentClassRepository;
        this.teacherService = teacherService;
    }

    //post
    public StudentClass saveStudentClass(StudentClassDTO classDTO)  {
        Integer teacherId = classDTO.getClassTeacherId();
        Teacher teacher = teacherService.getTeacher(teacherId);
        if(teacher == null){
            throw  new IllegalArgumentException("Teacher not found");
        }
        StudentClass studentClass = new StudentClass();
        studentClass.setName(classDTO.getName());
        studentClass.setRoomNumber(classDTO.getRoomNumber());
        studentClass.setClassTeacher(teacher);

        return studentClassRepository.save(studentClass);
    }

    //get id
    public StudentClass getStudentClass(Integer id) {
        return studentClassRepository.findById(id).orElse(null);
    }

    //delete
    public void deleteStudentClass(Integer id) {
        studentClassRepository.deleteById(id);
    }

    //get all
    public List<StudentClass> getAllStudentClass() {
        return studentClassRepository.findAll();
    }

    //put
    public StudentClass updateStudentClass(Integer id, StudentClassDTO classDTO) {
       Optional<StudentClass> classById = studentClassRepository.findById(id);

       if(classById.isPresent()){
           StudentClass aClass = new StudentClass();
           if(classDTO.getName() != null){
               aClass.setName(classDTO.getName());
           }

           if(classDTO.getRoomNumber() != null){
               aClass.setRoomNumber(classDTO.getRoomNumber());
           }

           if(classDTO.getClassTeacherId() != null){
               Integer teacherId = classDTO.getClassTeacherId();
               Teacher teacher = teacherService.getTeacher(teacherId);
               if(teacher == null){
                   throw new IllegalArgumentException("Teacher not found");
               }
               aClass.setClassTeacher(teacher);
           }
           return studentClassRepository.save(aClass);
       }else{
           throw new IllegalArgumentException("Class not found");
       }
    }

    public List<ClassTeacherProjection> getAllClassTeacher() {
        return studentClassRepository.getAllClassTeacher();
    }
}
