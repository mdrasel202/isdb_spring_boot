package com.rasel.student_management.dao;

import lombok.Getter;

@Getter
public class ClassTeacher {
    public String className;
    public String teacherName;
    public String teacherEmail;
    public Integer classRoomNumber;

    public ClassTeacher(String className, String teacherName, String teacherEmail, Integer classRoomNumber) {
        this.className = className;
        this.teacherName = teacherName;
        this.teacherEmail = teacherEmail;
        this.classRoomNumber = classRoomNumber;
    }
}
