<html>
<head>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>The Theatre Royal | Add Showing</title>
</head>

<body>



	<%@include file="WEB-INF/jsp/header.jsp"%>

	<div class="columnContainer">
		<div class="row">
			<div class="column" style="background-color: #ccc;">
				<h2>
					Add Showing </h2>
				<form method="post" action="AddShowing">
					<input type="hidden" class="form-control" name="id" id="title" value="<%=request.getAttribute("performanceId")%>">
					<div class="form-group">
						<label for="usr">Show Date: </label> <input type="text"
							class="form-control" name="date" id="title">
					</div>
					<div class="form-group">
						<label for="usr">Show Time: </label> <input type="text"
							class="form-control" name="time" id="showType">
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