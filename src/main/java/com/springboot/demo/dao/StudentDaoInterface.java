package com.springboot.demo.dao;

import java.util.HashMap;
import java.util.List;

import com.springboot.demo.model.Student;

public interface StudentDaoInterface {
	public void saveStudent(Student student, HashMap<String,Object> requestMap);
	public List<Student> getAllStudents();
	public List<Student> getStudentsByPage(int pageNo, int total);
	public Student getStudentById(int studentID);
	public void editStudent(Student student,HashMap<String,Object> requestMap);
	public void deleteStudent(int studentID);
	public void deleteAllStudents();
	public int getCount();
	public int getMaxID(String tableName,String columnName);
}