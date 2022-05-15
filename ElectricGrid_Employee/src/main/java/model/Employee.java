package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Employee {

	//Database Connection Methods
	public Connection connect() 
	{ 
	 Connection con = null; 
	 
	 try 
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 con= DriverManager.getConnection("jdbc:mysql://localhost:3309/electri", 
	 "root", ""); 
	 //For testing
	 System.out.print("DB Successfully connected"); 
	 } 
	 catch(Exception e) 
	 { 
	 e.printStackTrace(); 
	 } 
	 
	 return con; 
	}
	
	
	
	
	
	
	

	//Read Employee Method
	public String readEmployee() {
		String output = "";
		
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{ 
				return "Error while connecting to the database for reading."; 
			}
			
			// Prepare the html table to be displayed
			 output = "<table border='1'class=\"table table-striped\"><tr><th>Employee Code</th>" 
					 + "<th>Name</th>"
					 + "<th>Address</th>" 
					 + "<th>NIC</th>" 
					 + "<th>Email</th>" 
					 + "<th>Contact No</th>" 
					 + "<th>Update</th><th>Remove</th></tr>";  

			 
			 
			 String query = "select * from employee"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
					 String eID = Integer.toString(rs.getInt("eID")); 
				 	 String employeeCode = rs.getString("employeeCode"); 
				 	 String employeeName = rs.getString("employeeName"); 
					 String employeeAddress = rs.getString("employeeAddress"); 
					 String employeeNIC = rs.getString("employeeNIC"); 
					 String employeeEmail = rs.getString("employeeEmail"); 
					 String employeePNO = rs.getString("employeePNO"); 
					 
					 // Add a row into the html table 
						output += "<tr><td>" + employeeCode + "</td>";
						output += "<td>" + employeeName + "</td>";
						output += "<td>" + employeeAddress + "</td>";
						output += "<td>" + employeeNIC + "</td>";
						output += "<td>" + employeeEmail + "</td>";
						output += "<td>" + employeePNO + "</td>";
					 
					 // buttons				 
					 output += "<td><input name='btnUpdate' " + " type='button' value='Update' " + " class='btnUpdate btn btn-outline-secondary' data-itemid='" + eID + "'></td>"
							 + "<td><input name='btnRemove' " + " type='button' value='Remove' " + " class='btnRemove btn btn-outline-danger' data-itemid='" + eID + "'>"
							 + "</td></tr>";
					 
			 } 
			 
			 con.close(); 
			 // Complete the html table
			 output += "</table>";

			
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the Employee."; 
			System.err.println(e.getMessage()); 
		}
		
		return output;
	}
	
	
	
	
	
	
	//Insert Data Method
	public String insertEmployee(String employeeCode, String employeeName, String employeeAddress, String employeeNIC, String employeeEmail, String employeePNO) {
		
		String output = ""; 
		try
		 { 
			Connection con = connect(); 
			
			if (con == null) 
			{ 
				return "Error while connecting to the database"; 
			} 
		 
			// create a prepared statement
			String query = " insert into employee (`eID`, `employeeCode`,`employeeName`,`employeeAddress`,`employeeNIC`,`employeeEmail`,`employeePNO`)"+ "values (?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, employeeCode);
			preparedStmt.setString(3, employeeName);
			preparedStmt.setString(4, employeeAddress);
			preparedStmt.setString(5, employeeNIC);
			preparedStmt.setString(6, employeeEmail);
			preparedStmt.setString(7, employeePNO);
			
			//execute the statement
			preparedStmt.execute(); 
			con.close(); // close the connection
			 
			output = "Employee Details Inserted successfully!"; 
			
			String newEmployee = readEmployee(); 
			output = "{\"status\":\"success\", \"data\": \"" + newEmployee + "\"}";
		} 
		
		
		catch (Exception e) 
		{ 
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the Employee details.\"}";
			System.err.println("Error Insert" + e.getMessage()); 
		} 
		
		return output;
	}
	
	
	
	
	

	//update method
	public String updateEmployee(String eID, String employeeCode,  String employeeName, String employeeAddress, String employeeNIC, String employeeEmail, String employeePNO) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE employee SET employeeCode=?, employeeName=?,employeeAddress=?,employeeNIC=?,employeeEmail=?,employeePNO=?" + "WHERE eID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, employeeCode);
			preparedStmt.setString(2, employeeName);
			preparedStmt.setString(3, employeeAddress);
			preparedStmt.setString(4, employeeNIC);
			preparedStmt.setString(5, employeeEmail);
			preparedStmt.setString(6, employeePNO);
			preparedStmt.setInt(7, Integer.parseInt(eID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Employee Details Updated successfully!";
			
			
			String newEmployee = readEmployee(); 
			output = "{\"status\":\"success\", \"data\": \"" + newEmployee + "\"}"; 
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the Employee Details.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	
	
	
	
	
	
	//Delete Methods
	public String deleteEmployee(String eID)
	{ 
	 String output = ""; 
	 
	 try
	 { 
		 Connection con = connect(); 
		 if (con == null) { 
			 return "Error while connecting to the database for deleting."; 
		 }
		 
		 
		 // create a prepared statement
		 String query = "delete from employee where eID=?";
		 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(eID));
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 
		 output = "Employee Detail Deleted successfully!";
		 
		 String newEmployee = readEmployee(); 
		 output = "{\"status\":\"success\", \"data\": \"" + newEmployee + "\"}";

	 } 
	 catch (Exception e) 
	 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the Employee detail.\"}";
		 System.err.println(e.getMessage()); 
	 }
	 
	 return output; 
	}

	
}
