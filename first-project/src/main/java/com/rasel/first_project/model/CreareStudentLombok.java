package com.rasel.first_project.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreareStudentLombok {
	private String name;
	private String clazz;
	private int age;
	private String address;
	private LocalDate dob;
}
