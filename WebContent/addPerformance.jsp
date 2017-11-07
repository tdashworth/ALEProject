<html>
<head>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>The Theatre Royal</title>
</head>

<body>
	<%@include file="WEB-INF/jsp/header.jsp"%>


	<div class="columnContainer">
		<div class="row">
			<div id="columnMain" style="background-color: #bbb;">
				<h2>Add perfomance</h2>
				<form>
					<div class="form-group">
						<label for="usr">Title: </label> <input type="text"
							class="form-control" id="title">
					</div>
					<div class="form-group">
						<label for="usr">Show Type: </label> <input type="text"
							class="form-control" id="showType">
					</div>
					<div class="form-group">
						<label for="pwd">Language: </label> <input type="text"
							class="form-control" id="language">
					</div>
					<div class="form-group">
						<label for="usr">Duration: </label> <input type="text"
							class="form-control" id="duration">
					</div>
					<div class="form-group">
						<label for="usr">Description: </label> <input type="text"
							class="form-control" id="description">
					</div>
					<div class="form-group">
						<label for="pwd">Price: </label> <input type="text"
							class="form-control" id="price">
					</div>
					<button type="submit" class="btn btn-primary">Submit</button>
				</form>
			</div>

			<div class="column" style="background-color: #ccc;">
				<h2>Add Showing</h2>
				<form>
					<div class="form-group">
						<label for="usr">Show Date: </label> <input type="text"
							class="form-control" id="title">
					</div>
					<div class="form-group">
						<label for="usr">Show Time: </label> <input type="text"
							class="form-control" id="showType">
					</div>
					<div class="form-group">
						<label for="pwd">Availablility: </label> <input type="text"
							class="form-control" id="language">
					</div>
					<button type="submit" class="btn btn-primary">Submit</button>
				</form>
			</div>

			<div class="column" style="background-color: #ccc;">
				<h2>Add Showing</h2>
				<form>
					<div class="form-group">
						<label for="usr">Show Date: </label> <input type="text"
							class="form-control" id="title">
					</div>
					<div class="form-group">
						<label for="usr">Show Time: </label> <input type="text"
							class="form-control" id="showType">
					</div>
					<div class="form-group">
						<label for="pwd">Availablility: </label> <input type="text"
							class="form-control" id="language">
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