<%@ page import="model.Customer"%>
<div class='header'>
    <h1>The Theatre Royal</h1>
    <div class = 'nextto'>
  
<%
// List of Products
	String email = (String) request.getSession().getAttribute("username");
	if(email == null) {
		%><%@include file="loginForm.jsp"%><%
	} else {
		String name = Customer.getCustomerByEmail(email).getFirstName();
		String logoutText = "<h3>Welcome, "+name+"</h3> <div class='logout'> " +
			" <form method='post' action='LogoutServlet'> <button type='submit' class=btn btn-primary'> Logout </button> </form> " +
			" </div> " ;
		out.println(logoutText);		
	}
%>
	</div>
</div>

<div class='topnav'>
  <a href='index.jsp'>Home</a>
  <a href='basket.jsp'>Basket</a>
</div>

<div class="search">
	<form method="post" action="searchServlet">
  		<input type="text" name="search" placeholder="Search text or date (dd-MMM-yy)">
  		<button type="submit" class="btn btn-primary">Go</button>
	</form>
</div>