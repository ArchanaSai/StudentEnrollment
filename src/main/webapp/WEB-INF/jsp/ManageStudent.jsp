<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Enrollment Form</title>
<link rel="stylesheet" href="/css/bootstrap.css" type="text/css">
<link rel="stylesheet" href="/css/style.css" type="text/css">
</head>
<body>
	<div id="goToSummaryHeader" style="margin-top:2%;margin-left: 75%;">
	 		<a href="/studentSummary" style="font-style:italic;margin-left:18%;text-decoration: underline;">Goto Student List</a>
	 </div>
	 <div id="enrollmentFormPanel" class="form-container">
	 	<div id="titlePanel" style="margin-top: 3%;align-items: center;margin-bottom: 2%;height: 42px;">
			<label style="${pageType eq 'view' ? 'display: block;' : 'display:none'}" class="title-class">Student Details</label>
			<label style="${pageType eq 'add' ? 'display: block;' : 'display:none'}" class="title-class">Student Enrollment Form</label>
			<label style="${pageType eq 'edit' ? 'display: block;' : 'display:none'}" class="title-class">Edit Student Information</label>
	 	</div>
	 	<form:form id="enrollmentForm" method="post" modelAttribute="student" action="${pageType eq 'add' ? 'saveStudent' : (pageType eq 'edit' ? 'updateStudentInfo' : '') }" class="form-horizontal">
	 		<div class="row">
	 			<div class="form-group col-md-12">
	 				<label class="col-md-3 control-lable" for="studentNumber">Student Number</label>
	 				<div class="col-md-7" style="${pageType ne 'add' ? 'display:block' : 'display:none'}">
	 					<label style="font-weight:normal;">${student.studentNumber}</label>
	 				</div>
	 			</div>
	 		</div>
	 		<div class="row">
	 			<div class="form-group col-md-12">
	 				<label class="col-md-3 control-lable" for="firstName">First Name<span class="asterisk-mandatory" style="${pageType ne 'view' ? 'display:inline' : 'display:none'}">*</span></label>
	 				<div class="col-md-7" style="${pageType ne 'view' ? 'display:block' : 'display:none'}">
	 					<form:input path="firstName" id="firstName" class="form-control input-sm"/>
	 					<div class="has-error">
	 						<form:errors path="firstName" class="help-inline"></form:errors>
	 					</div>
	 				</div>
	 				<div class="col-md-7" style="${pageType eq 'view' ? 'display:block' : 'display:none'}">
	 					<label style="font-weight:normal;">${student.firstName}</label>
	 				</div>
	 			</div>
	 		</div>
	 		<div class="row">
	 			<div class="form-group col-md-12">
	 				<label class="col-md-3 control-lable" for="lastName">Last Name<span class="asterisk-mandatory" style="${pageType ne 'view' ? 'display:inline' : 'display:none'}">*</span></label>
	 				<div  class="col-md-7" style="${pageType ne 'view' ? 'display:block' : 'display:none'}">
	 					<form:input path="lastName" id="lastName" class="form-control input-sm" />
	 					<div class="has-error">
	 						<form:errors path="lastName" class="help-inline"></form:errors>
	 					</div>
	 				</div>
	 				<div  class="col-md-7" style="${pageType eq 'view' ? 'display:block' : 'display:none'}">
	 					<label style="font-weight:normal;">${student.lastName}</label>
	 				</div>
	 			</div>
	 		</div>
	 		<div class="row">
	 			<div class="form-group col-md-12">
	 				<label class="col-md-3 control-lable" for="gender">Gender<span class="asterisk-mandatory" style="${pageType ne 'view' ? 'display:inline' : 'display:none'}">*</span></label>
	 				<div class="col-md-7" class="form-control input-sm" style="${pageType ne 'view' ? 'display:block' : 'display:none'}">
	 					<form:radiobuttons path="gender" items="${genderList}" />
	 					<div class="has-error">
	 						<form:errors path="gender" class="help-inline"></form:errors>
	 					</div>
	 				</div>
	 				<div class="col-md-7" style="${pageType eq 'view' ? 'display:block' : 'display:none'}">
	 					<label style="font-weight:normal;">${student.gender}</label>
	 				</div>
	 			</div>
	 		</div>
	 		<div class="row">
	 			<div class="form-group col-md-12">
	 				<label class="col-md-3 control-lable" for="studentDOB">Date of Birth<span class="asterisk-mandatory" style="${pageType ne 'view' ? 'display:inline' : 'display:none'}">*</span></label>
	 				<div class="col-md-7" style="${pageType ne 'view' ? 'display:block' : 'display:none'}">
	 					<form:input type="date" path="studentDOB" id="studentDOB" class="form-control input-sm" />
	 					<div class="has-error">
	 						<form:errors path="studentDOB" class="help-inline"></form:errors>
	 					</div>
	 				</div>
	 				<div class="col-md-7" style="${pageType eq 'view' ? 'display:block' : 'display:none'}">
	 					<label style="font-weight:normal;">${student.dateOfBirthDisplay}</label>
	 				</div>
	 			</div>
	 		</div>
	 		<div class="row">
	 			<div class="form-group col-md-12">
	 				<label class="col-md-3 control-lable" for="studentEmail">Email<span class="asterisk-mandatory" style="${pageType ne 'view' ? 'display:inline' : 'display:none'}">*</span></label>
	 				<div class="col-md-7" style="${pageType ne 'view' ? 'display:block' : 'display:none'}">
	 					<form:input type="email" path="studentEmail" id="studentEmail" class="form-control input-sm"/>
	 					<div class="has-error">
	 						<form:errors path="studentEmail" class="help-inline"></form:errors>
	 					</div>
	 				</div>
	 				<div class="col-md-7" style="${pageType eq 'view' ? 'display:block' : 'display:none'}">
	 					<label style="font-weight:normal;">${student.studentEmail}</label>
	 				</div>
	 			</div>
	 		</div>
	 		<div class="row">
	 			<div class="form-group col-md-12">
	 				<label class="col-md-3 control-lable" for="studentSection">Section<span class="asterisk-mandatory" style="${pageType ne 'view' ? 'display:inline' : 'display:none'}">*</span></label>
	 				<div class="col-md-7" class="form-control input-sm" style="${pageType eq 'add' ? 'display:block' : 'display:none'}">
	 					<form:radiobuttons path="studentSection" items="${sectionList}" />
	 					<div class="has-error">
	 						<form:errors path="studentSection" class="help-inline"></form:errors>
	 					</div>
	 				</div>
	 				<div class="col-md-7" style="${pageType ne 'add' ? 'display:block' : 'display:none'}">
	 					<label style="font-weight:normal;">${student.studentSection}</label>
	 				</div>
	 			</div>
	 		</div>
	 		<div class="row">
	 			<div class="form-group col-md-12">
	 				<label class="col-md-3 control-lable" for="country">Country<span class="asterisk-mandatory" style="${pageType ne 'view' ? 'display:inline' : 'display:none'}">*</span></label>
	 				<div class="col-md-7" style="${pageType ne 'view' ? 'display:block' : 'display:none'}">
	 					<form:select path="country" id="country" class="form-control input-sm">
	 						<form:options items="${countryList}"></form:options>
	 					</form:select>
	 					<div class="has-error">
	 						<form:errors path="country" class="help-inline"></form:errors>
	 					</div>
	 				</div>
	 				<div class="col-md-7" style="${pageType eq 'view' ? 'display:block' : 'display:none'}">
	 					<label style="font-weight:normal;">${student.country}</label>
	 				</div>
	 			</div>
	 		</div>
	 		<%-- <div class="row">
	 			<div class="form-group col-md-12">
	 				<label class="col-md-3 control-lable" for="enrolledSubjects">Subjects<span class="asterisk-mandatory">*</span></label>
	 				<div class="col-md-7">
	 					<form:select path="enrolledSubjects" multiple="true" class="form-control input-sm">
	 						<form:options items="${subjectList}"></form:options>>
	 					</form:select>
	 					<div class="has-error">
	 						<form:errors path="enrolledSubjects" class="help-inline"></form:errors>
	 					</div>
	 				</div>
	 			</div>
	 		</div> --%>
	 		<div class="row">
	 			<div class="form-actions float-right">
	 				<input type="submit" style="${pageType eq 'view' ? 'display:none' : 'display:block;'}" value="${pageType eq 'add' ? 'Enroll' : (pageType eq 'edit' ? 'Update' : '') }" class="btn btn-primary btn-sm save-button">
	 				<form:hidden path="id"></form:hidden>
	 			</div>
	 		</div>
	 	</form:form>
	 </div>
</body>
</html>