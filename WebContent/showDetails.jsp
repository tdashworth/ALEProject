<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.Performance"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Performance"%>

<!DOCTYPE html>
<html>
<head>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>The Theatre Royal</title>
</head>

<body>
	<div class="">

		<%@include file="WEB-INF/jsp/header.jsp"%>

		<%
			String id = request.getParameter("id");
			System.out.println("ID: " + id);
			Performance p = Performance.getPerformanceById(id);
		%>

		<div class="title">
			<p><%=p.getTitle()%></p>
		</div>
		<div class="desc">
			<p>
				Description:
				<%=p.getDescription()%></p>
			<p></p>
			<p>
				Show Type:
				<%=p.getType()%></p>
			<p></p>
			<p>
				Languages:
				<%=p.getLanguages()%>
			</p>
			<p></p>
			<p>
				Duration:
				<%=p.getDuration()%></p>
			<p>
				Timings Available:
				<%=p.getTimingsList()%></p>
			<p>
				Price: £<%=p.getPrice()%></p>
		</div>

		<div class="custselect">
			<div class="form-group">
				<form class="form-horizontal" action="AddToBasket" method="get">
					<label for="sel1">Select your preferred ticket type:</label> <select
						class="form-control" id="sel1" name="ticketType">
						<option>Adult</option>
						<option>OAPS</option>
						<option>Under 16s(Child)</option>
					</select>
					<p></p>
					<label for="showing">Select your showing time:</label> <select
						class="form-control" id="showing" name="showing">
						<%=p.getTimingsOptions()%>
					</select> <label for="seat">Select your seat:</label>
					<%=p.getSeatOptions()%>
					<input type="hidden" name="performanceId" value="<%=p.getId()%>">
					<br>
					<br>
					<%
						if (email != null) {
					%>
					<button type="submit" class="btn btn-primary">Add To
						Basket</button>
					<%
						} else {
					%>
					<button type="submit" disabled="true" class="btn btn-primary">Add
						To Basket</button>
					<p>Please login before adding tickets to your basket</p>
					<%
						}
					%>
				</form>
			</div>
		</div>

		<div class="bigimage">
			<img src="theatreSeatingPlan.png" alt="Seating Plan">
		</div>


		<footer>
			<p>Copyright &copy; The Theatre Royal</p>
		</footer>
	</div>
	<!-- /container -->

	<script>
		var showing = document.getElementById('showing');
		showing.onchange = function() {
			fieldcheck();
		};
		
		var currentSeat = showing.value;
		fieldcheck();

		function fieldcheck() {
			console.log(currentSeat);
			document.getElementById(currentSeat).style.display = 'none';
			currentSeat = showing.value;
			console.log(currentSeat);
			document.getElementById(showing.value).style.display = 'block';
		}
	</script>
</body>
</html>