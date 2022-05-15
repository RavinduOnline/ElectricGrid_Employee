<%@ page import="model.Employee" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    
       
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Electric Grid</title>
	<link rel="stylesheet" href="css/bootstrap.css">
	<script src="js/jquery-3.6.0.min.js" type="text/javascript"></script>
	<script src="js/validation.js" type="text/javascript"></script>
	<script src="js/employee.js" type="text/javascript"></script>
</head>
<body>


<div class="container">
	 <div class="row">
		 <div class="col">
		 	
		 	
		 	<h1>ELECTRIC GRID SYSTEM</h1>
		 	<br><br>
		 	
		 	<h2>Employee Management</h2>
		 	<br>
			<form id="formEmployee" name="formEmployee">
			
			
				 Employee Code: 
				<input id="employeeCode" name="employeeCode" type="text" class="form-control form-control-sm">
				<br>
			
				 Employee Name: 
				<input id="employeeName" name="employeeName" type="text" class="form-control form-control-sm">
				<br>
				

				Employee Address: 
				<input id="employeeAddress" name="employeeAddress" type="text" class="form-control form-control-sm">

				
				<br> 
				Employee NIC: 
				<input id="employeeNIC" name="employeeNIC" type="text" class="form-control form-control-sm">
				<br> 
				
				Employee Email: 
				<input id="employeeEmail" name="employeeEmail" type="email" class="form-control form-control-sm">
				<br> 
				
				Employee Phone No: 
				<input id="employeePNO" name="employeePNO" type="text" class="form-control form-control-sm">
				<br>
				
				<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
				
				<input type="hidden" id="hidEmployeeIDSave" name="hidEmployeeIDSave" value="">
			</form>
			
			<br>
			
			<%--Insert Status Message print --%>
			<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
		 	
			
			<br><br>
			
			<%-- Data fetch to this Table --%>
			<div id="divEmployeeGrid">
				<%
					 Employee employeeObj = new Employee(); 
					 out.print(employeeObj.readEmployee()); 
				%>
			</div>
			
		 	
		 </div>
	 </div>
</div>


</body>
</html>