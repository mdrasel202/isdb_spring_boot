package com.rasel.student_management.dto;

import com.rasel.student_management.model.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private int id;
    private String name;
    private String email;
    private Integer classID;
    private Integer roll;
    private List<Integer> bookIds;
    private String phone;
    private String address;
    private String gender;
    private Instant dob;
}
