package com.springboot.demo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.springboot.demo.model.Student;

@Service
public class StudentDao implements StudentDaoInterface {
	
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDatasource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource); 
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void saveStudent(Student student,HashMap<String,Object> requestMap) {
		System.out.println("entering student DAO save student");
		int MaxID = getMaxID("DMS_STUDENT", "DMS_STUDENT_ID");
		String queryString = "INSERT INTO DMS_STUDENT (DMS_STUDENT_ID,STUDENT_NUMBER,FIRST_NAME,LAST_NAME,EMAIL_ADDRESS,GENDER,"
				+"DATE_OF_BIRTH,SECTION,COUNTRY,ACTIVE,CREATED_BY,CREATED_DATE,UPDATED_BY,UPDATED_DATE) SELECT "
				+MaxID+",CONCAT(:studentNumber,"+MaxID+"),:firstName,:lastName,:emailAddress,:gender,:dateOfBirth,:section,:country,'Y',:userName,NOW(),:userName,NOW()";
		
		  namedParameterJdbcTemplate.execute(queryString,requestMap, new
		  PreparedStatementCallback<Object>() {
		  
		  @Override public Object doInPreparedStatement(PreparedStatement ps) throws
		  SQLException, DataAccessException { 
			  return ps.executeUpdate(); } });
		 
	}

	@Override
	public List<Student> getAllStudents() {
		String queryString = "SELECT DMS_STUDENT_ID AS studentID,CONCAT(FIRST_NAME,' ',LAST_NAME) AS fullName,"
				+ "GENDER AS gender,SECTION AS section,STUDENT_NUMBER AS studentNumber "
				+ "FROM DMS_STUDENT WHERE ACTIVE = 'Y'";
		return (List<Student>) namedParameterJdbcTemplate.query(queryString,new RowMapper<Student>() {
			@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student studentTemp = new Student();
				studentTemp.setId(rs.getInt("studentID"));  
				studentTemp.setFullName(rs.getString("fullName"));  
				studentTemp.setGender(rs.getString("gender"));
				studentTemp.setStudentSection(rs.getString("section"));  
				studentTemp.setStudentNumber(rs.getString("studentNumber"));
				return studentTemp;
			}
		});
		
	}

	@Override
	public List<Student> getStudentsByPage(int pageNo, int total) {
		 String sql="SELECT DMS_STUDENT_ID AS studentID,CONCAT(FIRST_NAME,' ',LAST_NAME) AS fullName,"
		 		+ "GENDER AS gender,SECTION AS section,STUDENT_NUMBER AS studentNumber "
		 		+ "FROM DMS_STUDENT WHERE ACTIVE = 'Y' LIMIT "+(pageNo-1)+","+total;  
		 return namedParameterJdbcTemplate.query(sql,new RowMapper<Student>(){  
		    
			 @Override
				public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
					Student studentTemp = new Student();
					studentTemp.setId(rs.getInt("studentID"));  
					studentTemp.setFullName(rs.getString("fullName"));  
					studentTemp.setGender(rs.getString("gender"));
					studentTemp.setStudentSection(rs.getString("section"));  
					studentTemp.setStudentNumber(rs.getString("studentNumber"));
					return studentTemp;
				}
		    });  
	}

	@Override
	public Student getStudentById(int studentID) {
		String queryString = "SELECT DMS_STUDENT_ID AS studentID,FIRST_NAME AS firstName,LAST_NAME AS lastName,EMAIL_ADDRESS AS emailAddress,"
				+ "GENDER AS gender,DATE_OF_BIRTH AS DOB,SECTION AS section,COUNTRY AS country,STUDENT_NUMBER AS studentNumber,DATE_FORMAT(DATE_OF_BIRTH,'%d/%m/%Y') AS studentDOBDisplay "
				+ "FROM DMS_STUDENT WHERE ACTIVE = 'Y' AND DMS_STUDENT_ID = "+studentID;
		return jdbcTemplate.query(queryString,new ResultSetExtractor<Student>() {
			@Override
			public Student extractData(ResultSet rs) throws SQLException {
				Student studentTemp = new Student();
				while(rs.next()){ 
					studentTemp.setId(rs.getInt("studentID"));  
					studentTemp.setFirstName(rs.getString("firstName"));  
					studentTemp.setLastName(rs.getString("lastName"));  
					studentTemp.setGender(rs.getString("gender"));
					studentTemp.setStudentDOB(rs.getDate("DOB"));
					studentTemp.setStudentEmail(rs.getString("emailAddress"));
					studentTemp.setStudentSection(rs.getString("section"));  
					studentTemp.setCountry(rs.getString("country")); 
					studentTemp.setStudentNumber(rs.getString("studentNumber"));
					studentTemp.setDateOfBirthDisplay(rs.getString("studentDOBDisplay"));
				}
				return studentTemp;
			}
		});
	}

	@Override
	public void editStudent(Student student,HashMap<String,Object> requestMap) {
		String queryString = "UPDATE DMS_STUDENT SET FIRST_NAME = :firstName,LAST_NAME = :lastName,EMAIL_ADDRESS = :emailAddress,"
				+ "GENDER = :gender, DATE_OF_BIRTH = :dateOfBirth,SECTION = :section,COUNTRY = :country,"
				+ "UPDATED_BY = :userName,UPDATED_DATE = NOW() WHERE DMS_STUDENT_ID = :studentID";
		namedParameterJdbcTemplate.update(queryString, requestMap);
	}

	@Override
	public void deleteStudent(int studentID) {
		String queryString = "UPDATE DMS_STUDENT SET ACTIVE = 'D', UPDATED_DATE = NOW(), UPDATED_BY = 'deleteSpecific' WHERE DMS_STUDENT_ID = "+studentID;
		jdbcTemplate.update(queryString);
	}

	@Override
	public void deleteAllStudents() {
		String queryString = "UPDATE DMS_STUDENT SET ACTIVE = 'D', UPDATED_DATE = NOW(), UPDATED_BY = 'deleteAll'";
		jdbcTemplate.update(queryString);
	}

	@Override
	public int getCount() {
		String queryString = "SELECT COUNT(*) FROM DMS_STUDENT WHERE ACTIVE = 'Y'";
		int studentCount = jdbcTemplate.queryForObject(queryString, Integer.class);
		return studentCount;
	}

	@Override
	public int getMaxID(String tableName, String columnName) {
		String queryString = "SELECT MAX("+columnName+")+1 AS MaxID FROM "+tableName;
		int MaxID = jdbcTemplate.queryForObject(queryString, Integer.class);
		return MaxID;
	}
	
}