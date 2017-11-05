<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Ticket"%>
<%@ page import="model.Customer"%>

<!doctype html>
<html>
<head>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Project example - basket</title>
</head>
<body>


	<%@include file="WEB-INF/jsp/header.jsp"%>

	<div class="columnContainer">
		<div class="row">
			<div id="columnMain" style="background-color: #aaa;">
				<h2>Products Brought:</h2>
				
				<% 
				Customer c = null;
				List<Ticket> tickets = null;
				
				if (email !=null) {
					tickets = Ticket.getBasketTicketsByEmail(email);
					
					for (Ticket t : tickets) {
						out.println(t.getPerformanceTitle() + " - " + t.getType() + " (£" + t.getPrice() + ") <br>" +
									t.getShowingDate().substring(0, 10) + ": " + t.getShowingTime() + "<br>" + 
									"Seat: " + t.getSeatRow()+ t.getSeatNumber() + "<br><br>");
					}
					
					c = Customer.getCustomerByEmail(email);
					
					out.println("<br><h3>Price: </h3> £" + Ticket.getPriceOfBasket(tickets) );			
					
				} else {
					request.getRequestDispatcher("index.jsp").include(request, response);
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Please login before viewing basket');");
					out.println("location='index.jsp';");
					out.println("</script>");
				}
				%>
				<br>
				<div class="checkbox">
					<label><input type="checkbox" value="">Shipping
						Services (£<% if (email !=null) { out.println(Ticket.getDeliveryPriceOfBasket(tickets)); } %>)</label>
				</div>
				</p>
			</div>
			<form method="post" action="PaymentServlet">
			<div class="column" style="background-color: #bbb;">
				<h2>Purchaser Details</h2>
				
					<div class="form-group">
						<label for="usr">Email Address:</label> <input type="text"
							class="form-control" id="fName" disabled="true" value="<% if (email !=null) { out.println(c.getEmail()); }; %>">
					</div>
					<div class="form-group">
						<label for="usr">First Name:</label> <input type="text"
							class="form-control" id="fName" value="<% if (email !=null) { out.println( c.getFirstName() ); }; %>">
					</div>
					<div class="form-group">
						<label for="pwd">Last Name:</label> <input type="text"
							class="form-control" id="lName" value="<% if (email !=null) { out.println( c.getLastName() ); }; %>">
					</div>
					<div class="form-group">
						<label for="usr">Address Line 1:</label> <input type="text"
							class="form-control" id="add1" value="<% if (email !=null) { out.println( c.getAddressLine1()); }; %>">
					</div>
					<div class="form-group">
						<label for="usr">Address Line 2:</label> <input type="text"
							class="form-control" id="add2" value="<% if (email !=null) { out.println( c.getAddressLine2() ); }; %>">
					</div>
					<div class="form-group">
						<label for="pwd">Postcode:</label> <input type="text"
							class="form-control" id="pstcd" value="<% if (email !=null) { out.println( c.getPostcode() ); }; %>">
					</div>
					<div class="form-group">
						<label for="pwd">Country:</label> <input type="text"
							class="form-control" id="coun" value="<% if (email !=null) { out.println( c.getCountry()); }; %>">
					</div>
			</div>
			<div class="column" style="background-color: #ccc;">
				<h2>Payment Details:</h2>
				<div class="form-group">
					<label for="pwd">Account Type:</label> <input type="text"
						class="form-control" id="lName">
				</div>
				<div class="form-group">
					<label for="usr">Account Name:</label> <input type="text"
						class="form-control" id="add">
				</div>
				<div class="form-group">
					<label for="pwd">Account No:</label> <input type="text"
						class="form-control" id="pstcd">
				</div>
				
				<button type="submit" class="btn btn-primary">Submit details</button>
				
			</div>
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