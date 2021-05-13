<%@page import="com.customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/customer.js"></script>
</head>
<body>
	<div class="container"><div class="row"><div class="col-6">
	<h1>Customer Management </h1>
	<form id="formCustomer" name="formCustomer">
		 Name:
		 <input id="name" name="name" type="text"
		 class="form-control form-control-sm">
		 <br> 
		 Address:
		 <input id="address" name="address" type="text"
		 class="form-control form-control-sm">
		 <br> 
		 Phone Number:
		 <input id="phonenumber" name="phonenumber" type="text"
		 class="form-control form-control-sm">
		 <br>
		  Email:
		 <input id="email" name="email" type="text"
		 class="form-control form-control-sm">
		 <br>
		 Occupation:
		 <input id="occupation" name="occupation" type="text"
		 class="form-control form-control-sm">
		 <br>
		 Need Product:
		 <input id="needproduct" name="needproduct" type="text"
		 class="form-control form-control-sm">
 
 		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 
 		<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
	 customer CustomerObj = new customer();
	 out.print(CustomerObj.readcustomers());
 %>
</div>

</div> </div> </div>
</body>
</html>