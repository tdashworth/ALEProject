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

import model.Customer;
import model.Ticket;
import util.Email;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = (String) request.getSession().getAttribute("username");
		Ticket.makePayment(email);
		
		Customer c = null;
		try {
			c = Customer.getCustomerByEmail(email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String body = "First Name: " + c.getFirstName() + "\n" + "Last Name: " + c.getLastName() + "Address 1: " 
						+ c.getAddressLine1() + "Address 2: " + c.getAddressLine2() + ".";
		
		Email.sendEmail(email, "Your booking With The Theatre Royal", body);

		PrintWriter out = response.getWriter();
		
		request.getRequestDispatcher("index.jsp").include(request, response);
		out.println("<script type=\"text/javascript\">");
		out.println("alert('Payment complete.');");
		out.println("location='index.jsp';");
		out.println("</script>");
		
	}

}
