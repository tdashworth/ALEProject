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

	<div class= "columnContainer">
	<div class="row">
		
	<div id="user" style="background-color:#bbb;">
		<h2>New Account Details</h2>
		
		<form>
			<div class="form-group">
				<label for="usr">Email Address:</label>
				<input type="text" class="form-control" id="email">
			</div>
			<div class="form-group">
				<label for="usr">First Name:</label>
				<input type="text" class="form-control" id="fName">
			</div>
			<div class="form-group">
				<label for="usr">Last Name:</label>
				<input type="text" class="form-control" id="lName">
			</div>
			<div class="form-group">
				<label for="usr">Address Line 1:</label>
				<input type="text" class="form-control" id="add1">
			</div>
			<div class="form-group">
				<label for="usr">Address Line 2:</label>
				<input type="text" class="form-control" id="add2">
			</div>
			<div class="form-group">
				<label for="usr">Postcode:</label>
				<input type="text" class="form-control" id="pstcd">
			</div>
			<div class="form-group">
				<label for="usr">Country:</label>
				<input type="text" class="form-control" id="coun">
			</div>
			<div class="form-group">
				<label for="usr">Password:</label>
				<input type="text" class="form-control" id="pass">
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>

</div>

</div>
	<script src=" https://code.jquery.com/jquery-1.12.4.min.js"
		type="text/javascript"></script>
	<script
		src=" https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>