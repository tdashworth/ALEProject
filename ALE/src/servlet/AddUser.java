package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");
		String addressLine1 = request.getParameter("addressLine1");
		String addressLine2 = request.getParameter("addressLine2");
		String postcode = request.getParameter("postcode");
		String country = request.getParameter("country");
		
		PrintWriter out = response.getWriter();

		if (email != null && password != null && firstName != null) {
			Customer c = Customer.newCustomer(email, firstName, lastName, addressLine1, addressLine2, postcode, country,
					password);
			if (c.writeToDb() > 0) {
				request.getRequestDispatcher("index.jsp").include(request, response);
			} else {
				request.getRequestDispatcher("index.jsp").include(request, response);
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Error. Please try again.');");
				out.println("location='index.jsp';");
				out.println("</script>");
			}
		} else {
			request.getRequestDispatcher("addUser.jsp").include(request, response);
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Please enter atleast email, password and first name.');");
			out.println("location='index.jsp';");
			out.println("</script>");
		}
	}
}
