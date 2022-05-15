
//Let’s hide the alerts on page load. 
$(document).ready(function() { 
	if ($("#alertSuccess").text().trim() == "") { 
	 	$("#alertSuccess").hide(); 
	} 
	
 	$("#alertError").hide(); 
});


// CLIENT-MODEL================================================================

//Form validation function
function validateEmployeeForm() 
{ 
	// CODE
	if ($("#employeeName").val().trim() == "") 
	{ 
	 	return "Insert Employee Name."; 
	} 
	// STATUS
	if ($("#employeeAddress").val().trim() == "") 
	{ 
	 	return "Insert Employee Address."; 
	} 
	// PRICE-------------------------------
	if ($("#employeeNIC").val().trim() == "") 
	{ 
	 	return "Insert Employee NIC."; 
	} 
	if ($("#employeeEmail").val().trim() == "") 
	{ 
	 	return "Insert Employee Email."; 
	} 
	if ($("#employeePNO").val().trim() == "") 
	{ 
	 	return "Insert Employee Phone Number."; 
	} 
	
	var tmpcrdNo = $("#employeeCode").val().trim(); 
	if (!$.isNumeric(tmpcrdNo)) 
	{ 
	 	return "Insert a numerical value for Employee Code."; 
	}
	
	return true; 
}


//
function onEmployeeSaveComplete(response, status) 
{ 
	 if (status == "success") 
	 { 
		 var resultSet = JSON.parse(response); 
		 
		 if (resultSet.status.trim() == "success") 
		 { 
			 $("#alertSuccess").text("Successfully saved."); 
			 $("#alertSuccess").show(); 
			 $("#divEmployeeGrid").html(resultSet.data); 
		 } else if (resultSet.status.trim() == "error") 
		 { 
			 $("#alertError").text(resultSet.data); 
			 $("#alertError").show(); 
		 } 
		 
	 } else if (status == "error") 
	 { 
		 $("#alertError").text("Error while saving."); 
		 $("#alertError").show(); 
	 } else
	 { 
		 $("#alertError").text("Unknown error while saving.."); 
		 $("#alertError").show(); 
	 }
	 
	 $("#hidEmployeeIDSave").val(""); 
	 $("#formEmployee")[0].reset(); 
}





function onEmployeeDeleteComplete(response, status) 
{ 
	 if (status == "success") 
	 { 
		 var resultSet = JSON.parse(response); 
		 
		 if (resultSet.status.trim() == "success") 
		 { 
			 $("#alertSuccess").text("Successfully deleted."); 
			 $("#alertSuccess").show(); 
			 $("#divEmployeeGrid").html(resultSet.data); 
		 } else if (resultSet.status.trim() == "error") 
		 { 
			 $("#alertError").text(resultSet.data); 
			 $("#alertError").show(); 
		 }
		  
	 } else if (status == "error") 
	 { 
		 $("#alertError").text("Error while deleting."); 
		 $("#alertError").show(); 
	 } else
	 { 
		 $("#alertError").text("Unknown error while deleting.."); 
		 $("#alertError").show(); 
	 } 
}





//Implement Save button click handler
$(document).on("click", "#btnSave", function(event) { 
 	// Clear alerts---------------------
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide();
	 
	 // Form validation-------------------
	 var status = validateEmployeeForm(); 
	
	 // If not valid-------------------
	 if (status != true) 
	 { 
		 $("#alertError").text(status); 
		 $("#alertError").show(); 
		 
		 return; 
	 }
	 
	 // If valid------------------------
 	 var type = ($("#hidEmployeeIDSave").val() == "") ? "POST" : "PUT"; 
	 $.ajax( 
	 { 
		 url : "EmployeeAPI", 
		 type : type, 
		 data : $("#formEmployee").serialize(), 
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 	onEmployeeSaveComplete(response.responseText, status); 
		 } 
	 }); 	 

});



// Implement the Update button handler
$(document).on("click", ".btnUpdate", function(event) {
	  $("#hidEmployeeIDSave").val($(this).data("itemid"));   
	  $("#employeeCode").val($(this).closest("tr").find('td:eq(0)').text()); 
	  $("#employeeName").val($(this).closest("tr").find('td:eq(1)').text()); 
	  $("#employeeAddress").val($(this).closest("tr").find('td:eq(2)').text()); 
	  $("#employeeNIC").val($(this).closest("tr").find('td:eq(3)').text());
	  $("#employeeEmail").val($(this).closest("tr").find('td:eq(4)').text());
	  $("#employeePNO").val($(this).closest("tr").find('td:eq(5)').text());
});



//For the Delete operation, we can get the item ID from the data attribute of the Remove button. 
$(document).on("click", ".btnRemove", function(event) 
{ 
	 $.ajax( 
	 { 
		 url : "EmployeeAPI", 
		 type : "DELETE", 
		 data : "eID=" + $(this).data("itemid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 	onEmployeeDeleteComplete(response.responseText, status); 
		 } 
	 }); 
});

