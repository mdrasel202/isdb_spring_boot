package com.rasel.model;

import java.time.LocalDate;

public class Employee {

	private int id;
	private String name;
	private String email;
	private String designation;
	private int age;
	private String address;
//	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;
	private double salary;
	private String image;

	public Employee() {
	}

	public Employee(int id, String name, String email, String designation, int age, String address, LocalDate dob,
			double salary, String image) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.designation = designation;
		this.age = age;
		this.address = address;
		this.dob = dob;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
