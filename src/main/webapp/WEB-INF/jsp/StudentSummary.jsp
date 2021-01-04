<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Enrollment System</title>
<link rel="stylesheet" href="/css/bootstrap.css" type="text/css">
<link rel="stylesheet" href="/css/style.css" type="text/css">
<script>
</script>
</head>
<body>
	<div class="container">
		<h1 align="center">Students List</h1>
		<table id="manageLinksTable" cellpadding="2" class="manageLinksTable">
			<tr>
				<th>
					<a href="/enroll"><h2>Register Student</h2></a>
				</th>
				<th>
					<a style="float:right;" href="/deleteAll" onclick="return confirm('Are you sure , you would like to delete all the student entries?');"><h2>Delete All</h2></a>
				</th>
			</tr>
			<%-- <tr>
				<th>
					<c:forEach var="count" items="${pageCount}" varStatus="theCount">
						<a align="right" href="/viewstudents/${theCount.count}">${theCount.count}</a>
					</c:forEach>
				</th>
			</tr> --%>
		</table>
		<form:form id="studentSummaryForm" class="form-horizontal">
			<table id="studentSummaryTable" border="2" width="75%" cellpadding="2">
					<tr>
						<th>Student Number</th>
						<th>Student Name</th>
						<th>Gender</th>
						<th>Section</th>
						<th>View</th>
						<th>Edit</th>
						<th>Delete</th>
					</tr>
				<c:forEach items="${studentList}" var="student">			
					<tr>
							<td>${student.studentNumber}</td> 
							<td>${student.fullName}</td>  
							<td>${student.gender}</td> 
							<td>${student.studentSection}</td>  
							<td><a href="/viewStudent/${student.id}">View</a></td>
							<td><a href="/editStudent/${student.id}">Edit</a></td>  
							<td><a href="/deleteStudent/${student.id}">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</form:form>

		<ul class="pagination" style="margin-left:43%;;">
			<li class="active"><a href="/viewstudents/1">1</a></li>
			<li><a href="/viewstudents/2">2</a></li>
			<li><a href="/viewstudents/3">3</a></li>
			<li><a href="/viewstudents/4">4</a></li>
			<li><a href="/viewstudents/5">5</a></li>
		</ul>
	</div>
</body>
</html>