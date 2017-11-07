<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.Performance"%>
<%@ page import="java.util.List"%>
<%@ page import="util.Formatter"%>
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
			String searchTerm = request.getParameter("search");
			List<Performance> performances;

			if (searchTerm == null || searchTerm == "") {
				performances = Performance.getPerformances();
			} else if (Formatter.validateDate(searchTerm)) {
				performances = Performance.getPerformancesSearchDate(searchTerm);
				
				if (performances.size() == 0)  {
					out.println("<center>The search term '" + searchTerm +"' returned no results. </center>");
				} else {
					out.println("<center>There are " + performances.size() + " search result(s). </center>");
				}
			} else {
				performances = Performance.getPerformancesSearchText(searchTerm);
				
				if (performances.size() == 0)  {
					out.println("<center>The search term '" + searchTerm +"' returned no results. </center>");
				} else {
					out.println("<center>There are " + performances.size() + " search result(s). </center>");
				}
			}
			
			
			
			for (Performance p : performances) {
				%>
				<div class="rowtext">
					<div id="square" style="text-align: center; line-height:130px">Placeholder</div>
					<p class="padding">
						<%=p.getTitle()%>
						<br><br>
						<%=p.getDescription()%>
					</p>
					<form class="form-horizontal padding" action="showDetails" method="POST">
                 		<input type="hidden" name="id" value="<%= p.getId() %>" >
                 		<button type="submit" >View Showings</button>
                 	</form>	
				</div>
				<%
			}
		%>
		<br>
		<footer>
			<p>Copyright &copy; The Theatre Royal</p>
		</footer>
	</div>
	<!-- /container -->
</body>
</html>