package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import model.Customer;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String email = request.getParameter("username").toLowerCase();
		String password = request.getParameter("password");

		String actualPassword;
		try {
			actualPassword = Customer.getPasswordFromUsername(email);
			
			if (actualPassword == null) { // user name invalid
				request.getRequestDispatcher("index.jsp").include(request, response);
				out.println("<script type=\"text/javascript\">");
				out.println("alert('User or password incorrect');");
				out.println("location='index.jsp';");
				out.println("</script>");

			} else if (actualPassword.equals(password)) { // query db if user exists
				HttpSession session = request.getSession();
				session.setAttribute("username", email);
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
			} else { // password invalid
				request.getRequestDispatcher("index.jsp").include(request, response);
				out.println("<script type=\"text/javascript\">");
				out.println("alert('User or password incorrect');");
				out.println("location='index.jsp';");
				out.println("</script>");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.close();
	}

}
