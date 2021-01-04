package com.springboot.demo.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.demo.dao.StudentDao;
import com.springboot.demo.model.Student;

@Service
public class StudentService implements StudentInterface {
	@Autowired
	StudentDao studentDao;
	Logger mLog = LoggerFactory.getLogger(StudentService.class);
	
	@Override
	public void saveStudent(Student student) {
		mLog.info("saving student info");
		//student Number Generation
		String studentNumber = null;
		if(student.getStudentSection().equalsIgnoreCase("GRADUATE")) {
			studentNumber = "GS_";
		}else if(student.getStudentSection().equalsIgnoreCase("POST GRADUATE")) {
			studentNumber = "PGS_";
		}else if(student.getStudentSection().equalsIgnoreCase("RESEARCH")){
			studentNumber = "RS_";
		}
		HashMap<String,Object> requestMap = new HashMap<String,Object>();
		requestMap.put("studentID", student.getId());
		requestMap.put("firstName", student.getFirstName());
		requestMap.put("lastName", student.getLastName());
		requestMap.put("emailAddress", student.getStudentEmail());
		requestMap.put("gender", student.getGender());
		requestMap.put("dateOfBirth", ConvertDate(student.getStudentDOB()));
		requestMap.put("section", student.getStudentSection());
		requestMap.put("country", student.getCountry());
		requestMap.put("userName", "adminUser");
		requestMap.put("studentNumber", studentNumber);
		System.out.println("requestMap "+requestMap);
		
		studentDao.saveStudent(student,requestMap);
	}

	@Override
	public List<Student> getAllStudents() {
		mLog.info("getting students list");
		return studentDao.getAllStudents();
	}

	@Override
	public List<Student> getStudentsByPage(int pageNo, int total) {
		mLog.info("getting students for page : "+pageNo+"and total : "+total);
		return studentDao.getStudentsByPage(pageNo,total);
	}

	@Override
	public Student getStudentById(int studentID) {
		mLog.info("getting student info");
		return studentDao.getStudentById(studentID);
	}

	@Override
	public void editStudent(Student student) {
		mLog.info("edit student");
		HashMap<String,Object> requestMap = new HashMap<String,Object>();
		requestMap.put("studentID", student.getId());
		requestMap.put("firstName", student.getFirstName());
		requestMap.put("lastName", student.getLastName());
		requestMap.put("emailAddress", student.getStudentEmail());
		requestMap.put("gender", student.getGender());
		requestMap.put("dateOfBirth", ConvertDate(student.getStudentDOB()));
		requestMap.put("section", student.getStudentSection());
		requestMap.put("country", student.getCountry());
		requestMap.put("userName", "updateUser");
		System.out.println("requestMap "+requestMap);
		
		studentDao.editStudent(student,requestMap);
	}

	@Override
	public void deleteStudent(int studentID) {
		mLog.info("delete student");
		studentDao.deleteStudent(studentID);
	}

	@Override
	public void deleteAllStudents() {
		mLog.info("delete all students");
		studentDao.deleteAllStudents();
	}

	@Override
	public int getCount() {
		mLog.info("get active student count");
		return studentDao.getCount();
	}
	
	public String ConvertDate(Date date){
		String formatedDate ="";
	    try {
	    	DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
			date = (Date)formatter.parse(date.toString());
			 Calendar cal = Calendar.getInstance();
			   
			    cal.setTime(date);
			    
			    formatedDate=cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH) + 1)+"-"+cal.get(Calendar.DATE);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return formatedDate;
	  }
}