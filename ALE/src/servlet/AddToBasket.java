package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Performance;
import model.Ticket;

/**
 * Servlet implementation class AddToBasket
 */
@WebServlet("/AddToBasket")
public class AddToBasket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToBasket() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("performanceId")!=null &&
		   request.getParameter("ticketType")!=null &&
		   request.getParameter("showing")!=null) {
			String perfomanceId = request.getParameter("id");
			String ticketType = request.getParameter("ticketType");
			String showingId = request.getParameter("showing");
			String seat = request.getParameter(showingId);
			String seatRow = seat.substring(0, 1);
			int seatNumber = Integer.parseInt(seat.substring(1));
			
			String email = (String) request.getSession().getAttribute("username");
			Double price = 15.0; //Performance.getPerformanceById(perfomanceId).getPrice();
			
			
			
			if(Ticket.addToBasket(email, Integer.parseInt(showingId), ticketType, price, seatRow, seatNumber)){
				response.sendRedirect("basket.jsp");
				return;
			}
		}
		response.sendRedirect("error.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
