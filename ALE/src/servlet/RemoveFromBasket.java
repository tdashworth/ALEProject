package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Ticket;

/**
 * Servlet implementation class RemoveFromBasket
 */
@WebServlet("/RemoveFromBasket")
public class RemoveFromBasket extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveFromBasket() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("ticketId") != null) {
			int ticketId = Integer.parseInt(request.getParameter("ticketId"));
			
			try {
				Ticket.remove(ticketId);
				//System.out.println("Removed ticket " + ticketId);
				response.sendRedirect("basket.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
