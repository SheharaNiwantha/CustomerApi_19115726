package com;

import java.sql.*;

public class customer {
	
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
		 Class.forName("com.mysql.jdbc.Driver");
		 con =DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/customer", "root", "");
	 }
	 catch (Exception e)
	 {
		 e.printStackTrace();
	 }
	 return con;
	 } 
	
	public String readcustomers()
	{
		String output = "";
	try
	 {
		Connection con = connect();
	 if (con == null)
	 {
		 return "Error while connecting to the database for reading.";
	 }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Name</th>" + "<th>Address</th><th>Phoneno</th>"
	 + "<th>Email</th>"
	 + "<th>Occupation</th>"
	 +"<th>Needproduct</th>"
	 + "<th>Update</th><th>Remove</th></tr>";
	 String query = "select * from customers";
	 Statement stmt = con.createStatement();
	 
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
		 String id = Integer.toString(rs.getInt("id"));
		 String name = rs.getString("name");
		 String address = rs.getString("address");
		 String phoneno = rs.getString("phoneno");
		 String email = rs.getString("email");
		 String occupation = rs.getString("occupation");
		 String needproduct = rs.getString("needproduct");
		 

	// Add into the html table
		 output += "<tr><td>" + name + "</td>";
		 output += "<td>" + address + "</td>";
		 output += "<td>" + phoneno + "</td>";
		 output += "<td>" + email + "</td>";
		 output += "<td>" + occupation + "</td>";
		 output += "<td>" + needproduct + "</td>";
	// buttons
		output += "<td><input name='btnUpdate' type='button' value='Update' "
		+ "class='btnUpdate btn btn-secondary' data-id='" + id+ "'></td>"
		+ "<td><input name='btnRemove' type='button' value='Remove' "
		+ "class='btnRemove btn btn-danger' data-id='" + id + "'></td></tr>";
	 }
		 con.close();
		 // Complete the html table
		 output += "</table>";	
	 }
	catch (Exception e)
	 {
	 output = "Error while reading the customer.";
	 System.err.println(e.getMessage());
	 }
	return output;
	}
	//Insert Customer
	public String insertCustomer(String name, String address,String phoneno,String email, String occupation,String needproduct)
			 {
			 	String output = "";
			 try
			 {
				 Connection con = connect();
			 if (con == null)
			 {
			 return "Error while connectingto the database for inserting.";
			 }
			 String query = "INSERT INTO `customers`(`id`, `name`, `address`, `phoneno`, `email`, `occupation`, `needproduct`) VALUES (?,?,?,?,?,?,?)";
			 // create a prepared statement
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, name);
			 preparedStmt.setString(3, address);
			 preparedStmt.setString(4, phoneno);
			 preparedStmt.setString(5, email);
			 preparedStmt.setString(5, occupation);
			 preparedStmt.setString(5, needproduct);
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 String newItems = readcustomers();
			 output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
			 }
			 catch (Exception e)
			 {
			 output = "{\"status\":\"error\", \"data\":\"Error while inserting the customer.\"}";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 }
	
	public String updateCustomer(String id,String name, String address,String phoneno,String email, String occupation,String needproduct)
			 {
			 	String output = "";
			 try
			 {
				 Connection con = connect();
			 if (con == null)
			 {
			 return "Error while connecting to the database for updating.";
			 }
			 // create a prepared statement
			 String query = "UPDATE customers SET name=?,address=?,phoneno=?,email=?,occupation=?,needproduct=? WHERE id=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setString(1, name);
			 preparedStmt.setString(2, address);
			 preparedStmt.setString(3, phoneno);
			 preparedStmt.setString(5,email);
			 preparedStmt.setString(6,occupation);
			 preparedStmt.setString(7,needproduct);
			 preparedStmt.setInt(8, Integer.parseInt(id)); 

			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 String newItems = readcustomers();output = "{\"status\":\"success\", \"data\": \"" +
			 newItems + "\"}";
			 }
			 catch (Exception e)
			 {
			 output = "{\"status\":\"error\", \"data\":\"Error while updating the customer.\"}";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 }

	public String deleteCustomer(int id)
	 {
		String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
		 return "Error while connecting to the database for deleting.";
	 }
	 // create a prepared statement
	 String query = "delete from customers where id=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, id);
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 String newCustomer = readcustomers();
	 output = "{\"status\":\"success\", \"data\": \"" +
	 newCustomer + "\"}";
	 }
	 catch (Exception e)
	 {
	 output = "{\"status\":\"error\", \"data\":\"Error while deleting the customer.\"}";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
}

