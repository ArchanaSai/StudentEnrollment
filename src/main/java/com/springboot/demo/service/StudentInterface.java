package com.springboot.demo.service;

import java.util.List;

import com.springboot.demo.model.Student;

public interface StudentInterface {
	public void saveStudent(Student student);
	public List<Student> getAllStudents();
	public List<Student> getStudentsByPage(int pageNo, int total);
	public Student getStudentById(int studentID);
	public void editStudent(Student student);
	public void deleteStudent(int studentID);
	public void deleteAllStudents();
	public int getCount();
	
}