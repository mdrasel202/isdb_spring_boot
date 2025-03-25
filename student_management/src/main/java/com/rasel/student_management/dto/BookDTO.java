package com.rasel.student_management.dto;

import com.rasel.student_management.model.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private String name;
    private String author;
    private String publisher;
    private Integer clazzId;
//    private Student student;
}
