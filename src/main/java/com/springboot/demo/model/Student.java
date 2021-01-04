package com.springboot.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class Student implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	@Size(min=3, max=30)
	private String firstName;
	@Size(min=3, max=30)
	private String lastName;
	@NotEmpty
	private String gender;
	@NotNull @Past @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date studentDOB;
	@NotEmpty @Email
	private String studentEmail;
	@NotEmpty
	private String studentSection;
	@NotEmpty
	private String country;
	
	private String studentNumber;
	private String fullName;
	private String dateOfBirthDisplay;
	/*
	 * @NotEmpty private List<String> enrolledSubjects = new ArrayList<String>();
	 * public List<String> getEnrolledSubjects() { return enrolledSubjects; } public
	 * void setEnrolledSubjects(List<String> enrolledSubjects) {
	 * this.enrolledSubjects = enrolledSubjects; }
	 */
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getStudentDOB() {
		return studentDOB;
	}
	public void setStudentDOB(Date studentDOB) {
		this.studentDOB = studentDOB;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getStudentSection() {
		return studentSection;
	}
	public void setStudentSection(String studentSection) {
		this.studentSection = studentSection;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getDateOfBirthDisplay() {
		return dateOfBirthDisplay;
	}
	public void setDateOfBirthDisplay(String dateOfBirthDisplay) {
		this.dateOfBirthDisplay = dateOfBirthDisplay;
	}
	
}