<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Ticket"%>
<%@ page import="model.Customer"%>
<%@ page import="util.Formatter"%>

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

 <div id="print">
	<p> First Name: 
		<%= c.getFirstName() %>
	</p>
	
	<p> Last Name: 
		<%= c.getLastName() %>
	</p>
	
	<p> Email:
		<%= c.getEmail() %> 
	</p>
	<p> Address 1:
		<%= c.getAddressLine1() %>
	</p>
	
	<p> Address 2:
		<%= c.getAddressLine2() %>
	</p>
	
	<p> Country: 
		<%= c.getCountry() %>
	</p>
	
	<p> Your current booked ticket(s) is/are: 
		<% 
				List<Ticket> tickets = null;
				
				if (email !=null) {
					tickets = Ticket.getTicketsByEmail(email);
					
					for (Ticket t : tickets) {
						out.println(t.getPerformanceTitle() + " - " + t.getType() + " (£" + t.getPrice() + ") <br>" +
								Formatter.dateFormat(t.getShowingDate())+ ": " + t.getShowingTime() + "<br>" + 
									"Seat: " + t.getSeatRow()+ t.getSeatNumber() + "<br>" +
									"<form method='post' action='RemoveFromBasket'>" + 
									"<input type='hidden' name='ticketId' value='" + t.getId() + "'> " + 
									"<button type='submit' class='btn btn-primary'>Remove</button></form> <br>");
					}
					
					
					out.println("<br><h3>Price: </h3> £" + Formatter.moneyFormat(Ticket.getPriceOfBasket(tickets)));			
					
				} else {
					request.getRequestDispatcher("index.jsp").include(request, response);
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Please login before viewing basket');");
					out.println("location='index.jsp';");
					out.println("</script>");
				}
		%>
	</p>

</div>	

<div class="footer">
  <p>Copyright &copy; The Theatre Royal</p>
</div>

	<script src=" https://code.jquery.com/jquery-1.12.4.min.js"
		type="text/javascript"></script>
	<script
		src=" https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>