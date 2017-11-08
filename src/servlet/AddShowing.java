package servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Showing;

/**
 * Servlet implementation class AddShowing
 */
@WebServlet("/AddShowing")
public class AddShowing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddShowing() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Showing s = new Showing();
		s.setId(Integer.parseInt(request.getParameter("id")));
		DateFormat format = new SimpleDateFormat("dd-MMM-yy");
		try {
			s.setDate(format.parse(request.getParameter("date")));
			s.setTime(request.getParameter("time"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.writeToDB();
	}

}
