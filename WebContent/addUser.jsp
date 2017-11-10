<!doctype html>
<html>
<head>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>The Theatre Royal | Register</title>
</head>
<body>


	<%@include file="WEB-INF/jsp/header.jsp"%>

	<div class="columnContainer">
		<div class="row">
			
			<form method="post" action="AddUser" id="user" style="background-color: #bbb;">
				<h2>Register New User</h2>

				<div class="form-group">
					<label for="usr">Email Address:</label> 
					<input type="text" class="form-control" name="email" id="fName">
				</div>
				<div class="form-group">
					<label for="usr">First Name:</label> 
					<input type="text" class="form-control" name="firstName" id="fName"">
				</div>
				<div class="form-group">
					<label for="pwd">Last Name:</label> 
					<input type="text" class="form-control" name="lastName" id="lName">
				</div>
				<div class="form-group">
					<label for="usr">Address Line 1:</label> 
					<input type="text" class="form-control" name="addressLine1" id="add1">
				</div>
				<div class="form-group">
					<label for="usr">Address Line 2:</label> 
					<input type="text" class="form-control" name="addressLine2" id="add2">
				</div>
				<div class="form-group">
					<label for="pwd">Postcode:</label> 
					<input type="text" class="form-control" name="postcode" id="pstcd">
				</div>
				<div class="form-group">
					<label for="pwd">Country:</label> 
					<input type="text" class="form-control" name="country" id="coun">
				</div>
				<div class="form-group">
					<label for="pwd">Password:</label> 
					<input type="text" class="form-control" name="password" id="pswd">
				</div>
				<button type="submit" class="btn btn-primary">Submit details</button>
			</form>
	</div>
	</div>

	<script src=" https://code.jquery.com/jquery-1.12.4.min.js"
		type="text/javascript"></script>
	<script
		src=" https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>